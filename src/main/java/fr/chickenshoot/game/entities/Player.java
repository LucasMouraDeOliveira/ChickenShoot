package fr.chickenshoot.game.entities;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObjectBuilder;

import fr.chickenshoot.game.projectiles.Projectile;
import fr.chickenshoot.game.weapons.Weapon;

/**
 * Un Player est l'avatar contrôlé par un joueur dans ChickenShoot. 
 * Il comporte un certain nombre de caractéristiques (vie, vitesse, taille) qui varient en fonction du type du Player ({@link Chasseur} ou {@link Poulet}).
 * 
 * @author lucasmouradeoliveira
 *
 */
public abstract class Player {
	
	protected String name;
	
	protected int maxHealth;
	
	protected int health;
	
	protected int size;
	
	protected int x,y;
	
	protected int speed;
	
	protected boolean[] moving;
	
	protected boolean shooting;
	
	//the direction in which the player is looking
	protected double angle;
	
	protected Weapon<?> weapon;
	
	protected PlayerActionListener listener;

	/**
	 * Crée un nouveau joueur et initialise ses caractéristiques.
	 * 
	 * @param name le nom du joueur, qui sera affiché au dessus de sa tête en partie
	 * @param x la position sur l'axe x du joueur, en précision entière
	 * @param y la position sur l'axe y du joueur, en précision entière
	 * @param size la taille (à la fois verticale et horizontale) du joueur en pixel
	 * @param speed la vitesse de déplacement du joueur.
	 */
	public Player(String name, int x, int y, int size, int speed) {
		this.name = name;
		this.maxHealth = 100;
		this.health = 100;
		this.size = size;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.moving = new boolean[4];
		this.shooting = false;
		this.listener = new PlayerActionListener(this);		
	}
	
	/**
	 * Retourne vrai si le joueur est en capacité de tirer avec son arme.
	 * En fonction du type de joueur, les conditions pour tirer sont différentes.
	 * 
	 * @return vrai si le joueur peut tirer
	 */
	public abstract boolean canShoot();
	
	/**
	 * Déclenche un tir de l'arme du joueur et retourne le projectile crée.
	 * 
	 * @return un projectile.
	 */
	public Projectile shoot(){
		return weapon.shoot();
	}

	/**
	 * Retourne le rectangle correspondant à la hitbox du joueur.
	 * 
	 * @return la hitbox du joueur
	 */
	public Rectangle2D.Double getHitbox() {
		return new Rectangle2D.Double(x-size/2, y-size/2, size, size);
	}
	
	/**
	 * Fait subir un certain nombre de dégâts au joueur.
	 * 
	 * @param hitpoints le nombre de points de dégât infligés au joueur
	 */
	public void hit(int hitpoints) {
		health = Math.max(0, health-hitpoints);
	}
	
	/**
	 * Soigne le joueur d'un certain nombre de dégats.
	 * 
	 * @param hitpoints le nombre de point de vie récupérés par le joueur.
	 */
	public void heal(int hitpoints) {
		health = Math.min(maxHealth, health+hitpoints);
	}
	
	/**
	 * Fait pivoter le joueur pour qu'il regarde sur le point de coordonnées indiqué.
	 * 
	 * @param x la coordonnée x du point
	 * @param y la coordonnée y du point
	 */
	public void shift(int x, int y) {
		int x1 = x-this.x;
		int y1 = y-this.y;
		this.angle = -Math.atan2(x1, y1)-Math.PI;
	}
	
	public void handle(Action action) {
		listener.handle(action);
	}
	
	/**
	 * Retourne vrai si le joueur a encore des points de vie.
	 * 
	 * @return vrai si le joueur est vivant
	 */
	public boolean isAlive() {
		return health > 0;
	}
	
	/**
	 * Renvoie vrai si le joueur est en train de se déplacer dans la direction spécifiée.
	 * 
	 * @param direction la direction à tester
	 * 
	 * @return vrai si le joueur se déplace dans cette direction
	 */
	public boolean isMoving(int direction) {
		try{
			return moving[direction];
		}catch(ArrayIndexOutOfBoundsException e){
			return false;
		}
	}
	
	/**
	 * Met à jour la valeur de déplacement (vrai ou faux) du joueur pour une direction.
	 * 
	 * @param direction la direction à mettre à jour
	 * @param value la nouvelle valeur de déplacement pour la direction
	 */
	public void setMoving(int direction, boolean value) {
		try{
			moving[direction] = value;
		}catch(Exception e){}
	}

	/**
	 * @return vrai si le joueur est en train de tirer.
	 */
	public boolean isShooting() {
		return shooting;
	}
	
	/**
	 * Met à jour la valeur de tir du joueur.
	 * 
	 * @param shooting si le joueur est en train de tirer ou non
	 */
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	
	/**
	 * @return l'arme du joueur.
	 */
	public Weapon<?> getWeapon() {
		return weapon;
	}
	
	public void setWeapon(Weapon<?> weapon) {
		this.weapon = weapon;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getName() {
		return name;
	}
	
	public void addDefaultProperties(JsonObjectBuilder builder) {
		builder.add("name", name);
		builder.add("x", x);
		builder.add("y", y);
		builder.add("ammos", weapon.getAmmos());
		builder.add("maxHealth", maxHealth);
		builder.add("health", health);
		builder.add("angle", angle);
		builder.add("weapon", weapon.getName());
	}
	
	public abstract void addCustomProperties(JsonObjectBuilder builder);
	
	public void getJSon(JsonObjectBuilder builder) {
		addDefaultProperties(builder);
		addCustomProperties(builder);
	}
	
	@Deprecated
	public List<Point> hitboxPoints(String direction) {
		List<Point> points = new ArrayList<Point>();
		int x1 = x - (size/2);
		int y1 = y - (size/2);
		if(direction.equals("NORTH")){
			points.add(new Point(x1,y1));
			points.add(new Point(x1+size,y1));
		}else if(direction.equals("SOUTH")){
			points.add(new Point(x1,y1+size));
			points.add(new Point(x1+size,y1+size));		
		}else if(direction.equals("WEST")){
			points.add(new Point(x1,y1));
			points.add(new Point(x1,y1+size));
		}else if(direction.equals("EAST")){
			points.add(new Point(x1+size,y1));
			points.add(new Point(x1+size,y1+size));
		}
		return points;
	}
	
}