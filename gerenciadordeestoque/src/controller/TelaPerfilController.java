
package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Pessoa;
import model.PessoaDAO;
import service.ServicePessoa;
import util.AlertUtil;
import util.Session;
import view.MaskTextField;

/**
 *
 * @author j_ped
 */
public class TelaPerfilController implements Initializable {
  
    private final ServicePessoa servicePessoa = new ServicePessoa();
    private File selectedFile;
  
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private MaskTextField txtCpf;
    @FXML
    private DatePicker datePicker;
    @FXML
    private MaskTextField txtTelefone;
    @FXML
    private TextField txtEndereco;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnTrocarDeSenha;
    @FXML
    private Button btnSelecionarFoto;
    @FXML
    private ImageView imagemUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtCpf.setMask("NNNNNNNNNNN");
        txtTelefone.setMask("NNNNNNNNNNN");
        
        txtNome.setText(Session.getPessoa().getNome());
        txtCpf.setText(Session.getPessoa().getCpf());
        txtEmail.setText(Session.getPessoa().getEmail());
        txtEndereco.setText(Session.getPessoa().getEndereco());
        txtTelefone.setText(Session.getPessoa().getTelefone());
        datePicker.setValue(Session.getPessoa().getDatanasc().toLocalDate());
        
        Image image = new Image(new ByteArrayInputStream(Session.getPessoa().getFoto()));
        imagemUser.setImage(image);
    }
    
    @FXML
    public void handleButtonSelecionarFoto() throws IOException{
        this.selectedFile = servicePessoa.imagem(btnSelecionarFoto.getScene().getWindow());
        
       if (selectedFile != null){
            Image image = new Image(selectedFile.toURI().toString());
            imagemUser.setImage(image);
        }
    }
    @FXML
    public void handleButtonSalvar(){
        if (validateFields()){
            servicePessoa.updateCampos(txtNome.getText(), txtEmail.getText(), txtCpf.getText(), 
                    txtTelefone.getText(), datePicker.getValue(), txtEndereco.getText());
            Stage stage = (Stage) btnSalvar.getScene().getWindow();
            stage.close();
           
        }
    }
    @FXML
    public void handleButtonTrocarDeSenha() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/TelaTrocaSenha.fxml"));
            Parent pane = loader.load();
            Scene scene = new Scene(pane);

            Stage senhaStage = new Stage();
            senhaStage.setTitle("Tela Principal");
            senhaStage.setScene(scene);
            senhaStage.showAndWait();
        
    }
     private boolean validateFields(){
            if(txtEmail.getText().isEmpty()|| 
                txtNome.getText().isEmpty()||txtEndereco.getText().isEmpty()||
                txtCpf.getText().isEmpty() || txtTelefone.getText().isEmpty())
                return false;
            
            else return true;
     }
            
}
