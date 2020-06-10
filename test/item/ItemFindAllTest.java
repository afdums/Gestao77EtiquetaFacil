/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import dao.ItemDAO;
import java.util.List;
import model.Item;

/**
 *
 * @author Administrador
 */
public class ItemFindAllTest {
    
    public static void main(String args[]){
        ItemDAO itemDAO = new ItemDAO();
        List<Item> itens = itemDAO.selectAll();
        
        for(int i = 0; i < itens.size(); i++){
            System.out.println("Item[" + i + "]: " + itens.get(i).getId() + " - " + itens.get(i).getCodigo()+ " - " + itens.get(i).getDescricao());            
        }
    }
    
}
