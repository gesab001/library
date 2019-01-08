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
    
    public Loan(){
    }
    
    private int loanNo;
    private ArrayList<Item> items = new ArrayList<Item>(){};
    private int dueDate;
    private String type;
    
    
    private void addBorrower(){
    }
    
    private void setLoanNo(){
    }
    
    private void setItems(){
    }

    private void setDueDate(){
    }
    
    private int getLoanNo(){
        return this.loanNo;
    }
    
    private ArrayList getItems(){
        return this.items;
    }
    
    private int getDueDate(){
        return this.dueDate;
    }

    private void setType(){
    }
    
    private String getType(){
        return this.type;
    }
   
    

}
