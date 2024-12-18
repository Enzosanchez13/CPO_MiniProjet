package miniprojet_duo_SANCHEZ;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FenetrePrincipale extends javax.swing.JFrame {

    GrilleDeJeu grille;
    JButton[][] boutons;

   public FenetrePrincipale() {
    initComponents(); // Méthode générée automatiquement, ne pas modifier.

    ajouterBoutonPersonnalise(); // Ajout du bouton personnalisé en dehors de initComponents.

    initialiserJeu(10, 10, 10); // Initialisation avec des valeurs par défaut.
}

private void ajouterBoutonPersonnalise() {
    // Création du bouton pour le mode personnalisé
    JButton modePersonnalise = new JButton("Personnaliser");

    // Ajout d'un écouteur d'événements
    modePersonnalise.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            modePersonnaliseActionPerformed(evt);
        }
    });

    // Ajout du bouton à la fenêtre
    this.getContentPane().add(modePersonnalise, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 260, 30));

    // Mise à jour de l'affichage
    this.revalidate();
    this.repaint();
}

private void modePersonnaliseActionPerformed(ActionEvent evt) {
    // Demander au joueur de saisir les paramètres personnalisés
    try {
        String lignesInput = JOptionPane.showInputDialog(this, "Nombre de lignes :", "Personnaliser", JOptionPane.QUESTION_MESSAGE);
        String colonnesInput = JOptionPane.showInputDialog(this, "Nombre de colonnes :", "Personnaliser", JOptionPane.QUESTION_MESSAGE);
        String bombesInput = JOptionPane.showInputDialog(this, "Nombre de bombes :", "Personnaliser", JOptionPane.QUESTION_MESSAGE);

        if (lignesInput != null && colonnesInput != null && bombesInput != null) {
            int nbLignes = Integer.parseInt(lignesInput);
            int nbColonnes = Integer.parseInt(colonnesInput);
            int nbBombes = Integer.parseInt(bombesInput);

            // Validation des paramètres
            if (nbLignes > 0 && nbColonnes > 0 && nbBombes >= 0 && nbBombes < nbLignes * nbColonnes) {
                initialiserJeu(nbLignes, nbColonnes, nbBombes);
            } else {
                JOptionPane.showMessageDialog(this, "Paramètres invalides. Réessayez.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Entrée non valide. Réessayez.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

    private void initialiserJeu(int nbLignes, int nbColonnes, int nbBombes) {
        // Réinitialisation de la grille de jeu
        grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);

        // Réinitialisation de la disposition du panneau
        PanneauGrille.removeAll(); // Supprimer tous les anciens boutons
        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));

        // Réinitialisation des boutons
        boutons = new JButton[nbLignes][nbColonnes];

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                JButton bouton_cellule = new JButton();
                boutons[i][j] = bouton_cellule;
                PanneauGrille.add(bouton_cellule);

                // Capturer les coordonnées dans des variables locales
                int ligne = i;
                int colonne = j;

                bouton_cellule.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!bouton_cellule.getText().equals("M")) { // Vérifie si la case n'est pas marquée
                            revelerCellule(ligne, colonne);
                        }
                    }
                });

                // Ajout d'un écouteur pour détecter le clic droit
                bouton_cellule.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) { // Clic droit
                            marquerCellule(bouton_cellule, ligne, colonne);
                        }
                    }
                });
            }
        }

        // Rafraîchir l'affichage
        PanneauGrille.revalidate();
        PanneauGrille.repaint();
    }

    private void marquerCellule(JButton bouton, int ligne, int colonne) {
        Cellule cellule = grille.getCellule(ligne, colonne);

        if (!cellule.estDevoilee()) { // Seulement si la cellule n'est pas révélée
            if (bouton.getText().equals("M")) { // Si la case est déjà marquée
                bouton.setText(""); // Retirer le marquage
            } else {
                bouton.setText("M"); // Ajouter un marquage
            }
        }
    }

    private void revelerCellule(int ligne, int colonne) {
        if (grille.getCellule(ligne, colonne).estDevoilee()) {
            return;
        }

        grille.revelerCellule(ligne, colonne);
        mettreAJourBoutons();

        if (grille.getCellule(ligne, colonne).getPresenceBombe()) {
            JOptionPane.showMessageDialog(this, "Vous avez touché une bombe ! La partie est terminée.", "Perdu", JOptionPane.ERROR_MESSAGE);
            desactiverBoutons();
        } else if (grille.estPartieTerminee()) {
            JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez gagné.", "Gagné", JOptionPane.INFORMATION_MESSAGE);
            desactiverBoutons();
        }
    }

    private void mettreAJourBoutons() {
        for (int i = 0; i < grille.getNbLignes(); i++) {
            for (int j = 0; j < grille.getNbColonnes(); j++) {
                Cellule cellule = grille.getCellule(i, j);
                JButton bouton = boutons[i][j];

                if (cellule.estDevoilee()) {
                    bouton.setEnabled(false);
                    if (cellule.getPresenceBombe()) {
                        bouton.setText("B");
                    } else {
                        int nbBombesAdjacentes = cellule.getNbBombesAdjacentes();
                        bouton.setText(nbBombesAdjacentes > 0 ? String.valueOf(nbBombesAdjacentes) : "");
                    }
                }
            }
        }
    }

    private void desactiverBoutons() {
        for (int i = 0; i < grille.getNbLignes(); i++) {
            for (int j = 0; j < grille.getNbColonnes(); j++) {
                boutons[i][j].setEnabled(false);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanneauGrille = new javax.swing.JPanel();
        rejouer = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        niveau1 = new javax.swing.JButton();
        niveau2 = new javax.swing.JButton();
        niveau3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanneauGrille.setBackground(new java.awt.Color(153, 153, 153));
        PanneauGrille.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        PanneauGrille.setForeground(new java.awt.Color(102, 102, 102));
        PanneauGrille.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 520, 430));

        rejouer.setText("REJOUER");
        rejouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejouerActionPerformed(evt);
            }
        });
        getContentPane().add(rejouer, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));
        jPanel2.setForeground(new java.awt.Color(255, 0, 51));
        jPanel2.setLayout(new java.awt.GridLayout());

        niveau1.setForeground(new java.awt.Color(51, 51, 51));
        niveau1.setText("Niveau 1");
        niveau1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                niveau1ActionPerformed(evt);
            }
        });
        jPanel2.add(niveau1);

        niveau2.setForeground(new java.awt.Color(51, 51, 51));
        niveau2.setText("Niveau 2");
        niveau2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                niveau2ActionPerformed(evt);
            }
        });
        jPanel2.add(niveau2);

        niveau3.setForeground(new java.awt.Color(51, 51, 51));
        niveau3.setText("Niveau 3");
        niveau3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                niveau3ActionPerformed(evt);
            }
        });
        jPanel2.add(niveau3);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 260, 80));

        jLabel1.setText("M: Marquer cases (clique droit)");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 170, 20));

        jLabel2.setText("B: Bombes");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rejouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejouerActionPerformed
        int nbLignes = grille.getNbLignes();
        int nbColonnes = grille.getNbColonnes();
        int nbBombes = grille.getNbBombes();

        // Réinitialiser le jeu
        initialiserJeu(nbLignes, nbColonnes, nbBombes);

        // Rafraîchir l'affichage de la fenêtre
        PanneauGrille.revalidate();
        PanneauGrille.repaint();
    }//GEN-LAST:event_rejouerActionPerformed

    private void niveau1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_niveau1ActionPerformed
        changerNiveau(8, 8, 10); // Niveau 1 : facile
    }//GEN-LAST:event_niveau1ActionPerformed

    private void niveau2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_niveau2ActionPerformed
        changerNiveau(11, 11, 20); // Niveau 2 : intermédiaire
    }//GEN-LAST:event_niveau2ActionPerformed

    private void niveau3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_niveau3ActionPerformed
        changerNiveau(15, 15, 40); // Niveau 3 : difficile
    }//GEN-LAST:event_niveau3ActionPerformed
    private void changerNiveau(int nbLignes, int nbColonnes, int nbBombes) {
        // Demande de confirmation (optionnelle)
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Changer de niveau réinitialisera la partie en cours. Voulez-vous continuer ?",
                "Changement de niveau",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            // Réinitialisation du jeu avec les nouveaux paramètres
            initialiserJeu(nbLignes, nbColonnes, nbBombes);

            // Rafraîchissement de l'affichage
            PanneauGrille.revalidate();
            PanneauGrille.repaint();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetrePrincipale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton niveau1;
    private javax.swing.JButton niveau2;
    private javax.swing.JButton niveau3;
    private javax.swing.JButton rejouer;
    // End of variables declaration//GEN-END:variables
}
