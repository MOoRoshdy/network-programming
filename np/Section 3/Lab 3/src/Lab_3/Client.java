package Lab_3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket c = new Socket("localhost",5000);
        DataInputStream in=new DataInputStream(c.getInputStream());
        DataOutputStream out=new DataOutputStream(c.getOutputStream());
        System.out.println("Please enter your number: ");
        Scanner user = new Scanner(System.in);
        String userNumber = user.nextLine();
        out.writeUTF(userNumber);
        System.out.println(in.readUTF());
        c.close(); user.close();
    }
}
