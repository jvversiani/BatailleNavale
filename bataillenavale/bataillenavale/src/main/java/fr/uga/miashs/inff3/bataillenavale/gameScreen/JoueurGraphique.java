package fr.uga.miashs.inff3.bataillenavale.gameScreen;

import fr.uga.miashs.inff3.bataillenavale.gameScreen.Coordonnee;
import fr.uga.miashs.inff3.bataillenavale.gameScreen.GrilleGraphique;
import fr.uga.miashs.inff3.bataillenavale.gameScreen.GrilleNavaleGraphique;
import fr.uga.miashs.inff3.bataillenavale.gameScreen.Joueur;

import java.awt.*;


public class JoueurGraphique extends Joueur {
    public JoueurGraphique(GrilleGraphique gg, GrilleNavaleGraphique gng, int taille, String nomJoueur){
        super(gg, gng, taille, nomJoueur);
    }

    protected void retourAttaque(Coordonnee c, int etat){
        if (etat == Joueur.TOUCHE)
            gg.colorie(c, Color.RED);

        else if (etat == Joueur.A_L_EAU)
            gg.colorie(c, Color.BLUE);

        else if (etat == Joueur.COULE){
//            Coordonnee[] contour = gng.coordonneesVoisines(coule);
            gg.colorie(coule[0], coule[1], new Color(0x480B0B));
//            for (Coordonnee cord: contour){
//                if (cord == null)
//                    break;
//                gg.colorie(cord, Color.BLUE);
//            }
        }

    }

    public Coordonnee choisirAttaque() throws InterruptedException {
        return gg.getCoordonneeSelectionnee();
    }
}
