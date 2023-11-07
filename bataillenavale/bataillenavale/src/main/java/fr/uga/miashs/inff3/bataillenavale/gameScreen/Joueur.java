package fr.uga.miashs.inff3.bataillenavale.gameScreen;


import javax.swing.*;
import java.awt.*;

/**
 * Classe abstraite reprÃ©sentant la logique du jeu de bataille navale
 * @author jerome.david@univ-grenoble-alpes.fr
 *
 */
public abstract class Joueur {

    public final static int TOUCHE=1;
    public final static int COULE=2;
    public final static int A_L_EAU=3;
    public final static int GAMEOVER = 4;

    private Coordonnee[] matchCases;
    private String prenom;
    static protected Coordonnee[] coule;

    /**
     * L'adversaire du joueur courant. Cet attribut ne sera pas initialisÃ© dans le constructeur
     * mais dans la mÃ©thode <code>jouerAvec(Joueur a)</code>
     */
    protected Joueur adversaire;

    /**
     * La taille de la grille du joueur
     */
    private int tailleGrille;
    protected GrilleGraphique gg;
    protected GrilleNavaleGraphique gng;

    /**
     * Initialise un nouveau joueur
     * @param t la taille de la grille
     * @param n le prenom du joueur
     */

    public Joueur(GrilleGraphique gg, GrilleNavaleGraphique gng, int t, String n) {
        this.prenom=n;
        tailleGrille=t;
        this.gg = gg;
        this.gng = gng;
    }

    /**
     * Initialise un joueur sans nom
     * @param t la taille de la grille
     */
    public Joueur(int t) {
        this(new GrilleGraphique(t), new GrilleNavaleGraphique(t), t,"Inconnu");
    }

    /**
     * retourne la taille de grille du joueur
     * @return la taille de la grille du joueur
     */
    public int getTailleGrille() {
        return tailleGrille;
    }

    /**
     * retourne le prÃ©nom du joueur
     * @return le prenom du joueur
     */
    public String getPrenom() {
        return prenom;
    }


    /**
     * Cette mÃ©thode intialise l'attribut adversaire de l'instance courante avec le Joueur passÃ© en paramÃ¨tre.
     * Elle fait de mÃªme pour l'adversaire : l'adversaire de a va Ãªtre le joueur courant.
     * Finalement elle appelle les mÃ©thode <code>initPartie(int tailleGrilleAdversaire);</code> pour le joueur courant et pour l'adversaire
     * @param a l'aderversaire contre qui le joueur va jouer
     */
    public void jouerAvec(Joueur a) throws InterruptedException {

        if (this.adversaire != null) {
            throw new RuntimeException("You are already playing with " + adversaire.getPrenom());
        } else if (a.adversaire != null) {
            throw new RuntimeException("You're opponent is already playing");
        } else if (a.getTailleGrille() != this.getTailleGrille()) {
            throw new RuntimeException("You do not have the same grid size...");
        }
        this.adversaire = a;
        a.adversaire = this;

        Joueur p1 = this;
        Joueur p2 = adversaire;
        int res = 0;
        while (res != GAMEOVER) {
            System.out.println("==============================");
            System.out.println(p1.getPrenom() + " ataque: ");
            Coordonnee c = p1.choisirAttaque();
            System.out.println("Il a choisit: " + c.toString());
            res = p2.defendre(c);
            System.out.println("Resultat: " + printRes(res));
            p1.retourAttaque(c, res);
            p2.retourDefense(c, res);
            Joueur x = p1;
            p1 = p2;
            p2 = x;
        }
    }

    public String printRes(int res){
        return switch (res) {
            case 1 -> "touche";
            case 2 -> "coule";
            case 3 -> "à l'eau";
            case 4 -> "à gagne!";
            default -> "";
        };
    }

    /**
     * MÃ©thode appelÃ©e Ã  chaque retour d'attaque portÃ©e par ce joueur sur son adversaire
     * @param c La coordonnÃ©e attaquÃ©e sur la grille de l'adversaire
     * @param etat Etat de l'attaque portÃ©e qui peut Ãªtre Ã©gal au constantes <code>Joueur.TOUCHE</code>, <code>Joueur.COULE</code>, ou <code>Joueur.A_L_EAU</code>
     */
    protected abstract void retourAttaque(Coordonnee c, int etat);

    /**
     * MÃ©thode appelÃ©e Ã  chaque retour d'attaque portÃ© par l'adversaire sur ce Joueur
     * @param c La coordonnÃ©e attaquÃ©e par l'adversaire
     * @param c Etat de l'attaque reÃ§ue qui peut Ãªtre Ã©gal au constantes <code>Joueur.TOUCHE</code>, <code>Joueur.COULE</code>, ou <code>Joueur.A_L_EAU</code>
     */

    protected void retourDefense(Coordonnee c, int etat) throws InterruptedException {
        if (etat == TOUCHE)
            gng.getGrilleGraphique().colorie(c, Color.RED);

        else if (etat == A_L_EAU)
            gng.getGrilleGraphique().colorie(c, Color.BLUE);

        else if (etat == COULE)
            gng.getGrilleGraphique().colorie(coule[0], coule[1], new Color(0x480B0B));
        else if (etat == GAMEOVER){
            gng.getGrilleGraphique().colorie(coule[0], coule[1], new Color(0x480B0B));

            Thread.sleep(1000);

            gg.looserPattern();
            adversaire.gg.winnerPattern();

            gng.getGrilleGraphique().endGameScreen(false);
            adversaire.gng.getGrilleGraphique().endGameScreen(true);
            BatailleNavale.changeTitledBorder();
        }

    }

    /**
     * MÃ©thode appelÃ©e lorsque c'est au tour de ce joueur de jouer.
     * Elle doit retourner la coordonnÃ©e choisie pour l'attaque
     * @return la coordonnee Ã  attaquer sur la grille de l'adversaire
     */
    public abstract Coordonnee choisirAttaque() throws InterruptedException;

    /**
     * MÃ©thode de dÃ©fense. Cette mÃ©thode est appelÃ©e lorsqu'une attaque de l'adversaire est reÃ§ue.
     * En focntion de l'impact de l'attaque, elle retourne un entier qui peut prendre les valeurs suivantes :
     * Joueur.TOUCHE, Joueur.COULE, Joueur.A_L_EAU, Joueur.GAME_OVER
     * @param c la cordonnÃ©e attaquÃ©e
     * @return le resultat de cette attaque: Joueur.TOUCHE, Joueur.COULE, Joueur.A_L_EAU ou Joueur.GAME_OVER
     */
    protected int defendre(Coordonnee c){
        if (gng.recoitTir(c)){
            coule = gng.estCoule(c);

            if (gng.perdu()){
                return Joueur.GAMEOVER;
            }

            if (coule.length > 0)
                return Joueur.COULE;

            return Joueur.TOUCHE;
        }

        return Joueur.A_L_EAU;
    }
}