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
    private GrilleDeJeu grille;

    public Partie(int nbLignes, int nbColonnes, int nbBombes) {
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
    }

    public void demarrer() {
        Scanner scanner = new Scanner(System.in);
        while (!grille.estPartieTerminee()) {
            System.out.println(grille.toString()); // Affiche la grille avant chaque tour
            System.out.print("Entrez la ligne et la colonne à révéler (ex: 0 3) : ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();

            // Vérification des limites de la grille
            if (ligne < 0 || ligne >= grille.getNbLignes() || colonne < 0 || colonne >= grille.getNbColonnes()) {
                System.out.println("Coordonnées invalides, essayez encore.");
                continue;
            }

            // Révéler la cellule demandée
            grille.revelerCellule(ligne, colonne);

            // Vérifier si une bombe a été révélée
            if (grille.getCellule(ligne, colonne).getPresenceBombe()) {
                System.out.println("Vous avez touché une bombe ! la partie est terminée.");
                break;
            }

            // Vérifier si la partie est terminée
            if (grille.estPartieTerminee()) {
                System.out.println("Félicitations ! Vous avez gagné.");
                break;
            }
        }
        scanner.close();
    }
}
