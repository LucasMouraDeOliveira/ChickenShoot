package fr.chickenshoot.game.gameloop;

import java.util.Iterator;

import fr.chickenshoot.game.entities.Chicken;
import fr.chickenshoot.game.entities.Hunter;
import fr.lordkadoc.map.Carte;

public class UpdateWeapon extends GameLoopOperation {
	
	protected Carte carte;

	public UpdateWeapon(Carte carte, long delay) {
		super(delay);
		this.carte = carte;
	}

	@Override
	protected void update() {
		Iterator<Chicken> chickens = carte.getChickens().iterator();
		Chicken chicken;
		while(chickens.hasNext()){
			chicken = chickens.next();
			chicken.getWeapon().reload(delay);
		}
		Iterator<Hunter> hunters = carte.getHunters().iterator();
		Hunter hunter;
		while(hunters.hasNext()){
			hunter = hunters.next();
			hunter.getWeapon().reload(delay);
		}
	}

}
