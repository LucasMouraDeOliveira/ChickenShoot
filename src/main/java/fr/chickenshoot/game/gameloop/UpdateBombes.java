//package fr.chickenshoot.game.gameloop;
//
//import java.util.Iterator;
//
//import fr.chickenshoot.game.entities.Chicken;
//import fr.chickenshoot.game.entities.Hunter;
//import fr.chickenshoot.game.projectiles.Bombe;
//import fr.chickenshoot.game.projectiles.Explosion;
//import fr.lordkadoc.launcher.ServerInstance;
//import fr.lordkadoc.map.Carte;
//
//public class UpdateBombes extends GameLoopOperation{
//	
//	public UpdateBombes(ServerInstance instance, long delay) {
//		super(instance, delay);
//	}
//
//	@Override
//	protected void update() {
//		
//		Carte carte = this.instance.getCarte();
//		
//		Iterator<Bombe> bombes = carte.getBombes().iterator();
//		Bombe bombe;
//		while(bombes.hasNext()){
//			bombe = bombes.next();
//			if(bombe.isActivated()){
//				for(Chicken chicken : carte.getChickens()){
//					bombe.checkPlayerCollision(chicken);
//				}
//				for(Hunter hunter : carte.getHunters()){
//					bombe.checkPlayerCollision(hunter);
//				}
//				carte.addExplosion(new Explosion((int)bombe.getX(), (int)bombe.getY(), bombe.getRadius()));
//				bombes.remove();
//			}
//		}
//	}
//	
//	
//
//}
