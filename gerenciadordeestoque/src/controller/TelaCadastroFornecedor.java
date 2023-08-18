
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ProdutoDAO;
import model.TipoProduto;
import service.ServiceFornecedor;
import util.AlertUtil;

/**
 *
 * @author j_ped
 */
public class TelaCadastroFornecedor implements Initializable{
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCNPJ;
    @FXML
    private TextField txtRamo;
    @FXML
    private ComboBox comboBox;
    @FXML
    private ComboBox comboBoxUF;
    @FXML
    private Button btnCadastrarFornecedor;
    @FXML
    private Button btnSairFornecedor;
    
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private ServiceFornecedor serviceFornecedor = new ServiceFornecedor();
    
    ArrayList<TipoProduto> tiposProduto = new ArrayList<>();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        
        try {
            tiposProduto = produtoDAO.tipoProduto();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         ObservableList<String> itemsTipo = FXCollections.observableArrayList();
         ObservableList<String> UF = FXCollections.observableArrayList("AC", "AL","AP","AM","BA","CE","DF",
        "ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO");
       
         for (TipoProduto tipoProduto : tiposProduto) {
         itemsTipo.add(tipoProduto.getTipoProduto());
            }
       
        
        comboBox.setItems(itemsTipo);
        comboBoxUF.setItems(UF);
        
        
 
    }
    @FXML
    public void handleButtonCadastrarFornecedor(){
       if (validateFields()){
           serviceFornecedor.adicionarForn(txtNome.getText(), 
                   txtEndereco.getText(), txtCNPJ.getText(),
                   txtRamo.getText(), txtCidade.getText(),
                   txtBairro.getText(), comboBoxUF.getSelectionModel().getSelectedItem().toString(),
                   tiposProduto.get(comboBox.getSelectionModel().getSelectedIndex()).getId());
           Stage stage = (Stage) btnCadastrarFornecedor.getScene().getWindow();
           stage.close();
       }else{
            AlertUtil.show("Campos vazios!", "Preencha todos os campos!", 
                     Alert.AlertType.ERROR);
       }
    }
    
    @FXML 
    public void handleButtonSairFornecedor(){
        Stage stage = (Stage) btnSairFornecedor.getScene().getWindow();
        stage.close();
    }
    
    
    public boolean validateFields(){
        if (txtNome.getText().isEmpty()  | comboBox.getSelectionModel().isEmpty() || txtCidade.getText().isEmpty()||txtBairro.getText().isEmpty()||
                txtEndereco.getText().isEmpty()||txtCNPJ.getText().isEmpty()||
                comboBoxUF.getSelectionModel().getSelectedItem().toString().isEmpty()||
                txtRamo.getText().isEmpty()
                )
            return false;
        else return true;
    }
}
