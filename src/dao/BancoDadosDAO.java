/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.FactoryJDBC;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BancoDados;

/**
 *
 * @author Administrador
 */
public class BancoDadosDAO {
    
    String arquivoConfiguracao;
    
    public BancoDadosDAO(){
        arquivoConfiguracao = "configbd.conf";
    }

    public boolean insert(BancoDados bancoDados) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(arquivoConfiguracao, false));
            out.write(bancoDados.getServidor());
            out.write("\n");
            out.write(bancoDados.getDatabase());
            out.write("\n");
            out.write(bancoDados.getUsuario());
            out.write("\n");
            out.write(bancoDados.getSenha());
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }        
    }

    public BancoDados select() {
        BancoDados bancoDados = null;
        String[] param = new String[4];
        int cont;
        
        try {
            FileReader f = new FileReader(arquivoConfiguracao);
            BufferedReader in = new BufferedReader(f);
            String linha = in.readLine();
            cont = 0;
            while (linha != null) {
                param[cont] = linha;
                System.out.println("Linha " + linha);
                cont++;                
                //System.out.println(linha);
                linha = in.readLine(); // retorna null se não houver mais linhas
            }
            for(int i = cont; i < 4; i++){
                System.out.println(i);
                param[i] = "";
            }
            in.close();
            bancoDados = new BancoDados(param[0], param[1], param[2], param[3]);
            //System.out.println(param[0]);
            //System.out.println("Achou BD: " + (bancoDados != null));
            System.out.println("Server: " + bancoDados.getServidor());
            System.out.println("Database: " + bancoDados.getDatabase());
            System.out.println("Usuário: " + bancoDados.getUsuario());
            System.out.println("Senha: " + bancoDados.getSenha());
            
        } catch (FileNotFoundException e) {
            System.out.println("test.txt não existe");
            return null;
        } catch (IOException e) {
            System.out.println("Erro de leitura");
            return null;
        }
        
        return bancoDados;

    }

}
