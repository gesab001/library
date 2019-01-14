/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 14400
 */
public class Account  {
    int accNo;
    private int balance;
    private int dateOfOpening;
    private Borrower borrower;

    
    
    public Account(){
 
    }
    

    
    public String debitAmount(int accNo, int amount){
        Library library  = new Library();
        Connection conn = library.connectToLibraryDatabase("admin", "admin");
        int balance = Integer.parseInt(this.getBalance(accNo));
        int newbalance = balance - amount;
        try {
            String query = "update Account set Balance= " + newbalance + " where AccountNo = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, accNo);
            stmt.execute();
            conn.close();                    
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        return "successfully credited amount";
    }
    
    public String creditAmount(int accNo, int amount){
        Library library  = new Library();
        Connection conn = library.connectToLibraryDatabase("admin", "admin");
        int balance = Integer.parseInt(this.getBalance(accNo));
        int newbalance = balance + amount;
        try {
            String query = "update Account set Balance= " + newbalance + " where AccountNo = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, accNo);
            stmt.execute();
            conn.close();                    
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        return "successfully credited amount";
        
    }
    
    public String getBalance(int accNo){
        Library library  = new Library();
        Connection conn = library.connectToLibraryDatabase("admin", "admin");
        String balance = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Account where AccountNo = " + accNo);
            while(rs.next())  {     
                balance = rs.getString("Balance");
                System.out.print(rs.getString("Balance"));
            }
            
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        return balance;
    }
    
    public HashMap getAccountDetails(int accountNo){
        Library library  = new Library();
        Connection conn = library.connectToLibraryDatabase("admin", "admin");
        HashMap<Integer, ArrayList> hashmap = new HashMap<Integer, ArrayList>();
        ArrayList accountDetails = new ArrayList(){};
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Account where AccountNo = " + accountNo);
            while(rs.next())  {     
                String balance = rs.getString("Balance");
                String openingDate = rs.getString("DateOfOpening").toString();
                System.out.print(rs.getString("AccountNo"));
                System.out.print(rs.getString("Balance"));
                System.out.print(rs.getString("DateOfOpening").toString());
                accountDetails.add(balance);
                accountDetails.add(openingDate);
            }
            
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        hashmap.put(accountNo, accountDetails);
        return hashmap;
    }
    
}
