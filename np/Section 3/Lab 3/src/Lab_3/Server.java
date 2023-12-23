package Lab_3;

import java.io.*;
import java.net.*;
import java.math.BigInteger;

class Handler extends Thread {
    Socket c;

    Handler(Socket c) {
        this.c = c;
    }

    @Override
    public void run() {
        try { 
                DataInputStream in = new DataInputStream(c.getInputStream());
                DataOutputStream out = new DataOutputStream(c.getOutputStream());
                String userNumber = in.readUTF();
                int n = Integer.parseInt(userNumber);
                BigInteger factorial = BigInteger.valueOf(n);
                for (int i = 1; i < n; i++) {
                    factorial = factorial.multiply(BigInteger.valueOf(i));
                }
                out.writeUTF(String.valueOf(factorial));                 
            }

            catch (Exception e) {
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
                Handler h = new Handler(client);
                h.start();
            }
        }
        
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
