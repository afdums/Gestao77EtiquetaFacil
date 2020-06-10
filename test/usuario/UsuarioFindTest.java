/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import dao.UsuarioDAO;
import model.Usuario;

/**
 *
 * @author Administrador
 */
public class UsuarioFindTest {
    
    public static void main(String args[]){
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        Usuario usuario = (Usuario) usuarioDAO.select(6);
        
        System.out.println("Usuario: " + usuario.getEmail());
        
    }
    
}
