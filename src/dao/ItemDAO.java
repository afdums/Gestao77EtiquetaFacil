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
import model.Item;

/**
 *
 * @author Administrador
 */
public class ItemDAO {
    
    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }
    
    public void insert(Item item){
        try {
            PreparedStatement p = conexao().prepareStatement("insert into item (codigo, descricao, itemCliente, ean) values (?,?,?,?)");
            p.setString(1, item.getCodigo());
            p.setString(2, item.getDescricao());
            p.setString(3, item.getItemCliente());
            p.setString(4, item.getEan());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Item item) {
        try {
            PreparedStatement p = conexao().prepareStatement("update item set codigo = ?, "
                                                                + "descricao = ?, "
                                                                + "itemCliente = ?, "
                                                                + "ean = ? "
                                                                + "where idItem = ?");
            p.setString(1, item.getCodigo());
            p.setString(2, item.getDescricao());
            p.setString(3, item.getItemCliente());
            p.setString(4, item.getEan());
            p.setInt(5, item.getId());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Item item){
        try {
            PreparedStatement p = conexao().prepareStatement("delete from item where idItem = ?");
            p.setInt(1, item.getId());
            p.executeUpdate();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Item select(int id){
	Item item = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from item where idItem = ?");
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getInt("idItem"));
                item.setCodigo(rs.getString("codigo"));
                item.setDescricao(rs.getString("descricao"));
                item.setItemCliente(rs.getString("itemCliente"));
                item.setEan(rs.getString("ean"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return item;
    }    
    
    public Item selectWhereCodigo(String codigo){
	Item item = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from item where codigo = ?");
            p.setString(1, codigo);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getInt("idItem"));
                item.setCodigo(rs.getString("codigo"));
                item.setDescricao(rs.getString("descricao"));
                item.setItemCliente(rs.getString("itemCliente"));
                item.setEan(rs.getString("ean"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return item;            
        
    }
    
    public List<Item> selectAll(){
        
        List<Item> itens = new ArrayList<Item>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from item");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("idItem"));
                item.setCodigo(rs.getString("codigo"));
                item.setDescricao(rs.getString("descricao"));
                item.setItemCliente(rs.getString("itemCliente"));
                item.setEan(rs.getString("ean"));
                itens.add(item);
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return itens;
        
    }
    
}
