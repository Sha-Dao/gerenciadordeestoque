
package service;

import java.util.ArrayList;
import model.ProdutoDAO;
import model.Produto;


public class ServiceProduto {
    ProdutoDAO produtoDAO;

    public ServiceProduto() {
        produtoDAO = new ProdutoDAO();
    }

    public Produto getProdutoById(int produtoId) {
        return produtoDAO.getById(produtoId);
    }

    public ArrayList<Produto> listarProdutos() {
        return produtoDAO.listar();
        
    }

    public void editarProduto(Produto produto) {
        produtoDAO.alterar(produto);
    }
    
    public void deletarProduto(Produto produto){
        produtoDAO.excluir(produto);
    }
    
}
