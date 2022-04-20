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
import model.ContadorPropriedade;

/**
 *
 * @author Administrador
 */
public class ContadorPropriedadeDAO {

    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }

    public void insert(ContadorPropriedade contadorPropriedade) {
        try {
            PreparedStatement p = conexao().prepareStatement("insert into contadorpropriedade "
                    + "(idLayout, propriedade, "
                    + "conteudo, contador) values (?,?,?,?)");
            p.setInt(1, contadorPropriedade.getIdLayout());
            p.setString(2, contadorPropriedade.getPropriedade());
            p.setString(3, contadorPropriedade.getConteudo());
            p.setInt(4, contadorPropriedade.getContador());

            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(ContadorPropriedadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(ContadorPropriedade contadorPropriedade) {
        try {
            PreparedStatement p = conexao().prepareStatement("update contadorpropriedade set idLayout = ?, "
                                                                + "propriedade = ?, "
                                                                + "conteudo = ?, "
                                                                + "contador = ? "
                                                                + "where idContadorPropriedade = ?");
            p.setInt(1, contadorPropriedade.getIdLayout());
            p.setString(2, contadorPropriedade.getPropriedade());
            p.setString(3, contadorPropriedade.getConteudo());
            p.setInt(4, contadorPropriedade.getContador());
            p.setInt(5, contadorPropriedade.getId());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(ContadorPropriedadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ContadorPropriedade selectWherePropriedade(int idLayout, String propriedade, String conteudo) {
        ContadorPropriedade contadorPropriedade = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from contadorpropriedade where idLayout = ? and propriedade = ? and conteudo = ?");
            p.setInt(1, idLayout);
            p.setString(2, propriedade);
            p.setString(3, conteudo);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                contadorPropriedade = new ContadorPropriedade();
                contadorPropriedade.setId(rs.getInt("idContadorPropriedade"));
                contadorPropriedade.setIdLayout(rs.getInt("idLayout"));
                contadorPropriedade.setPropriedade(rs.getString("propriedade"));
                contadorPropriedade.setConteudo(rs.getString("conteudo"));
                contadorPropriedade.setContador(rs.getInt("contador"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(ContadorPropriedadeDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return contadorPropriedade;

    }

    public List<ContadorPropriedade> selectAllLayoutPropriedades(int idLayout) {
        List<ContadorPropriedade> contadorPropriedades = new ArrayList<ContadorPropriedade>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from contadorpropriedade where idLayout = ?");
            p.setInt(1, idLayout);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                ContadorPropriedade contadorPropriedade = new ContadorPropriedade();
                contadorPropriedade.setId(rs.getInt("idContadorPropriedade"));
                contadorPropriedade.setIdLayout(rs.getInt("idLayout"));
                contadorPropriedade.setPropriedade(rs.getString("propriedade"));
                contadorPropriedade.setConteudo(rs.getString("conteudo"));
                contadorPropriedade.setContador(rs.getInt("contador"));
                contadorPropriedades.add(contadorPropriedade);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(ContadorPropriedadeDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return contadorPropriedades;
        
    }

}
