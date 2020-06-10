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
import model.Permissao;

/**
 *
 * @author Administrador
 */
public class PermissaoDAO {

    String status = "";

    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }

    public void insert(Permissao permissao) {
        try {
            PreparedStatement p = conexao().prepareStatement("insert into permissao (login, funcao) values (?,?)");
            p.setString(1, permissao.getLogin());
            p.setString(2, permissao.getFuncao());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(PermissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertLote(List<Permissao> permissoes) {
        permissoes.forEach((Permissao permissao) -> {
            insert(permissao);
        });        
    }

    public boolean deleteUsuario(String login) {

        try {
            PreparedStatement p = conexao().prepareStatement("delete from permissao where login = ?");
            p.setString(1, login);
            p.executeUpdate();
            p.close();
            fecha();
            status = "";
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PermissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            status = ex.toString();
            return false;
        }

    }

    public String retornaStatus() {
        return status;
    }

    public List<String> selectAll(String login) {
        List<String> permissoes = new ArrayList<String>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from permissao where login = ?");
            p.setString(1, login);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                permissoes.add(rs.getString("funcao"));                
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(PermissaoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return permissoes;

    } 
    
    public boolean isLiberado(String login, String funcao) {
        boolean liberado;
        
        liberado = false;
        try {
            PreparedStatement p = conexao().prepareStatement("select count(*) as total from permissao where login = ? and funcao = ?");
            p.setString(1, login);
            p.setString(2, funcao);
            ResultSet rs = p.executeQuery();
            if(rs.next())
                liberado = rs.getInt("total") > 0;
            else
                liberado = false;
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(PermissaoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return liberado;

    }   

}
