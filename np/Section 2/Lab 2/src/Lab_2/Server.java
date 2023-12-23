package Lab_2;
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
                String receivedMsg = in.readUTF();
                System.out.println(receivedMsg);
                out.writeUTF(receivedMsg.toUpperCase());         
                }
                catch (Exception e) {
                System.out.println(e.getMessage());
                }
            }
        }
    }