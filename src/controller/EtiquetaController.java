/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContadorPropriedadeDAO;
import dao.EtiquetaCaixaDAO;
import dao.EtiquetaDAO;
import dao.EtiquetaPropriedadeDAO;
import dao.LayoutPropriedadeDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ContadorPropriedade;
import model.Etiqueta;
import model.EtiquetaCaixa;
import model.EtiquetaPropriedade;
import model.EtiquetaPropriedadeTela;
import model.Layout;
import model.LayoutPropriedade;
import model.PropriedadeAvulsa;

/**
 *
 * @author andersond
 */
public class EtiquetaController {

    List<Etiqueta> etiquetasNaoImportadas = new ArrayList<Etiqueta>();
    
    public void importarArquivoEtiquetaIndividual(Layout layout, String arquivo, List<EtiquetaPropriedadeTela> etiquetasPropriedadeTela) {
        Etiqueta etiqueta = null;
        List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        
        EtiquetaPropriedade etiquetaPropriedade = null;
        EtiquetaPropriedadeDAO etiquetaPropriedadeDAO = null;
        List<EtiquetaPropriedade> propriedades = new ArrayList<EtiquetaPropriedade>();

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(arquivo);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //anderson
            LayoutPropriedadeDAO layoutPropriedadeDAO = new LayoutPropriedadeDAO();
            List<LayoutPropriedade> layoutPropriedades = layoutPropriedadeDAO.selectAllLayoutPropriedades(layout.getId());
            /*for(int i = 0; i < layoutPropriedades.size(); i++){
                if(layoutPropriedades.get(i).getOrigem() != 1) //Arquivo
                    layoutPropriedades.remove(i);                    
            }*/

            //Incluir regra com chamada para preencher as propriedades do tipo tela
            String linha = bufferedReader.readLine();

            while (linha != null) {
                Etiqueta etiquetaImportada = new Etiqueta(layout.getId(), linha, layout.getIdEmitente(), "");
                etiquetas.add(etiquetaImportada);
                String[] campos = linha.split(";");

                for (int i = 0; i < campos.length; i++) {

                    for (int x = 0; x < layoutPropriedades.size(); x++) {
                        if (layoutPropriedades.get(x).getColuna() == i + 1) {
                            propriedades.add(new EtiquetaPropriedade(etiquetas.size() - 1, layoutPropriedades.get(x).getPropriedade(), campos[i] /*, linha*/));
                            if (layoutPropriedades.get(x).isChaveUnica()) {
                                etiquetaImportada.setChaveUnica(campos[i]);
                            }
                        }
                    }

                    for (int y = 0; y < etiquetasPropriedadeTela.size(); y++) {
                        propriedades.add(new EtiquetaPropriedade(etiquetas.size() - 1, etiquetasPropriedadeTela.get(y).getPropriedade(), etiquetasPropriedadeTela.get(y).getConteudo() /*, linha*/));
                    }

                }
                //anderson
                linha = bufferedReader.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(EtiquetaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioEx) {
            Logger.getLogger(EtiquetaController.class.getName()).log(Level.SEVERE, null, ioEx);
        }

        etiquetasNaoImportadas = new ArrayList<Etiqueta>();
        
        if (!etiquetas.isEmpty()) {
            System.out.println("INSERIDO LOTE ETIQUETAS");
            EtiquetaDAO etiquetaDAO = new EtiquetaDAO();
            etiquetaDAO.insertLote(etiquetas);
            System.out.println("IDs nao importados retornados para o lote");
            for (int i = 0; i < etiquetas.size(); i++) {
                if(etiquetas.get(i).getId() == 0){
                    etiquetasNaoImportadas.add(etiquetas.get(i));
                }
            }

        }

        if (!propriedades.isEmpty()) {
            for (int i = propriedades.size() - 1; i >= 0; i--) {
                Etiqueta etiqIndex = etiquetas.get(propriedades.get(i).getIdEtiqueta());
                if(etiqIndex.getId() == 0){
                    propriedades.remove(i); //elimina propriedades que não serão importadas devido o registro ser duplicado
                }else{
                    propriedades.get(i).setIdEtiqueta(etiqIndex.getId());
                }
            }

            System.out.println("INSERIDO PROPRIEDADE ETIQUETAS");
            etiquetaPropriedadeDAO = new EtiquetaPropriedadeDAO();
            etiquetaPropriedadeDAO.insertLote(propriedades);
        }

    }
    
    public List<Etiqueta> retornaNaoImportadas(){
        return etiquetasNaoImportadas;
    }

    public void imprimeEtiquetasIndividuais(List<Etiqueta> etiquetas, Layout layout) {
        List<EtiquetaPropriedade> etiquetaPropriedades;

        try {

            //FileWriter fileWriter = new FileWriter("c:/temp/etiquetas.txt", false);
            //BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Etiqueta etiqueta : etiquetas) {
                FileOutputStream fos = new FileOutputStream(layout.getImpressora());
                PrintWriter ps = new PrintWriter(fos);

                String fonteEtiqueta = layout.getFonteLayout();

                EtiquetaPropriedadeDAO etiquetaPropriedadeDAO = new EtiquetaPropriedadeDAO();
                etiquetaPropriedades = etiquetaPropriedadeDAO.selectAllEtiquetaPropriedades(etiqueta.getId());

                for (EtiquetaPropriedade etiquetaPropriedade : etiquetaPropriedades) {
                    fonteEtiqueta = fonteEtiqueta.replace(etiquetaPropriedade.getPropriedade(), etiquetaPropriedade.getConteudo());
                }

                //bufferedWriter.write("Etiqueta " + etiqueta.getId() + "\n");
                //bufferedWriter.write(fonteEtiqueta);
                etiqueta.setSituacao(2); //impressas
                //bufferedWriter.write("\n\n\n");
                
                
                //JOptionPane.showMessageDialog(null, fonteEtiqueta, "Informação", JOptionPane.INFORMATION_MESSAGE);

                ps.print(fonteEtiqueta);
                ps.close();
            }
            //bufferedWriter.close();
            //fileWriter.close();

            //se chegou aqui significa que imprimieu sem erros e altera o status para Impressas.
            EtiquetaDAO etiquetaDAO = new EtiquetaDAO();
            etiquetaDAO.updateLote(etiquetas);

        } catch (Exception e) {
            System.out.println("Erro imprime selecionados");
            e.printStackTrace();
        }        
        
    }

    public void imprimeEtiquetaEmbalagem(List<Etiqueta> etiquetas, Layout layout) {

        EtiquetaPropriedadeDAO etiquetaPropriedadeDAO;
        List<EtiquetaPropriedade> etiquetaPropriedades;
        
        System.out.println("Imprime Embalagem");

        try {
            FileOutputStream fos = new FileOutputStream(layout.getImpressora());
            PrintWriter ps = new PrintWriter(fos);

            String fonteEtiqueta = layout.getFonteLayout();
            
            EtiquetaDAO etiquetaDAO = new EtiquetaDAO();
            Etiqueta embalagem = new Etiqueta(layout.getId(), "", layout.getIdEmitente(), "");
            etiquetaDAO.insert(embalagem);
           
            List<EtiquetaCaixa> etiquetaCaixas = new ArrayList<EtiquetaCaixa>();
            
            String sEtiq = "";
            for(int i = 0; i < etiquetas.size(); i++){
                Etiqueta etiqueta = etiquetas.get(i);
                
                if(i==0){
                    //salva as propriedades da primeira etiqueta como sendo da caixa
                    etiquetaPropriedadeDAO = new EtiquetaPropriedadeDAO();
                    etiquetaPropriedades = etiquetaPropriedadeDAO.selectAllEtiquetaPropriedades(etiqueta.getId());

                    for (EtiquetaPropriedade etiquetaPropriedade : etiquetaPropriedades) {
                        fonteEtiqueta = fonteEtiqueta.replace(etiquetaPropriedade.getPropriedade(), etiquetaPropriedade.getConteudo());
                    }
                }
                
                etiquetaCaixas.add(new EtiquetaCaixa(embalagem.getId(), etiqueta.getId()));
                if(i < 9)
                    sEtiq = "0" + (i + 1);
                else
                    sEtiq = "" + (i + 1);
                fonteEtiqueta = fonteEtiqueta.replace("#SERIALSER" + sEtiq, etiqueta.getChaveUnica());
                etiqueta.setSituacao(3); //Relacionada a caixa
            }
            
            EtiquetaCaixaDAO etiquetaCaixaDAO = new EtiquetaCaixaDAO();
            etiquetaCaixaDAO.insertLote(etiquetaCaixas);
            
            ps.print(fonteEtiqueta);
            ps.close();

            //altera a situação da etiqueta para relacionada a caixa
            etiquetaDAO = new EtiquetaDAO();
            etiquetaDAO.updateLote(etiquetas);

        } catch (Exception e) {
            System.out.println("Erro imprime embalagem");
            e.printStackTrace();
        }
    }

    public String imprimeEtiquetaAvulsa(Layout layout, List<PropriedadeAvulsa> propriedadesAvulsa, boolean imprimirArquivo) {
        
        String retorno = "";
        int volumes = 1;
        String ordem = "";
        Integer sequencial = 0;
        String barCode = "";
        String ano = "9999";
        String semana = "99";
        
        Calendar calendario = Calendar.getInstance();
        
        ContadorPropriedade contadorPropriedade= new ContadorPropriedade();
        ContadorPropriedadeDAO contadorPropriedadeDAO = new ContadorPropriedadeDAO();
        
        ano = String.format("%04d", calendario.get(Calendar.YEAR)).substring(2, 4);
        semana = String.format("%02d", calendario.get(Calendar.WEEK_OF_YEAR));
        
        try {
            
            
            
            for (PropriedadeAvulsa propriedadeAvulsa : propriedadesAvulsa) {
                try {
                    if(propriedadeAvulsa.getPropriedade().equals("#ordem")) {
                        ordem = propriedadeAvulsa.getConteudo();
                        
                        while(ordem.length() < 6)
                            ordem = "0" + ordem;
                        
                        ordem = ordem.substring(0,6);
                        
                        //System.out.println(ordem + "tamanho: " + ordem.length());
                        
                        contadorPropriedade = contadorPropriedadeDAO.selectWherePropriedade(layout.getId(), propriedadeAvulsa.getPropriedade(), ordem);
                        if(contadorPropriedade == null){
                            System.out.println("criar novo contador");
                            contadorPropriedade = new ContadorPropriedade(layout.getId(), propriedadeAvulsa.getPropriedade(), ordem);
                            contadorPropriedadeDAO.insert(contadorPropriedade);
                            contadorPropriedade = contadorPropriedadeDAO.selectWherePropriedade(layout.getId(), propriedadeAvulsa.getPropriedade(), ordem);
                            sequencial = 0;
                        } else {
                            sequencial = contadorPropriedade.getContador();
                        }                        
                    }
                    if(propriedadeAvulsa.getPropriedade().equals("#volumes")) {
                        volumes = Integer.parseInt(propriedadeAvulsa.getConteudo());
                    }
                    
                } catch (Exception e) {
                    volumes = 1;                    
                }
                
            }
            
            System.out.println("Ordem: " + ordem);
            System.out.println("Contador: " + sequencial);
            System.out.println("Volumes: " + volumes);
            
            for(int i = 0; i < volumes; i++) {
                
                FileWriter fileWriter = null;
                BufferedWriter bufferedWriter = null;
                FileOutputStream fos = null;
                PrintWriter ps = null;
                String arquivoTemp = "";
                
                for (PropriedadeAvulsa propriedadeAvulsa : propriedadesAvulsa) {
                    
                    if(propriedadeAvulsa.getPropriedade().startsWith("#bar_code_")){
                        sequencial ++;
                        barCode = ano + semana + ordem + String.format("%04d",sequencial);
                        propriedadeAvulsa.setConteudo(barCode);
                    }
                    
                }

                if(imprimirArquivo) {
                    arquivoTemp = System.getProperty("java.io.tmpdir");
                    arquivoTemp += ordem + "-" + ano + "-" + semana + "-" + String.format("%04d",sequencial) + ".txt";
                    fileWriter = new FileWriter(arquivoTemp, false);
                    bufferedWriter = new BufferedWriter(fileWriter);  
                } else {
                    fos = new FileOutputStream(layout.getImpressora());
                    ps = new PrintWriter(fos);
                }


                String fonteEtiqueta = layout.getFonteLayout();

                for (PropriedadeAvulsa propriedadeAvulsa : propriedadesAvulsa) {
                    System.out.println("Propriedade: " + propriedadeAvulsa.getPropriedade() + ": " + propriedadeAvulsa.getConteudo());
                    fonteEtiqueta = fonteEtiqueta.replace(propriedadeAvulsa.getPropriedade(), propriedadeAvulsa.getConteudo());
                }

                if(imprimirArquivo) {
                    bufferedWriter.write(fonteEtiqueta);            
                    bufferedWriter.close();
                    fileWriter.close();
                    retorno = "Salvo em " + arquivoTemp;
                } else {
                    ps.print(fonteEtiqueta);
                    ps.close();
                    retorno = "Sucesso";
                }
                
                contadorPropriedade.setContador(sequencial);
                contadorPropriedadeDAO.update(contadorPropriedade);
                
            }
            
        } catch (Exception e) {
            System.out.println("Erro imprime avulsa");
            e.printStackTrace();
            retorno = e.toString();
        }
        
        return retorno;
        
    }
    
}
