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
    private int borrowerID;
    private String name;
    private String address;
    private String email;
    private int phone;
    
    public Borrower(int _borrowerID, String _name, String _address, String _email,
            int _phone){
        this.borrowerID = _borrowerID;
        this.name = _name;
        this.address = _address;
        this.email = _email;
        this.phone = _phone;
    }
    

    
    public void setBorrowerID(int borrowerID_input){
        this.borrowerID = borrowerID_input;
        
    }
    
    public void setEmail(String email_input){
        this.email = email_input;
    }
    
    public void setPhone(int phone_input){
        this.phone = phone_input;
    }
    
    public void setAddress(String address_input){
        this.address = address_input;
    }
    
    public void setName(String name_input){
        this.name = name_input;
    }
    
    public int getBorrowerID(){
        return this.borrowerID;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public int getPhone(){
        return this.phone;
    }
          
            
}
