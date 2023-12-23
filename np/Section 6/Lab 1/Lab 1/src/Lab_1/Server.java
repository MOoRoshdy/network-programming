package Lab_1;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

class handler3 extends Thread {
    Socket clientSocket;

    handler3(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            String str = in.readUTF();
            System.out.println(str);
            clientSocket.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Server {
    public static void main(String[] args) {
        try {
            int port = 6000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Listening on Port: " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                InetAddress clientAddress = clientSocket.getInetAddress();
                System.out.println("New Client Connected:\t" + clientAddress.toString());
                ExecutorService pool = Executors.newFixedThreadPool(3);
                pool.execute(new handler3(clientSocket));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
