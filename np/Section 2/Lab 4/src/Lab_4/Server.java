package Lab_4;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
 public static void main(String[] args) {    
    try { 
        ServerSocket server = new ServerSocket(6000);;
        System.out.println("Server Listening on Port: 6000");
        while (true) {                
            Socket client = server.accept();
            InetAddress clientAddress = client.getInetAddress();
            System.out.println("New Client Connected " + clientAddress.toString());
            ClientHandler clientSock = new ClientHandler(client);
            Thread clientthread =  new Thread(clientSock);
            clientthread.start();
        }
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
  
        @Override
        public void run()
        {
            
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
                System.out.println(e.getMessage());
                }
            }
        }
    }