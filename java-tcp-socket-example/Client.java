import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        BufferedReader in;
        
        try {
            // Connexion au serveur (adresse IP et port)
            socket = new Socket("localhost", 3280);  // J'ai mis "localhost" pour tester sur la même machine
            System.out.println("Connecté au serveur !");
            
            // Réception du message du serveur
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();
            System.out.println("Message reçu : " + message);
            
            // Fermeture de la connexion
            socket.close();
            System.out.println("Connexion fermée.");
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}