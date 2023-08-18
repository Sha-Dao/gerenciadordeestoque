
package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import util.AlertUtil;


public class FornecedorDAO {
    
    public void inserirFornecedor(Fornecedor fornecedor){
        String sql = "INSERT INTO `fornecedor`(`nome`,`cnpj`, `idTipo`, `ramoAtiv`, `endereco`, "
                + "`bairro`, `cidade`, `uf`, `dataCad`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst;
  
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
           
            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getCnpj());
            pst.setInt(3, fornecedor.getIdTipo());
            pst.setString(4, fornecedor.getRamo());
            pst.setString(5, fornecedor.getEndereco());
            pst.setString(6, fornecedor.getBairro());
            pst.setString(7, fornecedor.getCidade());
            pst.setString(8, fornecedor.getUf());
            pst.setDate(9, fornecedor.getData());
            
            
            pst.execute();
    
            pst.close();
            AlertUtil.show("Cadastro concluído!", "Seu fornecedor foi cadastrado!", 
                     Alert.AlertType.CONFIRMATION);
            
        } catch (SQLException e){
            AlertUtil.show("Erro!", "Ocorreu um erro ao cadastrar seu fornecedor", 
                     Alert.AlertType.CONFIRMATION);
             System.out.println(e);
             
                       
        }
        
    }
}
