package MTClientServer1;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
 public static void main(String[] args) {    
    try { 
        ServerSocket server = new ServerSocket(5000);;
        System.out.println("Server Started");
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
                 DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                 String st = input.readUTF();
                 System.out.println(st);                 
                }
                catch (Exception e) {
                System.out.println(e.getMessage());
                }
            }
        }
    }