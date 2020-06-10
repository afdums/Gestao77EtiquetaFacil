/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dums
 */
public class PropriedadeAvulsa {
    private String propriedade;
    private String conteudo;

    public PropriedadeAvulsa(String propriedade, String conteudo) {
        this.propriedade = propriedade;
        this.conteudo = conteudo;
    }

    public PropriedadeAvulsa() {
    }
    
    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    
    
}
