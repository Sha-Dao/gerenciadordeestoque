
package service;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import model.Pessoa;
import model.PessoaDAO;
import model.Produto;
import view.TelaPerfil;

/**
 *
 * @author j_ped
 */
public class ServicePerfil {
    
    private TelaPerfil telaPerfil;
    private PessoaDAO pessoaDAO;

    public ServicePerfil(TelaPerfil telaPerfil) {
        this.telaPerfil = telaPerfil;
        this.pessoaDAO = new PessoaDAO();
    }

    
    
    
    
     public void listar() {
        listaDados(pessoaDAO.listar());
    }
    
    private void listaDados(ArrayList<Pessoa> listaPessoa) { 
        Pessoa pessoa = new Pessoa();
        System.out.println(listaPessoa.get(0).getFoto());
        
      
        byte[] imagemBytes =listaPessoa.get(0).getFoto();;
        // Cria um objeto ImageIcon a partir dos bytes da imagem
        ImageIcon imagemIcon = new ImageIcon(imagemBytes);
        // Define o ícone do JLabel "jlabelFoto" da telaPerfil com o ImageIcon criado
        telaPerfil.getjLabelFoto().setIcon(imagemIcon);
    }
            
        
    }
    
    
