
package controller;

import java.io.File;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Pessoa;
import model.PessoaDAO;
import service.ServicePessoa;
import util.AlertUtil;
import view.MaskTextField;

/**
 *
 * @author j_ped
 */

public class TelaCadastroController implements Initializable {
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final Pessoa pessoa = new Pessoa();
    private final ServicePessoa servicePessoa = new ServicePessoa();
    private File selectedFile;
  
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCpf;
    @FXML
    private DatePicker datePicker;
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
    @FXML
    private ImageView imagemUser;
   
    

   
    
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       
        passwordFieldConfSenha.textProperty().addListener((observable, oldValue, newValue) -> {
        validatePasswordsMatch();
    });
        
       
        
    }
    
    @FXML
    public void handleButtonCadastrarPessoa() throws IOException, ParseException, SQLException, URISyntaxException{
        
        if (validateFields()){
            if (pessoaDAO.checarUsuario(txtEmail.getText())){
                  servicePessoa.cadastrar(txtNome.getText(), txtEmail.getText(), txtCpf.getText(), txtTelefone.getText(),
                  String.valueOf(passwordFieldSenha.getText()), datePicker.getValue(), txtEndereco.getText());
                        if (selectedFile == null){
                            AlertUtil.show("Aviso!", "É recomendável que utilize uma imagem para sua identificação", Alert.AlertType.WARNING);
            }
            AlertUtil.show("Cadastro concluído!", "Seu cadastro foi realizado com sucesso!", Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) btnCadastrar.getScene().getWindow();
            stage.close();
            
            } else{
                AlertUtil.show("Email já cadastrado!","O endereço de email já está cadastrado!", Alert.AlertType.ERROR);
            }
        }
             else{
                AlertUtil.show("Validação de Dados", "Preencha todos os campos", Alert.AlertType.ERROR);
        }
    }
         
        
    
    
    
    @FXML
    public void handleButtonFoto() throws IOException{
        this.selectedFile = servicePessoa.imagem(btnFoto.getScene().getWindow());
        
        if (selectedFile != null){
            Image image = new Image(selectedFile.toURI().toString());
            imagemUser.setImage(image);
 
        }
    
    }
    
     private boolean validateFields(){
            if(txtEmail.getText().isEmpty()|| passwordFieldSenha.getText().isEmpty()||  
                txtNome.getText().isEmpty()||txtEndereco.getText().isEmpty()||
                txtCpf.getText().isEmpty() || txtTelefone.getText().isEmpty())
                return false;
            else if (!(String.valueOf(passwordFieldSenha.getText()).equals(String.valueOf
            (passwordFieldConfSenha.getText())))){
                AlertUtil.show("Cadastro negado!","Campos senha e confirmar senha não batem!",Alert.AlertType.ERROR); 
                return false;
            }
             else return true;
    }

    private void validatePasswordsMatch() {
        if (!passwordFieldSenha.getText().equals(passwordFieldConfSenha.getText())) {
        passwordFieldConfSenha.setStyle("-fx-background-color: red; -fx-text-fill: white;");
    } else {
        passwordFieldConfSenha.setStyle("-fx-background-color: rgb(127,108,235); -fx-text-fill: white;"); // Remove o estilo
    }
    }
    
}

