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
public class Item {
    private String identifier;
    private String title;
    private String author;
    private String keywords;
    private String type;
    private String status;

    
    public Item(String _identifier, String _title, String _author, String _keywords, String _type,  String _status){
        this.identifier = _identifier;
        this.title = _title;
        this.author = _author;
        this.keywords= _keywords;
        this.type =  _type;
        this.status = _status;
    }
    
    public void setIdentifier(String _identifier){
        this.identifier = _identifier;
    }
    
    public void setTitle(String _title){
        this.title = _title;
    }
    
    public void setAuthor(String _author){
        this.author = _author;
    }
        
    public void setKeywords(String _keywords){
        this.keywords = _keywords;
    }
        
    public void setType(String _type){
        this.type = _type;

    }
    
    public void setStatus(String _status){
        this.status = _status;
    }
 
    public String getIdentifier(){
        return this.identifier;
    }   
    public String getTitle(){
        return this.title;
    }
    
    public String getAuthor(){
        return this.author;
    }
    
    public String getKeywords(){
        return this.keywords;
        
    }
    public String getType(){
        return this.type;
        
    }
    
    public String getStatus(){
        return this.status;
    }
    
}
