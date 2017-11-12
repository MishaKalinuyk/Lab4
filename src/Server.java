/**
 * Created by mac on 12.11.17.
 */
import java.net.*;
import java.io.*;

public class Server extends Thread {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        try {
            System.out.println("Waiting for client on port " +
                    serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to " + server.getRemoteSocketAddress() + "\n");
            while (true) {
                Thread.sleep(5000);
                DataInputStream in = new DataInputStream(server.getInputStream());

                String message = in.readUTF();
                System.out.println("Client says: " + message);
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                System.out.println("Message to the client: " + message);
                out.writeUTF(message);
            }
            //server.close();
        } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        int port = 7777;
        try {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}