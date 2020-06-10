/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author andersond
 */
public class EtiquetaPropriedadeTela {
    
    private String propriedade;
    private String conteudo;

    public EtiquetaPropriedadeTela() {
    }

    public EtiquetaPropriedadeTela(String propriedade, String conteudo) {
        this.propriedade = propriedade;
        this.conteudo = conteudo;
    }
    
    

    /**
     * @return the propriedade
     */
    public String getPropriedade() {
        return propriedade;
    }

    /**
     * @param propriedade the propriedade to set
     */
    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    /**
     * @return the conteudo
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * @param conteudo the conteudo to set
     */
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
}
