/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet_duo_SANCHEZ;

/**
 *
 * @author enzos
 */
import java.util.Scanner;

public class Partie {
    public GrilleDeJeu grille;
    public boolean partieEnCours;

    public Partie(int nbLignes, int nbColonnes, int nbBombes) {
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        this.partieEnCours = true;
    }

    // Méthode pour initialiser la partie
    public void initialiserPartie() {
        grille.initialiserGrille(); // Initialise la grille et place les bombes
        partieEnCours = true;      // Réinitialise l'état de la partie
        System.out.println("La partie a été initialisée. Bonne chance !");
    }

    // Méthode pour gérer un tour de jeu
    public void tourDeJeu(int ligne, int colonne) {
        // Vérification des limites
        if (ligne < 0 || ligne >= grille.getNbLignes() || colonne < 0 || colonne >= grille.getNbColonnes()) {
            System.out.println("Coordonnées invalides. Essayez encore.");
            return;
        }

        // Révéler la cellule
        grille.revelerCellule(ligne, colonne);

        // Vérification des bombes
        if (grille.getCellule(ligne, colonne).getPresenceBombe()) {
            System.out.println("Vous avez touché une bombe ! La partie est terminée.");
            partieEnCours = false;
        } else {
            System.out.println("Cellule révélée avec succès !");
        }
    }

    // Méthode pour vérifier la victoire
    public boolean verifierVictoire() {
        return grille.estVictoire(); // Méthode qui vérifie si toutes les cellules sûres ont été révélées
    }

    // Méthode principale pour démarrer la partie

    /**
     *
     */
    public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);
        initialiserPartie();

        while (partieEnCours) {
            System.out.println(grille.toString()); // Affiche la grille actuelle
            System.out.print("Entrez la ligne et la colonne à révéler (ex: 0 3) : ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();

            tourDeJeu(ligne, colonne);

            // Vérification de la victoire
            if (verifierVictoire()) {
                System.out.println("Félicitations ! Vous avez gagné.");
                partieEnCours = false;
            }
        }

        System.out.println("Fin de la partie !");
        scanner.close();
    }

    
}
