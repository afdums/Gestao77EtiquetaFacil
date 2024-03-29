/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PermissaoAcesso;
import controller.VariavelGlobal;
import dao.EmitenteDAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Emitente;

/**
 *
 * @author Administrador
 */
public class TelaCadastroEmitente extends javax.swing.JFrame {
    
    DefaultTableModel modeloTable;

    /**
     * Creates new form TelaCadastroUsuario
     */
    public TelaCadastroEmitente() {
        initComponents();
        URL url = this.getClass().getResource("/image/logoVerticalClean.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150); 
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250); 
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150); 
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); 
        populaTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnEtiquetas = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestão77 - Manutenção de Emitentes");
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CNPJ", "Razão Social", "Cidade", "UF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnEtiquetas.setText("Layout");
        btnEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtiquetasActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEtiquetas)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIncluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnEtiquetas)
                    .addComponent(btnExcluir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaEditaEmitente")){
            TelaEditaEmitente telaEditaEmitente = new TelaEditaEmitente(this, true);
            telaEditaEmitente.setEmitente(null);
            telaEditaEmitente.show();
            if(telaEditaEmitente.getReturnStatus()==1){
                Emitente emitente = telaEditaEmitente.getEmitente();
                modeloTable.addRow(new Object[] { emitente.getCnpj(),
                    emitente.getRazao(),
                    emitente.getCidade(),
                    emitente.getUf()});
            }
            System.out.println("Retorno: " + telaEditaEmitente.getReturnStatus());
        }
        
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
         //retorna um inteiro
        if(isLiberado("TelaEditaEmitente")){
            String cnpj = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString(); 
            System.out.println("Emitente selecionado " + cnpj);
            EmitenteDAO emitenteDAO = new EmitenteDAO();
            Emitente emitente = emitenteDAO.selectWhereCnpj(cnpj);
            System.out.println("Emitente encontrado " + emitente.getRazao());

            TelaEditaEmitente telaEditaEmitente = new TelaEditaEmitente(this, true);
            telaEditaEmitente.setEmitente(emitente);
            telaEditaEmitente.show();
            if(telaEditaEmitente.getReturnStatus()==1){

                emitenteDAO = new EmitenteDAO();
                emitente = emitenteDAO.selectWhereCnpj(cnpj);
                jTable1.setValueAt(emitente.getRazao(), jTable1.getSelectedRow(), 1);
                jTable1.setValueAt(emitente.getCidade(), jTable1.getSelectedRow(), 2);
                jTable1.setValueAt(emitente.getUf(), jTable1.getSelectedRow(), 3);
            }
        }
        /*System.out.println("Retorno: " + manutencaoUsuario.getReturnStatus());*/
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnEtiquetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEtiquetasActionPerformed
        // TODO add your handling code here:
        
        if(isLiberado("TelaCadastroLayout")){
            TelaCadastroLayout telaCadastroEtiqueta = new TelaCadastroLayout(this,true);

            String cnpj = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString(); 
            System.out.println("Emitente selecionado " + cnpj);
            EmitenteDAO emitenteDAO = new EmitenteDAO();
            Emitente emitente = emitenteDAO.selectWhereCnpj(cnpj);

            if(emitente != null){
                telaCadastroEtiqueta.setEmitente(emitente);
                telaCadastroEtiqueta.show();            
            }
        }
    }//GEN-LAST:event_btnEtiquetasActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaEditaEmitente")){
            String cnpj = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString(); 
            System.out.println("Emitente selecionado " + cnpj);
            EmitenteDAO emitenteDAO = new EmitenteDAO();
            Emitente emitente = emitenteDAO.selectWhereCnpj(cnpj);
            if(emitente != null){
                int resposta = JOptionPane.showConfirmDialog(null, "Confirma exclusão do emitente " + emitente.getRazao() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if(resposta == 0){
                    System.out.println("Emitente encontrado " + emitente.getRazao());
                    emitenteDAO = new EmitenteDAO();
                    if(!emitenteDAO.delete(emitente)){
                        JOptionPane.showMessageDialog(null, emitenteDAO.retornaStatus(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }else{
                        DefaultTableModel dtm = (DefaultTableModel)jTable1.getModel();
                        dtm.removeRow(jTable1.getSelectedRow());
                        jTable1.setModel(dtm);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    
    public boolean isLiberado(String funcao){
        if(PermissaoAcesso.isLiberado(VariavelGlobal.getInstancec().getUsuarioLogado(), funcao)){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Usuário sem permissão de acesso", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
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
            java.util.logging.Logger.getLogger(TelaCadastroEmitente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEmitente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEmitente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEmitente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroEmitente().setVisible(true);                
            }
        });
            
    }
    
    private void populaTabela(){
        EmitenteDAO emitenteDAO = new EmitenteDAO();
        List<Emitente> itens = emitenteDAO.selectAll();
        
        modeloTable = (DefaultTableModel) jTable1.getModel();
        
        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }

        itens.forEach((item) -> {
            modeloTable.addRow(new Object[] { item.getCnpj(),
                item.getRazao(),
                item.getCidade(),
                item.getUf()});
        });
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnEtiquetas;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
