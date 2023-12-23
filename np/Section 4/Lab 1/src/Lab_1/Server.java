package Lab_1;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            int port = 5000;
            DatagramSocket serverSocket = new DatagramSocket(port);
            
            while (true) {
                System.out.println("Server listening on Port: " + port);

                // Receiving the message from the client:
                byte[] buffer = new byte[1024];
                DatagramPacket p = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(p);

                // Converting the message from Byte[] to string.
                // and displaying it:
                String msg = new String(p.getData());
                System.out.println(msg);
            }
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
