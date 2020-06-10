/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import dao.UsuarioDAO;
import java.util.List;
import model.Usuario;

/**
 *
 * @author Administrador
 */
public class UsuarioFindAllTest {
    
    public static void main(String args[]){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.selectAll();
        
        for(int i = 0; i < usuarios.size(); i++){
            System.out.println("Usuario[" + i + "]: " + usuarios.get(i).getId() + " - " + usuarios.get(i).getNome());
        }
        
    }
    
}
