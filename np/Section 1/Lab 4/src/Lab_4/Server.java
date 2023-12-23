package Lab_4;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(6000);
            System.out.println("Waiting clients connections....");
            while (true) {
                Socket c = s.accept();
                DataInputStream in = new DataInputStream(c.getInputStream());
                DataOutputStream out = new DataOutputStream(c.getOutputStream());
                String userNumber = in.readUTF();
                int n = Integer.parseInt(userNumber);
                BigInteger factorial = BigInteger.valueOf(n);
                for (int i = 1; i < n; i++) {
                    factorial = factorial.multiply(BigInteger.valueOf(i));
                }
                out.writeUTF(String.valueOf(factorial));
                System.out.println("Waiting clients connections....");
            }
         }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
