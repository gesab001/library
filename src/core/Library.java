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
    public Item item;
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
        String title = item.title;
        String identifier = item.identifier;
        String status = item.status;
        String type = item.type; 
                try {
            String query = "insert into " + table + "  (Title, Type, Identifier, Status) values (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, identifier);
            stmt.setString(3, status);
            stmt.setString(4, type);
            stmt.execute();
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        System.out.print("successfully added");
        return "successfully added";
    }
    
    public String removeItem(String identifier, String table) {
        Connection conn = this.connectToLibraryDatabase("gesab001", "ch5t8k4u");

                try {
            String query = "delete from " + table + " where Identifier = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, table);
            stmt.setString(2, identifier);
            stmt.execute();
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        System.out.print("successfully deleted");
        return "successfully deleted";
    }
    
    private HashMap getItem(Item item){
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        HashMap<String, ArrayList> hashmap = new HashMap<String, ArrayList>();
        ArrayList arraylist = new ArrayList(){};
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Item where LoanNo = " + item.identifier);
            while(rs.next())  {     
                String a = rs.getString("Title").toString();
                String b = rs.getString("Type").toString();
                String c = rs.getString("Identifier").toString();
                String d = rs.getString("Status").toString();
                System.out.print(a + b + c + d);
                arraylist.add(a);
                arraylist.add(b);
                arraylist.add(c);
                arraylist.add(d);

            }
            
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        hashmap.put(item.identifier.toString(), arraylist);
        return hashmap;
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
