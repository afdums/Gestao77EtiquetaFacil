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
public class LogAlteracao {
    
    int id;
    String login;
    String tabela;
    String data;
    String chave;
    String alteracao;

    public LogAlteracao() {
    }

    public LogAlteracao(int id, String login, String tabela, String data, String chave, String alteracao) {
        this.id = id;
        this.login = login;
        this.tabela = tabela;
        this.data = data;
        this.chave = chave;
        this.alteracao = alteracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(String alteracao) {
        this.alteracao = alteracao;
    }
    
    
    
}
