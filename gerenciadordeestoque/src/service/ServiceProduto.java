
package service;

import java.util.ArrayList;
import model.ProdutoDAO;
import model.Produto;


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
