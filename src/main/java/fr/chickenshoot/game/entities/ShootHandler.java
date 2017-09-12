package fr.chickenshoot.game.entities;

public class ShootHandler implements ActionHandler {
	
	protected Player player;

	public ShootHandler(Player player) {
		this.player = player;
	}

	@Override
	public void handle() {
		if(player.getWeapon().canShoot()){
			player.getPlayerState().setShooting(true);
		}
	}

}