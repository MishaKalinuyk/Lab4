/**
 * Created by mac on 12.11.17.
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 7777;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress() + "\n");
            while (true) {
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                System.out.print("Message to the server: ");
                String str = scanner.nextLine();

                out.writeUTF(str);
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);

                System.out.println("Server says: " + in.readUTF());
                //client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
