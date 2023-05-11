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
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        telaPessoa.getjPasswordFieldConfSenha().addKeyListener(this);
        telaPessoa.getjPasswordFieldSenha().addKeyListener(this);
        telaPessoa.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(telaPessoa.getjButtonCadastrar())){
  
            if(String.valueOf(telaPessoa.getjPasswordFieldSenha().getPassword()).equals(String.valueOf(telaPessoa.getjPasswordFieldSenha().getPassword()))){ 

             if ((e.getSource().equals(telaPessoa.getjButtonCadastrar()))&& (!(telaPessoa.getjTextFieldNome().getText().equals("")))
                && (!(telaPessoa.getjTextFieldEmail().getText().equals(""))) && (!(telaPessoa.getjTextFieldEndereco().getText().equals("")))
                &&(!(telaPessoa.getjTextFieldTelefone().getText().equals("")))) {
                
            
                 try {
                     cadastrar();
                 } catch (ParseException ex) {
                     Logger.getLogger(ControlePessoa.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException ex) {
                     Logger.getLogger(ControlePessoa.class.getName()).log(Level.SEVERE, null, ex);
                 }
                
                JOptionPane.showMessageDialog(null, "Dados cadastrados");
                telaPessoa.dispose();
                
        
                
            }else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                telaPessoa.dispose();
                TelaCadastro telaPessoa = new TelaCadastro(null, true);
                telaPessoa.setVisible(true);
        
            }
            }else{
                    JOptionPane.showMessageDialog(null, "As senhas digitadas não coincidem");
                    telaPessoa.dispose();
                    TelaCadastro telaPessoa = new TelaCadastro(null, true);
                    telaPessoa.setVisible(true);
                 
            }
            
             
            }
        
            if(e.getSource().equals(telaPessoa.getjButtonFoto())){
               this.servicePessoa.imagem();
                    
                
                
                
            }
    }


    private void cadastrar() throws ParseException, IOException {
      servicePessoa.cadastrar();
      
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
   
    }

    private void validarSenha() {
        if ((!String.valueOf(telaPessoa.getjPasswordFieldSenha().getPassword()).isEmpty()) && (!String.valueOf(telaPessoa.
                getjPasswordFieldConfSenha().getPassword()).isEmpty())) {
            
              if (String.valueOf(telaPessoa.getjPasswordFieldConfSenha().getPassword()).equals
              (String.valueOf(telaPessoa.getjPasswordFieldSenha().getPassword()))){
                  telaPessoa.getjPasswordFieldConfSenha().setBackground(new Color(127,108,235));
                  
                 
              }else{
                      telaPessoa.getjPasswordFieldConfSenha().setBackground(new Color(224, 45, 45));
              }}
              else{
                  telaPessoa.getjPasswordFieldConfSenha().setBackground(new Color(127,108,235));
              
              }
              
             
            }
   
    

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
           if ((ke.getSource().equals(telaPessoa.getjPasswordFieldConfSenha()) || ke.getSource().equals(telaPessoa.getjPasswordFieldSenha()))){
                validarSenha();
            }

    }
    
}
