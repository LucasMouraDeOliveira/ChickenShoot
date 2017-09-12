package fr.chickenshoot.game.projectiles;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import fr.chickenshoot.game.entities.Chicken;
import fr.chickenshoot.game.entities.Player;

public class Bombe extends Projectile {
	
	protected boolean activated;

	protected int radius;
	
	public Bombe(Player owner, double x, double y, int damage, int radius) {
		super(owner, x, y, damage);
		this.radius = radius;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	public Rectangle2D.Double getHitbox(){
		int x1 = (int)(x - radius);
		int y1 = (int)(y - radius);
		return new Rectangle2D.Double(x1, y1, 2*radius, 2*radius);
	}
	
	public void checkPlayerCollision(Player player){
		Point point = new Point(player.getPlayerParams().getX(),player.getPlayerParams().getY());
		Rectangle2D.Double r = getHitbox();
		if(r.contains(point)){
			int damage = this.damage;
			if(player instanceof Chicken){
				damage/=2;
			}
			player.hit(damage);	
		}
	}

	public int getRadius() {
		return radius;
	}

}