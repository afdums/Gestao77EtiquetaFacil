/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PermissaoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dums
 */
public class VariavelGlobal {
    
    private static VariavelGlobal variavelGlobal;
    
    String usuarioLogado;
    
    List<String> permissoes = new ArrayList<>();
    
    private VariavelGlobal(){
        
    }
    
    
    public static synchronized VariavelGlobal getInstancec(){
        if(variavelGlobal == null)
            variavelGlobal = new VariavelGlobal();
        
        return variavelGlobal;
        
    }
    
    public void setUsuarioLogado(String usuarioLogado){
        this.usuarioLogado = usuarioLogado;
        PermissaoDAO permissaoDAO = new PermissaoDAO();
        permissoes = permissaoDAO.selectAll(usuarioLogado);
    }
    
    public String getUsuarioLogado(){
        return usuarioLogado;
    }
    
    public List<String> getPermissoesUsuario(){
        return permissoes;
    }
    
}
