
package service;

import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            listaDados(pessoaService);
        } catch (ParseException ex) {
            Logger.getLogger(ServicePerfil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
  
    public void listaDados(Pessoa pessoa) throws ParseException { 
       telaPerfil.getjTextFieldNome().setText(pessoa.getNome());
       
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
      
            telaPerfil.getjTextFieldEmail().setText(pessoa.getEmail());
            telaPerfil.getjTextFieldEndereco().setText(pessoa.getEndereco());
            
            SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
            String formatoBR = formatador.format(pessoa.getDatanasc());
            System.out.println(formatoBR);
            
            
            
            telaPerfil.getjFormattedTextFieldData().setText(formatoBR);
            telaPerfil.getjTextFieldTelefone().setText(pessoa.getTelefone());
            telaPerfil.getjTextFieldCPF().setText(pessoa.getCpf());
        



    
}
}
    
    
