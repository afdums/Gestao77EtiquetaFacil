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
public class Layout {

    int id;
    int idEmitente;
    String codigo;
    int tipo; // 0-Individual / 1-Caixa
    int quantidadeCaixa;
    int comprimentoChave;
    boolean validaImpressaoCaixa; //Lê individuais para liberar impressão da etiqueta da caixa
    String fonteLayout;
    String impressora;
    int pulaEtiqueta;

    public Layout() {

    }

    public Layout(int idEmitente, String codigo, int tipo, int quantidadeCaixa, boolean validaImpressaoCaixa, String fonteLayout, int comprimentoChave) {
        this.idEmitente = idEmitente;
        this.codigo = codigo;
        this.tipo = tipo;
        this.quantidadeCaixa = quantidadeCaixa;
        this.validaImpressaoCaixa = validaImpressaoCaixa;
        this.fonteLayout = fonteLayout;
        this.comprimentoChave = comprimentoChave;
    }

    public Layout(int idEmitente, String codigo, int tipo, String layout) {
        this.idEmitente = idEmitente;
        this.codigo = codigo;
        this.tipo = tipo;
        this.fonteLayout = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdEmitente() {
        return idEmitente;
    }

    public void setIdEmitente(int idEmitente) {
        this.idEmitente = idEmitente;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String retornaTipoToString() {
        if (tipo == 0) {
            return "Individual";
        } else {
            return "Caixa";
        }
    }

    public int getQuantidadeCaixa() {
        return quantidadeCaixa;
    }

    public void setQuantidadeCaixa(int quantidadeCaixa) {
        this.quantidadeCaixa = quantidadeCaixa;
    }

    public boolean isValidaImpressaoCaixa() {
        return validaImpressaoCaixa;
    }

    public void setValidaImpressaoCaixa(boolean validaImpressaoCaixa) {
        this.validaImpressaoCaixa = validaImpressaoCaixa;
    }

    public String getFonteLayout() {
        return fonteLayout;
    }

    public void setFonteLayout(String caminhoLayout) {
        this.fonteLayout = caminhoLayout;
    }

    public String getImpressora() {
        return impressora;
    }

    public void setImpressora(String impressora) {
        this.impressora = impressora;
    }

    public int getPulaEtiqueta() {
        return pulaEtiqueta;
    }

    public void setPulaEtiqueta(int pulaEtiqueta) {
        this.pulaEtiqueta = pulaEtiqueta;
    }

    public int getComprimentoChave() {
        return comprimentoChave;
    }

    public void setComprimentoChave(int comprimentoChave) {
        this.comprimentoChave = comprimentoChave;
    }    

    public String toString() {
        return getCodigo();
    }

}
