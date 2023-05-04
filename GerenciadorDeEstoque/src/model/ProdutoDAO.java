/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author j_ped
 */
public class ProdutoDAO {
    
    public void inserir(Produto produto){
        String sql = "INSERT INTO `produto`(`nome`,`idtipo`, `quantidade`, `preco`) VALUES (?, ?, ?, ?)";
        PreparedStatement pst;
  
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
           
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getIdTipo());
            pst.setInt(3, produto.getQuantidade());
            pst.setDouble(4, produto.getPreco());
            
            
            pst.execute();
    
            pst.close();
            
        } catch (SQLException e){
 
             System.out.println(e);
                       
        }
        
    }
    
    public void alterar(Produto produto){
        
        String sql = "UPDATE produto SET nome = ?, idtipo = ?, quantidade = ?, preco = ? WHERE id = ?";
        
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getIdTipo());
            pst.setInt(3, produto.getQuantidade());
            pst.setDouble(4, produto.getPreco());
            pst.setInt(5, produto.getId());
            pst.execute();
            pst.close();                
        } catch (SQLException ex) {
            System.out.println(ex);
        }
                      
        
    }
    
     public boolean excluir(Produto produto){
        String sql = "DELETE FROM produto WHERE id = ?";
        
        PreparedStatement pst;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, produto.getId());
            pst.execute();
            pst.close();                
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        
        return true;
    }
     
     public ArrayList<Produto> listar(){
        String sql = "SELECT * FROM produto ORDER BY nome";
        
        ArrayList<Produto> lista = new ArrayList<>();
        
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setIdTipo(rs.getInt("tipo"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                //produto.setImagem(rs.getBlob("imagem")));

                
                lista.add(produto);
            }
            
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return lista;  
    }
    
}
