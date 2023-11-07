package fr.uga.miashs.inff3.bataillenavale.gameScreen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestNavire {

    private Navire n1;
    private Navire n2;
    private Navire n3;
    private Navire n4;
    private Navire n5;
    private final Coordonnee c1 = new Coordonnee("A3");
    private final Coordonnee c2 = new Coordonnee("C7");
    private final Coordonnee c3 = new Coordonnee("J1");


    @Test
    public void testConstructeurNavire(){

        // Navire vertical
        n1 = new Navire(c1, 2, true);
        assertEquals("A5", n1.getFin().toString());
        assertEquals("A3", n1.getDebut().toString());

        // Navire horizontal
        n1 = new Navire(c1, 2, false);
        assertEquals("C3", n1.getFin().toString());
        assertEquals("A3", n1.getDebut().toString());
    }

    @Test
    public void testToString(){
        n1 = new Navire(c1, 2, false);
        n2 = new Navire(c2, 2, false);
        n3 = new Navire(c3, 2, false);


        assertEquals("(A3, C3, [null, null])", n1.toString());
        assertEquals("(C7, E7, [null, null])", n2.toString());
        assertEquals("(J1, L1, [null, null])", n3.toString());

    }

    @Test
    public void testGetDebut(){
        n1 = new Navire(new Coordonnee("A3"), 2, false);
        n2 = new Navire(c2, 2, false);

        assertEquals(c1, n1.getDebut());
        assertEquals(c2, n2.getDebut());
    }

    @Test
    public void testGetFin(){
        n1 = new Navire(new Coordonnee("A3"), 2, false);
        n2 = new Navire(c2, 2, false);

        assertEquals(new Coordonnee("C3"), n1.getFin());
        assertEquals(new Coordonnee("E7"), n2.getFin());
    }

    @Test
    public void testContient(){
        n1 = new Navire(c1, 2, false);
        n2 = new Navire(c2, 2, false);
        n3 = new Navire(c3, 2, false);

        // contient
        assertTrue(n1.contient(new Coordonnee("B3")));
        assertTrue(n2.contient(new Coordonnee("E7")));
        assertTrue(n3.contient(c3));

        // ne contient pas
        assertFalse(n1.contient(new Coordonnee("C4")));
        assertFalse(n2.contient(new Coordonnee("C6")));
        assertFalse(n3.contient(new Coordonnee("H27")));
    }

    @Test
    public void chevauche(){
        n1 = new Navire(c1, 2, false);
        n2 = new Navire(new Coordonnee("B1"), 2, true);
        n3 = new Navire(new Coordonnee("C3"), 2, false);
        n4 = new Navire(new Coordonnee(""), 2, false);
        n5 = new Navire(new Coordonnee("C3"), 2, false);

        // chevauche
        assertTrue(n1.chevauche(n2));
        assertTrue(n1.chevauche(n3));

        // ne chevauche pas

    }
}
