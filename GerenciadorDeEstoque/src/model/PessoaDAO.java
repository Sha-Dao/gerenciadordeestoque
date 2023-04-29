
package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author j_ped
 */
public class PessoaDAO {

    
    public Pessoa inserir (String nome, Date datanasc, String cpf, String endereco, String telefone, String email, String senha){
        String sql = "INSERT INTO `pessoa`(`nome`,`datanasc`,`endereco`,`telefone`) VALUES ( ?, ?, ?, ?, ?, ?, md5(?))";
        Pessoa pessoa = new Pessoa();
        PreparedStatement pst;
       
        
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
    
    
            pst.setString(1, nome);
            pst.setDate(2, datanasc);
            pst.setString(3, cpf);
            pst.setString(4, endereco);
            pst.setString(5, telefone);
            pst.setString(6, email);
            pst.setString (7, senha);
            
            pst.execute();
            pst.close();
            
        } catch (SQLException e){
 
             System.out.println(e);
                       
        }
        
        return pessoa;
           
    }


        public boolean excluir(Pessoa pessoa){
            
        String sql = "DELETE FROM pessoa WHERE id = ?";
        
        PreparedStatement pst;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, pessoa.getId());
            
            pst.execute();
            
            pst.close();       
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        
        return true;
        
        
    
    
    
   }

    
}
