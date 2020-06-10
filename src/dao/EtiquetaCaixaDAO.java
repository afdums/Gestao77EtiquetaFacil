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
import model.EtiquetaCaixa;

/**
 *
 * @author Administrador
 */
public class EtiquetaCaixaDAO {

    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }

    public void insert(EtiquetaCaixa etiquetaCaixa) {
        try {
            PreparedStatement p = conexao().prepareStatement("insert into etiquetaCaixa (idCaixa, idIndividual) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            p.setInt(1, etiquetaCaixa.getIdCaixa());
            p.setInt(2, etiquetaCaixa.getIdIndividual());
            p.executeUpdate();
            final ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                final int lastId = rs.getInt(1);
                etiquetaCaixa.setIdEtiquetaCaixa(lastId);
            }
            p.close();
            fecha();
            
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertLote(List<EtiquetaCaixa> etiquetasCaixas) {

        etiquetasCaixas.forEach((etiquetaCaixa) -> {
            insert(etiquetaCaixa);
        });

    }

    /*public void update(Etiqueta etiqueta) {
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
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    /*public void updateLote(List<Etiqueta> etiquetas) {

        etiquetas.forEach((etiqueta) -> {
            update(etiqueta);
        });

    }*/
    
    public void delete(EtiquetaCaixa etiquetaCaixa){
        try {
            PreparedStatement p = conexao().prepareStatement("delete from etiquetaCaixa where idEtiquetaCaixa = ?");
            p.setInt(1, etiquetaCaixa.getIdEtiquetaCaixa());
            p.executeUpdate();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EtiquetaCaixa select(int id) {
        EtiquetaCaixa etiquetaCaixa = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiquetaCaixa where idEtiquetaCaixa = ?");
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                etiquetaCaixa = new EtiquetaCaixa();
                etiquetaCaixa.setIdEtiquetaCaixa(rs.getInt("idEtiquetaCaixa"));
                etiquetaCaixa.setIdCaixa(rs.getInt("idCaixa"));
                etiquetaCaixa.setIdIndividual(rs.getInt("idIndividual"));                
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiquetaCaixa;
    }

    public List<EtiquetaCaixa> selectWhereCaixa(int idCaixa) {

        List<EtiquetaCaixa> etiquetasCaixas = new ArrayList<EtiquetaCaixa>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiquetaCaixa "
                                                                + "WHERE idCaixa = ?");
            p.setInt(1, idCaixa);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                EtiquetaCaixa etiquetaCaixa = new EtiquetaCaixa();
                etiquetaCaixa.setIdEtiquetaCaixa(rs.getInt("idEtiquetaCaixa"));
                etiquetaCaixa.setIdCaixa(rs.getInt("idCaixa"));
                etiquetaCaixa.setIdIndividual(rs.getInt("idIndividual"));
                etiquetasCaixas.add(etiquetaCaixa);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiquetasCaixas;

    }
 
    public List<EtiquetaCaixa> selectAll() {
        List<EtiquetaCaixa> etiquetasCaixas = new ArrayList<EtiquetaCaixa>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiquetaCaixa");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                EtiquetaCaixa etiquetaCaixa = new EtiquetaCaixa();
                etiquetaCaixa.setIdEtiquetaCaixa(rs.getInt("idEtiquetaCaixa"));
                etiquetaCaixa.setIdCaixa(rs.getInt("idCaixa"));
                etiquetaCaixa.setIdIndividual(rs.getInt("idIndividual"));
                etiquetasCaixas.add(etiquetaCaixa);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaCaixaDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiquetasCaixas;

    }

}
