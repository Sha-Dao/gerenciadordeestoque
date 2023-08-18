
package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Window;
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
    private static File imagemFile = null;

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
    public void adicionarProduto(Produto produto){
        produtoDAO.inserir(produto);
    }
    public File imagem(Window window) throws IOException{
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Imagem");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        java.io.File selectedFile = fileChooser.showOpenDialog(window);
        
        imagemFile = selectedFile;
        
        return selectedFile;
    }
}
