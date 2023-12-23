package Lab_5;
import java.net.Socket;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 6000);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter coefficients (a b c): ");
            String input = scanner.nextLine();
            String[] coefficients = input.split(" ");

            if (coefficients.length != 3) {
                System.out.println("Please enter three coefficients (a b c) separated by spaces.");
                s.close(); scanner.close();
                return;
            }
            
            double a = Double.parseDouble(coefficients[0]);
            double b = Double.parseDouble(coefficients[1]);
            double c = Double.parseDouble(coefficients[2]);
            
            out.writeDouble(a);
            out.writeDouble(b);
            out.writeDouble(c);

            String result = in.readUTF();
            System.out.println("Server Response: " + result);
            s.close(); scanner.close();
    }
}
