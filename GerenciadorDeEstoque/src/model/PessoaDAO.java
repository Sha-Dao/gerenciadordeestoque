 
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

    public Pessoa acesso (String email, String senha){
        String sql = "Select * FROM pessoa WHERE email = ? AND senha= md5(?)";
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
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setSenha (rs.getString("senha"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setDatanasc(rs.getDate("datanasc"));
                pessoa.setFoto(rs.getBytes("foto"));
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
        String sql = "INSERT INTO `pessoa`(`nome`,`datanasc`,`cpf`,`endereco`,`telefone`, `email`, `senha`, `foto`) VALUES(?, ?, ?, ?, ?, ?, md5(?),?)";
        PreparedStatement pst;
       
        
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
    
    
            pst.setString(1, pessoa.getNome());
            pst.setDate(2,  pessoa.getDatanasc());
            pst.setString(3, pessoa.getCpf());
            pst.setString(4, pessoa.getEndereco());
            pst.setString(5, pessoa.getTelefone());
            pst.setString(6, pessoa.getEmail());
            pst.setString (7, pessoa.getSenha());
            pst.setBytes(8, pessoa.getFoto());
        
            
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
      public ArrayList<Pessoa> listar(){
        String sql = "SELECT * FROM pessoa ORDER BY nome,cpf";
        
        ArrayList<Pessoa> lista = new ArrayList<>();
        
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setFoto(rs.getBytes("foto"));
                lista.add(pessoa);
            }
            
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return lista;  
    }
    

    }

   
