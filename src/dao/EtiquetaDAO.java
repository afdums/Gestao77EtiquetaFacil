/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.FactoryJDBC;
import controller.VariavelGlobal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Etiqueta;
import model.LogAlteracao;
import util.DataUtil;

/**
 *
 * @author Administrador
 */
public class EtiquetaDAO {

    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }

    public void insert(Etiqueta etiqueta) {
        try {
            Etiqueta etiquetaExiste = selectWhereEmitenteChave(etiqueta.getIdEmitente(), etiqueta.getChaveUnica());
            
            if(etiquetaExiste == null){
                PreparedStatement p = conexao().prepareStatement("insert into etiqueta (conteudo, idLayout, situacao, idEmitente, chaveUnica) values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, etiqueta.getConteudo());
                p.setInt(2, etiqueta.getIdLayout());
                p.setInt(3, etiqueta.getSituacao());
                p.setInt(4, etiqueta.getIdEmitente());
                p.setString(5, etiqueta.getChaveUnica());
                p.executeUpdate();
                final ResultSet rs = p.getGeneratedKeys();
                if (rs.next()) {
                    final int lastId = rs.getInt(1);
                    etiqueta.setId(lastId);
                }                
                p.close();
                fecha();
                LogAlteracao logAlteracao = new LogAlteracao();
                logAlteracao.setLogin(VariavelGlobal.getInstancec().getUsuarioLogado());
                logAlteracao.setTabela("etiqueta");
                logAlteracao.setData(DataUtil.dataHoraAtual());
                logAlteracao.setChave(etiqueta.getChaveUnica());
                logAlteracao.setAlteracao("Inclusao : " + etiqueta.toString());
                LogAlteracaoDAO logAlteracaoDAO = new LogAlteracaoDAO();
                logAlteracaoDAO.insert(logAlteracao);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertLote(List<Etiqueta> etiquetas) {

        etiquetas.forEach((etiqueta) -> {
            Etiqueta etiquetaAux = selectWhereEmitenteChave(etiqueta.getIdEmitente(), etiqueta.getChaveUnica());
            if(etiquetaAux != null){
                etiqueta.setId(0);
                //etiqueta já existe não insere uma nova
            }else{
                insert(etiqueta);
            }
        });

    }

    public void update(Etiqueta etiqueta) {
        try {
            PreparedStatement p = conexao().prepareStatement("update etiqueta set conteudo = ?, "
                                                                + "idLayout = ?, situacao = ?, "
                                                                + "chaveUnica = ? "
                                                                + "where idEtiqueta = ?");
            p.setString(1, etiqueta.getConteudo());
            p.setInt(2, etiqueta.getIdLayout());
            p.setInt(3, etiqueta.getSituacao());
            p.setString(4, etiqueta.getChaveUnica());
            p.setInt(5, etiqueta.getId());
            p.executeUpdate();
            p.close();
            fecha();
            LogAlteracao logAlteracao = new LogAlteracao();
            logAlteracao.setLogin(VariavelGlobal.getInstancec().getUsuarioLogado());
            logAlteracao.setTabela("etiqueta");
            logAlteracao.setData(DataUtil.dataHoraAtual());
            logAlteracao.setChave(etiqueta.getChaveUnica());
            logAlteracao.setAlteracao("Alteracao : " + etiqueta.toString());
            LogAlteracaoDAO logAlteracaoDAO = new LogAlteracaoDAO();
            logAlteracaoDAO.insert(logAlteracao);
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateLote(List<Etiqueta> etiquetas) {

        etiquetas.forEach((etiqueta) -> {
            update(etiqueta);
        });

    }
    
    public void delete(Etiqueta etiqueta){
        try {
            PreparedStatement p = conexao().prepareStatement("delete from etiqueta where idEtiqueta = ?");
            p.setInt(1, etiqueta.getId());
            p.executeUpdate();
            p.close();
            LogAlteracao logAlteracao = new LogAlteracao();
            logAlteracao.setLogin(VariavelGlobal.getInstancec().getUsuarioLogado());
            logAlteracao.setTabela("etiqueta");
            logAlteracao.setData(DataUtil.dataHoraAtual());
            logAlteracao.setChave(etiqueta.getChaveUnica());
            logAlteracao.setAlteracao("Exclusao : " + etiqueta.toString());
            LogAlteracaoDAO logAlteracaoDAO = new LogAlteracaoDAO();
            logAlteracaoDAO.insert(logAlteracao);
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Etiqueta select(int id) {
        Etiqueta etiqueta = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiqueta where idEtiqueta = ?");
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                etiqueta = new Etiqueta();
                etiqueta.setId(rs.getInt("idEtiqueta"));
                etiqueta.setConteudo(rs.getString("conteudo"));
                etiqueta.setChaveUnica(rs.getString("chaveUnica"));
                etiqueta.setIdEmitente(rs.getInt("idEmitente"));
                etiqueta.setIdLayout(rs.getInt("idLayout"));
                etiqueta.setSituacao(rs.getInt("situacao"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiqueta;
    }

    public List<Etiqueta> selectWhereLayout(int idLayout) {

        List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiqueta "
                                                                + "WHERE idLayout = ?");
            p.setInt(1, idLayout);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Etiqueta etiqueta = new Etiqueta();
                etiqueta.setId(rs.getInt("idEtiqueta"));
                etiqueta.setConteudo(rs.getString("conteudo"));
                etiqueta.setChaveUnica(rs.getString("chaveUnica"));
                etiqueta.setIdEmitente(rs.getInt("idEmitente"));
                etiqueta.setIdLayout(rs.getInt("idLayout"));
                etiqueta.setSituacao(rs.getInt("situacao"));
                etiquetas.add(etiqueta);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiquetas;

    }

    public List<Etiqueta> selectWhereLayoutSituacao(int idLayout, int situacao) {

        List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiqueta "
                                                                + "WHERE idLayout = ? "
                                                                + "AND situacao = ?");
            p.setInt(1, idLayout);
            p.setInt(2, situacao);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Etiqueta etiqueta = new Etiqueta();
                etiqueta.setId(rs.getInt("idEtiqueta"));
                etiqueta.setConteudo(rs.getString("conteudo"));
                etiqueta.setChaveUnica(rs.getString("chaveUnica"));
                etiqueta.setIdEmitente(rs.getInt("idEmitente"));
                etiqueta.setIdLayout(rs.getInt("idLayout"));
                etiqueta.setSituacao(rs.getInt("situacao"));
                etiquetas.add(etiqueta);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiquetas;

    }

    public Etiqueta selectWhereCodigo(int idLayout, String conteudo) {

        Etiqueta etiqueta = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiqueta "
                                                                + "where idLayout = ? "
                                                                + "and conteudo = ?");
            p.setInt(1, idLayout);
            p.setString(2, conteudo);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                etiqueta = new Etiqueta();
                etiqueta.setId(rs.getInt("idEtiqueta"));
                etiqueta.setConteudo(rs.getString("conteudo"));
                etiqueta.setChaveUnica(rs.getString("chaveUnica"));
                etiqueta.setIdEmitente(rs.getInt("idEmitente"));
                etiqueta.setIdLayout(rs.getInt("idLayout"));
                etiqueta.setSituacao(rs.getInt("situacao"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiqueta;

    }
    
    public Etiqueta selectWhereEmitenteChave(int idEmitente, String chaveUnica) {

        Etiqueta etiqueta = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiqueta "
                                                                + "where idEmitente = ? "
                                                                + "and chaveUnica = ?");
            p.setInt(1, idEmitente);
            p.setString(2, chaveUnica);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                etiqueta = new Etiqueta();
                etiqueta.setId(rs.getInt("idEtiqueta"));
                etiqueta.setConteudo(rs.getString("conteudo"));
                etiqueta.setChaveUnica(rs.getString("chaveUnica"));
                etiqueta.setIdEmitente(rs.getInt("idEmitente"));
                etiqueta.setIdLayout(rs.getInt("idLayout"));
                etiqueta.setSituacao(rs.getInt("situacao"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiqueta;

    }

    public List<Etiqueta> selectAll() {
        List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiqueta");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Etiqueta etiqueta = new Etiqueta();
                etiqueta.setId(rs.getInt("idEtiqueta"));
                etiqueta.setConteudo(rs.getString("conteudo"));
                etiqueta.setChaveUnica(rs.getString("chaveUnica"));
                etiqueta.setIdEmitente(rs.getInt("idEmitente"));
                etiqueta.setIdLayout(rs.getInt("idLayout"));
                etiqueta.setSituacao(rs.getInt("situacao"));
                etiquetas.add(etiqueta);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiquetas;

    }

}
