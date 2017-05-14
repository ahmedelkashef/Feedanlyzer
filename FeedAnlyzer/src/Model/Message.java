package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aelKashef on 5/13/2017.
 */
public class Message {
    Date Date ;
    String MessageType;
    ArrayList<SymbolIdentifiers>symbolIdentifiers;

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }

    public ArrayList<SymbolIdentifiers> getSymbolIdentifiers() {
        return symbolIdentifiers;
    }

    public void setSymbolIdentifiers(ArrayList<SymbolIdentifiers> symbolIdentifiers) {
        this.symbolIdentifiers = symbolIdentifiers;
    }
}
