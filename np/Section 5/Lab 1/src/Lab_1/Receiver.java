package Lab_1;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {
        public static void main(String[] args) throws Exception{
        MulticastSocket s=new MulticastSocket(5600);

        // Address must be in range 224.0.0.0 to 239.255.255.255
        InetAddress g=InetAddress.getByName("239.5.4.1");
        s.joinGroup(g);

        byte[] buffer=new byte[1024];
        DatagramPacket p=new DatagramPacket(buffer, buffer.length);
        s.receive(p);
        String msg=new String(p.getData());
        System.out.println(msg);
        s.close();
    }

}
