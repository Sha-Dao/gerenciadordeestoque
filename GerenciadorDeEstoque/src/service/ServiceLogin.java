/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.ControlePrincipal;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.PessoaDAO;
import view.TelaLogin;

/**
 *
 * @author j_ped
 */
public class ServiceLogin {
    
    //criacao das variaveis a serem utilizadas
    private TelaLogin telaLogin;
    private PessoaDAO pessoaDAO;
    private Pessoa pessoaLogin;

    public Pessoa getPessoaLogin() {
        return pessoaLogin;
    }

    public void setPessoaLogin(Pessoa pessoaLogin) {
        this.pessoaLogin = pessoaLogin;
    }
    

    public ServiceLogin(TelaLogin tela) {
        //as variaveis recebem o parametro passado ao Service
        this.telaLogin = tela;
        this.pessoaDAO = new PessoaDAO();
    }
    
    
    public void entrar() {
        //atribui o valor retornado por pessoaDAO a uma varivael pessoaLogin
       pessoaLogin = pessoaDAO.acesso(telaLogin.getjTextFieldUsuario().getText(), String.valueOf(telaLogin.
                getjPasswordFieldSenha().getPassword()));
        
       //checa se pessoaLogin recebeu algum valor do id, permitindo ou não o acesso
        if (pessoaLogin.getId() > 0) {
            
            JOptionPane.showMessageDialog(telaLogin,"Login feito com sucesso!");
            telaLogin.dispose();
            ControlePrincipal controlePrincipal = new ControlePrincipal(pessoaLogin);
          
        
        }else{
                JOptionPane.showMessageDialog(telaLogin,"Dados incorretos!");

            }
    }
    
    
    
    
    
}
