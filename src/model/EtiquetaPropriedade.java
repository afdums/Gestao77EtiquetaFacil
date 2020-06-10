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
public class EtiquetaPropriedade {
    
    int id;
    int idEtiqueta;
    String propriedade;
    String conteudo;
    //String linha;

    public EtiquetaPropriedade() {
    }

    public EtiquetaPropriedade(int idEtiqueta, String propriedade, String conteudo /*, String linha*/) {
        this.idEtiqueta = idEtiqueta;
        this.propriedade = propriedade;
        this.conteudo = conteudo;
        //this.linha = linha;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
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

    /*public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    } */   
    
}
