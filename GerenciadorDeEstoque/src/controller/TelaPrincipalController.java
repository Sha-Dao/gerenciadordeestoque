/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Joice
 */
public class TelaPrincipalController implements Initializable{
    
    public static Stage stage;
    
    
    @FXML
    private Button BtnLista;
    
    @FXML 
    private Button BtnAdicionar;
    
    @FXML 
    private Button BtnDados;
    
    @FXML 
    private Button BtnDeletar;
    
    @FXML 
    private Button BtnSair;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    private void openScreen(String resource) throws IOException{
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(resource));
    }

    
        public void handleButtonBtnLista() throws IOException{
        openScreen("/view/Tela.fxml");
        
    }
    
    public void handleButtonBtnAdicionar() throws IOException{
        openScreen("/view/TelaCadastrarProduto.fxml");
        
    }
    
    public void handleButtonBtnDados() throws IOException{
        openScreen("/view/TelaPerfil.fxml");
        
    }
    
    public void handleButtonBtnDeletar() throws IOException{
        
        
    }
    
    public void handleButtonBtnSair() throws IOException{
                   
       
    }
    
    private void openMainView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TelaPrincipal.fxml"));
        Parent pane = loader.load();            
        Launch.getStage().getScene().setRoot(pane);
        Launch.getStage().sizeToScene();
        Launch.getStage().centerOnScreen();
    } 
    
}
