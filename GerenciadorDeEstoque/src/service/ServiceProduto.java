/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import model.ProdutoDAO;
import model.Produto;

/**
 *
 * @author santo
 */
public class ServiceProduto {
    ProdutoDAO produtoDAO;

    public ServiceProduto() {
        this.produtoDAO = new ProdutoDAO();
    }
    
    

    public Produto getProdutoById(int produtoId) {
        System.out.println(produtoDAO.listar());
        return produtoDAO.getById(produtoId);
    }

    public ArrayList<Produto> listarProdutos() {
        return produtoDAO.listar();
        
    }

    public void EditarProduto(Produto produto) {
        produtoDAO.alterar(produto);
    }
    
}
