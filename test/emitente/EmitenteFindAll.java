/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emitente;

import dao.EmitenteDAO;
import java.util.List;
import model.Emitente;

/**
 *
 * @author Administrador
 */
public class EmitenteFindAll {
    
    public static void main(String args[]){
        EmitenteDAO emitenteDAO = new EmitenteDAO();
        List<Emitente> emitentes = emitenteDAO.selectAll();
        
        for(int i = 0; i < emitentes.size(); i++){
            System.out.println("Emitente[" + i + "]: " + emitentes.get(i).getId() + " - " + emitentes.get(i).getCnpj() + " - " + emitentes.get(i).getRazao());            
        }
    }
    
}
