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
    private GrilleDeJeu grilleDeJeu;
    private Scanner scanner;

    public Partie(int nbLignes, int nbColonnes, int nbBombes) {
        grilleDeJeu = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        scanner = new Scanner(System.in);
    }

    public void demarrer() {
        while (true) {
            System.out.println(grilleDeJeu.afficherGrille());
            System.out.println("Entrez la ligne et la colonne (par exemple: 1 2): ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();
            
            // Révéler la cellule
            grilleDeJeu.revelerCellule(ligne, colonne);
            
            if (grilleDeJeu.toutesCellulesRevelees()) {
                System.out.println("Félicitations, vous avez gagné !");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Partie partie = new Partie(5, 5, 5); // 5x5 grille et 5 bombes
        partie.demarrer();
    }
}
