package fr.chickenshoot.game.gameloop;

import java.util.Iterator;

import fr.chickenshoot.game.entities.Chicken;
import fr.chickenshoot.game.entities.Hunter;
import fr.lordkadoc.launcher.ServerInstance;
import fr.lordkadoc.map.Carte;

public class UpdateWeapon extends GameLoopOperation {
	
	public UpdateWeapon(ServerInstance instance, long delay) {
		super(instance, delay);
	}

	@Override
	protected void update() {

		Carte carte = this.instance.getCarte();
		
		Iterator<Chicken> chickens = carte.getChickens().iterator();
		Chicken chicken;
		while(chickens.hasNext()){
			chicken = chickens.next();
			chicken.getWeapon().reloadTick(delay);
		}
		Iterator<Hunter> hunters = carte.getHunters().iterator();
		Hunter hunter;
		while(hunters.hasNext()){
			hunter = hunters.next();
			hunter.getWeapon().reloadTick(delay);
		}
	}

}
