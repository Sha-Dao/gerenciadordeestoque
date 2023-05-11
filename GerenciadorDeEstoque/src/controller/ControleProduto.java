/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Produto;
import model.ProdutoDAO;
import service.ServiceProduto;
import view.TelaCadastrarProduto;
import view.TelaEditarProduto;
import view.TelaListagemProdutos;

/**
 *
 * @author j_ped
 */
public class ControleProduto implements ActionListener{
    
    private ServiceProduto serviceProduto;
    private TelaListagemProdutos telaListagemProdutos;
    private TelaEditarProduto telaEditarProduto;
    private TelaCadastrarProduto telaCadastrarProduto;
    

    public ControleProduto() {
        this.serviceProduto = new ServiceProduto();
        
    }
    
    public void iniciarTelaListagem() {
        this.telaListagemProdutos = new TelaListagemProdutos(this);
    }
    
    public ArrayList<Produto> ListarProdutos(){
       return this.serviceProduto.listar();
      
    }
    
    public void iniciarTelaEditarProduto(Produto produto){
        telaEditarProduto = new TelaEditarProduto(telaListagemProdutos, true, produto);
        telaEditarProduto.getjButtonSalvarEdicao().addActionListener(this);
        telaEditarProduto.getjButtonSelecionarFoto().addActionListener(this);
        
        ImageIcon icon = new ImageIcon(produto.getImagem());
 
        Image imagem = icon.getImage().getScaledInstance(200, -1, Image.SCALE_SMOOTH);
        icon = new ImageIcon(imagem);
        telaEditarProduto.getjLabelFotoProduto().setIcon(icon);
        telaEditarProduto.setVisible(true);
    }
    
    public void iniciarTelaCadastrarProduto(){
        telaCadastrarProduto = new TelaCadastrarProduto(null, true);
        telaCadastrarProduto.getjButtonSelecionarFoto().addActionListener(this);
        telaCadastrarProduto.getjButtonCadastrarProduto().addActionListener(this);
        telaCadastrarProduto.setVisible(true);
    }
    
    
    public void deletarProduto(int IdProduto){
        this.serviceProduto.deletarProduto(IdProduto);
        this.telaListagemProdutos.mostrarProdutos("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        

        if (source instanceof TelaListagemProdutos.JButtonProduto) {
            TelaListagemProdutos.JButtonProduto button = (TelaListagemProdutos.JButtonProduto) source;
            if (button.getTipoButton().equals("editar")){
                iniciarTelaEditarProduto(button.getProduto());
                
            }
            if (button.getTipoButton().equals("deletar")){
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja Realmente deletar esse produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    deletarProduto(button.getProduto().getId());
                } 
            }
        } else{
            if (telaCadastrarProduto != null ){
                if (e.getSource().equals(telaCadastrarProduto.getjButtonSelecionarFoto() )) {
                    this.serviceProduto.selecionarImagemParaCadastrar(telaCadastrarProduto);
                } else{
                    if (e.getSource().equals(telaCadastrarProduto.getjButtonCadastrarProduto())){
                        try {
                            this.serviceProduto.adicionarProduto(telaCadastrarProduto);
                            JOptionPane.showMessageDialog(null, "Produto adicionado!!");
                        } catch (IOException ex) {
                            Logger.getLogger(ControleProduto.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "Não foi possível adicionar o produto!!");
                        }
                    }
                }
                
            }
            if (telaListagemProdutos != null){
                if (e.getSource().equals(telaListagemProdutos.getTextField())){
                String stringPesquisa = telaListagemProdutos.getTextField().getText();
                telaListagemProdutos.mostrarProdutos(stringPesquisa);
                }
            }
            if (telaEditarProduto != null){
                if (e.getSource().equals(telaEditarProduto.getjButtonSalvarEdicao())){
                    try {
                        this.serviceProduto.editarProduto(telaEditarProduto.getProduto());
                    } catch (IOException ex) {
                        Logger.getLogger(ControleProduto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    telaEditarProduto.dispose();
                    if(telaListagemProdutos != null){
                        telaListagemProdutos.mostrarProdutos("");
                    }
                }
                if (e.getSource().equals(telaEditarProduto.getjButtonSelecionarFoto())){
                    this.serviceProduto.selecionarImagemParaEditar(telaEditarProduto);
                }
            }
            
        }  
    }
    
    public void adicionarActionListener(JButton botao) {
        botao.addActionListener(this);
    }
    
    
}
