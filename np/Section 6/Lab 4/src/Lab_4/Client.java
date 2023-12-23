package Lab_4;
import java.io.*;
import java.util.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        try {
            Socket clientSocket = new Socket("localhost",8000);
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            Scanner input = new Scanner(System.in);

            System.out.print("Enter coefficients (a b c): ");
            String msg = input.nextLine();
            String[] coefficients = msg.split(" ");

            if (coefficients.length != 3) {
                System.out.println("Please enter three coefficients (a b c) separated by spaces.");
                clientSocket.close(); input.close();
                return;
            }
            
            double a = Double.parseDouble(coefficients[0]);
            double b = Double.parseDouble(coefficients[1]);
            double c = Double.parseDouble(coefficients[2]);
            
            out.writeDouble(a);
            out.writeDouble(b);
            out.writeDouble(c);

            String result = in.readUTF();
            System.out.println("Server Response: " + result);
            clientSocket.close(); input.close();
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
