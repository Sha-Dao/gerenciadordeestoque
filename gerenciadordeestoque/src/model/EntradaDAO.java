/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author santo
 */

public class EntradaDAO {
    
    public void inserir(Entrada entrada){
        String sql = "INSERT INTO `entrada`(`idproduto`, `quantidade`, `data`) VALUES (?, ?, ?)";
        PreparedStatement pst;
  
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
           
            pst.setInt(1, entrada.getIdProduto());
            pst.setInt(2, entrada.getQuantidade());
            pst.setDate(3, entrada.getData());

            
            pst.execute();
    
            pst.close();
            
        } catch (SQLException e){
 
             System.out.println(e);
                       
        }
        
    }
    public List<Entrada> listar() {
        List<Entrada> entradas = new ArrayList<>();
        String sql = "SELECT * FROM `produto`";
        
        try {
            PreparedStatement pst = Conexao.getConexao().prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idProduto = resultSet.getInt("idproduto");
                int quantidade = resultSet.getInt("quantidade");
                java.sql.Date data = resultSet.getDate("data");
                
                Entrada entrada = new Entrada();
                entrada.setId(id);
                entrada.setIdProduto(idProduto);
                entrada.setQuantidade(quantidade);
                entrada.setData(data);
                entradas.add(entrada);
            }
            
            resultSet.close();
            pst.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return entradas;
    }
}
