package fr.chickenshoot.game.gameloop;

import java.util.Iterator;

import fr.chickenshoot.game.projectiles.Explosion;
import fr.lordkadoc.launcher.ServerInstance;
import fr.lordkadoc.map.Carte;

public class UpdateExplosions extends GameLoopOperation {
	
	public UpdateExplosions(ServerInstance instance, long delay) {
		super(instance, delay);
	}

	@Override
	protected void update() {

		Carte carte = this.instance.getCarte();
		
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
