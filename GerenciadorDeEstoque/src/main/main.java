/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.ControleLogin;
import controller.ControlePerfil;
import controller.ControlePessoa;
import controller.ControleProduto;
import controller.TelaLoginController;
import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Pessoa;

/**
 *
 * @author j_ped
 */
public class main extends Application {
    
    private static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;        
        Parent root = FXMLLoader.load(getClass().getResource
                                        ("view/TelaLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GERENCIADOR DE ESTOQUE");        
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }
    
    
}
