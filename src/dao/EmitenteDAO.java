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
import model.Emitente;
import model.Layout;

/**
 *
 * @author Administrador
 */
public class EmitenteDAO {
    
    String status = "";

    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }

    public void insert(Emitente emitente) {
        try {
            PreparedStatement p = conexao().prepareStatement("insert into emitente (cnpj, razao) values (?,?)");
            p.setString(1, emitente.getCnpj());
            p.setString(2, emitente.getRazao());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Emitente emitente) {
        try {
            PreparedStatement p = conexao().prepareStatement("update emitente set cnpj = ?, razao = ? where idEmitente = ?");
            p.setString(1, emitente.getCnpj());
            p.setString(2, emitente.getRazao());
            p.setInt(3, emitente.getId());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean delete(Emitente emitente){
        LayoutDAO layoutDAO = new LayoutDAO();
        List<Layout> layouts = layoutDAO.selectAllEmitente(emitente.getId());
        if(layouts.isEmpty()){
            try {
                PreparedStatement p = conexao().prepareStatement("delete from emitente where idEmitente = ?");
                p.setInt(1, emitente.getId());
                p.executeUpdate();
                p.close();
                status = "";
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
                status = ex.toString();
                return false;
            }
        }else{
            status = "Emitente possui layouts relacionados";
            return false;
        }
    }
    
    public String retornaStatus(){
        return status;
    }
    

    public Emitente select(int id) {
        Emitente emitente = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from emitente where idEmitente = ?");
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                emitente = new Emitente();
                emitente.setId(rs.getInt("idEmitente"));
                emitente.setCnpj(rs.getString("cnpj"));
                emitente.setRazao(rs.getString("razao"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return emitente;
    }

    public List<Emitente> selectAll() {
        List<Emitente> emitentes = new ArrayList<Emitente>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from emitente");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Emitente emitente = new Emitente();
                emitente.setId(rs.getInt("idEmitente"));
                emitente.setCnpj(rs.getString("cnpj"));
                emitente.setRazao(rs.getString("razao"));
                emitentes.add(emitente);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return emitentes;

    }

    public Emitente selectWhereCnpj(String cnpj) {
        Emitente emitente = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from emitente where cnpj = ?");
            p.setString(1, cnpj);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                emitente = new Emitente();
                emitente.setId(rs.getInt("idEmitente"));
                emitente.setCnpj(rs.getString("cnpj"));
                emitente.setRazao(rs.getString("razao"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return emitente;

    }

}
