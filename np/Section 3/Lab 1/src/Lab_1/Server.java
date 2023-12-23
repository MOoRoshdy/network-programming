package Lab_1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

class handler extends Thread {
    Socket c;
    handler (Socket c){
        this.c=c;
    }

    @Override
    public void run() {
        try { 
            DataInputStream input = new DataInputStream(c.getInputStream());
            String st = input.readUTF();
            System.out.println(st);                 
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

public class Server {
    public static void main(String[] args) {    
        try { 
            ServerSocket server = new ServerSocket(5000);;
            System.out.println("Server Listening on Port: 5000");
            while (true) {                
                Socket client = server.accept();
                InetAddress clientAddress = client.getInetAddress();
                System.out.println("New Client Connected " + clientAddress.toString());
                handler t1 = new handler(client);
                t1.start();
            }
        }
        
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }    
}