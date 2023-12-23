package Lab_2;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            int port = 5000;
            DatagramSocket serverSocket = new DatagramSocket(port);

            while (true) {
                System.out.println("Server Listening on Port: " + port);
                byte[] buffer1 = new byte[1024];

                // Receiving the Request:
                DatagramPacket clientPacket = new DatagramPacket(buffer1, buffer1.length); 
                serverSocket.receive(clientPacket);

                // Display the content of the client request:
                String msg = new String(clientPacket.getData());
                System.out.println(msg);

                // Processing the message (Making it in Uppercase):
                msg = msg.toUpperCase();

                // Sending the response
                byte[] buffer2 = new byte[1024];
                buffer2 = msg.getBytes();
                DatagramPacket serverPacket = new DatagramPacket(buffer2, buffer2.length, clientPacket.getAddress(), clientPacket.getPort());
                serverSocket.send(serverPacket);
            }
        }

        catch (Exception e ) {
            System.out.println(e.getMessage());
        }
    }
}
