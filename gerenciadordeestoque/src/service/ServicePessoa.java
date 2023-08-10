/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.TelaCadastroController;
import controller.TelaLoginController;
import java.awt.Graphics2D;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import main.Main;
import model.Pessoa;
import model.PessoaDAO;
import util.AlertUtil;

/**
 *
 * @author j_ped
 */
public class ServicePessoa {
   
    private PessoaDAO pessoaDAO;
    private BufferedImage imagem;
    private Date data;
    private Pessoa pessoa;
    private static File imagemFile;
    
     public ServicePessoa() {
        //pessoaDAO e data são instanciados e telaPessoa recebe o atributo de tela
 
        this.pessoaDAO = new PessoaDAO();
        this.data = new Date();
    }
    
    

    public void cadastrar(String nome, String email, String cpf, String telefone,
    String senha, String data, String endereco) throws ParseException, IOException {
            byte[] fotoEmBytes = null;
            
            if (imagemFile != null) {
            try {
                
                fotoEmBytes = Files.readAllBytes(imagemFile.toPath());
                AlertUtil.show("Cadastro concluído!", "Seu cadastro foi realizado com sucesso!", Alert.AlertType.CONFIRMATION);
            } catch (IOException e) {
                e.printStackTrace();
                AlertUtil.show("Erro ao cadastrar", "Ocorreu um erro ao processar a imagem", Alert.AlertType.ERROR);
            }
           
                
                   
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            Date date = dateFormat.parse(data);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            pessoa = new Pessoa(nome, sqlDate, cpf, endereco, telefone, email, senha, fotoEmBytes);
       
            pessoaDAO.inserir(pessoa);
    }
   
    
    //escolha da imagem utilizando o filechooser
     public File imagem(Window window) throws IOException{
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Imagem");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        java.io.File selectedFile = fileChooser.showOpenDialog(window);
        
        imagemFile = selectedFile;
        
        return selectedFile;
		
        
     }
     /*
    private byte[] imageToBytes(Image image, String formatName) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), formatName, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
*/
}
