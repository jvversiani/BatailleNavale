package fr.uga.miashs.inff3.bataillenavale.gameScreen;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


class JButtonCoordonnee extends JButton {

    private Coordonnee c;
    public JButtonCoordonnee(Coordonnee c) {
        this.c=c;
    }

    public Coordonnee getCoordonnee() {
        return c;
    }
}
/**
 * Classe reprÃ©sentant un composant graphique "Grille". Une grille est composÃ©e
 * de JButton
 *
 * @author jerome.david@univ-grenoble-alpes.fr
 *
 */
public class GrilleGraphique extends JPanel implements ActionListener {

    private static final long serialVersionUID = 8857166149660579225L;

    /**
     * La matrice des boutons (cases de la grille)
     */
    private JButton[][] cases;

    /**
     * La coordonnee actuellement selectionnÃ©e.
     * Null si aucune selection en cours
     */
    private Coordonnee coordonneeSelectionnee;

    private EndGamePanel endPannel;

    /**
     * Initialise une grille carrÃ©e de taille donnÃ©e
     *
     * @param taille
     *            la taille de la grille
     */
    public GrilleGraphique(int taille) {
        try {
            // Certains LookAndFeels ne colorient pas les boutons.
            // on choisi celui le plus simple (et le moins joli)
            UIManager.setLookAndFeel(UIManager
                    .getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setLayout(new GridLayout(taille + 1, taille + 1));
        this.setBackground(new Color(198,208,212));

        this.add(new JLabel());
        for (int i = 0; i < taille; i++) {
            JLabel lbl = new JLabel(String.valueOf((char) ('A' + i)));
            lbl.setHorizontalAlignment(JLabel.CENTER);
            lbl.setBackground(new Color(172,174,177));
            this.add(lbl);
        }
        JButton btn;
        cases = new JButton[taille][taille];
        for (int i = 0; i < taille; i++) {
            JLabel lbl = new JLabel(String.valueOf(i + 1));
            lbl.setHorizontalAlignment(JLabel.CENTER);
            this.add(lbl);
            for (int j = 0; j < taille; j++) {
                btn = new JButtonCoordonnee(new Coordonnee(i,j));
                btn.setBackground(new Color(186,188,191));
                cases[i][j] = btn;
                this.add(cases[i][j]);
                cases[i][j].addActionListener(this);
            }
        }
        coordonneeSelectionnee=null;
    }

/**
 * Colorie la case indiquÃ©e par la coordonnÃ©e
 *
 * @param cord
 *            la coordonnÃ©e de la case Ã  colorier
 * @param color
 *            la couleur de la case
 */
    public void colorie(Coordonnee cord, Color color) {
        cases[cord.getLigne()][cord.getColonne()].setBackground(color);
    }

    /**
     * Colorie le rectangle compris entre les deux coordonnees
     *
     * @param debut
     *            CoordonnÃ©e du dÃ©but de la zone Ã  colorier (haut gauche)
     * @param fin
     *            CoordonnÃ©e de la fin de la zone Ã  colorier (bas droit)
     * @param color
     *            la couleur de la case
     */
    public void colorie(Coordonnee debut, Coordonnee fin, Color color) {
        for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
            for (int j = debut.getColonne(); j <= fin.getColonne(); j++) {
                cases[i][j].setBackground(color);
            }
        }

    }

    public void endGameScreen(boolean result){
        this.removeAll();
        this.revalidate();
        this.repaint();
        this.setLayout(null);
        this.setLayout(new BorderLayout());
        endPannel = new EndGamePanel();
        this.add(endPannel.getMainPanel(), BorderLayout.CENTER);
        if (result)
            endPannel.getResultLabel().setText("Victory!");
        else
            endPannel.getResultLabel().setText("Defeat :(");
    }

    public EndGamePanel getEndPanel(){
        return endPannel;
    }

    public void winnerPattern(){
        for (JButton[] ligne: cases){
            for (JButton btn: ligne){
                btn.setBackground(Color.GREEN);
            }
        }

        paintEyes();

        cases[5][1].setBackground(Color.BLACK);
        cases[6][1].setBackground(Color.BLACK);
        cases[6][2].setBackground(Color.BLACK);
        cases[7][2].setBackground(Color.BLACK);
        cases[7][3].setBackground(Color.BLACK);
        cases[7][4].setBackground(Color.BLACK);
        cases[7][5].setBackground(Color.BLACK);
        cases[7][6].setBackground(Color.BLACK);
        cases[7][7].setBackground(Color.BLACK);
        cases[6][7].setBackground(Color.BLACK);
        cases[6][8].setBackground(Color.BLACK);
        cases[5][8].setBackground(Color.BLACK);
    }

    public void looserPattern(){
        for (JButton[] ligne: cases){
            for (JButton btn: ligne){
                btn.setBackground(Color.RED);
            }
        }

        paintEyes();

        cases[7][1].setBackground(Color.BLACK);
        cases[6][1].setBackground(Color.BLACK);
        cases[6][2].setBackground(Color.BLACK);
        cases[5][2].setBackground(Color.BLACK);
        cases[5][3].setBackground(Color.BLACK);
        cases[5][4].setBackground(Color.BLACK);
        cases[5][5].setBackground(Color.BLACK);
        cases[5][6].setBackground(Color.BLACK);
        cases[5][7].setBackground(Color.BLACK);
        cases[6][7].setBackground(Color.BLACK);
        cases[6][8].setBackground(Color.BLACK);
        cases[7][8].setBackground(Color.BLACK);
    }

    public void paintEyes(){
        // left eye
        cases[2][2].setBackground(Color.BLACK);
        cases[2][3].setBackground(Color.BLACK);
        cases[3][2].setBackground(Color.BLACK);
        cases[3][3].setBackground(Color.BLACK);

        // right eye
        cases[2][6].setBackground(Color.BLACK);
        cases[2][7].setBackground(Color.BLACK);
        cases[3][6].setBackground(Color.BLACK);
        cases[3][7].setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(d.width, d.width);
        return d;
    }

    public void setClicActive(boolean active) {
        SwingUtilities.invokeLater(() -> {
            this.setEnabled(false);
            for (JButton[] ligne : cases) {
                for (JButton bt : ligne) {
                    bt.setEnabled(active);
                }
            }
            this.setEnabled(true);
        });
    }


    /**
     * Methode appelÃ©e lorsque l'on clique sur une case de la grille.
     * Elle "reveille" la mÃ©thode getCoordonneeSelectionnee
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Color test = ((JButtonCoordonnee) e.getSource()).getBackground();
        if (test.equals(Color.RED) || test.equals(Color.BLUE))
            System.out.println("Error");

        else {
            this.setClicActive(false);
            coordonneeSelectionnee = ((JButtonCoordonnee) e.getSource()).getCoordonnee();
            synchronized (this) {
                this.notifyAll();
            }
        }
    }

    /**
     * Attend que l'utilisateur selectionne (clic) sur une case de la grille et
     * retourne la coordonnee qui a Ã©tÃ© selectionnÃ©e
     * @return la coordonnÃ©e selectionnÃ©e
     */
    public synchronized Coordonnee getCoordonneeSelectionnee() {
        this.setClicActive(true);
        try {
            this.wait();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        return coordonneeSelectionnee;
    }

}