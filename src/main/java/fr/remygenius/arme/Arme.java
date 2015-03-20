package fr.remygenius.arme;

/**
 * Classe generale des armes du poulet et du chasseur
 * @author remy
 *
 */

public abstract class Arme {
	private String nom;
	private int degat;
	private double tempsDeRecharge;
	private boolean rechargeTermine;
	private int munitions;
	private int niveauArme;
	
	public Arme(String nom, int degat, double tempsDeRecharge, int munitions){
		this.nom = nom;
		this.degat = degat;
		this.tempsDeRecharge = tempsDeRecharge;
		this.munitions = munitions;
		this.niveauArme = 1;
		this.rechargeTermine = true;
	}
	
	public boolean isRechargeTermine() {
		return rechargeTermine;
	}

	public void setRechargeTermine(boolean rechargeTermine) {
		this.rechargeTermine = rechargeTermine;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public abstract void tirer(int x1, int y1, int x2, int y2);
	
	public abstract void poser(int x, int y);
	
	public String getNom(){
		return nom;
	}
	
	public int getDegat(){
		return degat;
	}

	public double getTempsDeRecharge() {
		return tempsDeRecharge;
	}

	public void setTempsDeRecharge(double tempsDeRecharge) {
		this.tempsDeRecharge = tempsDeRecharge;
	}

	public int getMunitions() {
		return munitions;
	}

	public void setMunitions(int munitions) {
		this.munitions = munitions;
	}

	public int getNiveauArme() {
		return niveauArme;
	}

	public void setNiveauArme(int niveauArme) {
		this.niveauArme = niveauArme;
	}
}
