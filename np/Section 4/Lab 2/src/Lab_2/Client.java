package Lab_2;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        try {
            // Creating a UDP socket for the client:
            DatagramSocket clientSocket = new DatagramSocket();
            
            // Specifying a message to be processed:
            String msg = "welcome to luxor university";

            // Sending the request:
            byte[] sendBuffer = new byte[1024];
            sendBuffer = msg.getBytes();
            DatagramPacket clientPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("localhost"), 5000);
            clientSocket.send(clientPacket);

            // Receive the response:
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket serverPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(serverPacket);

            // Displaying the server response:
            System.out.println(new String(serverPacket.getData()));

            // Closing the UDP client socket:
            clientSocket.close();
        }
        
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
