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
    private boolean revelee;
    private int bombesVoisines;

    public Cellule() {
        this.presenceBombe = false;
        this.revelee = false;
        this.bombesVoisines = 0;
    }

    public boolean getPresenceBombe() {
        return presenceBombe;
    }

    public void setPresenceBombe(boolean presenceBombe) {
        this.presenceBombe = presenceBombe;
    }

    public boolean isRevelee() {
        return revelee;
    }

    public void reveler() {
        this.revelee = true;
    }

    public int getBombesVoisines() {
        return bombesVoisines;
    }

    public void setBombesVoisines(int bombesVoisines) {
        this.bombesVoisines = bombesVoisines;
    }

    @Override
    public String toString() {
        if (!revelee) return ".";
        if (presenceBombe) return "B";
        return String.valueOf(bombesVoisines);
    }
}
