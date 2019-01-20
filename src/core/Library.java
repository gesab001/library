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
    public Borrower borrower = null;
    private Account account;
    private Loan loan;

    
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

    public String addAccount(Account account){
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        String message_from_server = null;
        int accountNo = account.getAccountNo();
        int balance = 0;
        String openingDate = account.getOpeningDate();
        int borrowerID = account.getBorrower().getBorrowerID();
        try {
            String query = "insert into Account  (AccountNo, Balance, DateOfOpening, BorrowerID) values (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, accountNo);
            stmt.setInt(2, balance);
            stmt.setString(3, openingDate);
            stmt.setInt(4, borrowerID);
            stmt.execute();
            String borrower_creation_confirm = account.addBorrower();
            message_from_server = "new account successfully created. " + borrower_creation_confirm;
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
    
    public String updateItem(Item item){
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        String message_from_server = null;
        String identifier = item.getIdentifier();
        String title = item.getTitle();
        String author = item.getAuthor();
        String keywords = item.getKeywords();
        String type = item.getType(); 
        String status = item.getStatus();
        try {
            String query = "update Item set Identifier = ?, Title = ?, Author = ?, Keywords = ?, Type = ?,  Status = ? where Identifier = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, identifier);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setString(4, keywords);
            stmt.setString(5, type);
            stmt.setString(6, status);
            stmt.setString(7, identifier);
            stmt.execute();
            message_from_server = "item successfully updated";
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
    
    public ArrayList getItems(String keyword){
        String message_from_server = null;
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        ArrayList<Item> itemslist = new ArrayList<Item>(){};
        try {
            System.out.print(keyword);
            String query = "select * from Item  where Title like '%" + keyword + "%'" + "OR Author like '% " + keyword + "%'" + "OR Keywords like '% " + keyword + "%'";
            PreparedStatement stmt = conn.prepareStatement(query);
            //stmt.setString(1, "%" + keyword + "%");
//            stmt.setString(2, keyword);
//            stmt.setString(3, keyword);
//            stmt.setString(4, keyword);
//            stmt.setString(5, keyword);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next() == false){
                itemslist = new ArrayList<Item>(){};
            } else{
                do {
                String identifier = rs.getString("Identifier".toString());
                String title = rs.getString("Title").toString();
                String author = rs.getString("Author").toString();
                String keywords = rs.getString("Keywords").toString();
                String type = rs.getString("Type").toString();
                String status = rs.getString("Status").toString();
                item = new Item(identifier, title, author, keywords, type, status);
                itemslist.add(item);
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
        return itemslist;
    }
//    
//    public Borrower getAccountBorrowerInformation(int borrowerID){
//        Account account = null;
//        Borrower borrower = account.getBorrowerInformation(borrowerID);
//        return borrower;
//    }
 
    public ArrayList getBorrowerInformation(int borrowerID){
        System.out.print(borrowerID);
        String message_from_server = null;    
        ArrayList<Borrower> borrowerlist = new ArrayList<Borrower>(){};
        Connection conn = this.connectToLibraryDatabase("admin", "admin");    
        try {
            String query = "select * from Borrower where BorrowerID="+ borrowerID;
            PreparedStatement stmt = conn.prepareStatement(query);
            //stmt.setInt(1, borrowerID);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next() == false){
                return borrowerlist;
            } else{
                do {
                borrowerID = rs.getInt("BorrowerID");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String address = rs.getString("Address");
                String email = rs.getString("Email");
                int phone = rs.getInt("Phone");
                int accountNo = rs.getInt("AccountNo");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                borrower = new Borrower(borrowerID, name, surname, address, email, phone, accountNo, username, password);
                borrowerlist.add(borrower);
               
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
        
         return borrowerlist;

    }
 
    public String updateBorrower(Borrower borrower){
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        String message_from_server = null;
        int borrowerID = borrower.getBorrowerID();
        String name = borrower.getName();
        String surname = borrower.getSurname();
        String address = borrower.getAddress();
        String email = borrower.getEmail(); 
        int phone = borrower.getPhone();
        int accountNo = borrower.getAccountNo();
        String username = borrower.getUsername();
        String password = borrower.getPassword();
        try {
            String query = "update Borrower set Name = ?, Surname = ?, Address = ?, Email = ?, Phone = ?,  accountNo = ?, Username = ?, Password = ? where BorrowerID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, address);
            stmt.setString(4, email);
            stmt.setInt(5, phone);
            stmt.setInt(6, accountNo);
            stmt.setString(7, username);
            stmt.setString(8, password);
            stmt.setInt(9, borrowerID);
            stmt.execute();
            message_from_server = "borrower information successfully updated";
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
  
    public void addLoan(Loan loan){
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        String message_from_server = null;
        int borrowerID = loan.getBorrowerID();
        String duedate = loan.getDueDate();
        String type = loan.getType();
        ArrayList<Item> items = loan.getItems();
        for (Item item : items){
            try {
                String query = "insert into Loan (Item, DueDate, Type, BorrowerID) values (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, item.getIdentifier()  );
                stmt.setString(2, duedate);
                stmt.setString(3, type);
                stmt.setInt(4, borrowerID);
                stmt.execute();
                        System.out.print("add loan");

    //            message_from_server = "loan successfully created";
                conn.close();           
            }
            catch (SQLException ex) {
            // handle any errors
              String exception = ("SQLException: " + ex.getMessage());                 
              String state = ("SQLState: " + ex.getSQLState());
              String vendor = ("VendorError: " + ex.getErrorCode());
              System.out.print(exception + " " + state + " " + vendor);
            }
           // return message_from_server;

        }
    }
 
    public void renewLoan(Loan loan){
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        String message_from_server = null;
        int borrowerID = loan.getBorrowerID();
        String duedate = loan.getDueDate();
        String type = loan.getType();
        ArrayList<Item> items = loan.getItems();
        for (Item item : items){
            try {
                int renewalLimit = loan.getRenewalLimit(item.getIdentifier())+1;
                String query = "update Loan set DueDate = ?, RenewalLimit=? where Item = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, duedate);
                stmt.setInt(2, renewalLimit);
                stmt.setString(3, item.getIdentifier());
                stmt.execute();
                        System.out.print("renew loan");

    //            message_from_server = "loan successfully created";
                conn.close();           
            }
            catch (SQLException ex) {
            // handle any errors
              String exception = ("SQLException: " + ex.getMessage());                 
              String state = ("SQLState: " + ex.getSQLState());
              String vendor = ("VendorError: " + ex.getErrorCode());
              System.out.print(exception + " " + state + " " + vendor);
            }
           // return message_from_server;

        }
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
    
    public void removeLoan(String identifier){
                Connection conn = this.connectToLibraryDatabase("admin", "admin");
        
        String message_from_server = null;
        try {
            String query = "delete from Loan where Item ='" + identifier + "'";
            PreparedStatement stmt = conn.prepareStatement(query);
            //stmt.setString(identifier);
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
    }
    
    public String removeAccount(int borrowerID) {
        Connection conn = this.connectToLibraryDatabase("admin", "admin");
        
        String message_from_server = null;
        try {
            String query = "delete from Account where BorrowerID = (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, borrowerID);
            stmt.execute();
            String query1 = "delete from Borrower where BorrowerID = (?)";
            PreparedStatement stmt1 = conn.prepareStatement(query1);
            stmt1.setInt(1, borrowerID);
            stmt1.execute();
            message_from_server = "borrower account successfully deleted ";
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
