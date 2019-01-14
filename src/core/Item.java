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
         String title;
         String type;
         String identifier;
         String status;
    
    public Item(String _title, String _type, String _identifier, String _status){
        this.title = _title;
        this.type =  _type;
        this.identifier = _identifier;
        this.status = _status;
    }
    
    private void setTitle(String _title){
        this.title = _title;
    }
    
    private void setType(String _type){
        this.type = _type;

    }
    
    private void setIdentifier(String _identifier){
        this.identifier = _identifier;
    }
    
    private void setStatus(String _status){
        this.status = _status;
    }
    
    private String getTitle(String identifier){
        return this.title;
    }
    
    private String getType(String identifier){
        return this.type;
        
    }

    
    private String getStatus(String identifier){
        return this.status;
    }
    
}
