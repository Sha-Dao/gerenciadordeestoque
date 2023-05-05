
package service;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Icon;
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
    private Pessoa pessoaService;
   
    

    public ServicePerfil(TelaPerfil telaPerfil, Pessoa pessoa) {
        this.pessoaService=pessoa;
        this.telaPerfil =  telaPerfil;
        this.pessoaDAO = new PessoaDAO();
        listaDados(pessoaService);

    }
    
    
  
    public void listaDados(Pessoa pessoa) { 
       telaPerfil.getjTextFieldNome().setText(pessoa.getEmail());
       
       //adicionando imagem redimensionada
            byte[] imagemBytes = pessoa.getFoto();
            ImageIcon imagemBD = new ImageIcon(imagemBytes);
            
            
           
           
            int larguraOriginal = imagemBD.getIconWidth();
            int alturaOriginal = imagemBD.getIconWidth();

            int larguraMaxima = 200;
            int alturaMaxima = 200;
            double proporcaoOriginal = (double) larguraOriginal / alturaOriginal;

            int larguraNova = larguraMaxima;
            int alturaNova = (int) (larguraNova / proporcaoOriginal);

            if (alturaNova < alturaMaxima) {
                alturaNova = alturaMaxima;
                larguraNova = (int) (alturaNova * proporcaoOriginal);
            }


           
            ImageIcon image = new ImageIcon(imagemBD.getImage().getScaledInstance(larguraNova, alturaNova, Image.SCALE_SMOOTH));

          
            telaPerfil.getjLabelFoto().setIcon(image);
         
        



    
}
}
    
    
