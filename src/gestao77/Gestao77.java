/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestao77;

import controller.FactoryJDBC;
import dao.BancoDadosDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;
import view.TelaEditaConfiguracaoBD;
import view.TelaPrincipal;

/**
 *
 * @author Administrador
 */
public class Gestao77 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.show();
        
        
    }       
    
}
