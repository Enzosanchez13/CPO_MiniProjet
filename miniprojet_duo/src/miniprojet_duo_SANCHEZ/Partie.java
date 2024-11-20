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
    private int vies;
    private boolean partieEnCours;

    public Partie(int nbLignes, int nbColonnes, int nbBombes) {
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        this.vies = 3; // Nombre de vies du joueur
        this.partieEnCours = true;
    }

    public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu de Démineur !");
        System.out.println("Vous commencez avec " + vies + " vies.");
        System.out.println("Voici la grille initiale :");
        System.out.println(grille); // Affichage de la grille au début

        while (partieEnCours) {
            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("1 - Révéler une cellule");
            System.out.println("2 - Afficher le nombre de vies restantes");
            System.out.println("3 - Afficher la grille");
            System.out.println("4 - Quitter la partie");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    // Révéler une cellule
                    System.out.print("Entrez la ligne (0 à " + (grille.getNbLignes() - 1) + ") : ");
                    int ligne = scanner.nextInt();
                    System.out.print("Entrez la colonne (0 à " + (grille.getNbColonnes() - 1) + ") : ");
                    int colonne = scanner.nextInt();

                    if (grille.getPresenceBombe(ligne, colonne)) {
                        System.out.println("BOOM ! Vous avez touché une bombe !");
                        vies--;
                    } else {
                        grille.revelerCellule(ligne, colonne);
                        System.out.println("Cellule révélée !");
                    }

                    if (vies <= 0) {
                        System.out.println("Vous avez perdu toutes vos vies !");
                        partieEnCours = false;
                    }
                    break;

                case 2:
                    System.out.println("Il vous reste " + vies + " vies.");
                    break;

                case 3:
                    System.out.println("Voici l'état actuel de la grille :");
                    System.out.println(grille); // Affichage de l'état actuel de la grille
                    break;

                case 4:
                    System.out.println("Vous avez quitté la partie.");
                    partieEnCours = false; // Quitter la partie
                    break;

                default:
                    System.out.println("Option invalide. Veuillez choisir entre 1 et 4.");
                    break;
            }

            // Vérification de la victoire
            if (grille.toutesCellulesRevelees()) {
                System.out.println("Félicitations ! Vous avez gagné !");
                partieEnCours = false;
            }
        }

        System.out.println("Fin de la partie !");
    }
}
