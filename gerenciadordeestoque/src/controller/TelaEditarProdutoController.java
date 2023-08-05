/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
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
    
    private ServiceProduto serviceProduto;
    private Produto produto;

    public TelaEditarProdutoController() {
        serviceProduto = new ServiceProduto();
    }
    
    public void setProduto(int produtoId) {
        
        produto = getProdutoById(produtoId);
        System.out.println(produto);
        txtNome.setText(produto.getNome());
        txtTipo.setText(Integer.toString(produto.getIdTipo()));
        txtPreco.setText(Double.toString(produto.getPreco()));
        System.out.println(produto.getQuantidade());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, produto.getQuantidade());
        spnQuantidade.setValueFactory(valueFactory);
    }
    
    @FXML
    public void handleSalvarAlteracoes(ActionEvent event) {
        produto.setNome(txtNome.getText());
        produto.setIdTipo(Integer.parseInt(txtTipo.getText()));
        produto.setPreco(Double.parseDouble(txtPreco.getText()));
        produto.setQuantidade(spnQuantidade.getValue());
        serviceProduto.EditarProduto(produto);
        System.out.println("OIII");
    }
    
    
    public Produto getProdutoById(int produtoId){
        return serviceProduto.getProdutoById(produtoId);
    }
}
