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
    private Cellule[][] matriceCellules;
    private int nbLignes;
    private int nbColonnes;
    private int nbBombes;

    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;

        matriceCellules = new Cellule[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule();
            }
        }

        placerBombesAleatoirement();
        calculerBombesAdjacentes();
    }

    // Méthodes d'accès aux attributs
    public int getNbLignes() {
        return nbLignes;  // Retourne le nombre de lignes de la grille
    }

    public int getNbColonnes() {
        return nbColonnes;  // Retourne le nombre de colonnes de la grille
    }

    public int getNbBombes() {
        return nbBombes;  // Retourne le nombre total de bombes
    }

    // Méthode pour placer les bombes aléatoirement sur la grille
    private void placerBombesAleatoirement() {
        Random random = new Random();
        int bombesPlacees = 0;

        while (bombesPlacees < nbBombes) {
            int ligne = random.nextInt(nbLignes);
            int colonne = random.nextInt(nbColonnes);

            if (!matriceCellules[ligne][colonne].getPresenceBombe()) {
                matriceCellules[ligne][colonne].placerBombe();
                bombesPlacees++;
            }
        }
    }

    // Méthode pour calculer les bombes adjacentes à chaque cellule
    private void calculerBombesAdjacentes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe()) {
                    int count = 0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            int ni = i + di;
                            int nj = j + dj;
                            if (ni >= 0 && ni < nbLignes && nj >= 0 && nj < nbColonnes) {
                                if (matriceCellules[ni][nj].getPresenceBombe()) {
                                    count++;
                                }
                            }
                        }
                    }
                    matriceCellules[i][j].setNbBombesAdjacentes(count);
                }
            }
        }
    }

    // Révéler une cellule
    public void revelerCellule(int ligne, int colonne) {
        if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) return;

        Cellule cellule = matriceCellules[ligne][colonne];
        if (cellule.estDevoilee()) return;

        cellule.revelerCellule();

        if (!cellule.getPresenceBombe() && cellule.getNbBombesAdjacentes() == 0) {
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    if (di != 0 || dj != 0) {
                        revelerCellule(ligne + di, colonne + dj);
                    }
                }
            }
        }
    }

    // Vérifier la présence d'une bombe à une position donnée
    public boolean getPresenceBombe(int ligne, int colonne) {
        return matriceCellules[ligne][colonne].getPresenceBombe();
    }

    // Vérifier si toutes les cellules sûres ont été révélées
    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe() && !matriceCellules[i][j].estDevoilee()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Méthode pour afficher la grille
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                sb.append(matriceCellules[i][j].toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
