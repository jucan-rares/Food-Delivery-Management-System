package tools;
import businessLayer.DeliveryServiceProcessing;
import businessLayer.model.MenuItem;
import businessLayer.model.Order;
import businessLayer.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;


public class Serializer {

    public static Boolean serializeClients() {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("users.service");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Map.Entry<String, User> clientMapIt : DataGlobals.globalUsers.entrySet()  ){
                objectOutputStream.writeObject( clientMapIt.getValue() );
            }
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }
        finally {
            try {
                /* Make sure to close buffers even if object serialization failed */
                if (objectOutputStream != null)
                    objectOutputStream.close();

                if ( objectOutputStream != null)
                    fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
                e.getCause();
            }
        }
        return true;
    }

    public static Boolean serializeOrders() {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("orders.service");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Map.Entry<Order, ArrayList< MenuItem >> orderMapIt : DataGlobals.globalOrders.entrySet()  ){
                objectOutputStream.writeObject( orderMapIt.getValue() );
            }
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }
        finally {
            try {
                /* Make sure to close buffers even if object serialization failed */
                if (objectOutputStream != null)
                    objectOutputStream.close();

                if ( objectOutputStream != null)
                    fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
                e.getCause();
            }
        }
        return true;
    }

    public static Boolean serializeProducts() {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("products.service");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(MenuItem item : DataGlobals.globalMenuItems  ){
                objectOutputStream.writeObject( item );
            }
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }
        finally {
            try {
                /* Make sure to close buffers even if object serialization failed */
                if (objectOutputStream != null)
                    objectOutputStream.close();

                if ( objectOutputStream != null)
                    fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
                e.getCause();
            }
        }
        return true;
    }

    public static void  init() {

        // Init global variables
        ObjectInputStream objectInputStream1 = null;
        FileInputStream fileInputStream1 = null;
        ObjectInputStream objectInputStream2 = null;
        FileInputStream fileInputStream2 = null;
        ObjectInputStream objectInputStream3 = null;
        FileInputStream fileInputStream3 = null;
        DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();
        /////////////////

        try {
            fileInputStream1 = new FileInputStream("users.service");
            objectInputStream1 = new ObjectInputStream( fileInputStream1 );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Fetch users.service
        while( Boolean.TRUE ){
            try{
                User u;
                assert objectInputStream1 != null;
                u = (User) objectInputStream1.readObject();
                DataGlobals.globalUsers.put( u.getUsername(), u );
            }
            catch (EOFException e){
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Fetch orders.service

        try {
            fileInputStream2 = new FileInputStream("products.service");
            objectInputStream2 = new ObjectInputStream( fileInputStream2 );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fetch users.service
        while( Boolean.TRUE ){
            try{
                MenuItem m;
                m = (MenuItem) objectInputStream2.readObject();
                DataGlobals.globalMenuItems.add( m );
            }
            catch (EOFException e){
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        try {
            fileInputStream3 = new FileInputStream("orders.service");
            objectInputStream3 = new ObjectInputStream( fileInputStream3 );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fetch users.service
        while( Boolean.TRUE ){
            try{
                Order o;
                assert objectInputStream3 != null;
                o = (Order) objectInputStream3.readObject();
                DataGlobals.globalOrders.put( o, deliveryServiceProcessing.fetchProductsByOrder(o) );
            }
            catch (EOFException e){
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }


}



