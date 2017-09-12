package fr.chickenshoot.game.entities;

public class PlayerParams {
	
	protected String name;
	
	protected int maxHealth, health, size, speed;
	
	protected int x,y;
	
	public PlayerParams(String name, int size, int x, int y, int speed) {
		this.name = name;
		this.maxHealth = 100;
		this.health = 100;
		this.size = size;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
