package fr.chickenshoot.game.weapons;

import fr.chickenshoot.game.entities.Player;
import fr.chickenshoot.game.projectiles.Projectile;

public abstract class Weapon<P extends Player> {
	
	protected P owner;
	
	protected String name;
	
	protected int damage;
	
	protected int ammos;
	
	protected long reloadTime;
	
	protected long currentReloadTime;
	
	public Weapon(P owner, String name, int damage, int ammos, long reloadTime) {
		this.name = name;
		this.owner = owner;
		this.damage = damage;
		this.ammos = ammos;
		this.reloadTime = reloadTime;
		this.currentReloadTime = 0;
	}
	
	public abstract Projectile shoot();

	public P getOwner() {
		return owner;
	}
	
	public int getAmmos() {
		return ammos;
	}
	
	public void setAmmos(int ammos) {
		this.ammos =  ammos;
	}
	
	public int getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}
	
	public void setOnReload(){
		currentReloadTime = reloadTime;
	}

	public void reload(long delay) {
		currentReloadTime = Math.max(0, currentReloadTime-delay);
	}
	
	public boolean isReloaded(){
		return currentReloadTime == 0;
	}

}