/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.JOptionPane;
import service.ServicePessoa;
import view.TelaCadastro;
import view.TelaLogin;
import view.TelaPrincipal;

/**
 *
 * @author j_ped
 */
public class ControlePessoa implements ActionListener, KeyListener {
    
    private final TelaCadastro telaPessoa;
    private ServicePessoa servicePessoa;

    
    public ControlePessoa(TelaLogin telaLogin){
        
        //instanciando as telas
            telaPessoa = new TelaCadastro(null, true);
         
         
            servicePessoa = new ServicePessoa(telaPessoa);
        
        //adicionando os listeners

        telaPessoa.getjButtonCadastrar().addActionListener(this);
        telaPessoa.getjButtonFoto().addActionListener(this);
        telaPessoa.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(telaPessoa.getjButtonCadastrar())){
            if(telaPessoa.getjPasswordFieldSenha().equals(telaPessoa.getjPasswordFieldConfSenha())){   
             if ((e.getSource().equals(telaPessoa.getjButtonCadastrar()))&& (!(telaPessoa.getjTextFieldNome().getText().equals("")))
                && (!(telaPessoa.getjTextFieldEmail().getText().equals(""))) && (!(telaPessoa.getjTextFieldEndereco().getText().equals("")))
                &&(!(telaPessoa.getjTextFieldTelefone().getText().equals("")))) {
                
            
                cadastrar();
                
                JOptionPane.showMessageDialog(null, "Dados cadastrados");
                telaPessoa.dispose();
                TelaCadastro telaPessoa = new TelaCadastro(null, true);
                telaPessoa.setVisible(true);
        
                }
            }
        }
            if(e.getSource().equals(telaPessoa.getjButtonFoto())){
                this.servicePessoa.imagem();
                
            }else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
        
        }
    }


    private void cadastrar() {
        servicePessoa.cadastrar(); 
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        validarSenha();
     
        }

    private void validarSenha() {
        if (telaPessoa.getjPasswordFieldConfSenha().getText() != "" && telaPessoa.getjPasswordFieldSenha().getText()!="") {
              if (telaPessoa.getjPasswordFieldConfSenha().getText().equals(telaPessoa.getjPasswordFieldSenha().getText())){
                  telaPessoa.getjPasswordFieldConfSenha().setBackground(new Color(127,108,235));
                  
                 
              }else{
                      telaPessoa.getjPasswordFieldConfSenha().setBackground(new Color(224, 45, 45));
              }
             
    }
     
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
    
}
