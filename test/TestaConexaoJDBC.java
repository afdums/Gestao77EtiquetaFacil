
import controller.FactoryJDBC;
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andersond
 */
public class TestaConexaoJDBC {  
    public static void main(String[] args){
        Connection connection = FactoryJDBC.getConexaoMySQL();
        System.out.println(FactoryJDBC.statusConection());        
        FactoryJDBC.fechaConexao();
    }
}
