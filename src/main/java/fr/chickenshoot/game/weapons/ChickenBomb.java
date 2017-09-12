package fr.chickenshoot.game.weapons;

import fr.chickenshoot.game.entities.Player;
import fr.chickenshoot.game.projectiles.Bombe;
import fr.lordkadoc.launcher.ServerInstance;

public class ChickenBomb extends Weapon {

	public ChickenBomb(ServerInstance instance, Player owner, int damage, int ammos) {
		super(instance, owner, "Bombe", damage, ammos, 300);
	}

	@Override
	public void onShoot() {
		this.instance.getCarte().addBombe(new Bombe(owner, owner.getPlayerParams().getX(), owner.getPlayerParams().getY(), damage, 80));
	}

	@Override
	public boolean canShoot() {
		return ammos > 0 && isDoneReloading() /*&& getBombCount() < 3*/;
	}

}
