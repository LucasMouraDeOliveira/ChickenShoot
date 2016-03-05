package fr.chickenshoot.game.entities;

import javax.json.JsonObjectBuilder;

public class Hunter extends Player {
	
	public static final int HUNTER_SIZE = 30;

	public static final int HUNTER_SPEED = 8;
	
	public Hunter(String name, int x, int y) {
		super(name, x, y, HUNTER_SIZE, HUNTER_SPEED);
	}

	@Override
	public boolean canShoot() {
		return weapon.getAmmos() > 0 && weapon.isReloaded();
	}
	
	@Override
	public void addCustomProperties(JsonObjectBuilder builder) {
		builder.add("type", "Chasseur");
	}

}
