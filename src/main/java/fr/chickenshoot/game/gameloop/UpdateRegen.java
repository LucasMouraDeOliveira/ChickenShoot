package fr.chickenshoot.game.gameloop;

import java.awt.Point;
import java.util.Iterator;

import fr.chickenshoot.game.entities.Player;
import fr.lordkadoc.map.Carte;

public class UpdateRegen extends GameLoopOperation {
	
	private Carte carte;

	public UpdateRegen(Carte carte, long delay) {
		super(delay);
		this.carte = carte;
	}

	@Override
	protected void update() {
		updateIn(carte.getHunters().iterator());
		updateIn(carte.getChickens().iterator());
	}
	
	public void updateIn(Iterator<? extends Player> players){
		Player player;
		while(players.hasNext()) {
			player = players.next();
			if(carte.estRegen(carte.typeCellule(new Point(player.getX(),player.getY())))){
				player.getWeapon().setAmmos(player.getWeapon().getAmmos()+1);
				player.heal(5);
			}
		}
	}

}
