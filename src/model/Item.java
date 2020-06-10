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
public class Item {
    
    int id;
    String codigo;
    String descricao;
    String itemCliente;
    String ean;
    
    public Item(){

    }

    public Item(String codigo, String descricao, String itemCliente, String ean) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.itemCliente = itemCliente;
        this.ean = ean;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }      

    public String getItemCliente() {
        return itemCliente;
    }

    public void setItemCliente(String itemCliente) {
        this.itemCliente = itemCliente;
    }
    
}
