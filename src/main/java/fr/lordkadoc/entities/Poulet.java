package fr.lordkadoc.entities;

import java.util.ArrayList;
import java.util.List;

import fr.remygenius.armepoulet.Bombe;

public class Poulet extends Joueur {
	
	protected List<Bombe> bombes;

	public Poulet(String nom, int x, int y) {
		super(nom, x, y, 24, 12 ,"Poulet");
		bombes = new ArrayList<Bombe>();
	}

	@Override
	public void attaquer(int posX, int posY) {
		if(this.peutTirer() && this.bombes.size() < 3){
			this.getArme().poser(this.getX(), this.getY());
			this.getArme().setRechargeTermine(false);
		}
	}
	
	public void addBombe(Bombe bombe){
		this.bombes.add(bombe);
	}
	
	@Override
	public void detonate() {
		for(Bombe b : bombes){
			b.activate();
		}
		bombes.clear();
	}

}
