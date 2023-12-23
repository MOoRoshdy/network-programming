package Lab_3;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        try {
            Socket clientSocket = new Socket("localhost",6000);
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            Scanner input = new Scanner(System.in);
            System.out.print("Enter a Number: ");
            String n = input.nextLine();

            out.writeUTF(n);
            System.out.println("Server Response: " + in.readUTF());
            clientSocket.close(); input.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
