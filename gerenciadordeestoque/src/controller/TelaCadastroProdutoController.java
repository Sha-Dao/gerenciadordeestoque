/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Produto;
import service.ServiceProduto;
import util.AlertUtil;

/**
 *
 * @author Dani
 */
public class TelaCadastroProdutoController implements Initializable {
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtTipo;
    
    @FXML
    private TextField txtPreco;
    
    @FXML
    private Button btnSelecionarFoto;
   
    @FXML
    private Button btnCadastrarProduto;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private ImageView fotoProduto;
    private ServiceProduto serviceProduto;
    private File selectedFile;
    
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    public TelaCadastroProdutoController(){
        serviceProduto = new ServiceProduto();
    }
    
    public void handleCadastrarProduto(){
        
        if (validateFields()) {
            Produto produto = new Produto();
            produto.setPreco(Double.parseDouble(txtPreco.getText()));
            produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            produto.setNome(txtNome.getText());
            produto.setIdTipo(Integer.parseInt(txtTipo.getText()));
            byte[] fotoEmBytes = null;
            try {

                    fotoEmBytes = Files.readAllBytes(selectedFile.toPath());

                } catch (IOException e) {
                    e.printStackTrace();
                    AlertUtil.show("Erro ao cadastrar", "Ocorreu um erro ao processar a imagem", Alert.AlertType.ERROR);
                }
            produto.setImagem(fotoEmBytes);
            serviceProduto.adicionarProduto(produto);
        }
        
        
    }
    public void handleSelecionarFoto() throws IOException{
        this.selectedFile = serviceProduto.imagem(btnSelecionarFoto.getScene().getWindow());
        
        if (selectedFile != null){
            Image image = new Image(selectedFile.toURI().toString());
            fotoProduto.setImage(image);
 
        }
    }
    
    public boolean validateFields(){
        
        if (txtNome.getText().isEmpty() || txtPreco.getText().isEmpty() || txtTipo.getText().isEmpty() || txtQuantidade.getText().isEmpty() || !isNumeric(txtQuantidade.getText()) || !isNumeric(txtPreco.getText()) || !isNumeric(txtTipo.getText())) {
            AlertUtil.show("Campos inválidos","Preencha corretamente os campos!!!",Alert.AlertType.ERROR);  
            return false;
        } else if (selectedFile == null){
            AlertUtil.show("Sem imagem","Carregue uma imagem!!!",Alert.AlertType.ERROR);  
            return false;
        }
        return true;
        
    }
    public boolean isNumeric(String str) {
    for (char c : str.toCharArray()) {
        if (!Character.isDigit(c)) {
            return false;
        }
    }
    return true;
    }
    
}
