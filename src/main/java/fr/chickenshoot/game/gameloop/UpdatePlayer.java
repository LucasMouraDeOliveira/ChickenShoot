//package fr.chickenshoot.game.gameloop;
//
//import java.util.Iterator;
//
//import fr.chickenshoot.game.entities.Chicken;
//import fr.chickenshoot.game.entities.Hunter;
//import fr.chickenshoot.game.projectiles.Bombe;
//import fr.chickenshoot.game.projectiles.Bullet;
//import fr.lordkadoc.launcher.ServerInstance;
//import fr.lordkadoc.map.Carte;
//
//public class UpdatePlayer extends GameLoopOperation {
//	
//	public UpdatePlayer(ServerInstance instance, long delay) {
//		super(instance, delay);
//	}
//
//	@Override
//	protected void update() {
//		
//		Carte carte = instance.getCarte();
//		
//		Iterator<Chicken> chickens = carte.getChickens().iterator();
//		Iterator<Bombe> bombes;
//		Bombe bombe;
//		Chicken chicken;
//		while(chickens.hasNext()){
//			chicken = chickens.next();
//			if(chicken.isAlive()){
//				if(chicken.getPlayerState().isShooting()){
//					chicken.getWeapon().onShoot();
//					chicken.setBombCount(chicken.getBombCount() + 1);
//					chicken.getWeapon().setAmmos(chicken.getWeapon().getAmmos()-1);
//					chicken.getWeapon().startReloading();
//					chicken.getPlayerState().setShooting(false);
//				}
//				if(chicken.getDetonate()){
//					bombes = carte.getBombes().iterator();
//					while(bombes.hasNext()){
//						bombe = bombes.next();
//						if(bombe.getOwner().equals(chicken)){
//							bombe.setActivated(true);
//						}
//					}
//					chicken.setBombCount(0);
//					chicken.setDetonate(false);
//				}
//			}else{
//				chickens.remove();
//			}
//		}
//		Iterator<Hunter> hunters = carte.getHunters().iterator();
//		Hunter hunter;
//		while(hunters.hasNext()){
//			hunter = hunters.next();
//			if(hunter.isAlive()){
//				if(hunter.getPlayerState().isShooting()){
//					hunter.getWeapon().onShoot();
//					hunter.getWeapon().setAmmos(hunter.getWeapon().getAmmos()-1);
//					hunter.getWeapon().startReloading();
//					hunter.getPlayerState().setShooting(false);
//				}
//			}else{
//				hunters.remove();
//			}
//		}
//	}
//}
