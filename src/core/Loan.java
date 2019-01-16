/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

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
    
    
    public Loan(int _borrowerID, ArrayList<Item> _items, String _dueDate, String _type){
        this.items = _items;
        this.dueDate = _dueDate;
        this.type = _type;
        this.borrowerID = _borrowerID;
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
    
    
    public ArrayList getItems(){
        return this.items;
    }
    
    public String getDueDate(){
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
