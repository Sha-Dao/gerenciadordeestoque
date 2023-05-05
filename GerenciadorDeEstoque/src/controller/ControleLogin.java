/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import service.ServiceLogin;
import view.TelaCadastro;
import view.TelaLogin;

/**
 *
 * @author j_ped
 */
public class ControleLogin implements ActionListener, KeyListener {
    
    private final TelaLogin telaLogin;
    private ServiceLogin serviceLogin;
    

    public ControleLogin() {
        telaLogin = new TelaLogin(null, true);
        serviceLogin = new ServiceLogin(telaLogin);
        telaLogin.getjButtonEntrar().addActionListener(this);
        telaLogin.getjTextFieldUsuario().addKeyListener(this);
        telaLogin.getjPasswordFieldSenha().addKeyListener(this);
        telaLogin.getjButtonCadastrar().addActionListener(this);
        telaLogin.setVisible(true);
        

    
    }
    public void telaLogin (){
        ControlePessoa cp = new ControlePessoa(telaLogin);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(telaLogin.getjButtonEntrar())){
            serviceLogin.entrar();  
            
            
        }
        if(e.getSource().equals(telaLogin.getjButtonCadastrar())){
            telaLogin();
        }
    }
    
    


    @Override
    public void keyPressed(KeyEvent ke) {
        // if (ke.getSource().equals(telaLogin.getjTextFieldUsuario())||(ke.getSource().equals(telaLogin.getjPasswordField2()))){
         if(ke.getKeyCode() == KeyEvent.VK_ENTER){
            serviceLogin.entrar();
         }
            
        //}
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    
    
}
