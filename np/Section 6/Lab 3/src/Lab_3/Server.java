package Lab_3;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.math.BigInteger;

class Handler3 extends Thread {
    Socket clientSocket;

    Handler3 (Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            int n = Integer.parseInt(in.readUTF());
            BigInteger factorial = BigInteger.valueOf((n));
            for (int i = 1; i < n; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }

            out.writeUTF(factorial.toString());
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
            System.out.println("Server Listening on Port:\t" + port);
            while (true) {
                Socket client = serverSocket.accept();
                InetAddress clientAddress = client.getInetAddress();
                System.out.println("New Client Connected:\t" + clientAddress.toString());
                ExecutorService pool = Executors.newFixedThreadPool(3);
                pool.execute(new Handler3(client));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
