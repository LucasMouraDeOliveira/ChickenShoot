package fr.chickenshoot.game.gameloop;

import java.util.Iterator;

import fr.chickenshoot.game.entities.Chicken;
import fr.chickenshoot.game.entities.Hunter;
import fr.chickenshoot.game.projectiles.Bombe;
import fr.chickenshoot.game.projectiles.Bullet;
import fr.lordkadoc.map.Carte;

public class UpdatePlayer extends GameLoopOperation {
	
	protected Carte carte;

	public UpdatePlayer(Carte carte, long delay) {
		super(delay);
		this.carte = carte;
	}

	@Override
	protected void update() {
		
		Iterator<Chicken> chickens = carte.getChickens().iterator();
		Iterator<Bombe> bombes;
		Bombe bombe;
		Chicken chicken;
		while(chickens.hasNext()){
			chicken = chickens.next();
			if(chicken.isAlive()){
				if(chicken.isShooting()){
					carte.addBombe((Bombe)chicken.shoot());
					chicken.setBombCount(chicken.getBombCount() + 1);
					chicken.getWeapon().setAmmos(chicken.getWeapon().getAmmos()-1);
					chicken.getWeapon().setOnReload();
					chicken.setShooting(false);
				}
				if(chicken.getDetonate()){
					bombes = carte.getBombes().iterator();
					while(bombes.hasNext()){
						bombe = bombes.next();
						if(bombe.getOwner().equals(chicken)){
							bombe.setActivated(true);
						}
					}
					chicken.setBombCount(0);
					chicken.setDetonate(false);
				}
			}else{
				chickens.remove();
			}
		}
		Iterator<Hunter> hunters = carte.getHunters().iterator();
		Hunter hunter;
		while(hunters.hasNext()){
			hunter = hunters.next();
			if(hunter.isAlive()){
				if(hunter.isShooting()){
					carte.addBullet((Bullet)hunter.shoot());
					hunter.getWeapon().setAmmos(hunter.getWeapon().getAmmos()-1);
					hunter.getWeapon().setOnReload();
					hunter.setShooting(false);
				}
			}else{
				hunters.remove();
			}
		}
	}
}
