package Lab_2;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket c=new Socket("localhost", 6000);
            DataOutputStream out=new DataOutputStream(c.getOutputStream());
            out.writeUTF("Hello Server");
            c.close();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
