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
import model.EtiquetaPropriedade;

/**
 *
 * @author Administrador
 */
public class EtiquetaPropriedadeDAO {

    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }

    public void insert(EtiquetaPropriedade etiquetaPropriedade) {

        try {
            PreparedStatement p = conexao().prepareStatement("insert into etiquetaPropriedade (idEtiqueta, propriedade, conteudo) values (?,?,?)");
            p.setInt(1, etiquetaPropriedade.getIdEtiqueta());
            p.setString(2, etiquetaPropriedade.getPropriedade());
            p.setString(3, etiquetaPropriedade.getConteudo());
            p.executeUpdate();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertLote(List<EtiquetaPropriedade> propriedades) {

        propriedades.forEach((propriedade) -> {
            insert(propriedade);
        });

    }
    
    public void delete(EtiquetaPropriedade etiquetaPropriedade){
        try {
            PreparedStatement p = conexao().prepareStatement("delete from etiquetaPropriedade where idEtiquetaPropriedade = ?");
            p.setInt(1, etiquetaPropriedade.getId());
            p.executeUpdate();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void deleteLote(int idEtiqueta) {
        try {
            PreparedStatement p = conexao().prepareStatement("delete from etiquetaPropriedade where idEtiqueta = ?");
            p.setInt(1, idEtiqueta);
            p.executeUpdate();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       

    public void update(EtiquetaPropriedade etiquetaPropriedade) {
        try {
            PreparedStatement p = conexao().prepareStatement("update etiquetaPropriedade set propriedade = ?, "
                                                                + "conteudo = ? "
                                                                + "where idEtiquetaPropriedade = ?");
            p.setString(1, etiquetaPropriedade.getPropriedade());
            p.setString(3, etiquetaPropriedade.getConteudo());
            p.setInt(3, etiquetaPropriedade.getId());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EtiquetaPropriedade selectWherePropriedade(int idEtiqueta, String propriedade) {
        EtiquetaPropriedade etiquetaPropriedade = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiquetaPropriedade "
                                                                + "where idEtiqueta = ? "
                                                                + "and propriedade = ?");
            p.setInt(1, idEtiqueta);
            p.setString(2, propriedade);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                etiquetaPropriedade = new EtiquetaPropriedade();
                etiquetaPropriedade.setId(rs.getInt("idEtiquetaPropriedade"));
                etiquetaPropriedade.setIdEtiqueta(rs.getInt("idEtiqueta"));
                etiquetaPropriedade.setPropriedade(rs.getString("propriedade"));
                etiquetaPropriedade.setConteudo(rs.getString("conteudo"));                
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiquetaPropriedade;
    }

    public List<EtiquetaPropriedade> selectAllEtiquetaPropriedades(int idEtiqueta) {

        List<EtiquetaPropriedade> etiquetasPropriedade = new ArrayList<EtiquetaPropriedade>();
        
        try {
            PreparedStatement p = conexao().prepareStatement("select * from etiquetaPropriedade "
                                                                + "where idEtiqueta = ?");
            p.setInt(1, idEtiqueta);            
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                EtiquetaPropriedade etiquetaPropriedade = new EtiquetaPropriedade();
                etiquetaPropriedade.setId(rs.getInt("idEtiquetaPropriedade"));
                etiquetaPropriedade.setIdEtiqueta(rs.getInt("idEtiqueta"));
                etiquetaPropriedade.setPropriedade(rs.getString("propriedade"));
                etiquetaPropriedade.setConteudo(rs.getString("conteudo"));
                etiquetasPropriedade.add(etiquetaPropriedade);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return etiquetasPropriedade;
        
    }

}
