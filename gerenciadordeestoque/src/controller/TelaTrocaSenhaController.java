/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import service.ServicePessoa;
import util.AlertUtil;

/**
 *
 * @author j_ped
 */
public class TelaTrocaSenhaController implements Initializable{
    
    private final ServicePessoa servicePessoa = new ServicePessoa();
    
    @FXML
    private PasswordField txtSenhaNova;
    @FXML
    private PasswordField txtSenhaNovaConf;
    @FXML
    private Button btnSalvarNovaSenha;
    @FXML
    private Button btnSairNovaSenha;
     

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    @FXML
    public void handleButtonSalvarNovaSenha(){
        
       if(!(txtSenhaNova.getText().isEmpty())&&!(txtSenhaNovaConf.getText().isEmpty()))
        if (validateSenhas()){
            servicePessoa.atualizarSenha(txtSenhaNova.getText());
            Stage stage = (Stage) btnSairNovaSenha.getScene().getWindow();
            stage.close();
        }else{
             AlertUtil.show("Senha não atualizada!", "Os campos senha e confirmar senha precisam ser iguais!", 
                     Alert.AlertType.ERROR);
        }else{
           AlertUtil.show("Campos vazios!", "Preencha todos os campos!", 
                     Alert.AlertType.ERROR);
           
       }
        
        
    }
    
    @FXML
    public void handleButtonSairNovaSenha(){
        Stage stage = (Stage) btnSairNovaSenha.getScene().getWindow();
        stage.close();
    
    }
 
    public boolean validateSenhas(){
        if (txtSenhaNova.getText().equals(txtSenhaNovaConf.getText()))
            return true;
        else return false;

    }
    
}
