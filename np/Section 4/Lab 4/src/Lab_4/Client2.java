package Lab_4;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        try {
            // Creating a UDP socket for the client:
            DatagramSocket clientSocket = new DatagramSocket();

            // Getting the coefficients from the user (separated by spaces):
            Scanner input = new Scanner(System.in);
            System.out.print("Enter coefficients (a b c): ");
            String coefficients = input.nextLine();
            
            // Sending the coefficients to the server for processing:
            byte[] sendBuffer = new byte[1024];
            sendBuffer = coefficients.getBytes();
            DatagramPacket clientPacket = new DatagramPacket(sendBuffer, sendBuffer.length, "127.0.0.2", 8000);
            clientSocket.send(clientPacket);

            // Receiving the processed packet from the server:
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket serverPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(serverPacket);
            System.out.println("Server response: " + new String(serverPacket.getData()));

            // closing the UDP socket and scanner:
            clientSocket.close(); input.close();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
