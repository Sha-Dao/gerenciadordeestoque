 
package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author j_ped
 */
public class PessoaDAO {

    public Pessoa acesso (String email, String senha){
        String sql = "Select * FROM usuario WHERE login = ? AND senha= md5(?)";
        Pessoa pessoa = new Pessoa();
        
        PreparedStatement pst;
        ResultSet rs;
        
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, senha);
            rs = pst.executeQuery();
                    
            while (rs.next()){
                pessoa.setId(rs.getInt("id"));
                pessoa.setSenha (rs.getString("senha"));
                pessoa.setEmail(rs.getString("email"));
            }     
            rs.close();
            pst.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Login ou senha incorretos!");
             System.out.println(e);
                       
        }
        return pessoa;
    }
    
    public void inserir (Pessoa pessoa){
        String sql = "INSERT INTO `pessoa`(`nome`,`datanasc`,`endereco`,`telefone`, `email`, `senha`) VALUES ( ?, ?, ?, ?, ?, ?, md5(?))";
        PreparedStatement pst;
       
        
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
    
    
            pst.setString(1, pessoa.getNome());
            pst.setDate(2, pessoa.getDatanasc());
            pst.setString(3, pessoa.getCpf());
            pst.setString(4, pessoa.getEndereco());
            pst.setString(5, pessoa.getTelefone());
            pst.setString(6, pessoa.getEmail());
            pst.setString (7, pessoa.getSenha());
            
            pst.execute();
            pst.close();
            
        } catch (SQLException e){
 
             System.out.println(e);
                       
        }
           
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
