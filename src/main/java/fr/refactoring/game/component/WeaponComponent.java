package fr.refactoring.game.component;

import com.badlogic.ashley.core.Component;

public class WeaponComponent implements Component {
	
	private int ammo;
	
	private int reloadTime, maxReloadTime;
	
	private String weaponName;
	
	private int damage;
	
	private boolean shooting;
	
	public WeaponComponent(String weaponName, int damage, int defaultAmmo, int reloadTime) {
		this.weaponName = weaponName;
		this.damage = damage;
		this.ammo = defaultAmmo;
		this.reloadTime = 0;
		this.maxReloadTime = reloadTime;
		this.shooting = false;
	}

	public int getAmmo() {
		return ammo;
	}
	
	public void setAmmo(int amount) {
		this.ammo = amount;
	}
	
	public void reloadForAmount(int reload) {
		this.reloadTime = Math.max(0, this.reloadTime-reload);
	}
	
	public void startReloading() {
		this.reloadTime = this.maxReloadTime;
	}
	
	public boolean isReloaded() {
		return this.reloadTime == 0;
	}
	
	public String getWeaponName() {
		return weaponName;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public boolean isShooting() {
		return shooting;
	}
	
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	
}