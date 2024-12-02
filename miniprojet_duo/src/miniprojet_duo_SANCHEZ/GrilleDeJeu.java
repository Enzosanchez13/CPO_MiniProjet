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
    private int nbLignes;
    private int nbColonnes;
    private int nbBombes;
    private Cellule[][] matriceCellules;

    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
        this.matriceCellules = new Cellule[nbLignes][nbColonnes];

        // Initialiser la matrice de cellules
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule();
            }
        }

        // Placer les bombes de manière aléatoire
        placerBombesAleatoirement();

        // Calculer les bombes adjacentes pour chaque cellule
        calculerBombesAdjacentes();
    }

    public void placerBombesAleatoirement() {
        Random random = new Random();
        int bombesPlacees = 0;

        while (bombesPlacees < nbBombes) {
            int i = random.nextInt(nbLignes);
            int j = random.nextInt(nbColonnes);

            if (!matriceCellules[i][j].getPresenceBombe()) {
                matriceCellules[i][j].placerBombe();
                bombesPlacees++;
            }
        }
    }

    public void calculerBombesAdjacentes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe()) {
                    int nbBombesAdjacentes = 0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (i + di >= 0 && i + di < nbLignes && j + dj >= 0 && j + dj < nbColonnes) {
                                if (matriceCellules[i + di][j + dj].getPresenceBombe()) {
                                    nbBombesAdjacentes++;
                                }
                            }
                        }
                    }
                    matriceCellules[i][j].setNbBombesAdjacentes(nbBombesAdjacentes);
                }
            }
        }
    }

    public void revelerCellule(int i, int j) {
        // Si la cellule est valide et non déjà révélée
        if (i >= 0 && i < nbLignes && j >= 0 && j < nbColonnes && !matriceCellules[i][j].estDevoilee()) {
            matriceCellules[i][j].reveler();

            // Si la cellule révélée n'a pas de bombes adjacentes, révéler les cellules adjacentes
            if (matriceCellules[i][j].getNbBombesAdjacentes() == 0) {
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        if (i + di >= 0 && i + di < nbLignes && j + dj >= 0 && j + dj < nbColonnes) {
                            revelerCellule(i + di, j + dj);
                        }
                    }
                }
            }
        }
    }

    public boolean estPartieTerminee() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                // Si une cellule n'est pas révélée et ce n'est pas une bombe, la partie n'est pas terminée
                if (!matriceCellules[i][j].estDevoilee() && !matriceCellules[i][j].getPresenceBombe()) {
                    return false;
                }
            }
        }
        return true; // Si toutes les cellules sûres sont révélées, la partie est terminée
    }

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
    
    
    public Cellule getCellule(int i, int j) {
    return matriceCellules[i][j];
}

public int getNbLignes() {
    return nbLignes;
}

public int getNbColonnes() {
    return nbColonnes;
}


}
