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
import service.ServiceProduto;
import model.Produto;
import util.AlertUtil;

/**
 *
 * @author santo
 */
public class TelaDevolucaoProdutoController {
    @FXML
    private Text nomeProduto;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ImageView fotoProduto;
    @FXML
    private Button btnDevolucao;
    @FXML
    private TextField txtQuantidade;
    private ServiceProduto serviceProduto;
    private Produto produto;
    
    
    public  TelaDevolucaoProdutoController() {
        serviceProduto = new ServiceProduto();
        
        
    }
    public void handleDevolverProduto(ActionEvent event){
        int quantidade;
        if (txtQuantidade.getText().equals("")){
            quantidade = 0;
        }else{
            quantidade = Integer.parseInt(txtQuantidade.getText());
        }
        if (quantidade <= 0) {
            AlertUtil.show("Devolução negada","Adicione uma quantidade de produtos para devolução",Alert.AlertType.ERROR);  
        }else{
            produto.setQuantidade(produto.getQuantidade()+quantidade);
            serviceProduto.editarProduto(produto);
            Devolucao devolucao = new Devolucao();
            devolucao.setIdProduto(produto.getId());
            devolucao.setQuantidade(quantidade);
            java.sql.Date sqlDate = java.sql.Date.valueOf(datePicker.getValue());
            devolucao.setData(sqlDate);
            AlertUtil.show("Sucesso!!!","Devolução efetuada",Alert.AlertType.CONFIRMATION); 
            System.out.println(devolucao.getData());
            serviceProduto.devolucaoProduto(devolucao);
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
