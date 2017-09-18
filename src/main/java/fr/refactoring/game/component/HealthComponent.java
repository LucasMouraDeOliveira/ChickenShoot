package fr.refactoring.game.component;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
	
	protected int health;
	
	protected int maxHealth;
	
	public HealthComponent(int health) {
		this.maxHealth = health;
		this.health = health;
	}

	public int getHealth() {
		return health;
	}
	
	public void heal(int amount) {
		if(amount > 0)
			this.health = Math.min(this.maxHealth, this.health+amount);
	}
	
	public void hit(int amount) {
		if(amount > 0)
			this.health = Math.max(0, this.health-amount);
	}
	
	public boolean isAlive() {
		return this.health > 0;
	}

	public void display() {
		System.out.println(this.health + " health remaining");
	}
	
}
