/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import gui.Login;

/**
 *
 * @author 14400
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            Library library = new Library();
            Item item = new Item("asdf", "adsfd", "adfadsf", "asdfad");
            library.connectToLibraryDatabase("admin", "admin");
            library.getAccountDetails(1234);
            library.getLoan(3333);
            library.addItem(item);
            //new Login().setVisible(true);
                

    }
    
}
