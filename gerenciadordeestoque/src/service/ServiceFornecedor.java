
package service;

import java.sql.Date;
import java.time.LocalDate;
import model.Fornecedor;
import model.FornecedorDAO;
import model.ProdutoDAO;

/**
 *
 * @author j_ped
 */
public class ServiceFornecedor {
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private Fornecedor fornecedor = new Fornecedor();
    
    
    public void adicionarForn (String nome, String endereco, String cnpj, String 
            ramo, String cidade, String bairro, String uf, int idTipo){
        fornecedor.setNome(nome);
        fornecedor.setEndereco(endereco);
        fornecedor.setCnpj(cnpj);
        fornecedor.setRamo(ramo);
        fornecedor.setCidade(cidade);
        fornecedor.setBairro(bairro);
        fornecedor.setUf(uf);
        
        LocalDate dataLocal =  LocalDate.now();
        Date sqlDate = Date.valueOf(dataLocal);
        
        fornecedor.setData(sqlDate);
        fornecedor.setIdTipo(idTipo);
        
        fornecedorDAO.inserirFornecedor(fornecedor);
        
    
    
    }
    
}
