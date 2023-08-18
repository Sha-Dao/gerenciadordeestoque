
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import util.AlertUtil;

/**
 *
 * @author j_ped
 */

    
    
public class Conexao {
    private static Connection conexao;
    
    
 public static Connection getConexao(){
     if (conexao==null){
         try{

             conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciador", "root", "#Moriarty4419@");
  System.out.println("Conectou com o banco");
         }catch (SQLException e){
             AlertUtil.show("Conexão com o Banco","Não foi possível conectar ao Banco de Dados!",Alert.AlertType.ERROR); 
             System.out.println(e);
         }
     }
     return conexao;
         
    }
}



