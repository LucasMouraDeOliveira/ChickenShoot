package fr.refactoring.game.physics;

import java.awt.geom.Point2D;

public class CollisionParameters {
	
	//Le point de collision
	protected final Point2D.Double collisionPoint;
	
	//Le point de la hitbox qui va rentrer en collision
	protected final Point2D.Double hitboxPointColliding;
	
	public CollisionParameters(Point2D.Double collisionPoint, Point2D.Double hitboxPointColliding) {
		this.collisionPoint = collisionPoint;
		this.hitboxPointColliding = hitboxPointColliding;
	}
	
	public Point2D.Double getCollisionPoint() {
		return collisionPoint;
	}
	
	public Point2D.Double getHitboxPointColliding() {
		return hitboxPointColliding;
	}
	
	public Point2D.Double getVectorToCollision() {
		double x = collisionPoint.getX()-hitboxPointColliding.getX();
		double y = collisionPoint.getY()-hitboxPointColliding.getY();
		return new Point2D.Double(x, y);
	}

}
