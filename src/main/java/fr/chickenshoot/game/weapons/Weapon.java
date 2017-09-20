//package fr.chickenshoot.game.weapons;
//
//import fr.chickenshoot.game.entities.Player;
//import fr.lordkadoc.launcher.ServerInstance;
//
//public abstract class Weapon {
//	
//	protected ServerInstance instance;
//	
//	protected Player owner;
//	
//	protected String name;
//	
//	protected int damage;
//	
//	protected int ammos;
//	
//	protected long reloadTime;
//	
//	protected long currentReloadTime;
//	
//	public Weapon(ServerInstance instance, Player owner, String name, int damage, int ammos, long reloadTime) {
//		this.instance = instance;
//		this.name = name;
//		this.owner = owner;
//		this.damage = damage;
//		this.ammos = ammos;
//		this.reloadTime = reloadTime;
//		this.currentReloadTime = 0;
//	}
//	
//	public abstract void onShoot();
//	
//	public abstract boolean canShoot();
//
//	public Player getOwner() {
//		return owner;
//	}
//	
//	public int getAmmos() {
//		return ammos;
//	}
//	
//	public void setAmmos(int ammos) {
//		this.ammos =  ammos;
//	}
//	
//	public int getDamage() {
//		return damage;
//	}
//
//	public String getName() {
//		return name;
//	}
//	
//	public void startReloading(){
//		currentReloadTime = reloadTime;
//	}
//
//	public void reloadTick(long delay) {
//		currentReloadTime = Math.max(0, currentReloadTime-delay);
//	}
//	
//	public boolean isDoneReloading(){
//		return currentReloadTime == 0;
//	}
//
//}