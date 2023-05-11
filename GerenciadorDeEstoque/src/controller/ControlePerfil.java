
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pessoa;
import service.ServicePerfil;

import view.TelaLogin;
import view.TelaPerfil;
import view.TelaPrincipal;
import view.TelaTrocaSenha;

/**
 *
 * @author j_ped
 */
public class ControlePerfil implements ActionListener{
    
    private ServicePerfil servicePerfil;
    private Pessoa pessoaPerfil;
    private TelaPrincipal telaPrincipal;
    private TelaPerfil telaPerfil;
    private TelaTrocaSenha telaTrocaSenha;

 
    

    

    public ControlePerfil(TelaPrincipal telaPrincipal, Pessoa pessoa) {
        this.telaPrincipal = telaPrincipal;
        this.pessoaPerfil = pessoa;
        
        
        this.telaPerfil = new TelaPerfil(telaPrincipal, true);
        
        
        this.servicePerfil= new ServicePerfil(telaPerfil, pessoaPerfil);
        telaPerfil.getjButtonMudar().addActionListener(this);
        telaPerfil.getjButtonSalvar().addActionListener(this);
        telaPerfil.getjButtonFoto().addActionListener(this);
        telaPerfil.setVisible(true);
        
        
        
    }
    
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(telaPerfil.getjButtonSalvar())){
            try {
                pessoaPerfil = servicePerfil.salvar(telaPerfil);
            } catch (Exception ex) {
                Logger.getLogger(ControlePerfil.class.getName()).log(Level.SEVERE, null, ex);
            } 
            telaPerfil.dispose();
            telaPrincipal.dispose();
            ControlePrincipal controlePrincipal = new ControlePrincipal(pessoaPerfil);
            
            
 
        }
        if (e.getSource().equals(telaPerfil.getjButtonFoto())){
            servicePerfil.imagem();
           
        }
        if(e.getSource().equals(telaPerfil.getjButtonMudar())){
            ControleSenha controleSenha = new ControleSenha(pessoaPerfil);
            
        }
        
        
    }
    
}
