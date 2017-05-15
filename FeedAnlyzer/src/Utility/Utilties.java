package Utility;

import Model.Message;
import Model.SymbolIdentifiers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by aelKashef on 5/11/2017.
 */
public class Utilties {

    private static int negletedMessages =0;



    public int getNegletedMessages() {
        return negletedMessages;
    }

    public void setNegletedMessages(int negletedMessages) {
        this.negletedMessages = negletedMessages;
    }

    public static void ReadFile(String name) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;

        ArrayList<Message> messages = new ArrayList<Message>();
        try {
            inputStream = new FileInputStream(name);
            sc = new Scanner(inputStream, "UTF-8");
             int i =0 ;
            //while (sc.hasNextLine())
             while (sc.hasNextLine()) {
                i++;

                String line = sc.nextLine();
                //System.out.println( i + "  " + line);

                MapToObject(line , messages);

            }
            System.out.println("number of object = " + messages.size() + " \n");
            System.out.println("number of Neglected Messages = " + negletedMessages + " \n");
          /*  System.out.println("number of object = " + messages.size() + " \n");
            System.out.println("number of Neglected Messages = " + negletedMessages + " \n");*/
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }

    private static void MapToObject(String line, ArrayList<Message> messages) {
        String[] separatedItems;

        Message m = new Message();
        separatedItems = line.split("(-)|(\\:\\:)");

        if(separatedItems.length == 3)
        {
            m.setDate(toDate(separatedItems[0]));
            m.setMessageType(separatedItems[1]);
            m.setSymbolIdentifiers(toSymbol(separatedItems[2]));

            messages.add(m);
        }
        else
        {
             negletedMessages++;

        }
    }

    private static ArrayList<SymbolIdentifiers> toSymbol(String separatedItem) {

        String [] temp ;


        ArrayList<SymbolIdentifiers> symbolIdentifiers = new ArrayList<SymbolIdentifiers>();
        temp = separatedItem.split(";");

        for (String s: temp ) {
            SymbolIdentifiers symbolIdentifier = new SymbolIdentifiers();
            String [] temp2  = s.split("=");
            symbolIdentifier.setId(temp2[0]);
            symbolIdentifier.setValue(temp2[1]);
            symbolIdentifiers.add(symbolIdentifier);

        }
        return symbolIdentifiers;
    }

    public static Date toDate(String s)
    {

        DateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss" );
        Date startDate = null;
        try {
            startDate = df.parse(s);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;


    }

}
