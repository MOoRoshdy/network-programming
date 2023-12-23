package Lab_2;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {    
            Socket c = new Socket("127.0.0.1",5000);
            c.setSoTimeout(10000);
            DataOutputStream out = new DataOutputStream(c.getOutputStream()); 
            DataInputStream in=new DataInputStream(c.getInputStream());
            out.writeUTF("Hello Server");
            System.out.println(in.readUTF());
        } 

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
