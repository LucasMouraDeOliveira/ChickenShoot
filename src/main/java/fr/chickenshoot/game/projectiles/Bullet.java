//package fr.chickenshoot.game.projectiles;
//
//import java.awt.Point;
//import java.awt.geom.Rectangle2D;
//
//import fr.chickenshoot.game.entities.Player;
//import fr.lordkadoc.map.Carte;
//
//public class Bullet extends Projectile {
//	
//	protected double vx,vy;
//	
//	protected double angle;
//	
//	protected boolean collided;
//
//	public Bullet(Player owner, double x, double y, double angle, int damage) {
//		super(owner, x, y, damage);
//		this.angle = angle;
//		this.vx = 10*Math.cos(owner.getPlayerState().getAngle()-Math.PI/2);
//		this.vy = 10*Math.sin(owner.getPlayerState().getAngle()-Math.PI/2);
//		this.collided = false;
//	}
//	
//	public void move(){
//		this.x+=vx;
//		this.y+=vy;
//	}
//	
//	/**
//	 * Teste si la balle est rentrée en collision avec un élément du décor.
//	 * Si oui, la balle est détruite.
//	 * 
//	 * @param carte le terrain de jeu.
//	 */
//	public void checkMapCollision(Carte carte){
//		if(collided)
//			return;
//		if(!carte.estVide(carte.typeCellule(new Point((int)x, (int)y))))
//			collided = true;
//	}
//	
//	/**
//	 * Teste la collision avec un joueur. Si la balle touche la hitbox du joueur, elle est détruite, et le joueur prends des dégâts.
//	 * La balle ne teste pas la collision avec le joueur qui l'a tirée.
//	 * 
//	 * @param player le joueur avec lequel on veut tester la collision.
//	 */
//	public void checkPlayerCollision(Player player){
//		if(collided || player.equals(owner)){
//			return;
//		}
//		Rectangle2D.Double r = player.getHitbox();
//		Point p = new Point((int)x,(int)y);
//		if(r.contains(p)){
//			player.hit(damage);
//			collided = true;
//		}
//	}
//
//	/**
//	 * Retourne la position sur l'axe X de la balle, en précision double.
//	 * 
//	 * @return la position x de la balle.
//	 */
//	public double getX() {
//		return x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//	
//	public boolean isCollided(){
//		return collided;
//	}
//
//	public double getAngle() {
//		return angle;
//	}
//
//}