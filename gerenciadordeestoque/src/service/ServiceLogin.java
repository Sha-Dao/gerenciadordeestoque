


/*
Service a ser desenvolvido
 */
package service;


import javax.swing.JOptionPane;
import model.Pessoa;
import model.PessoaDAO;


public class ServiceLogin {
    

    private PessoaDAO pessoaDAO;
    private Pessoa pessoaLogin;

    public Pessoa getPessoaLogin() {
        return pessoaLogin;
    }

    public void setPessoaLogin(Pessoa pessoaLogin) {
        this.pessoaLogin = pessoaLogin;
    }

    
    public boolean entrar(String email, String senha) {
       
        //atribui o valor retornado por pessoaDAO a uma varivael pessoaLogin
       pessoaLogin = pessoaDAO.acesso(email, senha);
        
       //checa se pessoaLogin recebeu algum valor do id, permitindo ou não o acesso
        if (pessoaLogin.getId() > 0) {
      
            return true;
          
        
        }else{
            return false;

            }
    }
    
    
    
    
    
}
