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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import util.Session;

/**
 *
 * @author j_ped
 */
public class ServicePessoa {
   
    private PessoaDAO pessoaDAO;
    private BufferedImage imagem;
    private Date data;
    private Pessoa pessoa;
    private static File imagemFile = null;
    
     public ServicePessoa() {
        //pessoaDAO e data são instanciados e telaPessoa recebe o atributo de tela
        this.pessoa = new Pessoa();
        this.pessoaDAO = new PessoaDAO();
        this.data = new Date();
    }
    
    public void updateCampos(String nome, String email, String cpf, String telefone, 
            LocalDate data, String endereco){
       
        
            pessoa.setId(Session.getPessoa().getId());
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setEmail(email);
            pessoa.setEndereco(endereco);
            java.sql.Date sqlDate = java.sql.Date.valueOf(data);
            pessoa.setDatanasc(sqlDate);
            pessoa.setTelefone(telefone);
            
            
            byte[] fotoEmBytes = null;
            if (imagemFile != null) {
            try {
                
                fotoEmBytes = Files.readAllBytes(imagemFile.toPath());
                pessoa.setFoto(fotoEmBytes);
                
                
            } catch (IOException e) {
                e.printStackTrace();
                AlertUtil.show("Erro ao cadastrar", "Ocorreu um erro ao processar a imagem", Alert.AlertType.ERROR);
            }
            }
            else{
                pessoa.setFoto(Session.getPessoa().getFoto());
                
            
            }
            pessoaDAO.alterar(pessoa);
            
    }
    

    public void cadastrar(String nome, String email, String cpf, String telefone,
    String senha, LocalDate data, String endereco) throws ParseException, IOException, URISyntaxException {
            byte[] fotoEmBytes = null;
            
            if (imagemFile != null) {
            try {
                
                fotoEmBytes = Files.readAllBytes(imagemFile.toPath());
                
            } catch (IOException e) {
                e.printStackTrace();
                AlertUtil.show("Erro ao cadastrar", "Ocorreu um erro ao processar a imagem", Alert.AlertType.ERROR);
            }
            }
            else{
      
                File imagemPadraoFile = new File("src/imagens/perfil-imagem.png");

            try {
    
                fotoEmBytes = Files.readAllBytes(imagemPadraoFile.toPath());
                AlertUtil.show("Cadastro concluído!", "Seu cadastro foi realizado com sucesso!", Alert.AlertType.CONFIRMATION);
            } catch (IOException e) {
                e.printStackTrace();
                AlertUtil.show("Erro ao cadastrar", "Ocorreu um erro ao processar a imagem", Alert.AlertType.ERROR);
            }
           
                
                  
            }
            
           java.sql.Date sqlDate = java.sql.Date.valueOf(data);
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
     
     public void atualizarSenha(String senha){
         Session.getPessoa().setSenha(senha);
         pessoaDAO.alterarSenha(Session.getPessoa());
         
         
         
     }
 
}
