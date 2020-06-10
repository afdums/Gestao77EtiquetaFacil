/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BancoDadosDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BancoDados;

/**
 *
 * @author andersond
 */
public class FactoryJDBC {

    private static String status = "Não conectou...";
    private static BancoDados bancoDados = null;
    
    //atributo do tipo Connection
    private static Connection connection = null;

//Método Construtor da Classe//
    private FactoryJDBC() {
        if(bancoDados == null){
            BancoDadosDAO bancoDadosDAO = new BancoDadosDAO();
            bancoDados = bancoDadosDAO.select();
        }

    }

    /*private static BancoDados recuperaConfiguracao() {        
        return bancoDados;
    }*/

    //Método de Conexão//
    public static Connection getConexaoMySQL() {
        
        if(bancoDados == null){
            BancoDadosDAO bancoDadosDAO = new BancoDadosDAO();
            bancoDados = bancoDadosDAO.select();
        }

        if (connection == null)
            connection = conecta(bancoDados);
        
        return connection;

    }

    //Método que retorna o status da sua conexão//
    public static String statusConection() {

        return status;

    }

    private static Connection conecta(BancoDados bancoDados) {
       

        try {
            
            System.out.println("Achou Conf: " + (bancoDados != null));
            System.out.println("Server: " + bancoDados.getServidor());
            System.out.println("Database: " + bancoDados.getDatabase());
            System.out.println("Usuário: " + bancoDados.getUsuario());
            System.out.println("Senha: " + bancoDados.getSenha());

            String driverName = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://" + bancoDados.getServidor() + "/" + bancoDados.getDatabase();

            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            
            System.out.println("1: " + gregorianCalendar.getTimeInMillis());
            Class.forName(driverName);
            
            gregorianCalendar = new GregorianCalendar();
            System.out.println("2: " + gregorianCalendar.getTimeInMillis());
            
            connection = DriverManager.getConnection(url, bancoDados.getUsuario(), bancoDados.getSenha());
            
            gregorianCalendar = new GregorianCalendar();
            System.out.println("3: " + gregorianCalendar.getTimeInMillis());
            
            if (connection != null) {
                status = "OK : Conectado com sucesso!";
            } else {
                status = "NOK : Não foi possivel realizar conexão";
            }
            return connection;
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            status = "NOK : O driver expecificado não foi encontrado.";
            return null;
        } catch (SQLException e) {
            status = "NOK : Não foi possível conectar ao Banco de Dados.";
            return null;
        }
    }

    public static boolean testaConexao(BancoDados bancoDados) {
        return conecta(bancoDados)!= null;
    }

    //Método que fecha sua conexão//
    public static boolean fechaConexao() {

        /*try {

            if(connection != null)
                connection.close();
            
            connection = null;
        */
            return true;

        /*} catch (SQLException e) {

            return false;

        }*/

    }

    //Método que reinicia sua conexão//
    public static Connection reiniciaConexao() {
        
        
        fechaConexao();

        return getConexaoMySQL();

    }

}
