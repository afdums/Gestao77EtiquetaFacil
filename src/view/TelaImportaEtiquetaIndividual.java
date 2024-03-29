/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ComboModeloEmitente;
import controller.EtiquetaController;
import dao.EmitenteDAO;
import dao.LayoutDAO;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Emitente;
import model.Etiqueta;
import model.EtiquetaPropriedadeTela;
import model.Layout;

/**
 *
 * @author andersond
 */
public class TelaImportaEtiquetaIndividual extends javax.swing.JDialog {

    ArrayList<Layout> layouts = new ArrayList<Layout>();
    Emitente emitente = null;

    Frame pai;

    /**
     * Creates new form TelaImportaEtiquetaIndividual
     */
    public TelaImportaEtiquetaIndividual(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        pai = parent;
        initComponents();
        URL url = this.getClass().getResource("/image/logoVerticalClean.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        populaComboEmitente();
    }

    private void populaComboEmitente() {
        comboEmitente.removeAllItems();

        EmitenteDAO emitenteDAO = new EmitenteDAO();
        ArrayList<Emitente> emitentes = (ArrayList<Emitente>) emitenteDAO.selectAll();
        comboEmitente.setModel(new ComboModeloEmitente(emitentes));

        if (emitentes.size() > 0) {
            comboEmitente.setSelectedItem(emitentes.get(0));
            emitente = emitentes.get(0);
            populaComboLayout();
        }

    }

    private void populaComboLayout() {
        try {
            System.out.println("A");
            if (comboLayout.getItemCount() > 0) {
                comboLayout.removeAllItems();
            }
            if (emitente != null) {
                LayoutDAO layoutDAO = new LayoutDAO();
                layouts = (ArrayList<Layout>) layoutDAO.selectAllEmitente(emitente.getId());

                layouts.forEach((layout) -> {
                    if(layout.getTipo() == 0)
                        comboLayout.addItem(layout.getCodigo());
                });

                if (comboLayout.getItemCount() > 0) {
                    comboLayout.setSelectedIndex(0);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImportar = new javax.swing.JButton();
        textArquivo = new javax.swing.JTextField();
        comboLayout = new javax.swing.JComboBox<>();
        comboEmitente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        buttonSelecionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestão77 - Importação de Etiquetas Individuais");
        setResizable(false);

        btnImportar.setText("Importar");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        textArquivo.setEditable(false);

        comboLayout.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboEmitente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboEmitente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEmitenteItemStateChanged(evt);
            }
        });

        jLabel1.setText("Cliente:");

        jLabel2.setText("Layout:");

        jLabel3.setText("Arquivo:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        buttonSelecionar.setText("Sel");
        buttonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnImportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(comboLayout, 0, 336, Short.MAX_VALUE)
                        .addComponent(comboEmitente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSelecionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboEmitente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(buttonSelecionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnImportar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void comboEmitenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEmitenteItemStateChanged
        // TODO add your handling code here:
        try {
            emitente = (Emitente) comboEmitente.getSelectedItem();
            populaComboLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_comboEmitenteItemStateChanged

    private void buttonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarActionPerformed
        // TODO add your handling code here:
        textArquivo.setText(selecionarArquivo());
    }//GEN-LAST:event_buttonSelecionarActionPerformed

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        // TODO add your handling code here:
        importaArquivo();
    }//GEN-LAST:event_btnImportarActionPerformed

    public void importaArquivo() {
        EtiquetaController etiquetaController = new EtiquetaController();

        LayoutDAO layoutDAO = new LayoutDAO();

        Layout layout = layoutDAO.selectWhereCodigo(emitente.getId(), comboLayout.getSelectedItem().toString());

        TelaEditaPropriedadeEtiqueta telaEditaPropriedadeEtiqueta = new TelaEditaPropriedadeEtiqueta(pai, true);
        telaEditaPropriedadeEtiqueta.setLayout(layout);
        telaEditaPropriedadeEtiqueta.show();
        if (telaEditaPropriedadeEtiqueta.getReturnStatus() == 1) {
            List<EtiquetaPropriedadeTela> etiquetasPropriedadeTela = telaEditaPropriedadeEtiqueta.getPropriedades();
            for (EtiquetaPropriedadeTela etiq : etiquetasPropriedadeTela) {
                System.out.println("Propriedade Tela: " + etiq.getPropriedade() + " - " + etiq.getConteudo());
            }
            etiquetaController.importarArquivoEtiquetaIndividual(layout, textArquivo.getText(), etiquetasPropriedadeTela);
            JOptionPane.showMessageDialog(rootPane, "Arquivo importado com sucesso.\nAcesse Tarefas->Imprimir Etiquetas Individuais para imprimi-las", "Arquivo Importado", JOptionPane.INFORMATION_MESSAGE);
            List<Etiqueta> etiquetasNaoImportadas = etiquetaController.retornaNaoImportadas();
            if(etiquetasNaoImportadas != null && !etiquetasNaoImportadas.isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Alguns registros não foram importados, pressione OK para visualizá-los", "Registros com Problemas", JOptionPane.WARNING_MESSAGE);
                try {
                    BufferedWriter out = new BufferedWriter(
                    new FileWriter("etiquetasNaoImportadass.txt", false));
                    out.write("As etiquetas abaixo não foram importadas ou já estavam importadas");
                    out.newLine();
                    for(Etiqueta etiquetaNaoImportada : etiquetasNaoImportadas){
                        out.newLine();
                        out.write(etiquetaNaoImportada.getChaveUnica());
                        //JOptionPane.showMessageDialog(null, etiquetaNaoImportada.getChaveUnica(), "Etiqueta não importada/já importada", JOptionPane.WARNING_MESSAGE);
                    }
                    out.close();
                    Runtime.getRuntime().exec("notepad etiquetasNaoImportadass.txt");
                } catch(IOException e){
                    // possíveis erros são tratados aqui
                }
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Para realizar a importação devem ser informados os parâmetros de tela.", "Importação cancelada", JOptionPane.WARNING_MESSAGE);
        }
    }

    private String selecionarArquivo() {
        String arquivo = null;
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Escolha o arquivo com o layout...");
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setApproveButtonText("OK");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(false);
        int resultado = fc.showOpenDialog(fc);
        if (resultado != JFileChooser.CANCEL_OPTION) {
            arquivo = fc.getSelectedFile().getAbsolutePath();
        }
        return arquivo;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaImportaEtiquetaIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaImportaEtiquetaIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaImportaEtiquetaIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaImportaEtiquetaIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaImportaEtiquetaIndividual dialog = new TelaImportaEtiquetaIndividual(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImportar;
    private javax.swing.JButton buttonSelecionar;
    private javax.swing.JComboBox<String> comboEmitente;
    private javax.swing.JComboBox<String> comboLayout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField textArquivo;
    // End of variables declaration//GEN-END:variables
}
