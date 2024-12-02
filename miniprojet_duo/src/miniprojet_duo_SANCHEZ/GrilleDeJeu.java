/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet_duo_SANCHEZ;

/**
 *
 * @author enzos
 */
import java.util.Random;

public class GrilleDeJeu {
    private Cellule[][] grille;
    private int nbLignes;
    private int nbColonnes;
    private int nbBombes;

    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
        this.grille = new Cellule[nbLignes][nbColonnes];
        initialiserGrille();
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public Cellule getCellule(int ligne, int colonne) {
        return grille[ligne][colonne];
    }

    // Initialisation de la grille et placement des bombes
    public void initialiserGrille() {
        // Initialiser chaque cellule
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                grille[i][j] = new Cellule();
            }
        }

        // Placer les bombes aléatoirement
        Random rand = new Random();
        int bombesPlacees = 0;
        while (bombesPlacees < nbBombes) {
            int ligne = rand.nextInt(nbLignes);
            int colonne = rand.nextInt(nbColonnes);
            if (!grille[ligne][colonne].getPresenceBombe()) {
                grille[ligne][colonne].setPresenceBombe(true);
                bombesPlacees++;
                incrementerVoisines(ligne, colonne);
            }
        }
    }

    // Incrémenter le nombre de bombes voisines des cellules autour
    public void incrementerVoisines(int ligne, int colonne) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int l = ligne + i;
                int c = colonne + j;
                if (l >= 0 && l < nbLignes && c >= 0 && c < nbColonnes && !(i == 0 && j == 0)) {
                    grille[l][c].setBombesVoisines(grille[l][c].getBombesVoisines() + 1);
                }
            }
        }
    }

    // Révéler une cellule
    public void revelerCellule(int ligne, int colonne) {
        Cellule cellule = grille[ligne][colonne];
        if (cellule.isRevelee()) return;

        cellule.reveler();
        if (cellule.getBombesVoisines() == 0 && !cellule.getPresenceBombe()) {
            // Révéler les cellules voisines si la cellule n'a pas de bombes autour
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int l = ligne + i;
                    int c = colonne + j;
                    if (l >= 0 && l < nbLignes && c >= 0 && c < nbColonnes) {
                        revelerCellule(l, c);
                    }
                }
            }
        }
    }

    // Vérifie si toutes les cellules sûres ont été révélées
    public boolean estVictoire() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                Cellule cellule = grille[i][j];
                if (!cellule.getPresenceBombe() && !cellule.isRevelee()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Vérifie si la partie est terminée (victoire ou défaite)
    public boolean estPartieTerminee() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (grille[i][j].getPresenceBombe() && grille[i][j].isRevelee()) {
                    return true; // Une bombe a été révélée -> partie terminée
                }
            }
        }
        return estVictoire();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                sb.append(grille[i][j].toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
