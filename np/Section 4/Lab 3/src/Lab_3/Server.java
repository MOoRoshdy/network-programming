package Lab_3;
import java.net.*;
import java.math.*;

public class Server {
    public static void main(String[] args) throws Exception {
        try { 
            int port = 7000;
            DatagramSocket serverSocket = new DatagramSocket(port);
            while (true) {
                System.out.println("Server Listening on Port: " + port);

                // Receiving the client packet:
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket clientPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(clientPacket);

                // Processing the client packet:
                int n = Integer.parseInt(new String(clientPacket.getData()).trim());
                BigInteger factorial = BigInteger.valueOf(n);
                for (int i = 1; i < n; i++) {
                    factorial = factorial.multiply(BigInteger.valueOf(i));
                }

                // Sending the processed packet to the client:
                byte[] sendBuffer = new byte[1024];
                sendBuffer = String.valueOf(factorial).getBytes();
                DatagramPacket serverPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientPacket.getAddress(), clientPacket.getPort());
                serverSocket.send(serverPacket);
            }
        }
        
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
