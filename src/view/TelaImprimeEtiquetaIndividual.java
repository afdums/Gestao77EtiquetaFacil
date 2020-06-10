/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ComboModeloEmitente;
import controller.EtiquetaController;
import controller.PermissaoAcesso;
import controller.VariavelGlobal;
import dao.EmitenteDAO;
import dao.EtiquetaDAO;
import dao.EtiquetaPropriedadeDAO;
import dao.LayoutDAO;
import java.awt.MenuItem;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import model.Emitente;
import model.Etiqueta;
import model.Layout;

/**
 *
 * @author Administrador
 */
public class TelaImprimeEtiquetaIndividual extends javax.swing.JDialog {

    DefaultTableModel modeloTable;

    Layout layout;
    List<Etiqueta> etiquetas;

    JPopupMenu popup;

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    boolean novo = true;
    Emitente emitente = null;
    java.awt.Frame parent;
    ArrayList<Layout> layouts = new ArrayList<Layout>();

    /**
     * Creates new form ManutencaoUsuario
     */
    public TelaImprimeEtiquetaIndividual(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        criaPopupMenu();
        initComponents();
        populaComboEmitente();

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(3);

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

    public void criaPopupMenu() {
        //...where the GUI is constructed:
        //Create the popup menu.
        popup = new JPopupMenu();
        JMenuItem menuReabreEtiqueta = new JMenuItem("Habilita Reimpressão");
        menuReabreEtiqueta.addActionListener((e) -> {
            if(PermissaoAcesso.isLiberado(VariavelGlobal.getInstancec().getUsuarioLogado(), "HabilitaReimprimeEtiqueta")){            
                JOptionPane.showMessageDialog(null, "Apenas etiquetas não embaladas serão habilitadas para reimpressão", "Atenção", JOptionPane.WARNING_MESSAGE);
                reabreSelecionadas();
            }else{
                JOptionPane.showMessageDialog(null, "Usuário sem permissão de acesso", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        });
        popup.add(menuReabreEtiqueta);
        
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
            if (comboLayout.getItemCount() > 0) {
                comboLayout.removeAllItems();
            }
            if (emitente != null) {
                LayoutDAO layoutDAO = new LayoutDAO();
                layouts = (ArrayList<Layout>) layoutDAO.selectAllEmitente(emitente.getId());

                layouts.forEach((layout) -> {
                    if (layout.getTipo() == 0) {
                        comboLayout.addItem(layout.getCodigo());
                    }
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
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonImprimir = new javax.swing.JButton();
        buttonSelAll = new javax.swing.JButton();
        buttonDesAll = new javax.swing.JButton();
        comboEmitente = new javax.swing.JComboBox<>();
        comboLayout = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonCarregar = new javax.swing.JButton();
        selectNaoImpressas = new javax.swing.JCheckBox();

        setTitle("Gestão77 - Imprime Etiquetas Individuais");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        cancelButton.setLabel("Fechar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Chave", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setComponentPopupMenu(popup);
        jScrollPane1.setViewportView(jTable1);

        buttonImprimir.setText("Imprimir Etiquetas Marcadas");
        buttonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirActionPerformed(evt);
            }
        });

        buttonSelAll.setText("Marca Todos");
        buttonSelAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelAllActionPerformed(evt);
            }
        });

        buttonDesAll.setText("Desmarca Todos");
        buttonDesAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesAllActionPerformed(evt);
            }
        });

        comboEmitente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboEmitente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEmitenteItemStateChanged(evt);
            }
        });

        comboLayout.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Cliente:");

        jLabel2.setText("Layout:");

        buttonCarregar.setText("Carregar");
        buttonCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCarregarActionPerformed(evt);
            }
        });

        selectNaoImpressas.setSelected(true);
        selectNaoImpressas.setText("Apenas Não Impressas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSelAll, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDesAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(selectNaoImpressas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonCarregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(comboLayout, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboEmitente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboEmitente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCarregar)
                    .addComponent(selectNaoImpressas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSelAll)
                    .addComponent(buttonDesAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonImprimir)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void buttonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirActionPerformed
        // TODO add your handling code here:
        //retorna um inteiro
        imprimeSelecionadas();
    }//GEN-LAST:event_buttonImprimirActionPerformed

    private void buttonSelAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelAllActionPerformed
        // TODO add your handling code here:
        jTable1.setRowSelectionInterval(0, jTable1.getRowCount() - 1);
    }//GEN-LAST:event_buttonSelAllActionPerformed

    private void buttonDesAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesAllActionPerformed
        // TODO add your handling code here:
        jTable1.getSelectionModel().clearSelection();
    }//GEN-LAST:event_buttonDesAllActionPerformed

    private void comboEmitenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEmitenteItemStateChanged
        // TODO add your handling code here:
        try {
            emitente = (Emitente) comboEmitente.getSelectedItem();
            populaComboLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_comboEmitenteItemStateChanged

    private void buttonCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCarregarActionPerformed
        // TODO add your handling code here:
        populaTabela();
    }//GEN-LAST:event_buttonCarregarActionPerformed

    public void imprimeSelecionadas() {
        //Abertura da impressora  
        EtiquetaController etiquetaController = new EtiquetaController();

        int[] linhasMarcadas = jTable1.getSelectedRows();

        List<Etiqueta> etiquetasImp = new ArrayList<Etiqueta>();
        EtiquetaDAO etiquetaDAO = new EtiquetaDAO();
        for (int i = 0; i < linhasMarcadas.length; i++) {
            System.out.println("ID Etiq " + jTable1.getModel().getValueAt(linhasMarcadas[i], 0));
            int idEtiq = (int) jTable1.getModel().getValueAt(linhasMarcadas[i], 0);
            etiquetasImp.add(etiquetaDAO.select(idEtiq));
        }

        for (Etiqueta etiqAux : etiquetasImp) {
            System.out.println("Imprimir " + etiqAux.getConteudo());
        }

        etiquetaController.imprimeEtiquetasIndividuais(etiquetasImp, layout);

        JOptionPane.showMessageDialog(this, "As etiquetas marcadas foram impressas...", "Etiquetas Impressas", JOptionPane.INFORMATION_MESSAGE);

        populaTabela();

    }
    
    public void reabreSelecionadas() {
        //Abertura da impressora  
        
        int[] linhasMarcadas = jTable1.getSelectedRows();

        List<Etiqueta> etiquetasReabrir = new ArrayList<Etiqueta>();
        EtiquetaDAO etiquetaDAO = new EtiquetaDAO();
        for (int i = 0; i < linhasMarcadas.length; i++) {
            System.out.println("ID Etiq " + jTable1.getModel().getValueAt(linhasMarcadas[i], 0));
            int idEtiq = (int) jTable1.getModel().getValueAt(linhasMarcadas[i], 0);
            Etiqueta etiqueta = etiquetaDAO.select(idEtiq);
            if(etiqueta.getSituacao() == 2){
                etiqueta.setSituacao(1);
                etiquetasReabrir.add(etiqueta);
            }
        }

        etiquetaDAO = new EtiquetaDAO();
        etiquetaDAO.updateLote(etiquetasReabrir);

        JOptionPane.showMessageDialog(this, "As etiquetas marcadas foram reabertas", "Etiquetas reabertas", JOptionPane.INFORMATION_MESSAGE);

        populaTabela();

    }

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
        populaTabela();
    }

    private void populaTabela() {

        modeloTable = (DefaultTableModel) jTable1.getModel();

        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }
        try {

            LayoutDAO layoutDAO = new LayoutDAO();
            System.out.println("Layout selecionado: " + comboLayout.getSelectedItem().toString());

            layout = layoutDAO.selectWhereCodigo(emitente.getId(), comboLayout.getSelectedItem().toString());

            EtiquetaDAO etiquetaDAO = new EtiquetaDAO();

            System.out.println("Layout selecionado: " + layout.getId());

            if (selectNaoImpressas.isSelected()) {
                etiquetas = etiquetaDAO.selectWhereLayoutSituacao(layout.getId(), 1);
            } else {
                etiquetas = etiquetaDAO.selectWhereLayout(layout.getId());
            }

            if (etiquetas != null) {
                etiquetas.forEach((etiqueta) -> {
                    modeloTable.addRow(new Object[]{
                        etiqueta.getId(),
                        etiqueta.getChaveUnica(),
                        etiqueta.retornaSituacaoString()});
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(TelaImprimeEtiquetaIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaImprimeEtiquetaIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaImprimeEtiquetaIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaImprimeEtiquetaIndividual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaImprimeEtiquetaIndividual dialog = new TelaImprimeEtiquetaIndividual(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton buttonCarregar;
    private javax.swing.JButton buttonDesAll;
    private javax.swing.JButton buttonImprimir;
    private javax.swing.JButton buttonSelAll;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> comboEmitente;
    private javax.swing.JComboBox<String> comboLayout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox selectNaoImpressas;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
