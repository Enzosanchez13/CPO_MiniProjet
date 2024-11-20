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

import java.util.Scanner;

public class Partie {
    private final GrilleDeJeu grille;

    public Partie(int nbLignes, int nbColonnes, int nbBombes) {
        grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
    }

    public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);
        boolean enCours = true;

        while (enCours) {
            // Affichage de la grille
            System.out.println(grille);

            // Demande des coordonnées au joueur
            System.out.print("Entrez la ligne et la colonne à révéler (ex: 1 2) : ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();

            // Vérification des limites
if (ligne < 0 || ligne >= grille.getNbLignes() || colonne < 0 || colonne >= grille.getNbColonnes()) {
                System.out.println("Coordonnées invalides !");
                continue;
            }

            // Révéler la cellule
            if (grille.getPresenceBombe(ligne, colonne)) {
                System.out.println("BOOM ! Vous avez touché une bombe !");
                enCours = false; // Fin de partie
            } else {
                grille.revelerCellule(ligne, colonne);

                // Vérifier si toutes les cellules sûres sont révélées
                if (grille.toutesCellulesRevelees()) {
                    System.out.println("Félicitations, vous avez gagné !");
                    enCours = false; // Fin de partie
                }
            }
        }

        // Fin de la partie
        System.out.println("Fin de la partie !");
    }
}
