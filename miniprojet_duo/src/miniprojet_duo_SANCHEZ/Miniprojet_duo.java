/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miniprojet_duo_SANCHEZ;

/**
 *
 * @author enzos
 */
public class Miniprojet_duo {
    public static void main(String[] args) {
        System.out.println("Démarrage du jeu Super Démineur !");

        // Étape 1 : Initialisation de la partie
        int nbLignes = 5;      // Par exemple, 5 lignes
        int nbColonnes = 5;    // Par exemple, 5 colonnes
        int nbBombes = 5;      // Par exemple, 5 bombes

        // Création d'une nouvelle partie avec les paramètres
        Partie partie = new Partie(nbLignes, nbColonnes, nbBombes);

        // Étape 2 : Démarrage de la partie
        partie.demarrerPartie();
    }
}

