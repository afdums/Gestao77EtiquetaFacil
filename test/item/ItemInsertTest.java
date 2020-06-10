/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import model.Item;
import dao.ItemDAO;

/**
 *
 * @author Administrador
 */
public class ItemInsertTest {
    
    public static void main(String args[]){
        
        ItemDAO itemDAO = new ItemDAO();
        
        Item item = new Item("050190", "Bobina 0,90 RC", "123456" ,"7896349600030");       
        
        itemDAO.insert(item);
        
    }
    
}
