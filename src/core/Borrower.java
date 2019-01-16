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
    private String surname;
    private String address;
    private String email;
    private int phone;
    private String username;
    private String password;
    private int accountNo;

    
    public Borrower(int _borrowerID, String _name, String _surname, String _address, String _email,
            int _phone, int _accountNo, String _username, String _password){
        this.borrowerID = _borrowerID;
        this.name = _name;
        this.surname = _surname;
        this.address = _address;
        this.email = _email;
        this.phone = _phone;
        this.accountNo = _accountNo;
        this.username = _username;
        this.password = _password;

    }
     
    public void setBorrowerID(int borrowerID_input){
        this.borrowerID = borrowerID_input;
        
    }
 
    public void setName(String name_input){
        this.name = name_input;
    }
    
    public void setSurname(String surname_input){
        this.surname = surname_input;    
    }
 
    public void setAddress(String address_input){
        this.address = address_input;
    }
    
    public void setEmail(String email_input){
        this.email = email_input;
    }
    
    public void setPhone(int phone_input){
        this.phone = phone_input;
    }
 
    public void setAccountNo(int accountNo_input){
        this.accountNo = accountNo_input;
    }

    public void setUsername(String username_input){
        this.username = username_input;
    }
        
    public void setPassword(String password_input){
        this.password = password_input;
    }
    

    
    public int getBorrowerID(){
        return this.borrowerID;
    }
    
    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
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

    public int getAccountNo(){
        return this.accountNo;
    }

    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
}
