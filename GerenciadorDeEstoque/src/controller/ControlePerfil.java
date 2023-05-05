
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Pessoa;
import service.ServicePerfil;

import view.TelaLogin;
import view.TelaPerfil;
import view.TelaPrincipal;

/**
 *
 * @author j_ped
 */
public class ControlePerfil implements ActionListener{
    
    private ServicePerfil servicePerfil;
    private Pessoa pessoaPerfil;
    private TelaPrincipal telaPrincipal;

 
    

    

    public ControlePerfil(TelaPrincipal telaPrincipal, Pessoa pessoa) {
        this.telaPrincipal = telaPrincipal;
        this.pessoaPerfil = pessoa;
        
        
        
        
        TelaPerfil telaPerfil = new TelaPerfil(telaPrincipal, true);
        
        
        this.servicePerfil= new ServicePerfil(telaPerfil, pessoaPerfil);
        telaPerfil.setVisible(true);
        
        
        
    }
    
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
    
}
