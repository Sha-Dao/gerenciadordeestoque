/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Produto;
import model.ProdutoDAO;
import view.TelaCadastrarProduto;
import view.TelaEditarProduto;

/**
 *
 * @author santo
 */
public class ServiceProduto {

    private ProdutoDAO produtoDAO;
    private BufferedImage imagem;

    public ServiceProduto() {
        this.produtoDAO = new ProdutoDAO();
    }
    
    
    
    public ArrayList<Produto> listar() {
        return this.produtoDAO.listar();
    }

    public void deletarProduto(int IdProduto) {
        Produto produto = new Produto();
        produto.setId(IdProduto);
        boolean result = this.produtoDAO.excluir(produto);
    }
    public void adicionarProduto(TelaCadastrarProduto tela) throws IOException{
        Produto produto = new Produto();
        produto.setNome(tela.getjTextFieldNome().getText());
        produto.setIdTipo(Integer.parseInt(tela.getjTextFieldTipo().getText()));
        produto.setPreco(Double.parseDouble(tela.getjTextFieldPreco().getText()));
        produto.setQuantidade((int)tela.getjSpinnerQuantidade().getValue());
        
        if (imagem == null){
            imagem = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/produto-padrao1.png"));
            Image image = icon.getImage();
            // Obtém a largura e a altura da imagem original
            int larguraOriginal = image.getWidth(null);
            int alturaOriginal = image.getHeight(null);

            int larguraMaxima = 100;
            int alturaMaxima = 100;
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
            imagem.getGraphics().drawImage(icon.getImage(), 0, 0, null);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imagem, "jpg", baos);
        byte[] imagemEmBytes = baos.toByteArray();
        produto.setImagem(imagemEmBytes);
        produtoDAO.inserir(produto);
    }
    
    public void editarProduto(Produto produto) throws IOException{
        if (imagem == null){
            if (produto.getImagem() == null) {
                imagem = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
                ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/produto-padrao.png"));
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
                imagem.getGraphics().drawImage(icon.getImage(), 0, 0, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(imagem, "jpg", baos);
                byte[] imagemEmBytes = baos.toByteArray();
                produto.setImagem(imagemEmBytes);
            }
        } else{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imagem, "jpg", baos);
            byte[] imagemEmBytes = baos.toByteArray();
            produto.setImagem(imagemEmBytes);
        }
        
        produtoDAO.alterar(produto);
    }
    
    public void selecionarImagemParaCadastrar(TelaCadastrarProduto telaCadastrarProduto){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens (*.bmp, *.png, *.jpg, *.jpeg)", "bmp", "png", "jpg", "jpeg"));
        fileChooser.setDialogTitle("Abrir Imagem");
        fileChooser.showOpenDialog(telaCadastrarProduto);//abre o arquivo
        
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

            telaCadastrarProduto.getjLabelImagemProduto().setIcon(icon);
            telaCadastrarProduto.getjLabelImagemProduto().setHorizontalAlignment(JLabel.CENTER);
            telaCadastrarProduto.getjLabelImagemProduto().setVerticalAlignment(JLabel.CENTER);

            imagem = new BufferedImage(larguraNova, alturaNova, BufferedImage.TYPE_INT_RGB);
            imagem.getGraphics().drawImage(icon.getImage(), 0, 0, null);

        }catch(Exception e){
            JOptionPane.showMessageDialog(telaCadastrarProduto, "Não obteve o carregamento do arquivo");
        }
    }
    public void selecionarImagemParaEditar(TelaEditarProduto telaEditarProduto){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens (*.bmp, *.png, *.jpg, *.jpeg)", "bmp", "png", "jpg", "jpeg"));
        fileChooser.setDialogTitle("Abrir Imagem");
        fileChooser.showOpenDialog(telaEditarProduto);//abre o arquivo
        
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

            telaEditarProduto.getjLabelFotoProduto().setIcon(icon);
            telaEditarProduto.getjLabelFotoProduto().setHorizontalAlignment(JLabel.CENTER);
            telaEditarProduto.getjLabelFotoProduto().setVerticalAlignment(JLabel.CENTER);

            imagem = new BufferedImage(larguraNova, alturaNova, BufferedImage.TYPE_INT_RGB);
            imagem.getGraphics().drawImage(icon.getImage(), 0, 0, null);

        }catch(Exception e){
            JOptionPane.showMessageDialog(telaEditarProduto, "Não obteve o carregamento do arquivo");
        }
    }
    
}
