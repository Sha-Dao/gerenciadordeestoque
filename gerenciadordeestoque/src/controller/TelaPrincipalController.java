/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import model.Conexao;

/**
 *
 * @author Joice
 */
public class TelaPrincipalController implements Initializable{
    
    public static Stage stage;
    private Conexao conexao;
    
    
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
        // Esse método é chamado quando a tela é inicializada
    }   
    
    private void openScreen(String resource) throws IOException{
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(resource));
    }

    @FXML
    public void handleButtonBtnLista() throws IOException{
        // Carrega a tela de listagem de produtos
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TelaListagem.fxml"));
        Parent content = loader.load();

        // Cria um novo Stage para a tela de listagem
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("LISTA DE PRODUTOS");
        Scene scene = new Scene(content);
        dialogStage.setScene(scene);

        // Exibe a tela de listagem e aguarda até que ela seja fechada
        dialogStage.showAndWait();
        
    }
    @FXML
    public void handleButtonBtnAdicionar() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TelaCadastroProduto.fxml"));
        Parent content = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("CADASTRO DE PRODUTOS");
        Scene scene = new Scene(content);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        
    }
    @FXML
    public void handleButtonBtnDados() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TelaPerfil.fxml"));
        Parent content = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("DADOS PESSOAIS");
        Scene scene = new Scene(content);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        
    }
    @FXML
    public void handleButtonBtnDeletar() throws IOException, SQLException{
        
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmação");
        confirmationAlert.setHeaderText("Deletar Perfil");
        confirmationAlert.setContentText("Tem certeza que deseja deletar seu perfil? Essa ação não pode ser desfeita.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if ( /**variável do id*/ != null) {
                try {
                    
                    Connection connection = conexao.obterConexao();
                     
                    String sql = "DELETE FROM perfis WHERE id = ?";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, /**variavel do id*/);
                    
                    stmt.executeUpdate();
                
                    stmt.close();
                    connection.close();
                    
                    Stage stage = (Stage) BtnDeletar.getScene().getWindow();
                    stage.close();
                    } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    public void handleButtonBtnSair() throws IOException{
        
       // Fecha a janela atual (home) e volta para a tela de login 
        Stage stage = (Stage) BtnSair.getScene().getWindow();
        stage.close();
        
        // Carrega e exibe a tela de login 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaLog.fxml"));
        Parent loginRoot = loader.load();
        Scene loginScene = new Scene(loginRoot);

        Stage primaryStage = new Stage(); // Obtenha o palco principal da sua aplicação
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("TELA DE LOGIN");

        // Exibir a tela de login
        primaryStage.show();
    }
    
}
