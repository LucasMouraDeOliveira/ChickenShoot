package fr.chickenshoot.game.entities;

import javax.json.JsonObjectBuilder;

public class Chicken extends Player {
	
	public static final int CHICKEN_SIZE = 24;
	
	public static final int CHICKEN_SPEED = 12;
	
	protected boolean detonate;
	
	protected boolean visible;

	private int currentBombs;
	
	public Chicken(String name, int x, int y) {
		super(name, x, y, CHICKEN_SIZE, CHICKEN_SPEED);
		detonate = false;
		listener.addHandler(Action.DETONATE, new DetonationHandler(this));
		setBombCount(0);
		setVisible(true);
	}

	/**
	 * Met à jour la valeur de détonation de bombe pour le poulet.
	 * 
	 * @param value la nouvelle valeur de détonation
	 */
	public void setDetonate(boolean value) {
		detonate = value;
	}
	
	/**
	 * @return vrai si le poulet fait exploser ses bombes.
	 */
	public boolean getDetonate(){
		return detonate;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public int getBombCount() {
		return currentBombs;
	}

	public void setBombCount(int currentBombs) {
		this.currentBombs = currentBombs;
	}

	@Override
	public void addCustomProperties(JsonObjectBuilder builder) {
		builder.add("type", "Poulet");
		builder.add("visible", visible);
	}

}