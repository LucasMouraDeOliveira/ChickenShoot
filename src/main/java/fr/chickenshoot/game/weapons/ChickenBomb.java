package fr.chickenshoot.game.weapons;

import fr.chickenshoot.game.entities.Chicken;
import fr.chickenshoot.game.projectiles.Bombe;
import fr.chickenshoot.game.projectiles.Projectile;

public class ChickenBomb extends Weapon<Chicken> {

	public ChickenBomb(Chicken owner, int damage, int ammos) {
		super(owner, "Bombe", damage, ammos, 300);
	}

	@Override
	public Projectile shoot() {
		return new Bombe(owner, owner.getX(), owner.getY(), damage, 80);
	}

}
