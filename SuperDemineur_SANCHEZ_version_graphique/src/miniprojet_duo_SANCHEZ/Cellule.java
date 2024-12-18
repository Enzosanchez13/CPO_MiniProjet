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
    private boolean marquee = false;

    public Cellule() {
        this.presenceBombe = false;
        this.devoilee = false;
        this.nbBombesAdjacentes = 0;
    }

    // Placer une bombe dans la cellule
    public void placerBombe() {
        this.presenceBombe = true;
    }

    public boolean getPresenceBombe() {
        return this.presenceBombe;
    }

    public boolean estDevoilee() {
        return this.devoilee;
    }

    public void reveler() {
        this.devoilee = true;
    }

    public void setNbBombesAdjacentes(int nbBombesAdjacentes) {
        this.nbBombesAdjacentes = nbBombesAdjacentes;
    }

    public int getNbBombesAdjacentes() {
        return this.nbBombesAdjacentes;
    }

    public String toString() {
        if (!devoilee) {
            return "?"; // Cellule non révélée
        }
        if (presenceBombe) {
            return "B"; // Cellule avec une bombe
        }
        return nbBombesAdjacentes > 0 ? String.valueOf(nbBombesAdjacentes) : " "; // Cellule sans bombe et sans bombes adjacentes
    }
    private boolean estMarquee;

}
