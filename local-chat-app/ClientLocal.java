import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientLocal {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3280);
            System.out.println("Connecté au serveur local!");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            
            // Thread réception
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Serveur: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Serveur déconnecté");
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
            socket.close();
            
        } catch (IOException e) {
            System.out.println("Impossible de se connecter au serveur");
            e.printStackTrace();
        }
    }
}