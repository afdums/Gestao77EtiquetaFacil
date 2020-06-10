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
public class EtiquetaCaixa {
    
    private int idEtiquetaCaixa;
    private int idCaixa;
    private int idIndividual;

    public EtiquetaCaixa() {
    }

    public EtiquetaCaixa(int idEtiquetaCaixa, int idCaixa, int idIndividual) {
        this.idEtiquetaCaixa = idEtiquetaCaixa;
        this.idCaixa = idCaixa;
        this.idIndividual = idIndividual;
    }

    public EtiquetaCaixa(int idCaixa, int idIndividual) {
        this.idCaixa = idCaixa;
        this.idIndividual = idIndividual;
    }
    
    
    

    /**
     * @return the idEtiquetaCaixa
     */
    public int getIdEtiquetaCaixa() {
        return idEtiquetaCaixa;
    }

    /**
     * @param idEtiquetaCaixa the idEtiquetaCaixa to set
     */
    public void setIdEtiquetaCaixa(int idEtiquetaCaixa) {
        this.idEtiquetaCaixa = idEtiquetaCaixa;
    }

    /**
     * @return the idCaixa
     */
    public int getIdCaixa() {
        return idCaixa;
    }

    /**
     * @param idCaixa the idCaixa to set
     */
    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    /**
     * @return the idIndividual
     */
    public int getIdIndividual() {
        return idIndividual;
    }

    /**
     * @param idIndividual the idIndividual to set
     */
    public void setIdIndividual(int idIndividual) {
        this.idIndividual = idIndividual;
    }
    
    
    
}
