package fr.uga.miashs.inff3.bataillenavale.gameScreen;

import javax.swing.*;
import java.awt.*;

public class GrilleNavaleGraphique extends GrilleNavale {
    private GrilleGraphique fenetre;

    public GrilleNavaleGraphique(int taille){
        super(taille);
        fenetre = new GrilleGraphique(taille);
    }

    public GrilleGraphique getGrilleGraphique(){
        return fenetre;
    }

    public boolean ajouteNavire(Navire n) {
        if (super.ajouteNavire(n)){
            fenetre.colorie(n.getDebut(), n.getFin(), Color.GREEN);
            return true;
        }
        return false;
    }

    public boolean recoitTir(Coordonnee c) {
        if (super.recoitTir(c)){
            fenetre.colorie(c, Color.RED);
            return true;
        }
        fenetre.colorie(c, Color.BLUE);
        return false;
    }
}
