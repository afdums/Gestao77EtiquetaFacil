/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PermissaoDAO;

/**
 *
 * @author dums
 */
public class PermissaoAcesso {
    
    public static synchronized boolean isLiberado(String login, String funcao){
        
        boolean liberado = false;
        
        for(String permissao : VariavelGlobal.getInstancec().getPermissoesUsuario()){
            if(permissao.equals(funcao))
                liberado = true;
        }
        
        /*PermissaoDAO permissaoDAO = new PermissaoDAO();
        boolean liberado = permissaoDAO.isLiberado(login, funcao);*/
        
        return liberado;        
        
    }
    
}
