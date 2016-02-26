package fr.lordkadoc.entities;

public class Chasseur extends Joueur {

	public Chasseur(String nom, int x, int y) {
		super(nom, x, y, 30, 8, "Chasseur");
	}
	
	@Override
	public void attaquer(int posX, int posY) {
		if(this.peutTirer()){
			this.getArme().tirer(this.getX(), this.getY(), posX, posY);
			this.getArme().setRechargeTermine(false);
		}
	}

	@Override
	public void detonate() {}
}
