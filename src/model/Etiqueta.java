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
public class Etiqueta {
    
    int id;
    int idLayout;
    int idEmitente;
    String conteudo;
    String chaveUnica;
    int situacao; //1-Não Impresa / 2-Impressa / 3-Relacionada a Caixa

    public Etiqueta() {
    }
    
    public Etiqueta(int idLayout, String conteudo, int idEmitente, String chaveUnica) {
        this.idLayout = idLayout;
        this.conteudo = conteudo;
        this.idEmitente = idEmitente;
        this.chaveUnica = chaveUnica;
        this.situacao = 1;        
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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }        
    
    public String retornaSituacaoString(){
        String retorno;
        switch(situacao){
            case 1:
                retorno = "Não Impressa";
                break;
            case 2:
                retorno = "Impressa";
                break;
            case 3:
                retorno = "Embalada";
                break;
            default:
                retorno = "Não Especificada";
        }
        
        return retorno;
    }

    public int getIdEmitente() {
        return idEmitente;
    }

    public void setIdEmitente(int idEmitente) {
        this.idEmitente = idEmitente;
    }

    public String getChaveUnica() {
        return chaveUnica;
    }

    public void setChaveUnica(String chaveUnica) {
        this.chaveUnica = chaveUnica;
    }

    @Override
    public String toString() {
        return "Etiqueta{" + "id=" + id + ", idLayout=" + idLayout + ", idEmitente=" + idEmitente + ", conteudo=" + conteudo + ", chaveUnica=" + chaveUnica + ", situacao=" + situacao + '}';
    }        
    
}
