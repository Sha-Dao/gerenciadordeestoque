/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Pessoa;
import model.PessoaDAO;
import view.TelaCadastro;

/**
 *
 * @author j_ped
 */
public class ServicePessoa {
    
    private TelaCadastro telaPessoa;
    private PessoaDAO pessoaDAO;
    private BufferedImage imagem;
    private Date data;



    public ServicePessoa(TelaCadastro tela) {
        //pessoaDAO e data são instanciados e telaPessoa recebe o atributo de tela
 
        this.pessoaDAO = new PessoaDAO();
        this.telaPessoa = tela;
        this.data = new Date();

    }
    
    //salva os valores digitados nos texts fields utilizando o pessoaDAO passsando como parâmetro o objeto pessoa
    public void cadastrar() throws ParseException, IOException {
       
        Pessoa pessoa = new Pessoa();
        
        

            pessoa.setNome(telaPessoa.getjTextFieldNome().getText());
            pessoa.setEmail(telaPessoa.getjTextFieldEmail().getText());
            pessoa.setEndereco(telaPessoa.getjTextFieldEndereco().getText());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse(telaPessoa.getjFormattedTextFieldDataNasc().getText());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            pessoa.setDatanasc(sqlDate);
           
           
            
            pessoa.setCpf(telaPessoa.getjFormattedTextFieldCPF().getText().replaceAll("[\\.-]", ""));
            pessoa.setTelefone(telaPessoa.getjTextFieldTelefone().getText());
            pessoa.setSenha(telaPessoa.getjPasswordFieldSenha().getText());
            
            if(imagem!=null){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(imagem, "jpg", baos);
                byte[] fotoEmBytes = baos.toByteArray();
                pessoa.setFoto(fotoEmBytes);
               
            
            
            }else{
                Icon icon = telaPessoa.getjLabelImagem().getIcon();
                Image image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = (Graphics2D) image.getGraphics();
                icon.paintIcon(null, g2d, 0, 0);
                g2d.dispose();

                ImageIcon iconImage = new ImageIcon(image);
                Image imagem = iconImage.getImage();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write((RenderedImage) imagem, "jpg", baos);
                byte[] fotoEmBytes = baos.toByteArray();
                pessoa.setFoto(fotoEmBytes);
                JOptionPane.showMessageDialog(null, "OBS: É recomendável que você adicione uma foto de perfil!");
              
            
            }
            pessoaDAO.inserir(pessoa);
    }
   
    
    //escolha da imagem utilizando o jfilechooser
     public void imagem(){
         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens (*.bmp, *.png, *.jpg, *.jpeg)", "bmp", "png", "jpg", "jpeg"));
        fileChooser.setDialogTitle("Abrir Imagem");
        fileChooser.showOpenDialog(telaPessoa);//abre o arquivo
        
        File file = fileChooser.getSelectedFile();//abre o arquivo selecionados
		
        try{	
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            Image image = icon.getImage();
           
            // Obtém a largura e a altura da imagem original
            int larguraOriginal = image.getWidth(null);
            int alturaOriginal = image.getHeight(null);

            int larguraMaxima = 200;
            int alturaMaxima = 200;
            double proporcaoOriginal = (double) larguraOriginal / alturaOriginal;

            int larguraNova = larguraMaxima;
            int alturaNova = (int) (larguraNova / proporcaoOriginal);

            if (alturaNova < alturaMaxima) {
                alturaNova = alturaMaxima;
                larguraNova = (int) (alturaNova * proporcaoOriginal);
            }


            // Redimensiona a imagem para a nova largura e altura
            image = image.getScaledInstance(larguraNova, alturaNova, Image.SCALE_SMOOTH);

            // Cria um novo ImageIcon com a imagem redimensionada
            icon = new ImageIcon(image);

            telaPessoa.getjLabelImagem().setIcon(icon);
            telaPessoa.getjLabelImagem().setHorizontalAlignment(JLabel.CENTER);
            telaPessoa.getjLabelImagem().setVerticalAlignment(JLabel.CENTER);

            imagem = new BufferedImage(larguraNova, alturaNova, BufferedImage.TYPE_INT_RGB);
            imagem.getGraphics().drawImage(icon.getImage(), 0, 0, null);

        }catch(Exception e){
            JOptionPane.showMessageDialog(telaPessoa, "Não obteve o carregamento do arquivo");
        }
     }
    
}
            
        
    

