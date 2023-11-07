package fr.uga.miashs.inff3.bataillenavale.gameScreen;

import java.util.Arrays;

public class Navire {

    private Coordonnee debut;
    private Coordonnee fin;
    private Coordonnee[] partiesTouchees;
    private int nbTouchees;

    public Navire (Coordonnee debut, int longueur, boolean estVertical){
        partiesTouchees = new Coordonnee[longueur + 1];
        this.debut = debut;

        if (estVertical){
            fin = new Coordonnee(debut.getLigne() + longueur, debut.getColonne());
        }
        else {
            fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur);
        }
    }

    public String toString(){
        return "(" + debut.toString() + ", " + fin.toString() + ", " + Arrays.deepToString(partiesTouchees) + ")";
    }

    public Coordonnee getDebut(){
        return debut;
    }

    public Coordonnee getFin(){
        return fin;
    }

    public boolean contient(Coordonnee c){
        boolean x = fin.getLigne() >= c.getLigne() && c.getLigne() >= debut.getLigne();
        boolean y = fin.getColonne() >= c.getColonne() && c.getColonne() >= debut.getColonne();

        return x && y;
    }

    public static boolean intersectionNonVide(int d1, int f1, int d2, int f2){
        return Math.max(d1, d2) <= Math.min(f1, f2);
    }

    public boolean chevauche(Navire n){
        boolean x = intersectionNonVide(n.debut.getLigne(), n.fin.getLigne(), debut.getLigne(), fin.getLigne());
        boolean y = intersectionNonVide(n.debut.getColonne(), n.fin.getColonne(), debut.getColonne(), fin.getColonne());
        return x && y;
    }

    public boolean touche(Navire n){
        boolean y = intersectionNonVide(n.debut.getLigne(), n.fin.getLigne(), debut.getLigne(), fin.getLigne());
        boolean x = intersectionNonVide(n.debut.getColonne(), n.fin.getColonne(), debut.getColonne(), fin.getColonne());

        if (x && !y){
            return Math.abs(n.debut.getLigne() - debut.getLigne()) <= 1 ||
                    Math.abs(n.debut.getLigne() - fin.getLigne()) <= 1 ||
                    Math.abs(n.fin.getLigne() - debut.getLigne()) <= 1 ||
                    Math.abs(n.fin.getLigne() - fin.getLigne()) <= 1;
        }

        else if (y && !x){
            return Math.abs(n.debut.getColonne() - debut.getColonne()) <= 1 ||
                    Math.abs(n.debut.getColonne() - fin.getColonne()) <= 1 ||
                    Math.abs(n.fin.getColonne() - debut.getColonne()) <= 1 ||
                    Math.abs(n.fin.getColonne() - fin.getColonne()) <= 1;

        }
        return false;
    }

    public boolean estTouche(Coordonnee c){
        for (int i = 0; i < nbTouchees; i++){
            if (partiesTouchees[i].equals(c))
                return true;

        }
        return false;
    }

    public boolean recoitTir(Coordonnee c){
        if (contient(c)){
            if (!(estTouche(c))){
                partiesTouchees[nbTouchees] = c;
                nbTouchees ++;
            }
            return true;
        }
        return false;
    }

    public boolean aEteTouche(){
        return nbTouchees > 0;
    }

    public boolean estCoule(){
        return nbTouchees == partiesTouchees.length;
    }
}
