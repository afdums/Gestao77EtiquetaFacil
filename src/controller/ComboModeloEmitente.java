/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.AbstractList;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import model.Emitente;

/**
 *
 * @author andersond
 */
public class ComboModeloEmitente extends AbstractList implements ComboBoxModel{
    
    private ArrayList<Emitente> lista;
    private Emitente selecionado;
    
    public ComboModeloEmitente(ArrayList<Emitente> lista){
        this.lista = lista;
    }

    @Override
    public Object get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        return lista.size();        
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selecionado = (Emitente) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selecionado;
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
    
    public int getIdObjetoSelecionado(){
        return selecionado.getId();
    }
    
    
}
