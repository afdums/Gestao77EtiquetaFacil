/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.HashtagReservada;

/**
 *
 * @author andersond
 */
public class LayoutPropriedade {
    
    int id;
    int idLayout;
    String propriedade;
    int origem; // 0-Tabela / 1-Tela / 2-Arquivo
    int coluna; // Se do arquivo, qual coluna popula  
    boolean chaveUnica = false;
    int mantem;
    int incremental;

    public LayoutPropriedade() {
    }

    public LayoutPropriedade(int idLayout, String propriedade, int origem, int coluna, boolean chaveUnica, int mantem, int incremental) {
        this.idLayout = idLayout;
        this.propriedade = propriedade;
        this.origem = origem;
        this.coluna = coluna;
        this.chaveUnica = chaveUnica;
        this.mantem = mantem;
        this.incremental = incremental;
    }

    public LayoutPropriedade(String propriedade) {
        this.propriedade = propriedade;
        this.origem = 0;
        this.chaveUnica = false;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLayout() {
        return idLayout;
    }

    public void setIdLayout(int idLayout) {
        this.idLayout = idLayout;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public int getOrigem() {
        return origem;
    }

    public void setOrigem(int origem) {
        this.origem = origem;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    public String retornaOrigemString(){
        String retorno = "";
        switch(origem){
            case 0:
                retorno = "Não Especificado";            
                break;
            case 1:
                retorno = "Arquivo - Col " + coluna;
                break; 
            case 2:
                retorno = "Tela";
                break;            
            case 3:
                retorno = "Tabela";
                break;
            case 4:
                retorno = "Sistema";
                break;
            default:
                retorno = "Não Especificado";
        }
        
        return retorno;
        
    }

    public boolean isChaveUnica() {
        return chaveUnica;
    }
    
    public String retornaChaveUnica(){
        if(chaveUnica)
            return "Sim";
        else
            return "Não";
    }

    public void setChaveUnica(boolean chaveUnica) {
        this.chaveUnica = chaveUnica;
    }        
    
    public int getMantem() {
        return this.mantem;
    }
    
    public void setMantem(int mantem) {
        this.mantem = mantem;
    }
    
    public int getIncremental() {
        return this.incremental;
    }
    
    public void setIncremental(int incremental) {
        this.incremental = incremental;
    }
    
    
    
    public int validaOrigem(){
        
        int origemAux = 0;
        
        try{
            origemAux = HashtagReservada.retornaHashtagTabela().indexOf(propriedade);
            if(origemAux >= 0)
                origemAux = 3; //Tabela
            else
                origemAux = 0;
        }catch(NullPointerException nullPointerException){
            origemAux = 0;
        }
                
        if(origemAux == 0){ //Testa demais validações
            
        }
        
                  
        return origemAux;
        
    }
    
}
