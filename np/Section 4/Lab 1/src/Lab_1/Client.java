package Lab_1;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        try {
            // Creating a UDP socket for the client:
            DatagramSocket clientSocket = new DatagramSocket();

            // Specifying a message to be send to the server:
            String msg = "Welcome to Luxor University";

            // Sending the message to the server:
            byte[] buffer = new byte[1024];
            buffer = msg.getBytes();
            DatagramPacket p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 5000);
            clientSocket.send(p);

            // Closing the socket:
            clientSocket.close();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
