/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.TelaPrincipal;
/**
 *
 * @author joice
 */
public class ControlePrincipal implements ActionListener{
  
    private TelaPrincipal telaPrincipal;
    
    public ControlePrincipal(){
        telaPrincipal = new TelaPrincipal();
        telaPrincipal.getjButtonListaProdutos().addActionListener(this);
        telaPrincipal.getjButtonAdicionarProduto().addActionListener(this);
        telaPrincipal.getjButtonDadosPessoais().addActionListener(this);
        telaPrincipal.getjButtonSair().addActionListener(this);
        telaPrincipal.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
}
    
}