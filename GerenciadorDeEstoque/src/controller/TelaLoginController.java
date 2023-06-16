/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import model.Pessoa;
import model.PessoaDAO;




public class TelaLoginController implements Initializable {
    
    private final PessoaDAO pessoaDAO = new PessoaDAO();

    @FXML
    private TextField txtEmail;
    
    @FXML
    private PasswordField passwordFieldSenha;
    
    @FXML
    private Button btnEntrar;
    
    @FXML
    private Button btnCadastrar;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void handleButtonEntrar(){
        txtEmail.setText("a");
        
    }
    
    @FXML
    public void handleButtonCadastrar(){
    
    }
    
    
    
}
