package Lab_1;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Sender {
    public static void main(String[] args) throws Exception {
        MulticastSocket s=new MulticastSocket();

        // The address must be the same as in the Receiver.
        InetAddress g=InetAddress.getByName("239.5.4.1");
        s.joinGroup(g);
        String msg="Welcome to luxor university";
        DatagramPacket p=new DatagramPacket(msg.getBytes(),msg.length(),g,5600);
        s.send(p);
        s.close();
    }

}
