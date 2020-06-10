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
import model.LayoutPropriedade;

/**
 *
 * @author Administrador
 */
public class LayoutPropriedadeDAO {

    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }

    public void insert(LayoutPropriedade layoutPropriedade) {
        try {
            PreparedStatement p = conexao().prepareStatement("insert into layoutpropriedade "
                    + "(idLayout, propriedade, "
                    + "origem, coluna, "
                    + "chaveUnica) values (?,?,?,?,?)");
            p.setInt(1, layoutPropriedade.getIdLayout());
            p.setString(2, layoutPropriedade.getPropriedade());
            p.setInt(3, layoutPropriedade.getOrigem());
            p.setInt(4, layoutPropriedade.getColuna());
            p.setBoolean(5, layoutPropriedade.isChaveUnica());

            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(LayoutPropriedadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertLote(List<LayoutPropriedade> propriedades) {

        propriedades.forEach((propriedade) -> {
            insert(propriedade);
        });

    }

    public void deleteLote(int idLayout) {
        try {
            PreparedStatement p = conexao().prepareStatement("delete from layoutpropriedade where idLayout = ?");
            p.setInt(1, idLayout);
            p.executeUpdate();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(LayoutPropriedadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(LayoutPropriedade layoutPropriedade) {
        try {
            PreparedStatement p = conexao().prepareStatement("update layoutpropriedade set idLayout = ?, "
                                                                + "propriedade = ?, "
                                                                + "origem = ?, "
                                                                + "coluna = ?, "
                                                                + "chaveUnica = ? "
                                                                + "where idlayoutpropriedade = ?");
            p.setInt(1, layoutPropriedade.getIdLayout());
            p.setString(2, layoutPropriedade.getPropriedade());
            p.setInt(3, layoutPropriedade.getOrigem());
            p.setInt(4, layoutPropriedade.getColuna());
            p.setBoolean(5, layoutPropriedade.isChaveUnica());
            p.setInt(6, layoutPropriedade.getId());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(LayoutPropriedadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LayoutPropriedade selectWherePropriedade(int idLayout, String propriedade) {
        LayoutPropriedade layoutPropriedade = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from layoutpropriedade where idLayout = ? and propriedade = ?");
            p.setInt(1, idLayout);
            p.setString(2, propriedade);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                layoutPropriedade = new LayoutPropriedade();
                layoutPropriedade.setId(rs.getInt("idLayoutPropriedade"));
                layoutPropriedade.setIdLayout(rs.getInt("idLayout"));
                layoutPropriedade.setPropriedade(rs.getString("propriedade"));
                layoutPropriedade.setOrigem(rs.getInt("origem"));
                layoutPropriedade.setColuna(rs.getInt("coluna"));
                layoutPropriedade.setChaveUnica(rs.getBoolean("chaveUnica"));                                
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(LayoutPropriedadeDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return layoutPropriedade;

    }

    public List<LayoutPropriedade> selectAllLayoutPropriedades(int idLayout) {
        List<LayoutPropriedade> layoutPropriedades = new ArrayList<LayoutPropriedade>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from layoutpropriedade where idLayout = ?");
            p.setInt(1, idLayout);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                LayoutPropriedade layoutPropriedade = new LayoutPropriedade();
                layoutPropriedade.setId(rs.getInt("idLayoutPropriedade"));
                layoutPropriedade.setIdLayout(rs.getInt("idLayout"));
                layoutPropriedade.setPropriedade(rs.getString("propriedade"));
                layoutPropriedade.setOrigem(rs.getInt("origem"));
                layoutPropriedade.setColuna(rs.getInt("coluna"));
                layoutPropriedade.setChaveUnica(rs.getBoolean("chaveUnica"));                                
                layoutPropriedades.add(layoutPropriedade);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(LayoutPropriedadeDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return layoutPropriedades;
        
    }

}
