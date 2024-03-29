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
    
    private int accNo;
    private int balance;
    private String dateOfOpening;
    private Borrower borrower;
    private Library library;
    private int BookLoanLimit;
    private int MagazineLoanLimit;
    
    public Account(int _accNo, int _balance, String _dateOfOpening, Borrower _borrower){
        this.accNo = _accNo;
        this.balance = _balance;
        this.dateOfOpening = _dateOfOpening;
        this.borrower = _borrower;
    }
    
    public Account(){}
    
    public int getAccountNo(){
        return this.accNo;
    }
    
    public int getBalance(int accountNo){
        Library library  = new Library();
        int balance = 0;
        Connection conn = library.connectToLibraryDatabase("admin", "admin");        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Balance from Account where AccountNo = " + accountNo);
            while(rs.next())  {     
                balance = rs.getInt("Balance");
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
    
    public String getOpeningDate(){
        return this.dateOfOpening;
    }
    
    public Borrower getBorrower(){
        return borrower;
    }
    
    public int getAccountNumber(String borrowerID){
        Library library  = new Library();
        Connection conn = library.connectToLibraryDatabase("admin", "admin");
        int accountNo = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select AccountNo from Account where AccountNo = " + borrowerID);
            while(rs.next())  {     
                accountNo = Integer.parseInt(rs.getString("BorrowerID"));
                //System.out.print(rs.getString("Balance"));
            }
            
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        return accountNo;
    }
   
    public String addBorrower(){
        library = new Library();
        Connection conn = library.connectToLibraryDatabase("admin", "admin");
        String message_from_server = null;
        int borrowerID = this.borrower.getBorrowerID();
        String name = this.borrower.getName();
        String surname = this.borrower.getSurname();
        String address = this.borrower.getAddress();
        String email = this.borrower.getEmail();
        int phone = this.borrower.getPhone();
        int accountNo = this.borrower.getAccountNo();
        String username = this.borrower.getUsername();
        String password = this.borrower.getPassword();
        try {
            String query = "insert into Borrower (BorrowerID, Name, Surname, Address, Email, Phone, AccountNo, Username, Password) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, borrowerID);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.setString(4, address);
            stmt.setString(5, email);
            stmt.setInt(6, phone);
            stmt.setInt(7, accountNo);
            stmt.setString(8, username);
            stmt.setString(9, password);
            stmt.execute();
            message_from_server = "new borrower successfully created";
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
    
    public String debitAmount(int accNo, Long amount){
        Library library  = new Library();
        Connection conn = library.connectToLibraryDatabase("admin", "admin");
        int balance = this.setBalance(accNo);
        Long newbalance = balance + amount;
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
        int balance = this.setBalance(accNo);
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


    
    public int setBalance(int accNo){
        Library library  = new Library();
        Connection conn = library.connectToLibraryDatabase("admin", "admin");
        String balance = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Account where AccountNo = " + accNo);
            while(rs.next())  {     
                this.balance = Integer.parseInt(rs.getString("Balance"));
                //System.out.print(rs.getString("Balance"));
            }
            
            conn.close();           
        }
        catch (SQLException ex) {
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());                 
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        return this.balance;
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
    
     public String removeBorrower(int borrowerID) {
        Connection conn = library.connectToLibraryDatabase("admin", "admin");
        String message_from_server = null;
        try {
            String query = "delete from Borrower where BorrowerID = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, borrowerID);
            stmt.execute();
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
}
