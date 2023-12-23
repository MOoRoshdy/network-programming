package Lab_1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) {
       try {
            Socket c = new Socket("127.0.0.1",5000);
            c.setSoTimeout(10000);
            DataOutputStream out = new DataOutputStream(c.getOutputStream());          
            out.writeUTF("Hello Server");
        } 
       catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
} 