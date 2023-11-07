package fr.uga.miashs.inff3.bataillenavale.gameScreen;

import java.awt.*;
import javax.swing.*;

public class BatailleNavale {
    private static GrilleGraphique grilleAttaque;
    private static GrilleNavaleGraphique grilleJoueur;
    private static GrilleGraphique grilleJeu;
    private static int taille;

    public static JPanel initFenetre(final int taille) {
        BatailleNavale.taille = taille;

        grilleAttaque = new GrilleGraphique(taille);
        grilleJoueur = new GrilleNavaleGraphique(taille);
        grilleJoueur.placementAuto(new int[]{5,4,3,3,2,2});

        grilleJeu = grilleJoueur.getGrilleGraphique();

        JPanel fenetre = new JPanel();
        fenetre.setLayout(new GridLayout(1, 2));
        grilleAttaque.setBorder(BorderFactory.createTitledBorder("Grille de tirs"));
        grilleJeu.setBorder(BorderFactory.createTitledBorder("Grille de jeu"));
        grilleJeu.setClicActive(false);
        fenetre.add(grilleAttaque);
        fenetre.add(grilleJeu);

        fenetre.setVisible(true);

        return fenetre;
    }

    public static GrilleNavaleGraphique getGrilleJoueur(){
        return grilleJoueur;
    }

    public static void changeTitledBorder(){
        grilleJeu.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        grilleAttaque.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    }

	public static JoueurGraphique initJoueur(String nomJoueur, int taille) {
		return new JoueurGraphique(grilleAttaque,grilleJoueur, taille, nomJoueur);
	}

    public static Bot initBot(String botname, int taille) {
        GrilleGraphique grilleAttaque = new GrilleGraphique(taille);
        GrilleNavaleGraphique grilleJoueur = new GrilleNavaleGraphique(taille);
        grilleJoueur.placementAuto(new int[]{5,4,3,3,2,2});
//        initFenetre(botname, grilleAttaque,grilleJoueur.getGrilleGraphique());
        return new Bot(grilleAttaque,grilleJoueur, taille, botname);
    }

    public static Bot simulatePlayer(String botname, int taille) {
        return new Bot(grilleAttaque,grilleJoueur, taille, botname);
    }

    public void start() throws InterruptedException {
        Joueur j1 = simulatePlayer("Player 1", taille);
        Joueur j2 = initBot("Bot 1", taille);

        Thread.sleep(2000);

        j1.jouerAvec(j2);
    }
}
