package fr.chickenshoot.game.weapons;

import fr.chickenshoot.game.entities.Hunter;
import fr.chickenshoot.game.projectiles.Bullet;
import fr.chickenshoot.game.projectiles.Projectile;

public class Gun extends Weapon<Hunter> {

	public Gun(Hunter player, int damage, int ammos) {
		super(player, "Ghetto blasta", damage, ammos, 200);
	}

	@Override
	public Projectile shoot() {
		return new Bullet(owner, owner.getX(), owner.getY(), owner.getAngle(), damage);
	}

}