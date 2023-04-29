/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author j_ped
 */
public class UsuarioDAO {
    
        public Usuario acesso (String email, String senha){
        String sql = "Select * FROM usuario WHERE login = ? AND senha= md5(?)";
        Usuario usuario = new Usuario();
        
        PreparedStatement pst;
        ResultSet rs;
        
        try{
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, senha);
            rs = pst.executeQuery();
                    
            while (rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setSenha (rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
            }     
            rs.close();
            pst.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Login ou senha incorretos!");
             System.out.println(e);
                       
        }
        return usuario;
           
    }
    
}
