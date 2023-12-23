package Lab_1;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        try {
            Socket clientSocket = new Socket("localhost", 6000);
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            String message = "Hello Server!";
            out.writeUTF(message);
            clientSocket.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
