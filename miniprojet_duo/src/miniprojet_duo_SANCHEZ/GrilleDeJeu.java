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
    private final Cellule[][] matriceCellules;
    private final int nbLignes;
    private final int nbColonnes;
    private final int nbBombes;

    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;

        // Initialisation de la grille
        matriceCellules = new Cellule[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule();
            }
        }

        // Placement aléatoire des bombes
        placerBombesAleatoirement();

        // Calcul des bombes adjacentes
        calculerBombesAdjacentes();
    }

    public int getNbLignes() {
        return this.nbLignes;
    }

    public int getNbColonnes() {
        return this.nbColonnes;
    }

    public boolean getPresenceBombe(int ligne, int colonne) {
        return matriceCellules[ligne][colonne].getPresenceBombe();
    }

    public void revelerCellule(int ligne, int colonne) {
        if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) {
            return; // Hors limites
        }
        Cellule cellule = matriceCellules[ligne][colonne];
        if (cellule.estDevoilee()) {
            return; // Déjà révélée
        }
        cellule.revelerCellule();
        if (cellule.getNbBombesAdjacentes() == 0 && !cellule.getPresenceBombe()) {
            // Propagation aux cellules adjacentes si pas de bombe adjacente
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    if (di != 0 || dj != 0) { // Éviter de vérifier la cellule elle-même
                        revelerCellule(ligne + di, colonne + dj);
                    }
                }
            }
        }
    }

    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                Cellule cellule = matriceCellules[i][j];
                if (!cellule.getPresenceBombe() && !cellule.estDevoilee()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placerBombesAleatoirement() {
        Random random = new Random();
        int bombesPlacees = 0;

        while (bombesPlacees < nbBombes) {
            int ligne = random.nextInt(nbLignes);
            int colonne = random.nextInt(nbColonnes);

            // Si la cellule ne contient pas déjà une bombe, on place une bombe
            if (!matriceCellules[ligne][colonne].getPresenceBombe()) {
                matriceCellules[ligne][colonne].placerBombe();
                bombesPlacees++;
            }
        }
    }

    private void calculerBombesAdjacentes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe()) {
                    int count = 0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            int ni = i + di;
                            int nj = j + dj;

                            // Vérification des limites et présence d'une bombe
                            if (ni >= 0 && ni < nbLignes && nj >= 0 && nj < nbColonnes &&
                                matriceCellules[ni][nj].getPresenceBombe()) {
                                count++;
                            }
                        }
                    }
                    matriceCellules[i][j].setNbBombesAdjacentes(count);
                }
            }
        }
    }

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
