/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dums
 */
public class NomeTela {
    
    private NomeTela(){
        
    }
    
    public static List<String> retornaTelas(){
        List<String> nomesTela = new ArrayList<String>();
        
        nomesTela.add("TelaCadastroEmitente");
        nomesTela.add("TelaCadastroItem");
        nomesTela.add("TelaCadastroLayout");
        nomesTela.add("TelaCadastroUsuario");
        nomesTela.add("TelaEditaConfiguracaoBD");
        nomesTela.add("TelaEditaEmitente");
        nomesTela.add("TelaEditaFonteLayout");
        nomesTela.add("TelaEditaItem");
        nomesTela.add("TelaEditaFonteLayout");
        nomesTela.add("TelaEditaItem");
        nomesTela.add("TelaEditaLayout");
        nomesTela.add("TelaEditaLayoutPropriedade");
        nomesTela.add("TelaEditaPermissoes");
        nomesTela.add("TelaEditaPropriedadeEtiqueta");
        nomesTela.add("TelaEditaUsuario");
        nomesTela.add("TelaImportaEtiquetaIndividual");
        nomesTela.add("TelaImprimeEtiquetaCaixa");
        nomesTela.add("TelaImprimeEtiquetaIndividual");
        nomesTela.add("TelaParametroEtiquetaIndividual");
        nomesTela.add("HabilitaReimprimeEtiqueta");
        nomesTela.add("TelaDesembalaEtiqueta");
        nomesTela.add("TelaHistoricoEtiqueta");
        
        return nomesTela;
        
    }
    
}
