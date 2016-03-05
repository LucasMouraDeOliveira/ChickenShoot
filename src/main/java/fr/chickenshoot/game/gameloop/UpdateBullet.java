package fr.chickenshoot.game.gameloop;

import java.util.Iterator;

import fr.chickenshoot.game.entities.Chicken;
import fr.chickenshoot.game.entities.Hunter;
import fr.chickenshoot.game.projectiles.Bullet;
import fr.lordkadoc.map.Carte;

public class UpdateBullet extends GameLoopOperation{
	
	private Carte carte;
	
	public UpdateBullet(Carte carte, long delay) {
		super(delay);
		this.carte = carte;
	}

	@Override
	protected void update() {
		Iterator<Bullet> bullets = carte.getBullets().iterator();
		Bullet bullet;
		while(bullets.hasNext()){
			bullet = bullets.next();
			if(bullet.isCollided()){
				bullets.remove();
			}
			bullet.move();
			bullet.checkMapCollision(carte);
			for(Chicken chicken : carte.getChickens()){
				bullet.checkPlayerCollision(chicken);
			}
			for(Hunter hunter : carte.getHunters()){
				bullet.checkPlayerCollision(hunter);
			}
		}
	}

}
