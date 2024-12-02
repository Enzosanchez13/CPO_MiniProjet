/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miniprojet_duo_SANCHEZ;

/**
 *
 * @author enzos
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demande des paramètres de la partie au joueur
        System.out.println("Bienvenue dans le jeu de démineur !");
        System.out.print("Entrez le nombre de lignes de la grille : ");
        int lignes = scanner.nextInt();

        System.out.print("Entrez le nombre de colonnes de la grille : ");
        int colonnes = scanner.nextInt();

        System.out.print("Entrez le nombre de bombes : ");
        int bombes = scanner.nextInt();

        // Validation des paramètres
        if (bombes >= lignes * colonnes) {
            System.out.println("Erreur : Le nombre de bombes doit être inférieur au nombre total de cellules.");
            return;
        }

        // Initialisation et démarrage de la partie
        Partie partie = new Partie(lignes, colonnes, bombes);
        partie.demarrerPartie(); // Lance la boucle principale de la partie

        System.out.println("Merci d'avoir joué !");
        scanner.close();
    }
}
