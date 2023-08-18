/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.application.Platform;
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
import model.PessoaDAO;
import util.Session;

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
    
    @FXML
    private Button BtnAdicionarFornecedor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Esse método é chamado quando a tela é inicializada
    }   
    
    private void openScreen(String resource) throws IOException{
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(resource));
    }

    @FXML
    public void handleButtonLista() throws IOException{
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
            if (Session.getPessoa().getId() != 0) {
                PessoaDAO pessoaDAO =  new PessoaDAO();
                pessoaDAO.excluir(Session.getPessoa());
                
        }
    }
    }
    @FXML
    public void handleButtonBtnSair() throws IOException{
        
       // Fecha a janela atual (home) e volta para a tela de login 
        Stage stage = (Stage) BtnSair.getScene().getWindow();
        stage.close();
        
        // Carrega e exibe a tela de login 
      Platform.runLater(() -> {
        try {
            Main newMainApp = new Main();
            newMainApp.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}

        // Exibir a tela de login
     @FXML
     public void handleButtonAdicionarFornecedor() throws IOException{
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TelaCadastroFornecedor.fxml"));
        Parent content = loader.load();

        Stage fornecedor = new Stage();
        fornecedor.setTitle("CADASTRO FORNECEDOR");
        Scene scene = new Scene(content);
        fornecedor.setScene(scene);
        fornecedor.centerOnScreen();
        fornecedor.setResizable(false);
        fornecedor.showAndWait();

    }
     
    }

    
