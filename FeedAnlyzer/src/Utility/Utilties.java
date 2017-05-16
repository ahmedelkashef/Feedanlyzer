package Utility;

import Model.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by aelKashef on 5/11/2017.
 */
public class Utilties {

    private static int neglectedMessages = 0;



    public int getNegletedMessages() {
        return neglectedMessages;
    }

    public void setNegletedMessages(int negletedMessages) {
        this.neglectedMessages = negletedMessages;
    }

    public static void ReadFile(String name) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;

        //ArrayList<String> messages = new ArrayList<String>();
        ArrayList<Message> messages = new ArrayList<Message>();

        try {
            inputStream = new FileInputStream(name);
            sc = new Scanner(inputStream, "UTF-8");
             int i = 0 ;
            //while (sc.hasNextLine())
             while (sc.hasNextLine()) {
                i++;
                String line = sc.nextLine();
               // messages.add(line);
               System.out.println( i + "  " + line);
                 System.out.println( "size  " + messages.size());
               MapToObject(line , messages);
                 System.out.println("number of Neglected Messages = " + neglectedMessages + " \n");
            }
     /*       System.out.println("number of object = " + messages.size() + " \n");
            System.out.println("number of Neglected Messages = " + neglectedMessages + " \n");*/
          /*  System.out.println("number of object = " + messages.size() + " \n");
            System.out.println("number of Neglected Messages = " + neglectedMessages + " \n");*/
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
        separatedItems = line.split("(-\\s)|(\\:\\:)");

        if(separatedItems.length == 3)
        {
            m.setDate(toDate(separatedItems[0]));
        //    m.setDate(separatedItems[0]);
            m.setMessageType(separatedItems[1]);
            m.setSymbolIdentifiers(separatedItems[2]);
           // m.setSymbolIdentifiers(toSymbol(separatedItems[2]));
            messages.add(m);
        }
        else
        {
             neglectedMessages++;

        }
    }

    private static HashMap<String, String> toSymbol(String separatedItem) {

        String [] temp ;

        HashMap<String, String>  symbolIdentifiers = new HashMap<String, String> ();
        temp = separatedItem.split(";");

        for (String s: temp ) {
            String [] temp2  = s.split("=");
                symbolIdentifiers.put(temp2[0],temp2[1]);

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
