/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Produto;
import service.ServiceProduto;

/**
 *
 * @author santo
 */
public class TelaEditarProdutoController {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtPreco;
    @FXML
    private Spinner<Integer> spnQuantidade;
    @FXML
    private ImageView fotoProduto;
    
    private ServiceProduto serviceProduto;
    private Produto produto;

    public TelaEditarProdutoController() {
        serviceProduto = new ServiceProduto();
    }
    
    public void setProduto(int produtoId) {
        
        produto = getProdutoById(produtoId);
        txtNome.setText(produto.getNome());
        txtTipo.setText(Integer.toString(produto.getIdTipo()));
        txtPreco.setText(Double.toString(produto.getPreco()));
        if (produto.getImagem() != null) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(produto.getImagem());
            Image imagem = new Image(inputStream);
            fotoProduto.setImage(imagem);
        }

       
    }
    
    @FXML
    public void handleSalvarAlteracoes(ActionEvent event) {
        produto.setNome(txtNome.getText());
        produto.setIdTipo(Integer.parseInt(txtTipo.getText()));
        produto.setPreco(Double.parseDouble(txtPreco.getText()));
        serviceProduto.editarProduto(produto);
    }
    @FXML
    public void handleSelecionarFoto(ActionEvent event) {
        System.out.println("Selecione a imagem");
    }
    
    public Produto getProdutoById(int produtoId){
        return serviceProduto.getProdutoById(produtoId);
    }
}
