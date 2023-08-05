/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Produto;
import service.ServiceProduto;

public class ControleProduto {

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
        
        Button button = (Button) event.getSource();
        ArrayList<Produto> produtosParaRemover = new ArrayList<>();

        for (Produto produto : listaProdutos) {
            int id = produto.getId();
            if (Integer.toString(id).equals(button.getId())) {
                produtosParaRemover.add(produto);
            }
        }

        listaProdutos.removeAll(produtosParaRemover);
        
        mostrarProdutosNaTela(listaProdutos);
    }
    
    @FXML
    public void handleButtonEditarProduto(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        int produtoId = Integer.parseInt(button.getId());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaEditarProduto.fxml"));
        Parent root = loader.load();
        
        TelaEditarProdutoController controleEditarProduto = loader.getController();
        System.out.println(produtoId);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("panel.fxml"));
                AnchorPane panel = loader.load();
                panel.setPadding(new Insets(0, 0, 10, 0));

                Button buttonEditar = (Button) panel.lookup("#editar");
                Button buttonDeletar = (Button) panel.lookup("#deletar");
                
                Label labelNome = (Label) panel.lookup("#lblnome");
                labelNome.setText("Nome: "+produto.getNome());
                Label labelQuantidade = (Label) panel.lookup("#lblquantidade");
                labelQuantidade.setText("Quantidade: "+Integer.toString(produto.getQuantidade()));
                Label labelPreco = (Label) panel.lookup("#lblpreco");
                labelPreco.setText("Preço: "+Double.toString(produto.getPreco()));    
                Label labelTipo = (Label) panel.lookup("#lblTipo");
                labelTipo.setText("Tipo: "+Integer.toString(produto.getIdTipo()));
                
                buttonDeletar.setId(""+produto.getId());
                buttonEditar.setId(""+produto.getId());
                buttonDeletar.setOnAction(event -> handleButtonDeletarProduto(event));
                buttonEditar.setOnAction(event -> {
                    try {
                        handleButtonEditarProduto(event);
                    } catch (IOException ex) {
                        Logger.getLogger(ControleProduto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

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