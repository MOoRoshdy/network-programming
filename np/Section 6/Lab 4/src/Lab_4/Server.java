package Lab_4;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

class Handler3 extends Thread {
    Socket clientSocket;

    Handler3(Socket clientSocket) {
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
                
            else if (discriminant == 0) {
                root1 = -b / (2 * a);
                out.writeUTF("One real root: " + root1);
            }
                
            else {
                double realPart = -b / (2 * a);
                double imagPart = Math.sqrt(-discriminant) / (2 * a);
                out.writeUTF("root1 = " + realPart + "+" + imagPart + "i and root2 = " + realPart + "-" + imagPart + "i");
            }
            System.out.println("Solved Equation for a = " + a + ", b = " + b + ", c = " + c);
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Server {
    public static void main(String[] args) {
        try {
            int port = 8000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Listening on Port:\t" + port);
            while(true) {
                Socket client = serverSocket.accept();
                InetAddress clientAddress = client.getInetAddress();
                System.out.println("New Client Connected:\t" + clientAddress.toString());
                ExecutorService pool = Executors.newFixedThreadPool(3);
                pool.execute(new Handler3(client));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
