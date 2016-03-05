package fr.chickenshoot.game.gameloop;

import java.util.Iterator;

import fr.chickenshoot.game.projectiles.Explosion;
import fr.lordkadoc.map.Carte;

public class UpdateExplosions extends GameLoopOperation {
	
	private Carte carte;

	public UpdateExplosions(Carte carte, long delay) {
		super(delay);
		this.carte = carte;
	}

	@Override
	protected void update() {
		Iterator<Explosion> explosions = carte.getExplosions().iterator();
		Explosion explosion;
		while(explosions.hasNext()){
			explosion = explosions.next();
			explosion.setTailleActu(explosion.getTailleActu()+1);
			if(explosion.getTailleActu() >= explosion.getTailleMax()){
				explosions.remove();
			}
		}
	}

}
