
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextField;
import model.ProdutoDAO;
import model.TipoProduto;

/**
 *
 * @author j_ped
 */
public class TelaCadastroFornecedor implements Initializable{
    
    @FXML
    private TextField txtNome;
    @FXML
    private ComboBox comboBox;
    @FXML
    private Button btnCadastrarFornecedor;
    @FXML
    private Button btnSairFornecedor;
    
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<TipoProduto> tiposProduto = new ArrayList<>();
        
        try {
            tiposProduto = produtoDAO.tipoProduto();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         ObservableList<String> items = FXCollections.observableArrayList();
       
         for (TipoProduto tipoProduto : tiposProduto) {
         items.add(tipoProduto.getTipoProduto());
            }
       
        
        comboBox.setItems(items);
        
        
 
    }
    @FXML
    public void handleButtonCadastrarFornecedor(){
        System.out.println(comboBox.getValue().toString());
    
    }
    
    @FXML 
    public void handleButtonSairFornecedor(){
    }
    
    
    
}
