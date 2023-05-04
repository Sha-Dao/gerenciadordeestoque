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
import model.Produto;
import model.ProdutoDAO;
import view.TelaListagemProdutos;

/**
 *
 * @author j_ped
 */
public class ControleProduto implements ActionListener{
    
    private ProdutoDAO produtoDAO;
    private TelaListagemProdutos telaListagemProdutos;
    

    public ControleProduto() {
        this.produtoDAO = new ProdutoDAO();
        this.telaListagemProdutos = new TelaListagemProdutos(this);

        
    }
    
    public ArrayList<Produto> ListarProdutos(){
       return this.produtoDAO.listar();
      
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
