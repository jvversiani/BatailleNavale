package fr.uga.miashs.inff3.bataillenavale.gameScreen;

import java.util.Random;

public class GrilleNavale {
    private Navire[] navires;
    private int nbNavires;
    private int tailleGrille;
    private Coordonnee[] tirsRecus;
    private int nbTirsRecus;

    public GrilleNavale(){
        this(10);
    }

    public GrilleNavale(int taille){
        tailleGrille = taille;
    }

    public boolean ajouteNavire(Navire n) {
        if (estDansGrille(n.getDebut()) && estDansGrille(n.getFin())){
            for (int i = 0; i < nbNavires; i++){
                if (n.chevauche(navires[i]) || n.touche(navires[i]))
                    return false;
            }
            navires[nbNavires] = n;
            nbNavires ++;
            return true;
        }
        return false;
    }

    public void placementAuto(int[] taillesNavires) {
        navires = new Navire[taillesNavires.length];
        Random r = new Random();

        int nbCordonnesOccupes = 0;

        for (int taille : taillesNavires) {
            Navire n;
            boolean reponse;

            do {
                Coordonnee c = new Coordonnee(r.nextInt(tailleGrille), r.nextInt(tailleGrille));
                n = new Navire(c, taille - 1, r.nextBoolean());
                reponse = ajouteNavire(n);
            } while (!reponse);

            nbCordonnesOccupes += taille;
        }

        tirsRecus = new Coordonnee[nbCordonnesOccupes];
    }

    public boolean estDansGrille(Coordonnee c) {
        return c.getLigne() >= 0 && c.getLigne() < tailleGrille && c.getColonne() >= 0 && c.getColonne() < tailleGrille;
    }

    private boolean estDansTirsRecus(Coordonnee c) {
        for (int i = 0; i < nbTirsRecus; i++){
            if (tirsRecus[i].equals(c))
                return true;

        }
        return false;
    }

    private boolean ajouteDansTirsRecus(Coordonnee c) {
        if (! estDansTirsRecus(c)){
            tirsRecus[nbTirsRecus] = c;
            nbTirsRecus ++;
            return true;
        }
        return false;
    }

    public boolean recoitTir(Coordonnee c) {
        for (Navire n: navires){
            if (n.recoitTir(c) && ajouteDansTirsRecus(c))
                return true;
        }
        return false;
    }

    public boolean estTouche(Coordonnee c) {
        for (Navire n: navires){
            if (n.estTouche(c))
                return true;
        }
        return false;
    }

    public boolean estALEau(Coordonnee c) {
        return (!estTouche(c) && estDansTirsRecus(c));
    }

    public boolean perdu() {
        for (Navire n: navires){
            if (!n.estCoule())
                return false;
        }
        return true;
    }

    public int getTaille() {
        return tailleGrille;
    }

    public Coordonnee[] estCoule(Coordonnee c) {
        for (Navire n: navires){
            if (n.contient(c) && n.estCoule())
                return new Coordonnee[]{n.getDebut(), n.getFin()};
        }
        return new Coordonnee[0];
    }

//    public Coordonnee[] coordonneesVoisines (Coordonnee[] c) {
//        Coordonnee[] cases = new Coordonnee[20];
//        int nbCases = 0;
//
//        int debut_c = c[0].getColonne();
//        int debut_l = c[0].getLigne();
//        int fin_c = c[1].getColonne();
//        int fin_l = c[1].getLigne();
//
//        for (int i = debut_c; i < fin_c + 1; i++) {
//
//            for (int j = debut_l - 1
//                 ; j <= fin_l + 1; j++) {
//
//                Coordonnee cord = new Coordonnee(j, i);
//                if (estDansGrille(cord) && !(Navire.intersectionNonVide(debut_c, fin_c, cord.getColonne(), cord.getColonne()) &&
//                        Navire.intersectionNonVide(debut_l, fin_l, cord.getLigne(), cord.getLigne()))) {
//                    cases[nbCases] = cord;
//                    nbCases++;
//                }
//            }
//        }
//
//        return cases;
//    }

    public static void main(String[] args) {
        GrilleNavale gn = new GrilleNavale(10);
        System.out.println(gn.estDansGrille(new Coordonnee("e13")));
    }

}
