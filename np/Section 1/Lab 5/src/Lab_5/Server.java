package Lab_5;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(6000);
            Socket s = new Socket();

            while (true) {
                System.out.println("Server Listening on Port: 6000");
                s = server.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                
                double a = in.readDouble();
                double b = in.readDouble();
                double c = in.readDouble();
                double discriminant = b * b - 4 * a * c;
                double root1, root2;
    
                if (discriminant > 0) {
                    root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                    root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                    out.writeUTF("Two real roots: " + root1 + " and " + root2);
                }
                
                else if (discriminant == 0) {
                    root1 = -b / (2 * a);
                    out.writeUTF("One real root: " + root1);
                }
                
                else {
                    double realPart = -b / (2 * a);
                    double imagPart = Math.sqrt(-discriminant) / (2 * a);
                    out.writeUTF("root1 = " + realPart + "+" + imagPart + "i and root2 = " + realPart + "-" + imagPart + "i");
                }
                System.out.println("Solved Equation for a = " + a + ", b = " + b + ", c = " + c);
            }
        }

        catch (Exception e) {

        }
    }
}
