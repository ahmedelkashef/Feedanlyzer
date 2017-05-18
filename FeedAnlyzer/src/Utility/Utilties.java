package Utility;

import Model.Message;
import sun.management.jmxremote.LocalRMIServerSocketFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by aelKashef on 5/11/2017.
 */
public class Utilties {

    private static int neglectedMessages = 0;

    public static void LoadFile(String name, Thread thread) {

        FileInputStream inputStream = null;
        Scanner sc = null;

        //ArrayList<String> messages = new ArrayList<String>();
        ArrayList<Message> messages = new ArrayList<Message>();

        try {
            inputStream = new FileInputStream(name);
            sc = new Scanner(inputStream, "UTF-8");
            int i = 0;
            //while (sc.hasNextLine())
            System.out.println("Loading Feed File .. Please Wait.");
            LoadingThread loadingThread = new LoadingThread(thread);
            loadingThread.start();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                // messosages.add(line);
                //System.out.println( i + "  " + line);
                // System.out.println( "size  " + messages.size());
                MapToObject(line, messages);
                //System.out.println("number of Neglected Messages = " + neglectedMessages + " \n");
            }
     /*       System.out.println("number of object = " + messages.size() + " \n");
            System.out.println("number of Neglected Messages = " + neglectedMessages + " \n");*/
          /*  System.out.println("number of object = " + messages.size() + " \n");
            System.out.println("number of Neglected Messages = " + neglectedMessages + " \n");*/
            System.out.println("\n" + "File Loaded Successfully. \n");
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("can not find the file ");
        } catch (IOException e) {
            System.out.println("can not read the file ");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static void ReadFile(String path) {
        FileInputStream inputStream = null;
        Scanner sc = null;

        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }

            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("can not find the file ");
        } catch (IOException e) {
            System.out.println("can not read the file ");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        if (separatedItems.length == 3) {
            m.setDate(toDate(separatedItems[0]));
            //    m.setDate(separatedItems[0]);
            m.setMessageType(separatedItems[1]);
            m.setSymbolIdentifiers(separatedItems[2]);
            // m.setSymbolIdentifiers(toSymbol(separatedItems[2]));
            messages.add(m);
        } else {
            neglectedMessages++;

        }
    }

    private static HashMap<String, String> toSymbol(String separatedItem) {

        String[] temp;

        HashMap<String, String> symbolIdentifiers = new HashMap<String, String>();
        temp = separatedItem.split(";");

        for (String s : temp) {
            String[] temp2 = s.split("=");
            symbolIdentifiers.put(temp2[0], temp2[1]);

        }
        return symbolIdentifiers;
    }

    public static Date toDate(String s) {

        DateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date startDate = null;
        try {
            startDate = df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;


    }

    public static void DisplayBar(Thread t) {
        String anim = "(-.-)";
        int x = 0;
        while (t.isAlive()) {

            String data = "\r" + anim.charAt(x % anim.length()) + anim.charAt(x % anim.length()) + anim.charAt(x % anim.length());

            try {
                System.out.write(data.getBytes());
                x++;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Properties ReadProperties(String path) {
        Properties prop = new Properties();
        InputStream input = null;


        try {

            input = new FileInputStream(path);

            // load a properties file
            prop.load(input);
            return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }
    public int getNegletedMessages() {
        return neglectedMessages;
    }

}

class LoadingThread extends Thread {

    Thread t;

    LoadingThread(Thread thread) {
        t = thread;
    }

    @Override
    public void run() {
        Utilties.DisplayBar(t);
    }
}
