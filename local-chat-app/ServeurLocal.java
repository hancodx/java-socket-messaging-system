import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServeurLocal {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3280);
            System.out.println("Serveur local démarré...");
            
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté!");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            
            // Thread réception
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Client: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Client déconnecté");
                }
            });
            receiveThread.start();
            
            // Envoi messages
            System.out.println("Tapez vos messages ('quit' pour quitter):");
            String message;
            while (true) {
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("quit")) break;
                out.println(message);
            }
            
            scanner.close();
            clientSocket.close();
            serverSocket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}