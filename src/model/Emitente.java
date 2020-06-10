/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Administrador
 */
public class Emitente {
    
    private int id;
    private String cnpj;
    private String razao;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String inscricaoMunicipal;
    private String inscricaoEstadual;
    private String emailRespFinanceiro;
    private String foneRespFinanceiro;
    private int tipo; /*1 - Cliente, 2 - Fornecedor, 3-Ambos*/
    
    public Emitente(){
        
    }

    public Emitente(String cnpj, String razao, String rua, int numero, String bairro, String cidade, String uf, String cep, String inscricaoMunicipal, String inscricaoEstadual, String emailRespFinanceiro, String foneRespFinanceiro, int tipo) {
        this.cnpj = cnpj;
        this.razao = razao;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.inscricaoEstadual = inscricaoEstadual;
        this.emailRespFinanceiro = emailRespFinanceiro;
        this.foneRespFinanceiro = foneRespFinanceiro;
        this.tipo = tipo;
    }

    public Emitente(String cnpj, String razao, String cidade, String uf) {
        this.cnpj = cnpj;
        this.razao = razao;
        this.rua = "";
        this.numero = 0;
        this.bairro = "";
        this.cidade= cidade;
        this.uf = uf;
        this.cep = "";
        this.inscricaoMunicipal = "";
        this.inscricaoEstadual = "";
        this.emailRespFinanceiro = "";
        this.foneRespFinanceiro ="";
        this.tipo = 3; /*Ambos*/
    }
    
    public Emitente(String cnpj, String razao) {
        this.cnpj = cnpj;
        this.razao = razao;
        this.tipo = 3; /*Ambos*/
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getEmailRespFinanceiro() {
        return emailRespFinanceiro;
    }

    public void setEmailRespFinanceiro(String emailRespFinanceiro) {
        this.emailRespFinanceiro = emailRespFinanceiro;
    }

    public String getFoneRespFinanceiro() {
        return foneRespFinanceiro;
    }

    public void setFoneRespFinanceiro(String foneRespFinanceiro) {
        this.foneRespFinanceiro = foneRespFinanceiro;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public String toString(){
        return getRazao();
    }
    
}
