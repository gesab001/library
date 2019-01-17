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
import java.util.ArrayList;

/**
 *
 * @author 14400
 */
public class Loan {
    
    int loanNo;
    ArrayList<Item> items = new ArrayList<Item>(){};
    String dueDate;
    String type;
    int borrowerID;
    int renewalLimit;
    Library library = new Library(){};
    
    public Loan(){}
    
    public Loan(int _borrowerID, ArrayList<Item> _items, String _dueDate, String _type){
        this.items = _items;
        this.dueDate = _dueDate;
        this.type = _type;
        this.borrowerID = _borrowerID;
    }
    
    public void setRenewalLimit(int limit){
        this.renewalLimit = limit;
    }

    public int renewalLimit(){
        return this.renewalLimit;
    }
    
    
    public void setBorrower(int borrowerID_input){
        this.borrowerID = borrowerID_input;
    }
    
    public void setLoanNo(int loanNo_input){
        this.loanNo = loanNo_input;
    }
    
    public void setItems(ArrayList<Item> items_input){
        this.items = items_input;
    }

    public void setDueDate(String dueDate_input){
        this.dueDate = dueDate_input;
    }
    
    public int getLoanNo(){
        return this.loanNo;
    }
    
    
    public ArrayList<Item> getItems(){
        return this.items;
    }
    
    public String getDueDate(){
        return this.dueDate;
    }

    public String getDueDateForRenewingItem(String identifier){
        String message_from_server = null;
        Connection conn = library.connectToLibraryDatabase("admin", "admin");    
        try {
            String query = "select DueDate from Loan where Item='" + identifier + "'" ;
            PreparedStatement stmt = conn.prepareStatement(query);
           // stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next() == false){
                return null;
            } else{
                do {
                this.dueDate = rs.getString("DueDate").toString();
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
        return this.dueDate;
    }
    
    public int getBorrowerID(){
        return this.borrowerID;
    }
    public void setType(String type_input){
        this.type = type_input;
    }
    
    public String getType(){
        return this.type;
    }
   
    public String loanItemsToString(){
        ArrayList<Item> items = this.items;
        StringBuilder sb = new StringBuilder();
        for (Item item : items){
            sb.append(item.getIdentifier());
            sb.append(",");
        }
        String result = sb.toString();
        return result;
        
    }
    

}
