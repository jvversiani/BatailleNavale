package fr.uga.miashs.inff3.bataillenavale.gameScreen;

public class Coordonnee {
	
	private int ligne;
	private int colonne;
	
	public Coordonnee(String s) {
		colonne = s.toUpperCase().charAt(0) - 'A';
		ligne = Integer.parseInt(s.substring(1)) - 1;
	}
	
	public Coordonnee(int l, int c) {
		ligne = l;
		colonne = c;
	}
	
	public int getLigne() {
		return ligne;
	}
	
	public int getColonne() {
		return colonne;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Coordonnee) {
			Coordonnee c = (Coordonnee) o;
			return c.colonne == colonne && c.ligne == ligne;
		}
		return false;
	}
	
	public boolean voisine(Coordonnee c) {
		return (Math.abs(c.colonne - colonne) + Math.abs(c.ligne - ligne)) == 1;
	}
	
	public int compareTo(Coordonnee c) {
		int l_diff = ligne - c.ligne;
		int c_diff = colonne - c.colonne;
		
		if (l_diff > 0)
			return l_diff;
		
		else if (l_diff == 0) {
			return c_diff;
		}
		else 
			return l_diff;
	}
	
	public String toString() {
		char c = (char) (colonne + 'A');
		int l = ligne + 1;
		return "" + c + l;
	}
}

