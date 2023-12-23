package Lab_3;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
 public static void main(String[] args) {    
    try { 
        ServerSocket server = new ServerSocket(7000);;
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
    }