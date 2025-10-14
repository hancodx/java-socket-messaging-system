import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        ServerSocket socketserver;
        Socket socketserveur;
        PrintWriter out;
        
        try {
            // Création du serveur sur le port 3280
            socketserver = new ServerSocket(3280);
            System.out.println("Serveur démarré, en attente de connexion...");
            
            // Attente de connexion d'un client
            socketserveur = socketserver.accept();
            System.out.println("Client connecté !");
            
            // Envoi d'un message au client
            out = new PrintWriter(socketserveur.getOutputStream());
            out.println("Master M2 SIR ");
            out.flush();
            
            // Fermeture des connexions
            socketserveur.close();
            socketserver.close();
            System.out.println("Connexion fermée.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}