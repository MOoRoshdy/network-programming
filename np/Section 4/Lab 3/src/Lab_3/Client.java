package Lab_3;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Creating a UDP socket for the client:
            DatagramSocket clientSocket = new DatagramSocket();

            // Getting a number from the client:
            Scanner input = new Scanner(System.in);
            System.out.print("Please Enter Your Number: ");
            String number = input.nextLine();

            // Sending the request:
            byte[] sendBuffer = new byte[1024];
            sendBuffer = number.getBytes();
            DatagramPacket clientPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("localhost"), 7000);
            clientSocket.send(clientPacket);
            
            // Receiving the response:
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket serverPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(serverPacket);

            // Displaying the factorial of the number & length of the digits:
            System.out.println(new String(serverPacket.getData()).trim());
            System.out.println(new String(serverPacket.getData()).trim().length());

            // Closing the UDP socket and the scanner:
            clientSocket.close(); input.close();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
