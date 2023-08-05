/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

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
    private Spinner spnQuantidade;
    
    
    
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    } 
    
    
}
