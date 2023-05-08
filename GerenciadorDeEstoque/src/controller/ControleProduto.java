/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
    
    public void iniciarTelaEditarProduto(int IdProduto){
        telaEditarProduto = new TelaEditarProduto(telaListagemProdutos, true, IdProduto);
        telaEditarProduto.setVisible(true);
    }
    
    public void iniciarTelaCadastrarProduto(){
        telaCadastrarProduto = new TelaCadastrarProduto(null, true);
        telaCadastrarProduto.getjButtonSelecionarFoto().addActionListener(this);
        telaCadastrarProduto.setVisible(true);
    }
    
    
    public void deletarProduto(int IdProduto){
        this.serviceProduto.deletarProduto(IdProduto);
        this.telaListagemProdutos.mostrarProdutos();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        


        
        if (source instanceof TelaListagemProdutos.JButtonProduto) {
            TelaListagemProdutos.JButtonProduto button = (TelaListagemProdutos.JButtonProduto) source;
            if (button.getTipoButton().equals("editar")){
                iniciarTelaEditarProduto(button.getProdutoId());
                
            }
            if (button.getTipoButton().equals("deletar")){
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja Realmente deletar esse produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    deletarProduto(button.getProdutoId());
                } 
            }
        } else{
            if (e.getSource().equals(telaCadastrarProduto.getjButtonSelecionarFoto())) {
                serviceProduto.selecionarImagem(telaCadastrarProduto);
            }
        }  
    }
    
    public void adicionarListener(JButton botao) {
        botao.addActionListener(this);
    }
    
}
