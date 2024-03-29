/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import model.Pessoa;
import model.PessoaDAO;
import service.ServiceLogin;
import util.AlertUtil;




public class TelaLoginController implements Initializable {
    
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final ServiceLogin serviceLogin = new ServiceLogin();
    

    @FXML
    private TextField txtEmail;
    
    @FXML
    private PasswordField passwordFieldSenha;
    
    @FXML
    private Button btnEntrar;
    
    @FXML
    private Button btnCadastrar;
   
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
     passwordFieldSenha.setOnKeyPressed(event -> {
            if (event.getCode().getName().equals("Enter")) {
                try {
                    handleButtonEntrar();
                } catch (IOException ex) {
                    Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });  
    }
    
  
    @FXML
    public void handleButtonEntrar() throws IOException{
       
       
        if (validateFields()){
            if(serviceLogin.entrar(txtEmail.getText(), String.valueOf(passwordFieldSenha.getText()))){
                openTelaPrincipal();
            }else{
                AlertUtil.show("Acesso Negado","Nome de Usuário e/ou Senha incorreto(s)!",Alert.AlertType.ERROR);  
            
            }
        }
             else{
                AlertUtil.show("Validação de Dados", "Preencha todos os campos", Alert.AlertType.ERROR);
        }     
         
        }
    
    
    @FXML
    public void handleButtonCadastrar() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TelaCad.fxml"));
        Parent content = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("CADASTRO DE USUÁRIO");
        Scene scene = new Scene(content);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        
    
    }
    private boolean validateFields(){
        if(txtEmail.getText().isEmpty() || passwordFieldSenha.getText().isEmpty()) return false;
        else return true;
    }
     
    private void openTelaPrincipal() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/TelaPrincipa.fxml"));
            Parent pane = loader.load();
            Scene scene = new Scene(pane);

            Stage principalStage = new Stage();
            principalStage.setTitle("Tela Principal");
            principalStage.setScene(scene);
            principalStage.setMaximized(true);

            Main.getStage().close(); // Fecha a janela de login
            principalStage.showAndWait();
       
        
    } 
    
}
