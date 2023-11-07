package fr.uga.miashs.inff3.bataillenavale.gameScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Bot extends Joueur {
    private Coordonnee[] cases;
    private int nbCases;
    private Coordonnee touchedStart;
    private Coordonnee touchedEnd;

    public Bot(GrilleGraphique gg, GrilleNavaleGraphique gng, int taille, String nomJoueur){
        super(gg, gng, taille, nomJoueur);
        cases = new Coordonnee[taille * taille];
        nbCases = 0;
//        gg.setVisible(false);
//        gng.getGrilleGraphique().setVisible(false);
    }

    protected void retourAttaque(Coordonnee c, int etat) {
        if (etat == TOUCHE){
            if (touchedStart == null){
                touchedStart = c;
                touchedEnd = c;
            }
            else
                touchedEnd = c;
            gg.colorie(c, Color.RED);
        }
        else if (etat == A_L_EAU){
            gg.colorie(c, Color.BLUE);
        }
        else if (etat == COULE){
            gg.colorie(coule[0], coule[1], new Color(0x480B0B));
            touchedStart = null;
            touchedEnd = null;
        }
    }

    public Coordonnee choisirAttaque() throws InterruptedException {
        java.lang.Thread.sleep(0);
        Random r = new Random();
        Coordonnee c;
        int [] nextMove;
        ArrayList<int[]> listMoves = defineArray();

        if (touchedStart == null){
            do {
                c = new Coordonnee(r.nextInt(getTailleGrille()), r.nextInt(getTailleGrille()));
            } while (searchList(cases, c) >= 0);
            cases[nbCases] = c;
            nbCases ++;
            return c;
        }
        else {
            do {
                if (listMoves.isEmpty()){
                    Coordonnee temporary = touchedStart;
                    touchedStart = touchedEnd;
                    touchedEnd = temporary;
                    listMoves = defineArray();
                }

                nextMove = listMoves.get(r.nextInt(listMoves.size()));
                listMoves.remove(nextMove);
                c = new Coordonnee(touchedEnd.getLigne() + nextMove[0], touchedEnd.getColonne() + nextMove[1]);

            } while (searchList(cases, c) >= 0 || !(gng.estDansGrille(c)));
            cases[nbCases] = c;
            nbCases ++;
            return c;
        }
    }

    static int searchList(Coordonnee[] list_c, Coordonnee c){
        for (int i = 0; i < list_c.length; i++){
            if (list_c[i] == null)
                return -1;
            if (list_c[i].equals(c))
                return i;
        }
        return -1;
    }

    static ArrayList<int[]> defineArray(){
        ArrayList<int[]> listMoves = new ArrayList<>();
        listMoves.add(new int[]{0, 1});
        listMoves.add(new int[]{0, -1});
        listMoves.add(new int[]{1, 0});
        listMoves.add(new int[]{-1, 0});
        return listMoves;
    }
}
