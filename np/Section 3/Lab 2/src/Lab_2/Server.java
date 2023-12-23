package Lab_2;
import java.io.*;
import java.net.*;

class Handler extends Thread {
    Socket c;
    Handler (Socket c){
        this.c=c;
    }

    @Override
    public void run() {
        try { 
                 DataInputStream input = new DataInputStream(c.getInputStream());
                 DataOutputStream output = new DataOutputStream(c.getOutputStream());
                 String st = input.readUTF();
                 System.out.println(st); 
                 output.writeUTF(st.toUpperCase());
        }
        
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

public class Server {
    public static void main(String[] args) throws Exception {    
        try { 
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Server Listening on Port: 5000");
               while (true) {                
                Socket client = server.accept();
                InetAddress clientAddress = client.getInetAddress();
                System.out.println("New Client Connected " + clientAddress.toString());
                Handler t1 = new Handler(client);
                t1.start();
            }
        }
        
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }    
    
}