/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Joice
 */
public class TelaPrincipalController implements Initializable{
    
    public static Stage stage;
    

    public void start(Stage stage) throws Exception{ 
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("view/TelaPrincipal.fxml"));
        Scene scene = new Scene (root);
        stage.setScene (scene);
        stage.setTitle("Gerenciador de Estoque (Gerens)");
        stage.setResizable(false);
        stage.show();
        
        
    }
    
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
    
    
    public static void main(String[] args){
        launch(args);
    }
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
