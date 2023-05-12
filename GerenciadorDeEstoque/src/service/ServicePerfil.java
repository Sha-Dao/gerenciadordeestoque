
package service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Pessoa;
import model.PessoaDAO;
import model.Produto;
import view.TelaPerfil;

/**
 *
 * @author j_ped
 */
public class ServicePerfil {
    
    private TelaPerfil telaPerfil;
    private PessoaDAO pessoaDAO;
    private Pessoa pessoaService;
    private BufferedImage imagem;
  
   
    

    public ServicePerfil(TelaPerfil telaPerfil, Pessoa pessoa) {
        this.pessoaService=pessoa;
        this.telaPerfil =  telaPerfil;
        this.pessoaDAO = new PessoaDAO();
        try {
            listaDados(pessoaService);
        } catch (ParseException ex) {
            Logger.getLogger(ServicePerfil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
  //metodo que vai atribuir o valor de pessoa aos texts fields
    public void listaDados(Pessoa pessoa) throws ParseException { 
       
   
       telaPerfil.getjTextFieldNome().setText(pessoa.getNome());
       
       //adicionando imagem redimensionada
            byte[] imagemBytes = pessoa.getFoto();
            ImageIcon imagemBD = new ImageIcon(imagemBytes);
            
            
           
         //redimensiona a imagem mantendo a proporção original
            int larguraOriginal = imagemBD.getIconWidth();
            int alturaOriginal = imagemBD.getIconWidth();

            int larguraMaxima = 200;
            int alturaMaxima = 200;
            double proporcaoOriginal = (double) larguraOriginal / alturaOriginal;

            int larguraNova = larguraMaxima;
            int alturaNova = (int) (larguraNova / proporcaoOriginal);

            if (alturaNova < alturaMaxima) {
                alturaNova = alturaMaxima;
                larguraNova = (int) (alturaNova * proporcaoOriginal);
            }


           
            ImageIcon image = new ImageIcon(imagemBD.getImage().getScaledInstance(larguraNova, alturaNova, Image.SCALE_SMOOTH));

          
            telaPerfil.getjLabelFoto().setIcon(image);
      
            telaPerfil.getjTextFieldEmail().setText(pessoa.getEmail());
            telaPerfil.getjTextFieldEndereco().setText(pessoa.getEndereco());
            
            SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
            String formatoBR = formatador.format(pessoa.getDatanasc());
           
            
            
            
            telaPerfil.getjFormattedTextFieldData().setText(formatoBR);
            telaPerfil.getjTextFieldTelefone().setText(pessoa.getTelefone());
            telaPerfil.getjTextFieldCPF().setText(pessoa.getCpf());
        



    
            
}
    
  
       public Pessoa salvar(TelaPerfil telaPerfil) throws ParseException, IOException{
           //salva os dados digitados na tela
           PessoaDAO pessoaDAO= new PessoaDAO();
           Pessoa pessoa = new Pessoa();
           try{
           
          
           
           pessoa.setId(pessoaService.getId());
           pessoa.setNome(telaPerfil.getjTextFieldNome().getText());
           pessoa.setEmail(telaPerfil.getjTextFieldEmail().getText());
           pessoa.setEndereco(telaPerfil.getjTextFieldEndereco().getText());
           pessoa.setCpf(telaPerfil.getjTextFieldCPF().getText());
           pessoa.setTelefone(telaPerfil.getjTextFieldTelefone().getText());
           SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
           Date date = dateFormat.parse(telaPerfil.getjFormattedTextFieldData().getText());
           java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            pessoa.setDatanasc(sqlDate);
           
          
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (imagem!=null){   
        
            ImageIO.write(imagem, "jpg", baos);
      
           byte[] fotoEmBytes = baos.toByteArray();
           pessoa.setFoto(fotoEmBytes);
           
  
           
           }
           
           else{
              pessoa.setFoto(pessoaService.getFoto());   }
        
          }catch(Exception e){
               JOptionPane.showMessageDialog(null, "Não foi possível alterar as informações! Verifique os dados e tente novamente");
               System.out.println(e);
           }
        pessoaDAO.alterar(pessoa);
        
       
        return pessoa;
       }
       
       
       //metodo de escolha da foto a partir do jfilechooser
       public void imagem(){
           
           
         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens (*.bmp, *.png, *.jpg, *.jpeg)", "bmp", "png", "jpg", "jpeg"));
        fileChooser.setDialogTitle("Abrir Imagem");
        fileChooser.showOpenDialog(telaPerfil);//abre o arquivo
        
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

            telaPerfil.getjLabelFoto().setIcon(icon);
            telaPerfil.getjLabelFoto().setHorizontalAlignment(JLabel.CENTER);
            telaPerfil.getjLabelFoto().setVerticalAlignment(JLabel.CENTER);

            imagem = new BufferedImage(larguraNova, alturaNova, BufferedImage.TYPE_INT_RGB);
            imagem.getGraphics().drawImage(icon.getImage(), 0, 0, null);

        }catch(Exception e){
            JOptionPane.showMessageDialog(telaPerfil, "Não obteve o carregamento do arquivo");
        }
     }
       
       }

    
    
