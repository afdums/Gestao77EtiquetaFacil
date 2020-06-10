/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author andersond
 */
public class HashtagReservada {
    
    private static List<String> hashList = null;
    
    private HashtagReservada(){
        
    }
    
    public static List<String> retornaHashtagTabela(){
        
        if (hashList == null){
            hashList = new ArrayList<String>();
            hashList.add("#codItem"); //Código do Item, deve ser solicitado em tela ou ser importado do arquivo
            hashList.add("#desItem"); //Descrição do item, deve ser solicitado código em tela
            hashList.add("#codCNPJ"); //CNPJ do Emitnete, deve ser solicitado em tela ou ser importado do arquivo            
        }
        
        return hashList; 
        
    }
    
}
