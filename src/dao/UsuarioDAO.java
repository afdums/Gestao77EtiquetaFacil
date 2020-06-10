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
import model.Usuario;

/**
 *
 * @author Administrador
 */
public class UsuarioDAO {

    public Connection conexao() {
        return FactoryJDBC.getConexaoMySQL();
    }

    public void fecha() {
        FactoryJDBC.fechaConexao();
    }

    public void insert(Usuario usuario) {
        try {
            PreparedStatement p = conexao().prepareStatement("insert into usuario (login, nome, "
                    + "email, senha, ativo) values (?,?,?,?,?)");
            p.setString(1, usuario.getLogin());
            p.setString(2, usuario.getNome());
            p.setString(3, usuario.getEmail());
            p.setString(4, usuario.getSenha());
            p.setBoolean(5, usuario.isAtivo());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Usuario usuario) {
        try {
            PreparedStatement p = conexao().prepareStatement("update usuario set login = ?, "
                    + "nome = ?, "
                    + "email = ?, "
                    + "senha = ?, "
                    + "ativo = ? "
                    + "where idUsuario = ?");
            p.setString(1, usuario.getLogin());
            p.setString(2, usuario.getNome());
            p.setString(3, usuario.getEmail());
            p.setString(4, usuario.getSenha());
            p.setBoolean(5, usuario.isAtivo());
            p.setInt(6, usuario.getId());
            p.executeUpdate();
            p.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(EmitenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario select(int id) {
        Usuario usuario = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from usuario where idUsuario = ?");
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAtivo(rs.getBoolean("ativo"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }

    public Usuario selectWhereLogin(String login) {
        Usuario usuario = null;
        try {
            PreparedStatement p = conexao().prepareStatement("select * from usuario where login = ?");
            p.setString(1, login);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAtivo(rs.getBoolean("ativo"));
            }
            rs.close();
            p.close();
            fecha();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;

    }

    public List<Usuario> selectAll() {

        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            PreparedStatement p = conexao().prepareStatement("select * from usuario");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuarios.add(usuario);
            }
            rs.close();
           // p.close();
           // fecha();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return usuarios;
    }

}
