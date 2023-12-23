package Lab_3;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {   
    try {
      ServerSocket s = new ServerSocket(6000);
      System.out.println("waiting for clients connections....");
      Socket c=s.accept();
      DataInputStream in = new DataInputStream(c.getInputStream());
      DataOutputStream out = new DataOutputStream(c.getOutputStream());
      String receivedMsg = in.readUTF();
      System.out.println(receivedMsg);
      out.writeUTF(receivedMsg.toUpperCase());
      c.close(); s.close();
    }

    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }      
}
