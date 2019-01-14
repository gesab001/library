/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author 14400
 */
public class Borrower {
    int borrowerID;
    String name;
    String address;
    String email;
    int phone;
    
    public Borrower(int _borrowerID, String _name, String _address, String _email,
            int _phone){
        this.borrowerID = _borrowerID;
        this.name = _name;
        this.address = _address;
        this.email = _email;
        this.phone = _phone;
    }
    

    
    private void setBorrowerID(int borrowerID_input){
        this.borrowerID = borrowerID_input;
        
    }
    
    private void setEmail(String email_input){
        this.email = email_input;
    }
    
    private void setPhone(int phone_input){
        this.phone = phone_input;
    }
    
    private void setAddress(String address_input){
        this.address = address_input;
    }
    
    private void setName(String name_input){
        this.name = name_input;
    }
    
    private int getBorrowerID(){
        return this.borrowerID;
    }
    
    private String getName(){
        return this.name;
    }
    
    private String getAddress(){
        return this.address;
    }
    
    private String getEmail(){
        return this.email;
    }
    
    private int getPhone(){
        return this.phone;
    }
          
            
}
