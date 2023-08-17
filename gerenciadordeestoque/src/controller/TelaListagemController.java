/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.PessoaDAO;
import model.Produto;
import service.ServiceProduto;
import util.Session;

public class TelaListagemController {

    @FXML
    private VBox panelContainer;
    @FXML
    private TextField txtPesquisar;
    
    private ArrayList<Produto> listaProdutos;
    private ServiceProduto serviceProduto;

    public void initialize() {
        listaProdutos = getProdutos();
        mostrarProdutosNaTela(listaProdutos);
        
    }

    @FXML
    public void handleKeyPressedPesquisar(KeyEvent event) {
        if (event.getSource().equals(txtPesquisar)){
            String character = txtPesquisar.getText();

            ArrayList<Produto> produtosEncontrados = new ArrayList<>();

            for (Produto produto : listaProdutos) {
                if (produto.getNome().toLowerCase().contains(character.toLowerCase())) {
                    produtosEncontrados.add(produto);
                }
            }
            mostrarProdutosNaTela(produtosEncontrados);
        }
    }
    
    @FXML
    public void handleButtonDeletarProduto(ActionEvent event){
        MenuItem button = (MenuItem) event.getSource();
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmação");
        confirmationAlert.setHeaderText("Deletar Perfil");
        confirmationAlert.setContentText("Tem certeza que deseja deletar o produto? Essa ação não pode ser desfeita.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
               
               ArrayList<Produto> produtosParaRemover = new ArrayList<>();

               for (Produto produto : listaProdutos) {
                   int id = produto.getId();
                   if (Integer.toString(id).equals(button.getId())) {
                       produtosParaRemover.add(produto);
                       serviceProduto.deletarProduto(produto);
               }
                

                listaProdutos.removeAll(produtosParaRemover);
                
            }
        }
        mostrarProdutosNaTela(listaProdutos);
    }
    
    @FXML
    public void handleButtonEditarProduto(ActionEvent event) throws IOException{
        MenuItem button = (MenuItem) event.getSource();
        int produtoId = Integer.parseInt(button.getId());
        System.out.println(produtoId);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaEditarProduto.fxml"));
        Parent root = loader.load();
        
        TelaEditarProdutoController controleEditarProduto = loader.getController();
        controleEditarProduto.setProduto(produtoId);

        Stage editStage = new Stage();
        editStage.setTitle("Editar Produto");
        editStage.setScene(new Scene(root));
        editStage.initModality(Modality.APPLICATION_MODAL);

        editStage.showAndWait();

    }
    
    
    public void mostrarProdutosNaTela(ArrayList<Produto> produtos){
        panelContainer.getChildren().clear();
        
        for (Produto produto : produtos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/panel.fxml"));
                AnchorPane panel = loader.load();
                panel.setPadding(new Insets(0, 0, 10, 0));

                MenuButton opcoes = (MenuButton)panel.lookup("#Opcoes");
                opcoes.setId(Integer.toString(produto.getId()));
                ObservableList<MenuItem> menuItems = opcoes.getItems();

                for (MenuItem menuItem : menuItems) {
                     if(menuItem.getId().equals("btnEditar")){
                        menuItem.setOnAction(event -> {
                            try {
                                handleButtonEditarProduto(event);
                            } catch (IOException e) {
                                e.printStackTrace(); 
                            }
                        });
                         
                     }else if(menuItem.getId().equals("btnDeletar")){
                         menuItem.setOnAction(event -> handleButtonDeletarProduto(event));
                     } else if(menuItem.getId().equals("btnEntrada")){
                         
                     }else if(menuItem.getId().equals("btnRetirada")){
                         
                     }else if(menuItem.getId().equals("btnDevolucao")){
                         
                     }
                     menuItem.setId(Integer.toString(produto.getId()));
                }

   
                
                Label labelNome = (Label) panel.lookup("#lblnome");
                labelNome.setText("Nome: "+produto.getNome());
                Label labelQuantidade = (Label) panel.lookup("#lblquantidade");
                labelQuantidade.setText("Quantidade: "+Integer.toString(produto.getQuantidade()));
                Label labelPreco = (Label) panel.lookup("#lblpreco");
                labelPreco.setText("Preço: "+Double.toString(produto.getPreco()));    
                Label labelTipo = (Label) panel.lookup("#lblTipo");
                labelTipo.setText("Tipo: "+Integer.toString(produto.getIdTipo()));
                if (produto.getImagem() != null) {
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(produto.getImagem());
                    Image imagem = new Image(inputStream);
                    ImageView fotoProduto = (ImageView) panel.lookup("#fotoProduto");
                    fotoProduto.setImage(imagem);
                 }
                

                panelContainer.getChildren().add(panel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        panelContainer.layout();
        }

    }

    private ArrayList<Produto> getProdutos() {
        serviceProduto = new ServiceProduto();
       return serviceProduto.listarProdutos();
    }
}