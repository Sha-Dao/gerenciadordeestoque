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
        this.telaLogin = tela;
        this.pessoaDAO = new PessoaDAO();
    }
    
    public void entrar() {
       pessoaLogin = pessoaDAO.acesso(telaLogin.getjTextFieldUsuario().getText(), String.valueOf(telaLogin.
                getjPasswordFieldSenha().getPassword()));
        
        if (pessoaLogin.getId() > 0) {
            
            JOptionPane.showMessageDialog(telaLogin,"Login feito com sucesso!");
            telaLogin.dispose();
            ControlePrincipal controlePrincipal = new ControlePrincipal(pessoaLogin);
          
        
        }else{
                JOptionPane.showMessageDialog(telaLogin,"Dados incorretos!");

            }
    }
    
    
    
    
    
}
