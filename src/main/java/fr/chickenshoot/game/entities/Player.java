package fr.chickenshoot.game.entities;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObjectBuilder;

import fr.chickenshoot.game.weapons.Weapon;

/**
 * Un Player est l'avatar contrôlé par un joueur dans ChickenShoot. 
 * Il comporte un certain nombre de caractéristiques (vie, vitesse, taille) qui varient en fonction du type du Player ({@link Chasseur} ou {@link Poulet}).
 * 
 * @author lucasmouradeoliveira
 *
 */
public abstract class Player implements Parsable {
	
	protected PlayerParams playerParams;
	
	protected PlayerState playerState;
	
	protected Weapon weapon;
	
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
		this.playerParams = new PlayerParams(name, size, x, y, speed);
		this.playerState = new PlayerState();
		this.listener = new PlayerActionListener(this);		
	}

	/**
	 * Retourne le rectangle correspondant à la hitbox du joueur.
	 * 
	 * @return la hitbox du joueur
	 */
	public Rectangle2D.Double getHitbox() {
		int size = this.playerParams.getSize();
		return new Rectangle2D.Double(this.playerParams.getX()-size/2, this.playerParams.getY()-size/2, size, size);
	}
	
	/**
	 * Fait subir un certain nombre de dégâts au joueur.
	 * 
	 * @param hitpoints le nombre de points de dégât infligés au joueur
	 */
	public void hit(int hitpoints) {
		this.playerParams.setHealth(Math.max(0, this.playerParams.getHealth()-hitpoints));
	}
	
	/**
	 * Soigne le joueur d'un certain nombre de dégats.
	 * 
	 * @param hitpoints le nombre de point de vie récupérés par le joueur.
	 */
	public void heal(int hitpoints) {
		this.playerParams.setHealth(Math.min(this.playerParams.getMaxHealth(), this.playerParams.getHealth()+hitpoints));
	}
	
	/**
	 * Fait pivoter le joueur pour qu'il regarde sur le point de coordonnées indiqué.
	 * 
	 * @param x la coordonnée x du point
	 * @param y la coordonnée y du point
	 */
	public void shift(int x, int y) {
		int x1 = x-this.playerParams.getX();
		int y1 = y-this.playerParams.getY();
		this.playerState.setAngle(-Math.atan2(x1, y1)-Math.PI);
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
		return this.playerParams.getHealth() > 0;
	}
	
	/**
	 * @return l'arme du joueur.
	 */
	public Weapon getWeapon() {
		return weapon;
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	@Override
	public void getJSon(JsonObjectBuilder builder) {
		addDefaultProperties(builder);
		addCustomProperties(builder);
	}
	
	private void addDefaultProperties(JsonObjectBuilder builder) {
		builder.add("name", this.playerParams.getName());
		builder.add("x", this.playerParams.getX());
		builder.add("y", this.playerParams.getY());
		builder.add("ammos", weapon.getAmmos());
		builder.add("maxHealth", this.playerParams.getMaxHealth());
		builder.add("health", this.playerParams.getHealth());
		builder.add("angle", this.playerState.getAngle());
		builder.add("weapon", weapon.getName());
	}
	
	protected abstract void addCustomProperties(JsonObjectBuilder builder);
	
	@Deprecated
	public List<Point> hitboxPoints(String direction) {
		List<Point> points = new ArrayList<Point>();
		int size = this.playerParams.getSize();
		int x1 = this.playerParams.getX() - (size/2);
		int y1 = this.playerParams.getY() - (size/2);
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

	public PlayerParams getPlayerParams() {
		return this.playerParams;
	}
	
	public PlayerState getPlayerState() {
		return this.playerState;
	}
	
}