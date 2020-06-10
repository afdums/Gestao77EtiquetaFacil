/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emitente;

import model.Emitente;
import dao.EmitenteDAO;

/**
 *
 * @author Administrador
 */
public class EmitenteInsertTest {
    
    public static void main(String args[]){
        EmitenteDAO emitenteDAO = new EmitenteDAO();
        
        Emitente emitente = new Emitente("21775027000126", "Dums - Sistemas Inteligentes LTDA ME");
        
        emitenteDAO.insert(emitente);
        
    }
    
}
