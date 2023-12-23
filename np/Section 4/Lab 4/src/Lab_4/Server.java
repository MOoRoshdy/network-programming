package Lab_4;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            int port = 8000;
            DatagramSocket serverSocket = new DatagramSocket(8000);

            while (true) {
                System.out.println("Server Listening on Port: " + port);

                // Receive the packet from the client:
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket clientPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(clientPacket);
                System.out.println("Received Packet from: " + clientPacket.getAddress());

                // Processing the packet:
                String[] coefficients = new String(clientPacket.getData()).split(" ");
                double a = Double.parseDouble(coefficients[0]);
                double b = Double.parseDouble(coefficients[1]);
                double c = Double.parseDouble(coefficients[2]);
                double discriminant = b * b - 4 * a * c;
                double root1, root2;
                String result = "";

                if (discriminant > 0) {
                    root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                    root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                    result = "{Root[1] = " + root1 + ",   " + "Root[2] = " + root2 + "}";
                }
                
                else {
                    result = "{NAN NAN}";
                }
                
                // Display the information about the solved equation to the server:
                System.out.println("Solved Equation for a = " + a + ", b = " + b + ", c = " + c);

                // Sending the response to the client:
                byte[] sendBuffer = new byte[1024];
                sendBuffer = result.getBytes();
                DatagramPacket serverPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientPacket.getAddress(), clientPacket.getPort());
                serverSocket.send(serverPacket);
                System.out.println("===========================================");
            }
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
