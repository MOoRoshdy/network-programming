package Lab_2;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        try {
            Socket clientSocket = new Socket("localhost", 6000);
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            String msg = "Hello Server!";
            out.writeUTF(msg);
            System.out.println(in.readUTF());
            clientSocket.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
