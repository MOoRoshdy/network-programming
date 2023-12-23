package Lab_4;
import java.io.*;
import java.net.*;

class Handler extends Thread {
    Socket clientSocket;

    Handler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                
            double a = in.readDouble();
            double b = in.readDouble();
            double c = in.readDouble();
            double discriminant = b * b - 4 * a * c;
            double root1, root2;
    
            if (discriminant > 0) {
                root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                out.writeUTF("Two real roots: " + root1 + " and " + root2);
            }
                
            else {
                out.writeUTF("{NAN NAN}");
            }

            System.out.println("Solved Equation for a = " + a + ", b = " + b + ", c = " + c);
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
public class Server {
    public static void main(String[] args) {
        try { 
            ServerSocket server = new ServerSocket(6000);;
            System.out.println("Server Listening on Port: 6000");
            while (true) {                
                Socket client = server.accept();
                InetAddress clientAddress = client.getInetAddress();
                System.out.println("New Client Connected " + clientAddress.toString());
                Handler h = new Handler(client);
                h.start();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
