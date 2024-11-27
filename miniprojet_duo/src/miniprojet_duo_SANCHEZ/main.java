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

import java.util.Scanner;

public class main {
    private GrilleDeJeu grilleDeJeu;
    private Scanner scanner;

    // Constructeur pour initialiser la grille de jeu
    public main(int nbLignes, int nbColonnes, int nbBombes) {
        grilleDeJeu = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        scanner = new Scanner(System.in);
    }

    // Méthode qui démarre la boucle du jeu
    public void demarrer() {
        // Boucle de jeu
        while (true) {
            // Afficher la grille à chaque tour
            System.out.println(grilleDeJeu.afficherGrille());
            
            // Demander à l'utilisateur de saisir les coordonnées de la cellule à révéler
            System.out.print("Entrez la ligne (0 à " + (grilleDeJeu.getNbLignes() - 1) + ") et la colonne (0 à " + (grilleDeJeu.getNbColonnes() - 1) + "): ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();

            // Vérifier si les coordonnées sont valides
            if (ligne < 0 || ligne >= grilleDeJeu.getNbLignes() || colonne < 0 || colonne >= grilleDeJeu.getNbColonnes()) {
                System.out.println("Coordonnées invalides. Essayez encore.");
                continue;
            }

            // Révéler la cellule choisie par l'utilisateur
            grilleDeJeu.revelerCellule(ligne, colonne);

            // Vérifier si toutes les cellules sûres sont révélées
            if (grilleDeJeu.toutesLesCellulesSuresRevelees()) {
                System.out.println("Félicitations, vous avez gagné !");
                break;
            }
        }
    }

    // Méthode main pour lancer le jeu
    public static void main(String[] args) {
        // Demander à l'utilisateur de configurer la taille de la grille et le nombre de bombes
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le nombre de lignes pour la grille : ");
        int nbLignes = scanner.nextInt();
        System.out.print("Entrez le nombre de colonnes pour la grille : ");
        int nbColonnes = scanner.nextInt();
        System.out.print("Entrez le nombre de bombes à placer : ");
        int nbBombes = scanner.nextInt();

        // Initialiser la partie avec les paramètres donnés par l'utilisateur
        Partie partie = new Partie(nbLignes, nbColonnes, nbBombes);
        // Lancer le jeu
        partie.demarrer();
    }
}
