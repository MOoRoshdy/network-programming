package Lab_2;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {
    public static void main(String[] args) {
        try {
            int port = 5000; // Use the same port as the sender
            InetAddress group = InetAddress.getByName("225.1.1.1"); // Multicast group address
            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(group);
            byte[] buffer = new byte[1024];

            while(true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData()).trim();
                System.out.println("Received: " + received);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}