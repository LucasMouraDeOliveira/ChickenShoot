package fr.lordkadoc.entities;

public class Chasseur extends Joueur {

	public Chasseur(int x, int y) {
		super(x, y, 31, "Chasseur");
	}
	
	@Override
	public void attaquer(int posX, int posY) {
		if(this.peutTirer()){
			this.getArme().tirer(this.getX(), this.getY(), posX, posY);
			this.getArme().setRechargeTermine(false);
		}
	}
}
