/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PermissaoAcesso;
import controller.VariavelGlobal;
import dao.ItemDAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Item;

/**
 *
 * @author Administrador
 */
public class TelaCadastroItem extends javax.swing.JFrame {
    
    DefaultTableModel modeloTable;

    /**
     * Creates new form TelaCadastroUsuario
     */
    public TelaCadastroItem() {
        initComponents();
        URL url = this.getClass().getResource("/image/logoVerticalClean.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100); 
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250); 
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150); 
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(150); 
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestão77 - Manutenção de Itens");
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Descrição", "Item Cliente", "EAN"
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

        jButton1.setText("Incluir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Alterar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
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
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaEditaItem")){
            TelaEditaItem telaEditaItem = new TelaEditaItem(this, true);
            telaEditaItem.setItem(null);
            telaEditaItem.show();
            if(telaEditaItem.getReturnStatus()==1){
                Item item = telaEditaItem.getItem();
                modeloTable.addRow(new Object[] { item.getCodigo(),
                    item.getDescricao(),
                    item.getItemCliente(),
                    item.getEan()});
            }
            System.out.println("Retorno: " + telaEditaItem.getReturnStatus());
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         //retorna um inteiro
        if(isLiberado("TelaEditaItem")){
            String codigo = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString(); 
           System.out.println("Item selecionado " + codigo);
           ItemDAO itemDAO = new ItemDAO();
           Item item = itemDAO.selectWhereCodigo(codigo);
           System.out.println("Item encontrado " + item.getDescricao());

           TelaEditaItem telaEditaItem = new TelaEditaItem(this, true);
           telaEditaItem.setItem(item);
           telaEditaItem.show();
           if(telaEditaItem.getReturnStatus() == 1){

               itemDAO = new ItemDAO();
               item = itemDAO.selectWhereCodigo(codigo);
               jTable1.setValueAt(item.getDescricao(), jTable1.getSelectedRow(), 1);
               jTable1.setValueAt(item.getItemCliente(), jTable1.getSelectedRow(), 2);
               jTable1.setValueAt(item.getEan(), jTable1.getSelectedRow(), 3);
           }
        }
        /*System.out.println("Retorno: " + manutencaoUsuario.getReturnStatus());*/
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroItem().setVisible(true);                
            }
        });
            
    }
    
    private void populaTabela(){
        ItemDAO itemDAO = new ItemDAO();
        List<Item> itens = itemDAO.selectAll();
        
        modeloTable = (DefaultTableModel) jTable1.getModel();
        
        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }

        itens.forEach((item) -> {
            modeloTable.addRow(new Object[] { item.getCodigo(),
                item.getDescricao(),
                item.getItemCliente(),
                item.getEan()});
        });
            
    }
    
    public boolean isLiberado(String funcao){
        if(PermissaoAcesso.isLiberado(VariavelGlobal.getInstancec().getUsuarioLogado(), funcao)){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Usuário sem permissão de acesso", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
