
package service;

import model.Fornecedor;
import model.ProdutoDAO;

/**
 *
 * @author j_ped
 */
public class ServiceFornecedor {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private Fornecedor fornecedor = new Fornecedor();
    
    
    public void adicionarForn (String nome, int idTipo){
        fornecedor.setNome(nome);
        fornecedor.setIdTipo(idTipo);
        
        produtoDAO.adicionarFornecedor(fornecedor);
        
    
    
    }
    
}
