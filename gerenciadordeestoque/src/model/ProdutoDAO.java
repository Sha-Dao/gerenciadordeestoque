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
import javafx.scene.control.Alert;
import util.AlertUtil;

/**
 *
 * @author j_ped
 */
public class ProdutoDAO {
    
    public void inserir(Produto produto){
        String sql = "INSERT INTO `produto`(`nome`,`idtipo`, `quantidade`, `preco`, `imagem`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst;
  
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
           
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getIdTipo());
            pst.setInt(3, produto.getQuantidade());
            pst.setDouble(4, produto.getPreco());
            pst.setBytes(5, produto.getImagem());
            
            
            pst.execute();
    
            pst.close();
            
        } catch (SQLException e){
 
             System.out.println(e);
                       
        }
        
    }
    
    public void alterar(Produto produto){
        
        String sql = "UPDATE produto SET nome = ?, idtipo = ?, quantidade = ?, preco = ?, imagem = ? WHERE id = ?";
        
        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getIdTipo());
            pst.setInt(3, produto.getQuantidade());
            pst.setDouble(4, produto.getPreco());     
            pst.setBytes(5, produto.getImagem());
            pst.setInt(6, produto.getId());
            
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
        
        PreparedStatement  pst;
        ResultSet rs;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setIdTipo(rs.getInt("idtipo"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setImagem(rs.getBytes("imagem"));

                
                lista.add(produto);
            }
            
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return lista;  
    }
    public ArrayList <TipoProduto> tipoProduto() throws SQLException{
        String sql = "SELECT * FROM tipo_produto ORDER BY id";
        
        ArrayList <TipoProduto> listaTipo = new ArrayList<>();
        
        PreparedStatement  pst;
        ResultSet rs;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                TipoProduto tipoProduto = new TipoProduto();
                tipoProduto.setId(rs.getInt("id"));
                tipoProduto.setTipoProduto(rs.getString("nome"));

                
                listaTipo.add(tipoProduto);
            }
            
            rs.close();
            pst.close();
            
             } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listaTipo;
    
    
    }
    
    public void adicionarFornecedor (Fornecedor fornecedor){
        String sql = "INSERT INTO `fornecedor`(`nome`,`idtipo`) VALUES (?, ?)";
        PreparedStatement pst;
  
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
           
            pst.setString(1, fornecedor.getNome());
            pst.setInt(2, fornecedor.getIdTipo());
            
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
    public Produto getById(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        PreparedStatement pst;
        ResultSet rs;
        Produto produto = null;

        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setIdTipo(rs.getInt("idtipo"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setImagem(rs.getBytes("imagem"));
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return produto;
    }
}
