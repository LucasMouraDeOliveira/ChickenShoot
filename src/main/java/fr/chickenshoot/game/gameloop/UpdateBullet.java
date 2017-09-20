//package fr.chickenshoot.game.gameloop;
//
//import java.util.Iterator;
//
//import fr.chickenshoot.game.entities.Chicken;
//import fr.chickenshoot.game.entities.Hunter;
//import fr.chickenshoot.game.projectiles.Bullet;
//import fr.lordkadoc.launcher.ServerInstance;
//import fr.lordkadoc.map.Carte;
//
//public class UpdateBullet extends GameLoopOperation{
//	
//	public UpdateBullet(ServerInstance instance, long delay) {
//		super(instance, delay);
//	}
//
//	@Override
//	protected void update() {
//
//		Carte carte = this.instance.getCarte();	
//		
//		Iterator<Bullet> bullets = carte.getBullets().iterator();
//		Bullet bullet;
//		while(bullets.hasNext()){
//			bullet = bullets.next();
//			if(bullet.isCollided()){
//				bullets.remove();
//			}
//			bullet.move();
//			bullet.checkMapCollision(carte);
//			for(Chicken chicken : carte.getChickens()){
//				bullet.checkPlayerCollision(chicken);
//			}
//			for(Hunter hunter : carte.getHunters()){
//				bullet.checkPlayerCollision(hunter);
//			}
//		}
//	}
//
//}
