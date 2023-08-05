
package controller;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import model.Pessoa;
import model.PessoaDAO;

/**
 *
 * @author j_ped
 */

public class TelaCadastroController implements Initializable {
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final Pessoa pessoa = new Pessoa();
  
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtDataNasc;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEndereco;
    @FXML
    private PasswordField passwordFieldSenha;
    @FXML
    private PasswordField passwordFieldConfSenha;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnFoto;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void handleButtonCadastrarPessoa() throws IOException{
    
    }
    
    @FXML
    public void handleButtonSelecionarFoto() throws IOException{
    
    }
    
}