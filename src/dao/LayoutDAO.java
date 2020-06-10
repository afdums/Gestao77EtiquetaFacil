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
import model.Etiqueta;
import model.Layout;

/**
 *
 * @author Administrador
 */
public class LayoutDAO {
    
    String status = "";
    
    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }
    
    public void insert(Layout layout){
        try {
            PreparedStatement p = conexao().prepareStatement("insert into layout "
                                                                + "(codigo, tipo, quantidadeCaixa, "
                                                                + "validaImpressaoCaixa, fonteLayout, "
                                                                + "impressora, pulaEtiqueta, idEmitente, comprimentoChave) "
                                                                + "values (?,?,?,?,?,?,?,?,?)",
                                                                PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, layout.getCodigo());
            p.setInt(2, layout.getTipo());
            p.setInt(3, layout.getQuantidadeCaixa());
            p.setBoolean(4, layout.isValidaImpressaoCaixa());
            p.setString(5, layout.getFonteLayout());
            p.setString(6, layout.getImpressora());
            p.setInt(7, layout.getPulaEtiqueta());
            p.setInt(8, layout.getIdEmitente());
            p.setInt(9, layout.getComprimentoChave());
            p.executeUpdate();
            final ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                final int lastId = rs.getInt(1);
                System.out.println("Id: " + lastId);
                layout.setId(lastId);
            }
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Layout layout) {
        try {
            PreparedStatement p = conexao().prepareStatement("update layout "
                                                                + "set codigo = ?, tipo = ?, quantidadeCaixa = ?, "
                                                                + "validaImpressaoCaixa = ?, fonteLayout = ?, "
                                                                + "impressora = ?, pulaEtiqueta = ?, comprimentoChave = ? "
                                                                + "where idLayout = ?");
            p.setString(1, layout.getCodigo());
            p.setInt(2, layout.getTipo());
            p.setInt(3, layout.getQuantidadeCaixa());
            p.setBoolean(4, layout.isValidaImpressaoCaixa());
            p.setString(5, layout.getFonteLayout());
            p.setString(6, layout.getImpressora());
            p.setInt(7, layout.getPulaEtiqueta());
            p.setInt(8, layout.getComprimentoChave());
            p.setInt(9, layout.getId());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(LayoutDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean delete(Layout layout){
        EtiquetaDAO etiquetaDAO = new EtiquetaDAO();
        List<Etiqueta> etiquetas = etiquetaDAO.selectWhereLayout(layout.getId());
        if(etiquetas.isEmpty()){
            try {
                PreparedStatement p = conexao().prepareStatement("delete from layout where idLayout = ?");
                p.setInt(1, layout.getId());
                p.executeUpdate();
                p.close();
                status = "";
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LayoutDAO.class.getName()).log(Level.SEVERE, null, ex);
                status = ex.toString();
                return false;
            }
        }else{
            status = "Layout possui etiquetas relacionadas";
            return false;
        }
    }
    
    public String retornaStatus(){
        return status;
    }
    
    public Layout select(int id){
	Layout layout = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from layout where idLayout = ?");
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                layout = new Layout();
                layout.setId(rs.getInt("id"));
                layout.setId(rs.getInt("idEmitente"));
                layout.setCodigo(rs.getString("codigo"));
                layout.setTipo(rs.getInt("tipo"));
                layout.setQuantidadeCaixa(rs.getInt("quantidadeCaixa"));
                layout.setValidaImpressaoCaixa(rs.getBoolean("validaImpressaoCaixa"));
                layout.setFonteLayout(rs.getString("fonteLayout"));
                layout.setImpressora(rs.getString("impressora"));
                layout.setPulaEtiqueta(rs.getInt("pulaEtiqueta"));                
                layout.setComprimentoChave(rs.getInt("comprimentoChave"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(LayoutDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return layout;
    }    
    
    public Layout selectWhereCodigo(int idEmitente, String codigo){
	Layout layout = null;
        System.out.println("Codigo: " + codigo);
        try {
            PreparedStatement p = conexao().prepareStatement("select * from layout where idEmitente = ? and codigo = ?");
            p.setInt(1, idEmitente);
            p.setString(2, codigo);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                layout = new Layout();
                layout.setId(rs.getInt("idLayout"));
                layout.setIdEmitente(rs.getInt("idEmitente"));
                layout.setCodigo(rs.getString("codigo"));
                layout.setTipo(rs.getInt("tipo"));
                layout.setQuantidadeCaixa(rs.getInt("quantidadeCaixa"));
                layout.setValidaImpressaoCaixa(rs.getBoolean("validaImpressaoCaixa"));
                layout.setFonteLayout(rs.getString("fonteLayout"));
                layout.setImpressora(rs.getString("impressora"));
                layout.setPulaEtiqueta(rs.getInt("pulaEtiqueta"));
                layout.setComprimentoChave(rs.getInt("comprimentoChave"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(LayoutDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return layout;
        
    }
    
    public List<Layout> selectAllEmitente(int idEmitente){
        
        List<Layout> layouts = new ArrayList<Layout>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from layout "
                                                                + "WHERE idEmitente = ?");
            p.setInt(1, idEmitente);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Layout layout = new Layout();                
                layout.setId(rs.getInt("idLayout"));
                layout.setIdEmitente(rs.getInt("idEmitente"));
                layout.setCodigo(rs.getString("codigo"));
                layout.setTipo(rs.getInt("tipo"));
                layout.setQuantidadeCaixa(rs.getInt("quantidadeCaixa"));
                layout.setValidaImpressaoCaixa(rs.getBoolean("validaImpressaoCaixa"));
                layout.setFonteLayout(rs.getString("fonteLayout"));
                layout.setImpressora(rs.getString("impressora"));
                layout.setPulaEtiqueta(rs.getInt("pulaEtiqueta"));
                layout.setComprimentoChave(rs.getInt("comprimentoChave"));
                layouts.add(layout);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(LayoutDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return layouts;
    }
    
}
