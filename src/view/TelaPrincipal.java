/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.jmx.mbeanserver.JmxMBeanServer;
import controller.FactoryJDBC;
import controller.PermissaoAcesso;
import controller.VariavelGlobal;
import dao.BancoDadosDAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        URL url = this.getClass().getResource("/image/logoVerticalClean.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        
        Icon fundo = new ImageIcon(getClass().getResource("/image/logoHorizontalFundo.png"));
        
        jLabel1.setIcon(fundo);        
        
        habilitaMenu(false);
        
        if(validaArquivo()){
            Connection connection = FactoryJDBC.getConexaoMySQL();
            if(connection == null){
                JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados\n"
                                                + "Caso tenha dúvidas entre em contato com o administrador", "Acesso", JOptionPane.ERROR_MESSAGE);
                FactoryJDBC.fechaConexao();                            
            }
            else{
                TelaLogin telaLogin = new TelaLogin(this, true);
                telaLogin.show();
                if(telaLogin.getReturnStatus() == 1){
                    habilitaMenu(true);
                }else{
                    habilitaMenu(false);
                }
            }
        }
    }
    
    
    public void habilitaMenu(boolean habilita){
        menuTarefas.setEnabled(habilita);
        menuCadastros.setEnabled(habilita);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        cadastroUsuarios = new javax.swing.JMenuItem();
        cadatroEmitentes = new javax.swing.JMenuItem();
        cadastroItens = new javax.swing.JMenuItem();
        menuTarefas = new javax.swing.JMenu();
        importaEtiquetasIndividuais = new javax.swing.JMenuItem();
        imprimirEtiquetasIndividuais = new javax.swing.JMenuItem();
        imprimirEtiquetasCaixa = new javax.swing.JMenuItem();
        menuAbrirCaixa = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuConsultaEtiqueta = new javax.swing.JMenuItem();
        menuOpcoes = new javax.swing.JMenu();
        menuBanco = new javax.swing.JMenuItem();
        menuLogin = new javax.swing.JMenuItem();
        menuLogout = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestão77");
        setResizable(false);

        menuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        menuCadastros.setText("Cadastros");

        cadastroUsuarios.setText("Usuários");
        cadastroUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroUsuariosActionPerformed(evt);
            }
        });
        menuCadastros.add(cadastroUsuarios);

        cadatroEmitentes.setText("Emitentes");
        cadatroEmitentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadatroEmitentesActionPerformed(evt);
            }
        });
        menuCadastros.add(cadatroEmitentes);

        cadastroItens.setText("Itens");
        cadastroItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroItensActionPerformed(evt);
            }
        });
        menuCadastros.add(cadastroItens);

        menuBar.add(menuCadastros);

        menuTarefas.setText("Tarefas");

        importaEtiquetasIndividuais.setText("Importar Etiquetas Individuais");
        importaEtiquetasIndividuais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importaEtiquetasIndividuaisActionPerformed(evt);
            }
        });
        menuTarefas.add(importaEtiquetasIndividuais);

        imprimirEtiquetasIndividuais.setText("Imprimir Etiquetas Individuais");
        imprimirEtiquetasIndividuais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirEtiquetasIndividuaisActionPerformed(evt);
            }
        });
        menuTarefas.add(imprimirEtiquetasIndividuais);

        imprimirEtiquetasCaixa.setText("Imprimir Etiquetas de Caixa");
        imprimirEtiquetasCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirEtiquetasCaixaActionPerformed(evt);
            }
        });
        menuTarefas.add(imprimirEtiquetasCaixa);

        menuAbrirCaixa.setText("Desembalar Etiqueta");
        menuAbrirCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirCaixaActionPerformed(evt);
            }
        });
        menuTarefas.add(menuAbrirCaixa);

        jMenuItem1.setText("Imprimir Etiqueta Avulsa");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuTarefas.add(jMenuItem1);

        menuBar.add(menuTarefas);

        jMenu1.setText("Consultas");

        menuConsultaEtiqueta.setText("Histórico Etiqueta");
        menuConsultaEtiqueta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultaEtiquetaActionPerformed(evt);
            }
        });
        jMenu1.add(menuConsultaEtiqueta);

        menuBar.add(jMenu1);

        menuOpcoes.setText("Opções");

        menuBanco.setText("Configurações");
        menuBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBancoActionPerformed(evt);
            }
        });
        menuOpcoes.add(menuBanco);

        menuLogin.setText("Login");
        menuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoginActionPerformed(evt);
            }
        });
        menuOpcoes.add(menuLogin);

        menuLogout.setText("Logout");
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });
        menuOpcoes.add(menuLogout);

        menuBar.add(menuOpcoes);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addGap(356, 356, 356))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(163, 163, 163))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cadastroUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroUsuariosActionPerformed
        // TODO add your handling code here:
        if(PermissaoAcesso.isLiberado(VariavelGlobal.getInstancec().getUsuarioLogado(), "TelaCadastroUsuario")){
            TelaCadastroUsuario telaCadastroUsuario = new TelaCadastroUsuario();
            telaCadastroUsuario.show();
        }else{
            JOptionPane.showMessageDialog(null, "Usuário sem permissão de acesso", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cadastroUsuariosActionPerformed

    private void cadastroItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroItensActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaCadastroItem")){
            TelaCadastroItem telaCadastroItem = new TelaCadastroItem();
            telaCadastroItem.show();
        }
    }//GEN-LAST:event_cadastroItensActionPerformed

    private void cadatroEmitentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadatroEmitentesActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaCadastroEmitente")){
            TelaCadastroEmitente telaCadastroEmitente = new TelaCadastroEmitente();
            telaCadastroEmitente.show();
        }                
    }//GEN-LAST:event_cadatroEmitentesActionPerformed

    private void importaEtiquetasIndividuaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importaEtiquetasIndividuaisActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaImportaEtiquetaIndividual")){
            TelaImportaEtiquetaIndividual telaImportaEtiquetaIndividual = new TelaImportaEtiquetaIndividual(this, true);
            telaImportaEtiquetaIndividual.show();
        }
    }//GEN-LAST:event_importaEtiquetasIndividuaisActionPerformed

    private void imprimirEtiquetasIndividuaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirEtiquetasIndividuaisActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaImprimeEtiquetaIndividual")){
            TelaImprimeEtiquetaIndividual telaImprimeEtiquetaIndividual = new TelaImprimeEtiquetaIndividual(this, true);
            telaImprimeEtiquetaIndividual.show();
        }
    }//GEN-LAST:event_imprimirEtiquetasIndividuaisActionPerformed

    private void imprimirEtiquetasCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirEtiquetasCaixaActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaImprimeEtiquetaCaixa")){
            TelaImprimeEtiquetaCaixa telaImprimeEtiquetaCaixa = new TelaImprimeEtiquetaCaixa(this, true);
            telaImprimeEtiquetaCaixa.show();
        }
    }//GEN-LAST:event_imprimirEtiquetasCaixaActionPerformed

    private void menuBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBancoActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaEditaConfiguracaoBD")){
            TelaEditaConfiguracaoBD telaEditaConfiguracaoBD = new TelaEditaConfiguracaoBD(this, true);
            telaEditaConfiguracaoBD.show();
        }
    }//GEN-LAST:event_menuBancoActionPerformed

    private void menuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoginActionPerformed
        // TODO add your handling code here:
        TelaLogin telaLogin = new TelaLogin(this, true);
        telaLogin.show();
        if(telaLogin.getReturnStatus() == 1){
            habilitaMenu(true);
        }else{
            habilitaMenu(false);
        }
    }//GEN-LAST:event_menuLoginActionPerformed

    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        // TODO add your handling code here:
        habilitaMenu(false);
    }//GEN-LAST:event_menuLogoutActionPerformed

    private void menuAbrirCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirCaixaActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaDesembalaEtiqueta")){
            TelaDesembalaEtiqueta telaDesembalaEtiqueta = new TelaDesembalaEtiqueta(this, true);
            telaDesembalaEtiqueta.show();
        }
    }//GEN-LAST:event_menuAbrirCaixaActionPerformed

    private void menuConsultaEtiquetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultaEtiquetaActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaHistoricoEtiqueta")){
            TelaHistoricoEtiqueta telaHistoricoEtiqueta = new TelaHistoricoEtiqueta(this, true);
            telaHistoricoEtiqueta.show();
        }
    }//GEN-LAST:event_menuConsultaEtiquetaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(isLiberado("TelaImprimeEtiquetaAvulsa")){
            TelaImprimeEtiquetaAvulsa telaImprimeEtiquetaAvulsa = new TelaImprimeEtiquetaAvulsa(this, true);
            telaImprimeEtiquetaAvulsa.show();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
    
    private boolean validaArquivo(){
        boolean conecta;
        BancoDadosDAO bancoDadosDAO = new BancoDadosDAO();
        if(bancoDadosDAO.select() == null){
            JOptionPane.showMessageDialog(null, "Você será direcionado para a tela de configuração do banco de dados\n"
                                                + "Caso tenha dúvidas entre em contato com o administrador", "Primeiro Acesso", JOptionPane.WARNING_MESSAGE);
            TelaEditaConfiguracaoBD telaEditaConfiguracaoBD = new TelaEditaConfiguracaoBD(this, true);
            telaEditaConfiguracaoBD.show();
            conecta = telaEditaConfiguracaoBD.getReturnStatus() == 1;                                        
        }else{
            conecta = true;
        }
        
        return conecta;
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cadastroItens;
    private javax.swing.JMenuItem cadastroUsuarios;
    private javax.swing.JMenuItem cadatroEmitentes;
    private javax.swing.JMenuItem importaEtiquetasIndividuais;
    private javax.swing.JMenuItem imprimirEtiquetasCaixa;
    private javax.swing.JMenuItem imprimirEtiquetasIndividuais;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem menuAbrirCaixa;
    private javax.swing.JMenuItem menuBanco;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenuItem menuConsultaEtiqueta;
    private javax.swing.JMenuItem menuLogin;
    private javax.swing.JMenuItem menuLogout;
    private javax.swing.JMenu menuOpcoes;
    private javax.swing.JMenu menuTarefas;
    // End of variables declaration//GEN-END:variables

}