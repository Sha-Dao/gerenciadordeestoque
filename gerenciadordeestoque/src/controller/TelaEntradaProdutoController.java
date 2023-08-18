/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Devolucao;
import model.Entrada;
import model.Produto;
import service.ServiceProduto;
import util.AlertUtil;

/**
 *
 * @author santo
 */
public class TelaEntradaProdutoController {
    @FXML
    private Text nomeProduto;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ImageView fotoProduto;
    @FXML
    private Button btnEntrada;
    @FXML
    private TextField txtQuantidade;
    private ServiceProduto serviceProduto;
    private Produto produto;
    
    
    public  TelaEntradaProdutoController() {
        serviceProduto = new ServiceProduto();
        
        
    }
    public void handleEntradaProduto(ActionEvent event){
        int quantidade;
        if (txtQuantidade.getText().equals("")){
            quantidade = 0;
        }else{
            quantidade = Integer.parseInt(txtQuantidade.getText());
        }
        if (quantidade <= 0) {
            AlertUtil.show("Entrada negada","Adicione uma quantidade de produtos para entrada",Alert.AlertType.ERROR);  
        }else{
            produto.setQuantidade(produto.getQuantidade()+quantidade);
            serviceProduto.editarProduto(produto);
            Entrada entrada = new Entrada();
            entrada.setIdProduto(produto.getId());
            entrada.setQuantidade(quantidade);
            java.sql.Date sqlDate = java.sql.Date.valueOf(datePicker.getValue());
            entrada.setData(sqlDate);
            AlertUtil.show("Sucesso!!!","Entrada efetuada",Alert.AlertType.CONFIRMATION); 
            System.out.println(entrada.getData());
            serviceProduto.entradaProduto(entrada);
        }
    }
    
    public void setProduto(int produtoId){
        produto = serviceProduto.getProdutoById(produtoId);
        nomeProduto.setText("Nome: "+produto.getNome());
        datePicker.setValue(LocalDate.now());
        if (produto.getImagem() != null) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(produto.getImagem());
            Image imagem = new Image(inputStream);
            fotoProduto.setImage(imagem);
        }

    }
}