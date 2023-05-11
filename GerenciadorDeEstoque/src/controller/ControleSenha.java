/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Pessoa;
import service.ServiceSenha;
import view.TelaPerfil;
import view.TelaTrocaSenha;

/**
 *
 * @author j_ped
 */
public class ControleSenha implements ActionListener{
    private TelaTrocaSenha telaTrocaSenha;
    private TelaPerfil telaPerfil;
    private Pessoa pessoa;
    private ServiceSenha serviceSenha;
    

    public ControleSenha(Pessoa pessoa) {
        
        this.pessoa = pessoa;
        
       
        this.telaTrocaSenha = new TelaTrocaSenha(null, true);
       
        
        telaTrocaSenha.getjButtonSair().addActionListener(this);
        telaTrocaSenha.getjButtonSalvarSenha().addActionListener(this);
        telaTrocaSenha.setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource().equals(telaTrocaSenha.getjButtonSair())){
           telaTrocaSenha.dispose();
       }
        
        if(e.getSource().equals(telaTrocaSenha.getjButtonSalvarSenha())){
            if(String.valueOf(telaTrocaSenha.getjPasswordFieldSenhaNova().getPassword())
       .equals(String.valueOf(telaTrocaSenha.getjPasswordFieldConfirmacao().getPassword()))) {
                pessoa.setSenha(String.valueOf(telaTrocaSenha.getjPasswordFieldSenhaNova().getPassword()));
                ServiceSenha serviceSenha = new ServiceSenha(pessoa);
                serviceSenha.alterarSenha();
                telaTrocaSenha.dispose();
        
            }
            else{
                JOptionPane.showMessageDialog(null, "Campo senha e confirmar senha!");
            }
        
         
        
        
        
        }


            
            
    }
}
    
    
    
