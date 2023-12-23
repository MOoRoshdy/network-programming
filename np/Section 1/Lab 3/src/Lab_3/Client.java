package Lab_3;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket c= new Socket("localhost",6000);
            DataInputStream in=new DataInputStream(c.getInputStream());
            DataOutputStream out=new DataOutputStream(c.getOutputStream());
            out.writeUTF("luxor university");
            System.out.println(in.readUTF());
            c.close();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }   
}
