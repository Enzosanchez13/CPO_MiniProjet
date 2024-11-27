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
        this.matriceCellules = new Cellule[nbLignes][nbColonnes];
        
        // Initialisation des cellules
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule();
            }
        }
        
        placerBombesAleatoirement();
        calculerBombesAdjacentes();
    }

    private void placerBombesAleatoirement() {
        Random rand = new Random();
        int bombesPlacees = 0;
        while (bombesPlacees < nbBombes) {
            int i = rand.nextInt(nbLignes);
            int j = rand.nextInt(nbColonnes);
            if (!matriceCellules[i][j].getPresenceBombe()) {
                matriceCellules[i][j].placerBombe();
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

    public void revelerCellule(int i, int j) {
        if (i < 0 || i >= nbLignes || j < 0 || j >= nbColonnes || matriceCellules[i][j].estDevoilee()) {
            return;
        }

        matriceCellules[i][j].revelerCellule();
        
        if (matriceCellules[i][j].getPresenceBombe()) {
            System.out.println("BOOM! Vous avez perdu.");
            System.exit(0); // Fin de la partie
        } else if (matriceCellules[i][j].getNbBombesAdjacentes() == 0) {
            // Propagation automatique des cellules vides adjacentes
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    int ni = i + di;
                    int nj = j + dj;
                    if (ni >= 0 && ni < nbLignes && nj >= 0 && nj < nbColonnes) {
                        revelerCellule(ni, nj);
                    }
                }
            }
        }
    }

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

    public String afficherGrille() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                sb.append(matriceCellules[i][j].toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    int getNbLignes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int getNbColonnes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean toutesLesCellulesSuresRevelees() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
