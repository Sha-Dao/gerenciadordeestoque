
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author j_ped
 */

    
    
public class Conexao {
    private static Connection conexao;
    
    
 public static Connection getConexao(){
     if (conexao==null){
         try{
             conexao= DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciador", "[]", "[]");
         }catch (SQLException e){
             JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados!");
             System.out.println(e);
         }
     }
     return conexao;
         
    }
}



