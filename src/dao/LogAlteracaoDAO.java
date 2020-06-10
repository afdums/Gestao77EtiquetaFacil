/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.FactoryJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LogAlteracao;

/**
 *
 * @author dums
 */
public class LogAlteracaoDAO {
    
    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }
    
    public void insert(LogAlteracao logAlteracao){
        try {
            PreparedStatement p = conexao().prepareStatement("insert into logalteracao (login, tabela, data, chave, alteracao) values (?,?,?,?,?)");
            p.setString(1, logAlteracao.getLogin());
            p.setString(2, logAlteracao.getTabela());
            p.setString(3, logAlteracao.getData());
            p.setString(4, logAlteracao.getChave());
            p.setString(5, logAlteracao.getAlteracao());            
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<LogAlteracao> selectAll(String tabela, String chave){
        
        List<LogAlteracao> logAlteracoes = new ArrayList<>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from logalteracao where tabela = ? and chave = ?");
            p.setString(1, tabela);
            p.setString(2, chave);
                    
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                LogAlteracao logAlteracao = new LogAlteracao();
                logAlteracao.setId(rs.getInt("id"));
                logAlteracao.setLogin(rs.getString("login"));
                logAlteracao.setTabela(rs.getString("tabela"));
                logAlteracao.setData(rs.getString("data"));
                logAlteracao.setChave(rs.getString("chave"));
                logAlteracao.setAlteracao(rs.getString("alteracao"));
                logAlteracoes.add(logAlteracao);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(LogAlteracaoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return logAlteracoes;
        
    }
    
}
