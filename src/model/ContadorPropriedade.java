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
public class ContadorPropriedade {
    
    int id;
    int idLayout;
    String propriedade;
    String conteudo;
    int contador;
    
    public ContadorPropriedade() {
    }

    public ContadorPropriedade(int idLayout, String propriedade, String conteudo, int contador) {
        this.idLayout = idLayout;
        this.propriedade = propriedade;
        this.conteudo = conteudo;
        this.contador = contador;
    }
    
    public ContadorPropriedade(int idLayout, String propriedade, String conteudo) {
        this.idLayout = idLayout;
        this.propriedade = propriedade;
        this.conteudo = conteudo;
        this.contador = 0;
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
    
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    } 

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }    
    
}
