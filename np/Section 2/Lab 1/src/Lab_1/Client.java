package MTClientServer1;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) {
        try {        
            Socket s = new Socket("127.0.0.1",5000);
            s.setSoTimeout(10000);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());          
            out.writeUTF("Hello Server");
            s.close();
        } 
        
        catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
} 
  
