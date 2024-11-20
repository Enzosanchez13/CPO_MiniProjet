/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet_duo_SANCHEZ;

/**
 *
 * @author enzos
 */
public class Cellule {
    private boolean presenceBombe;
    private boolean devoilee;
    private int nbBombesAdjacentes;

    public Cellule() {
        this.presenceBombe = false;
        this.devoilee = false;
        this.nbBombesAdjacentes = 0;
    }

    public boolean getPresenceBombe() {
        return presenceBombe;
    }

    public int getNbBombesAdjacentes() {
        return nbBombesAdjacentes;
    }

    public boolean estDevoilee() {
        return devoilee;
    }

    public void placerBombe() {
        this.presenceBombe = true;
    }

    public void revelerCellule() {
        this.devoilee = true;
    }

    public void setNbBombesAdjacentes(int nbBombesAdjacentes) {
        this.nbBombesAdjacentes = nbBombesAdjacentes;
    }

    @Override
    public String toString() {
        if (!devoilee) {
            return "?"; // Si la cellule n'est pas dévoilée
        } else if (presenceBombe) {
            return "B"; // Si la cellule contient une bombe
        } else if (nbBombesAdjacentes > 0) {
            return String.valueOf(nbBombesAdjacentes); // Nombre de bombes adjacentes
        } else {
            return " "; // Si la cellule est vide
        }
    }
}
