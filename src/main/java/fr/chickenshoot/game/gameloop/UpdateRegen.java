package fr.chickenshoot.game.gameloop;

import java.awt.Point;
import java.util.Iterator;

import fr.chickenshoot.game.entities.Player;
import fr.lordkadoc.launcher.ServerInstance;
import fr.lordkadoc.map.Carte;

public class UpdateRegen extends GameLoopOperation {
	
	public UpdateRegen(ServerInstance instance, long delay) {
		super(instance, delay);
	}

	@Override
	protected void update() {

		Carte carte = this.instance.getCarte();
		
		updateIn(carte, carte.getHunters().iterator());
		updateIn(carte, carte.getChickens().iterator());
	}
	
	public void updateIn(Carte carte, Iterator<? extends Player> players){
		Player player;
		while(players.hasNext()) {
			player = players.next();
			if(carte.estRegen(carte.typeCellule(new Point(player.getPlayerParams().getX(),player.getPlayerParams().getY())))){
				player.getWeapon().setAmmos(player.getWeapon().getAmmos()+1);
				player.heal(5);
			}
		}
	}

}
