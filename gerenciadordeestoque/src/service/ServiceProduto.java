
package service;

import java.util.ArrayList;
import model.DevolucaoDAO;
import model.EntradaDAO;
import model.ProdutoDAO;
import model.Produto;
import model.RetiradaDAO;
import model.Devolucao;
import model.Entrada;
import model.Retirada;


public class ServiceProduto {
    ProdutoDAO produtoDAO;
    EntradaDAO entradaDAO;
    DevolucaoDAO devolucaoDAO;
    RetiradaDAO retiradaDAO;

    public ServiceProduto() {
        produtoDAO = new ProdutoDAO();
        devolucaoDAO = new DevolucaoDAO();
        entradaDAO = new EntradaDAO();
        retiradaDAO = new RetiradaDAO();
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
    public void devolucaoProduto(Devolucao devolucao){
        devolucaoDAO.inserir(devolucao);
    }
    public void entradaProduto(Entrada entrada){
        entradaDAO.inserir(entrada);
    }
    public void retiradaProduto(Retirada retirada){
        retiradaDAO.inserir(retirada);
    }
}
