package fr.lordkadoc.entities;

public class Poulet extends Joueur {

	public Poulet(int x, int y) {
		super(x, y, 30, "Poulet");
	}

	@Override
	public void attaquer(int posX, int posY) {
		if(this.peutTirer()){
			this.getArme().poser(this.getX(), this.getY());
			this.getArme().setRechargeTermine(false);
		}
	}

}
