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
public class UsuarioInsertTest {
    
    public static void main(String args[]){
        
        Usuario usuario = new Usuario("anderson",
                                      "Anderson Fabiano Dums",
                                      "anderson@dums.com.br",
                                      "teste123",
                                      true);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        usuarioDAO.insert(usuario);
        
        
    }
    
}
