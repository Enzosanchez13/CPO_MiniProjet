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

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Demander les paramètres du jeu
        System.out.print("Entrez le nombre de lignes: ");
        int lignes = scanner.nextInt();
        System.out.print("Entrez le nombre de colonnes: ");
        int colonnes = scanner.nextInt();
        System.out.print("Entrez le nombre de bombes: ");
        int bombes = scanner.nextInt();
        
        // Démarrer la partie
        Partie partie = new Partie(lignes, colonnes, bombes);
        partie.demarrer();
    }
}
