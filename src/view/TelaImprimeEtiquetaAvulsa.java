/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ComboModeloEmitente;
import controller.ComboModeloLayout;
import controller.EtiquetaController;
import dao.EmitenteDAO;
import dao.LayoutDAO;
import dao.LayoutPropriedadeDAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import model.Emitente;
import model.EtiquetaPropriedadeTela;
import model.Layout;
import model.LayoutPropriedade;
import model.PropriedadeAvulsa;

/**
 *
 * @author Administrador
 */
public class TelaImprimeEtiquetaAvulsa extends javax.swing.JDialog {
    
    DefaultTableModel modeloTable;

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    Layout layout;
    java.awt.Frame parent;
    ArrayList<Layout> layouts = new ArrayList<Layout>();
    Emitente emitente = null;
    
    List<EtiquetaPropriedadeTela> etiquetasPropriedaTela = new ArrayList<EtiquetaPropriedadeTela>();
    
    /**
     * Creates new form ManutencaoUsuario
     */
    public TelaImprimeEtiquetaAvulsa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        URL url = this.getClass().getResource("/image/logoVerticalClean.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        populaComboEmitente();
        
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
                    if(layout.getTipo() == 2)
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
        buttonSalvar = new javax.swing.JButton();
        comboEmitente = new javax.swing.JComboBox<>();
        comboLayout = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        imprimirArquivo = new javax.swing.JCheckBox();

        setTitle("Gestão77 - Edição de Propriedades de Etiquetas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Propriedade", "Conteúdo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        buttonSalvar.setText("Imprimir");
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });

        comboEmitente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboEmitente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEmitenteItemStateChanged(evt);
            }
        });
        comboEmitente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEmitenteActionPerformed(evt);
            }
        });

        comboLayout.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboLayout.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboLayoutItemStateChanged(evt);
            }
        });

        jLabel2.setText("Layout:");

        jLabel1.setText("Cliente:");

        imprimirArquivo.setText("Imprimir em Arquivo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imprimirArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboEmitente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboLayout, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboEmitente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSalvar)
                    .addComponent(cancelButton)
                    .addComponent(imprimirArquivo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        // TODO add your handling code here:
        List<PropriedadeAvulsa> propriedadesAvulsa = new ArrayList<>();
        
        for(int i = 0; i < jTable1.getRowCount(); i++){
            propriedadesAvulsa.add(new PropriedadeAvulsa(jTable1.getValueAt(i,0).toString(),jTable1.getValueAt(i,1).toString()));            
        }
        
        EtiquetaController etiquetaController = new EtiquetaController();
        
        if(layout != null){
            String retorno = etiquetaController.imprimeEtiquetaAvulsa(layout, propriedadesAvulsa, imprimirArquivo.isSelected());
            JOptionPane.showMessageDialog(null, "Impressão finalizada\n" + retorno, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Layout não foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        doClose(RET_OK);
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void comboEmitenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEmitenteItemStateChanged
        // TODO add your handling code here:
        try {
            emitente = (Emitente) comboEmitente.getSelectedItem();
            populaComboLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_comboEmitenteItemStateChanged

    private void comboEmitenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEmitenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEmitenteActionPerformed

    private void comboLayoutItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboLayoutItemStateChanged
        // TODO add your handling code here:
        modeloTable = (DefaultTableModel) jTable1.getModel();
        
        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }
        try{
            LayoutPropriedadeDAO layoutPropriedadeDAO = new LayoutPropriedadeDAO();
            LayoutDAO layoutDAO = new LayoutDAO();
            if(comboLayout.getItemCount() > 0){
                System.out.println("Layout Selecionado: " + comboLayout.getSelectedItem().toString());
                layout = layoutDAO.selectWhereCodigo(emitente.getId(), comboLayout.getSelectedItem().toString());
                if(layout != null){
                    List<LayoutPropriedade> layoutPropriedades = layoutPropriedadeDAO.selectAllLayoutPropriedades(layout.getId());                
                    if(layoutPropriedades != null){
                        layoutPropriedades.forEach((layoutPropriedade) -> {
                            if(layoutPropriedade.getOrigem() == 2 /*tela*/ )
                                modeloTable.addRow(new Object[] { layoutPropriedade.getPropriedade(),
                                    ""});
                        });
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_comboLayoutItemStateChanged
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    
    public void setLayout(Layout layout){
        this.layout = layout;
        populaTabela();
    }
    
    public List<EtiquetaPropriedadeTela> getPropriedades(){
        return etiquetasPropriedaTela;
    }
    
    private void populaTabela(){
        
        modeloTable = (DefaultTableModel) jTable1.getModel();
        
        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }
        try{
            LayoutDAO layoutDAO = new LayoutDAO();
            
            
            LayoutPropriedadeDAO layoutPropriedadeDAO = new LayoutPropriedadeDAO();
            List<LayoutPropriedade> layoutPropriedades = layoutPropriedadeDAO.selectAllLayoutPropriedades(layout.getId());
            
            if(layoutPropriedades != null){
                layoutPropriedades.forEach((layoutPropriedade) -> {
                    if(layoutPropriedade.getOrigem() == 2 /*tela*/ )
                        modeloTable.addRow(new Object[] { layoutPropriedade.getPropriedade(),
                            ""});
                });
            }
        }catch(Exception e){
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
            java.util.logging.Logger.getLogger(TelaImprimeEtiquetaAvulsa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaImprimeEtiquetaAvulsa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaImprimeEtiquetaAvulsa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaImprimeEtiquetaAvulsa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                TelaImprimeEtiquetaAvulsa dialog = new TelaImprimeEtiquetaAvulsa(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> comboEmitente;
    private javax.swing.JComboBox<String> comboLayout;
    private javax.swing.JCheckBox imprimirArquivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
