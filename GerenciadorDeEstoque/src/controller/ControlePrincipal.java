/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Pessoa;
import view.TelaPrincipal;

/**
 *
 * @author j_ped
 */
public class ControlePrincipal implements ActionListener{
    
    private Pessoa pessoaPrincipal;
    private TelaPrincipal telaPrincipal;

    public Pessoa getPessoaPrincipal() {
        return pessoaPrincipal;
    }

    public void setPessoaPrincipal(Pessoa pessoa) {
        this.pessoaPrincipal = pessoaPrincipal;
    }


    
    

    public ControlePrincipal(Pessoa pessoa) {
     
        this.telaPrincipal = new TelaPrincipal();
        this.pessoaPrincipal = pessoa;
        
        
        telaPrincipal.getjButtonPerfil().addActionListener(this);
        telaPrincipal.getjButtonListar().addActionListener(this);
        telaPrincipal.getjButtonAdicionar().addActionListener(this);
        telaPrincipal.getjButtonLogoff().addActionListener(this);
    
        telaPrincipal.setVisible(true);
                
        
    }
    
    
    
     


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(telaPrincipal.getjButtonPerfil())){
            ControlePerfil controlePerfil = new ControlePerfil(telaPrincipal, pessoaPrincipal);
            
        }
        else if(e.getSource().equals(telaPrincipal.getjButtonAdicionar())){
            ControleProduto controleProduto = new ControleProduto();
            controleProduto.IniciarTelaCadastrarProduto();
            
        
        }
        else if(e.getSource().equals(telaPrincipal.getjButtonListar())){
            ControleProduto controleProduto = new ControleProduto();
            controleProduto.IniciarTelaListagem();
        }
        else if (e.getSource().equals(telaPrincipal.getjButtonLogoff())){
            telaPrincipal.dispose();
            ControleLogin controleLogin = new ControleLogin();
        }
            
        
    }
    
    
}
