package fr.lordkadoc.entities;

public class Poulet extends Joueur {

	public Poulet(String nom, int x, int y) {
		super(nom, x, y, 25, 12 ,"Poulet");
	}

	@Override
	public void attaquer(int posX, int posY) {
		if(this.peutTirer()){
			this.getArme().poser(this.getX(), this.getY());
			this.getArme().setRechargeTermine(false);
		}
	}

}
