package fr.chickenshoot.game.projectiles;

import fr.chickenshoot.game.entities.Player;

public abstract class Projectile {
	
	protected double x, y;
	
	protected int damage;
	
	protected Player owner;
	
	public Projectile(Player owner, double x, double y, int damage) {
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.owner = owner;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public int getDamage() {
		return damage;
	}

}