/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.ServicePerfil;

import view.TelaLogin;
import view.TelaPerfil;

/**
 *
 * @author j_ped
 */
public class ControlePerfil implements ActionListener{
    private final TelaPerfil telaPerfil;
    private ServicePerfil servicePerfil;

    public ControlePerfil() {
        
        telaPerfil = new TelaPerfil(null, true);
        
        telaPerfil.setVisible(true);
        servicePerfil.listar();
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
