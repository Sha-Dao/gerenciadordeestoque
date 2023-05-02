/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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



    public ServicePessoa(TelaCadastro tela) {
 
        this.pessoaDAO = new PessoaDAO();
        this.telaPessoa = tela;

    }
    public void cadastrar() {
       
        Pessoa pessoa = new Pessoa();
        
        

            pessoa.setNome(telaPessoa.getjTextFieldNome().getText());
            pessoa.setEmail(telaPessoa.getjTextFieldEmail().getText());
            pessoa.setEndereco(telaPessoa.getjTextFieldEndereco().getText());
            pessoa.setCpf(telaPessoa.getjFormattedTextFieldCPF().getText());
            pessoa.setTelefone(telaPessoa.getjTextFieldTelefone().getText());
            pessoa.setSenha(telaPessoa.getjPasswordFieldSenha().getText());
            
            if(this.imagem != null){
                pessoa.setFoto(this.telaPessoa.getLinkImagem());
                this.salvarImagem();
            }

            pessoaDAO.inserir(pessoa);
            
     
    }
    
     public void imagem(){
         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens (*.bmp, *.png, *.jpg)", "bmp", "png", "jpg"));
        fileChooser.setDialogTitle("Abrir Imagem");
        fileChooser.showOpenDialog(telaPessoa);//abre o arquivo
        
        File file = fileChooser.getSelectedFile();//abre o arquivo selecionados
		
        try{	
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            icon.setImage(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
            telaPessoa.getjLabelImagem().setIcon(icon);            
            imagem = new BufferedImage(120, 120, BufferedImage.TYPE_INT_RGB);
            imagem.getGraphics().drawImage(icon.getImage(), 0, 0, null);
                  
        }catch(Exception e){
            JOptionPane.showMessageDialog(telaPessoa, "Não obteve o carregamento do arquivo");
        }
         
     }
      private boolean salvarImagem(){
        
        try {
            File arquivo = new File(telaPessoa.getLinkImagem());
            ImageIO.write(imagem, "jpg", arquivo);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(telaPessoa, "Não foi possível enviar a imagem");
            return false;
        }
      }
}
            
        
    

