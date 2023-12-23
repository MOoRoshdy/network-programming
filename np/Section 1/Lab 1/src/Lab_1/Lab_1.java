package Lab_1;
import java.net.*;

public class Lab_1 {
    public static void main(String[] args)  {
        
        try {
            System.out.println(InetAddress.getLocalHost());
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
    
