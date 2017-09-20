//package fr.chickenshoot.game.weapons;
//
//import fr.chickenshoot.game.entities.Player;
//import fr.chickenshoot.game.projectiles.Bullet;
//import fr.lordkadoc.launcher.ServerInstance;
//
//public class Gun extends Weapon {
//
//	public Gun(ServerInstance instance, Player player, int damage, int ammos) {
//		super(instance, player, "Ghetto blasta", damage, ammos, 200);
//	}
//
//	@Override
//	public void onShoot() {
//		this.instance.getCarte().addBullet(new Bullet(owner, owner.getPlayerParams().getX(), owner.getPlayerParams().getY(), owner.getPlayerState().getAngle(), damage));
//	}
//
//	@Override
//	public boolean canShoot() {
//		return ammos > 0 && isDoneReloading();
//	}
//
//}