package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by aelKashef on 5/13/2017.
 */
public class Message {


    Date Date ;
    //String Date ;
    String MessageType;

    public String getSymbolIdentifiers() {
        return symbolIdentifiers;
    }

    public void setSymbolIdentifiers(String symbolIdentifiers) {
        this.symbolIdentifiers = symbolIdentifiers;
    }

    String symbolIdentifiers ;
  //  HashMap<String,String> symbolIdentifiers;

 /*   public HashMap<String, String> getSymbolIdentifiers() {
        return symbolIdentifiers;
    }

    public void setSymbolIdentifiers(HashMap<String, String> symbolIdentifiers) {
        this.symbolIdentifiers = symbolIdentifiers;
    }*/
    //ArrayList<SymbolIdentifiers>symbolIdentifiers;

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
/* public String getDate() {
     return Date;
 }

    public void setDate(String date) {
        Date = date;
    }*/
    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }


}
