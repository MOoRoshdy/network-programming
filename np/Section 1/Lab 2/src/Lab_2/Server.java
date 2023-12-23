package Lab_2;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
     public static void main(String[] args) {
        try {
            ServerSocket s =new ServerSocket(6000);
            System.out.println("Waiting for clients connections.....");
            Socket c=s.accept();
            DataInputStream in=new DataInputStream(c.getInputStream());
            System.out.println(in.readUTF());
            c.close(); s.close();
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
