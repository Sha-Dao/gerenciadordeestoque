/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.ControleSenha;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.PessoaDAO;
import view.TelaPerfil;
import view.TelaTrocaSenha;

/**
 *
 * @author j_ped
 */
public class ServiceSenha {
    
    private String senhaNova;
    private String senhaAntiga;
    private TelaTrocaSenha telaTrocaSenha;
    private Pessoa pessoa;
    
    
    public ServiceSenha(Pessoa pessoa) {
        
        //atribui pessoa a variavel pessoa
        this.telaTrocaSenha =  this.telaTrocaSenha;
        this.pessoa = pessoa;
        
    }
    
    //pega os valores recebidos no construtor do Service e adiciona ao metodo alterar senha do PessoaDAO
    public void alterarSenha(){
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.alterarSenha(pessoa);
        JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
        
         
       
   
  
        
       
    }}
    
    
    
    

