/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;

/**
 *
 * @author 14400
 */
public class Library {
    
    private String Name;
    private int Code;
    public Item item = null;
    public Account account;
    public Loan loan;

    
    public Library(){};
    
    public Connection connectToLibraryDatabase(String username_input, String password_input){
   
                Properties prop = new Properties();
                prop.put("user", username_input);
                prop.put("password", password_input);
                String databaseURL = "jdbc:mysql://bible.cvtfhbljhzkg.ap-southeast-2.rds.amazonaws.com:3306/library";
                Connection conn = null;

                    try {   
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection(databaseURL, prop);
                        System.out.print("success");
                       
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                    }                  
                    // Do something with the Connection
                    catch (SQLException ex) {
//                      // handle any errors
                     System.out.println("SQLException: " + ex.getMessage());              
                        
                      System.out.println("SQLState: " + ex.getSQLState());
                      System.out.println("VendorError: " + ex.getErrorCode());
                    }
                return conn;
    }
    public String addItem(Item item, String table){
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        String message_from_server = null;
        String identifier = item.getIdentifier();
        String title = item.getTitle();
        String author = item.getAuthor();
        String keywords = item.getKeywords();
        String type = item.getType(); 
        String status = item.getStatus();
        try {
            String query = "insert into " + table + "  (Identifier, Title, Author, Keywords, Type,  Status) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, identifier);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setString(4, keywords);
            stmt.setString(5, type);
            stmt.setString(6, status);
            stmt.execute();
            message_from_server = "item successfully added";
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          String exception = ("SQLException: " + ex.getMessage());                 
          String state = ("SQLState: " + ex.getSQLState());
          String vendor = ("VendorError: " + ex.getErrorCode());
          message_from_server = exception + " " + state + " " + vendor;
        }
        return message_from_server;
    }
    
    public String removeItem(String identifier) {
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        String message_from_server = null;
        try {
            String query = "delete from Item where Identifier = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, identifier);
            stmt.execute();
            message_from_server = "item successfully deleted";
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          String exception = ("SQLException: " + ex.getMessage());                 
          String state = ("SQLState: " + ex.getSQLState());
          String vendor = ("VendorError: " + ex.getErrorCode());
          message_from_server = exception + " " + state + " " + vendor;

        }
        return message_from_server;
    }
    
    public Item getItem(String identifier){
        String message_from_server = null;
        Connection conn = this.connectToLibraryDatabase("admin", "admin");    
        try {
            String query = "select * from Item where Identifier='" + identifier + "'" ;
            PreparedStatement stmt = conn.prepareStatement(query);
           // stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next() == false){
                return null;
            } else{
                do {
                String title = rs.getString("Title").toString();
                String author = rs.getString("Author").toString();
                String keywords = rs.getString("Keywords").toString();
                String type = rs.getString("Type").toString();
                String status = rs.getString("Status").toString();
                item = new Item(identifier, title, author, keywords, type, status);
                } while(rs.next()); 
            }
            
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          String exception = ("SQLException: " + ex.getMessage());                 
          String state = ("SQLState: " + ex.getSQLState());
          String vendor = ("VendorError: " + ex.getErrorCode());
          message_from_server = exception + " " + state + " " + vendor;
          System.out.print(message_from_server);
        }
        return item;
    }
    
    private void addLoan(){

        
    }
    
    public HashMap getLoan(int x){
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        HashMap<Integer, ArrayList> hashmap = new HashMap<Integer, ArrayList>();
        ArrayList arraylist = new ArrayList(){};
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Loan where LoanNo = " + x);
            while(rs.next())  {     
                String a = rs.getString("Item").toString();
                String b = rs.getString("DueDate").toString();
                String c = rs.getString("Type").toString();
                System.out.print(a + b + c);
                arraylist.add(a);
                arraylist.add(b);
                arraylist.add(c);

            }
            
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        hashmap.put(x, arraylist);
        return hashmap;
    }
    
    private void removeLoan(){
    }
    
    private void addAcount(){
    }
    
    private void removeAccount(){
    }
    
 
    
    
}
