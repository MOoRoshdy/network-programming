package Lab_2;
import java.net.*;

public class Sender {
    public static void main(String[] args) {
        try {
            int port = 5000; // Choose a port for the multicast group
            InetAddress group = InetAddress.getByName("225.1.1.1"); // Multicast group address
            MulticastSocket socket = new MulticastSocket();
            int counter = 0;

            while (true) {
                String message = ++counter + "";
                byte[] buffer = message.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
                socket.send(packet);
                System.out.println("Sent: " + message);

                Thread.sleep(1000); // Wait for 1 second before sending the next message
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}