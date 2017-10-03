package fr.refactoring.game.physics;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.Entity;

import fr.refactoring.game.GameMap;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.SizeComponent;
import fr.refactoring.game.component.VelocityComponent;

public class CollisionEngine implements ICollisionEngine {
	
	private final static CollisionEngine instance = new CollisionEngine();
	
	private CollisionEngine() {
		
	}
	
	public static CollisionEngine getInstance() {
		return instance;
	}

	/**
	 * Retourne la zone englobante d'une entité réalisant un mouvement. La zone
	 * englobante corresponds à un rectangle qui englobe à la fois la position
	 * actuelle d'une entité et sa future position si le mouvement est réalisé.
	 * 
	 * @param position
	 *            la position de départ de l'entité
	 * @param size
	 *            la taille de l'entité
	 * @param velocity
	 *            le vecteur directeur du mouvement qu'on souhaite faire
	 *            réaliser à l'entité
	 * 
	 * @return une hitbox rectangulaire (AABB) englobant la position actuelle et
	 *         future de l'entité
	 */
	public Rectangle2D getMovementBoundingBox(PositionComponent position, SizeComponent size, VelocityComponent velocity) {
		double x1 = position.getX();
		double y1 = position.getY();
		double x2 = x1 + velocity.getDx();
		double y2 = y1 + velocity.getDy();
		double w = size.getWidth() / 2;
		double h = size.getHeight() / 2;
		double minX = Math.min(x1 - w, x2 - w);
		double minY = Math.min(y1 - h, y2 - h);
		double maxX = Math.max(x1 + w, x2 + w);
		double maxY = Math.max(y1 + h, y2 + h);
		return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
	}

	@Override
	public Map<Entity, Point2D.Double> getCollisionWithEntities(List<Entity> otherEntities, Entity entity, PositionComponent position, SizeComponent size, VelocityComponent velocity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollisionParameters getCollisionWithGameMap(GameMap gameMap, Entity entity, PositionComponent position, SizeComponent size, VelocityComponent velocity) {
		Rectangle2D collisionArea = getMovementBoundingBox(position, size, velocity);
		//Si pas de collision dans la zone englobante -> pas de point de collision
		if(!areaCollidesWithGameMap(collisionArea, gameMap)) {
			return null;
		} else {
			//Sinon on teste une collision un peu plus détaillée
			List<Rectangle2D.Double> obstacleHitboxes = getMapHitboxesInArea(gameMap, collisionArea);
			List<Line2D.Double> obstacleLines = getLinesForHitboxes(obstacleHitboxes);
			List<Line2D.Double> movementLines = getLinesForMovement(position, size, velocity);
			Point2D.Double collisionPoint = null, hitboxCollidingPoint = null, tmp = null, tmp2 = null;
			Double distanceToCollisionPoint = null, tmpDistance = null;
			for(Line2D.Double obstacleLine : obstacleLines) {
				for(Line2D.Double movementLine : movementLines) {
					tmp = getCollisionPointBetweenLines(movementLine, obstacleLine);
					if(tmp != null) {
						tmp2 = new Point2D.Double(movementLine.getX1(), movementLine.getY1());
						tmpDistance = getAbsoluteDistance(tmp, tmp2);
						if(collisionPoint == null || tmpDistance < distanceToCollisionPoint) {
							distanceToCollisionPoint = tmpDistance;
							collisionPoint = tmp;
							hitboxCollidingPoint = tmp2;
						}
					}
				}
			}
			if(collisionPoint != null) {
				return new CollisionParameters(collisionPoint, hitboxCollidingPoint);
			} else {
				return null;
			}
		}
	}

	private Double getAbsoluteDistance(Point2D.Double p1, Point2D.Double p2) {
		return Math.abs(p2.x-p1.x)+Math.abs(p2.y-p1.y);
	}

	private Point2D.Double getCollisionPointBetweenLines(Line2D.Double movementLine, Line2D.Double obstacleLine) {
		double p0_x = movementLine.getX1();
		double p0_y = movementLine.getY1();
		double p1_x = movementLine.getX2();
		double p1_y = movementLine.getY2();
		double p2_x = obstacleLine.getX1();
		double p2_y = obstacleLine.getY1();
		double p3_x = obstacleLine.getX2();
		double p3_y = obstacleLine.getY2();
		double s1_x, s1_y, s2_x, s2_y;
	    s1_x = p1_x - p0_x;     s1_y = p1_y - p0_y;
	    s2_x = p3_x - p2_x;     s2_y = p3_y - p2_y;

	    double s, t;
	    s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
	    t = ( s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);

	    if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {// Collision detected
	    	return new Point2D.Double(p0_x + (t * s1_x), p0_y + (t * s1_y));
	    }

	    return null; // No collision
	}
	
	private List<Line2D.Double> getLinesForMovement(PositionComponent position, SizeComponent size, VelocityComponent velocity) {
		List<Line2D.Double> lines = new ArrayList<Line2D.Double>();
		double width = size.getWidth()/2;
		double height = size.getHeight()/2;
		double x1 = position.getX()-width;
		double y1 = position.getY()-height;
		double x2 = position.getX()+width;
		double y2 = position.getY()+height;
		lines.add(new Line2D.Double(x1, y1, x1 + velocity.getDx(), y1 + velocity.getDy()));
		lines.add(new Line2D.Double(x1, y2, x1 + velocity.getDx(), y2 + velocity.getDy()));
		lines.add(new Line2D.Double(x2, y2, x2 + velocity.getDx(), y2 + velocity.getDy()));
		lines.add(new Line2D.Double(x2, y1, x2 + velocity.getDx(), y1 + velocity.getDy()));
		return lines;
	}

	private List<Line2D.Double> getLinesForHitboxes(List<Rectangle2D.Double> obstacleHitboxes) {
		List<Line2D.Double> lines = new ArrayList<Line2D.Double>();
		for(Rectangle2D.Double rectangle : obstacleHitboxes) {
			lines.add(new Line2D.Double(rectangle.getX(), rectangle.getY(), rectangle.getMaxX(), rectangle.getY()));
			lines.add(new Line2D.Double(rectangle.getMaxX(), rectangle.getY(), rectangle.getMaxX(), rectangle.getMaxY()));
			lines.add(new Line2D.Double(rectangle.getMaxX(), rectangle.getMaxY(), rectangle.getX(), rectangle.getMaxY()));
			lines.add(new Line2D.Double(rectangle.getX(), rectangle.getMaxY(), rectangle.getX(), rectangle.getY()));
		}
		return lines;
	}

	private List<Rectangle2D.Double> getMapHitboxesInArea(GameMap gameMap, Rectangle2D collisionArea) {
		List<Rectangle2D.Double> hitboxes = new ArrayList<Rectangle2D.Double>();
		int cellSize = gameMap.getCellSize();
		int cellX1 = (int)(collisionArea.getX()/cellSize);
		int cellX2 = (int)(collisionArea.getMaxX()/cellSize);
		int cellY1 = (int)(collisionArea.getY()/cellSize);
		int cellY2 = (int)(collisionArea.getMaxY()/cellSize);
		Rectangle2D.Double rect;
		for(int i=cellX1; i <= cellX2; i++) {
			for (int j = cellY1; j <= cellY2; j++) {
				if(!gameMap.isEmpty(gameMap.getTypeForCell(i, j))) {
					rect = new Rectangle2D.Double(i*cellSize, j*cellSize, cellSize, cellSize);
					if(rect.intersects(collisionArea)) {
						hitboxes.add(rect);
					}
				}
			}
		}
		return hitboxes;
	}

	private boolean areaCollidesWithGameMap(Rectangle2D collisionArea, GameMap gameMap) {
		int cellSize = gameMap.getCellSize();
		int cellX1 = (int)(collisionArea.getX()/cellSize);
		int cellX2 = (int)(collisionArea.getMaxX()/cellSize);
		int cellY1 = (int)(collisionArea.getY()/cellSize);
		int cellY2 = (int)(collisionArea.getMaxY()/cellSize);
		for(int i=cellX1; i <= cellX2; i++) {
			for (int j = cellY1; j <= cellY2; j++) {
				if(!gameMap.isEmpty(gameMap.getTypeForCell(i, j))) {
					return true;
				}
			}
		}
		return false;
	}

}
