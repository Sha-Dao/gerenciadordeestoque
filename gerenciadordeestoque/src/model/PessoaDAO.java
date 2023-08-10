 
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

     //verifica se existe determinado usuario no banco e retorna todos seus dados se for
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
           
             System.out.println(e);
                       
        }
        return pessoa;
    }
    public boolean checarUsuario (String email) throws SQLException{
        String sql = "Select count(*) FROM pessoa WHERE email LIKE ?";
        PreparedStatement pst;
        ResultSet rs;
        int check = 0;
       
        
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            while (rs.next()){
            check = rs.getInt("count(*)");
            }
            rs.close();
            pst.close();
            
            if (check>0){
                return false;
            }else{
                return true;
            }
            }
        catch (Exception e){
            System.out.println(e);
            return false;   
                }
        
    
    }
    //cadastro de pessoas
    
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

    //exclusao da pessoa que estiver o id digitado
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
     
     //lista os dados de pessoa do Banco de Dados
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
      
      //atualiza tudo menos a senha do usuario na telaPerfil
        public void alterar(Pessoa pessoa){
            String sql = "UPDATE pessoa SET nome = ?, email = ?, endereco = ?, cpf = ?, telefone = ?, datanasc = ?, foto = "
                    + "? WHERE id = ?";
        
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEmail());
            pst.setString(3, pessoa.getEndereco());
            pst.setString(4, pessoa.getCpf());
            pst.setString(5, pessoa.getTelefone());
            pst.setDate(6, pessoa.getDatanasc());
            pst.setBytes(7, pessoa.getFoto());
      
            pst.setInt(8, pessoa.getId());
            pst.execute();
            pst.close();
            
            JOptionPane.showMessageDialog(null, "Dados atualizados!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar os dados!");
            System.out.println(ex);
        }
        }
        
        
        //altera a senha digitada no TelaTrocaSenha
         public void alterarSenha(Pessoa pessoa){
             String sql = "UPDATE pessoa SET senha = md5(?) WHERE email LIKE ?";
             PreparedStatement pst;
             
             try {
                pst = Conexao.getConexao().prepareStatement(sql);
                
                pst.setString(1, pessoa.getSenha());
                pst.setString(2, pessoa.getEmail());
               
                pst.execute();
                pst.close();
                
     
             }catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Não foi possível atualizar a senha!");
                 System.out.println(ex);
                
                 
                 
             
             
         
         }
            
        
         }
         //verifica a senha a partir de parametros passados. OBS: em andamento...
         
         public boolean verificarSenhaAntiga(String senhaNova, int id) {
             Pessoa pessoa = new Pessoa();
                String sql = "SELECT * FROM pessoa WHERE senha = md5(?) AND id = ?";
                PreparedStatement pst;

                try {
                    pst = Conexao.getConexao().prepareStatement(sql);
                    pst.setString(1, senhaNova);
                    pst.setInt(2, id);
                    ResultSet rs = pst.executeQuery();
                    pst.close();
                    while (rs.next()){
                        
                        pessoa.setId(id);
                    
                    }
                    if (pessoa.getId()>0){
                        return true;
                    }
                    else{
                        return false;
                    }
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao verificar senha antiga!");
                    System.out.println(ex);
                }
                return false;
            }


    }

   
