package fr.refactoring.game.system;

import java.awt.geom.Point2D;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.refactoring.game.GameMap;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.SizeComponent;
import fr.refactoring.game.component.VelocityComponent;
import fr.refactoring.game.component.type.ChickenComponent;
import fr.refactoring.game.component.type.HunterComponent;
import fr.refactoring.game.physics.CollisionEngine;
import fr.refactoring.game.physics.CollisionParameters;

public class PlayerMovementSystem extends EntitySystem {
	
	private ImmutableArray<Entity> entities;

	private GameMap gameMap;
	
	public PlayerMovementSystem(GameMap gameMap) {
		this.gameMap = gameMap;
	}

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.one(ChickenComponent.class, HunterComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		for(Entity entity : entities) {
			this.move(entity);
		}
	}
	
	public void move(Entity entity){
		VelocityComponent vc = Mapper.velocityMapper.get(entity);
		
		if(vc.getDx() == 0 && vc.getDy() == 0)
			return;
		
		PositionComponent pc = Mapper.positionMapper.get(entity);
		SizeComponent sc = Mapper.sizeMapper.get(entity);
		
		CollisionEngine collisionEngine = CollisionEngine.getInstance();
		CollisionParameters collisionParams = collisionEngine.getCollisionWithGameMap(gameMap, entity, pc, sc, vc);
		
		//S'il n'y a pas de collision, on déclenche le mouvement
		if(collisionParams == null) {
			pc.setX(pc.getX()+vc.getDx());
			pc.setY(pc.getY()+vc.getDy());
		} else {
			//Sinon on effectue un mouvement partiel et on tente de se déplacer sur un axe à la fois 
			//(pour "glisser" sur les murs)
			Point2D.Double collisionDist = collisionParams.getVectorToCollision();
			double moveX = collisionDist.getX();
			double moveY = collisionDist.getY();
			pc.setX(pc.getX()+moveX);
			pc.setY(pc.getY()+moveY);
			vc.setDx(vc.getDx()-moveX);
			vc.setDy(vc.getDy()-moveY);
			
			//Si plus de potentiel de mouvement, on quitte
			if(vc.isVoid()) {
				return;
			}
			
			//On crée deux vecteurs de déplacement (un horizontal, un vertical)
			VelocityComponent vcHorizontal = new VelocityComponent(vc.getDx(), 0);
			VelocityComponent vcVertical = new VelocityComponent(0, vc.getDy());
			
			if(!vcHorizontal.isVoid()){ //Mouvement horizontal
				collisionParams = collisionEngine.getCollisionWithGameMap(gameMap, entity, pc, sc, vcHorizontal);
				if(collisionParams == null) { //Mouvement total
					pc.setX(pc.getX()+vc.getDx());
				} else { //Mouvement partiel
					collisionDist = collisionParams.getVectorToCollision();
					moveX = collisionDist.getX();
					pc.setX(pc.getX()+moveX);
				}
			} else if(!vcVertical.isVoid()) { // Mouvement vertical
				collisionParams = collisionEngine.getCollisionWithGameMap(gameMap, entity, pc, sc, vcVertical);
				if(collisionParams == null) { // Mouvement total
					pc.setY(pc.getY()+vc.getDy());
				} else { // Mouvement partiel
					collisionDist = collisionParams.getVectorToCollision();
					moveY = collisionDist.getY();
					pc.setY(pc.getY()+moveY);
				}
			}
			
		}
	}
	
	/*private void oldMove() {
		int speed = mc.getSpeed();
		List<Point> hitboxPoints = null;
		int x = 0;
		int y = 0;
				
		if(c[0]){ // north
			x = 0;
			y = -speed;
			hitboxPoints = this.getHitboxForEntityAndDirection(entity, "NORTH");
			calculerDeplacement("NORTH", x, y, hitboxPoints, entity);
		}
		if(c[1]){ //south
			x = 0;
			y = speed;
			hitboxPoints = this.getHitboxForEntityAndDirection(entity, "SOUTH");
			calculerDeplacement("SOUTH", x, y, hitboxPoints, entity);
		}
		if(c[2]){ // west
			x = -speed;
			y = 0;
			hitboxPoints = this.getHitboxForEntityAndDirection(entity, "WEST");
			calculerDeplacement("WEST", x, y, hitboxPoints, entity);
		}
		if(c[3]){ // east
			x = speed;
			y = 0;
			hitboxPoints = this.getHitboxForEntityAndDirection(entity, "EAST");
			calculerDeplacement("EAST", x, y, hitboxPoints, entity);
		}
			
	}*/
		
	/*private void calculerDeplacement(String direction, int x, int y, List<Point> points, Entity entity) {
		
		Position mc = mm.get(entity);
		TypeComponent tc = tm.get(entity);
		
		Point p1 = new Point(points.get(0).x+x,points.get(0).y+y);
		Point p2 = new Point(points.get(1).x+x,points.get(1).y+y);
		int typeP1 = this.gameMap.getTypeForPosition(p1);
		int typeP2 = this.gameMap.getTypeForPosition(p2);
		boolean emptyP1 = gameMap.isEmpty(typeP1);
		boolean emptyP2 = gameMap.isEmpty(typeP2);
		
		if(emptyP1 && emptyP2){
			mc.setX(mc.getX()+x);
			mc.setY(mc.getY()+y);
		}else{
			if(direction.equals("NORTH")){
				if(emptyP1){
					mc.setY((p1.y/32+1)*32+1+mc.getSize()/2);				
				}else{
					mc.setY((p2.y/32+1)*32+1+mc.getSize()/2);		
				}
			}else if(direction.equals("SOUTH")){
				if(emptyP1){
					mc.setY(p1.y/32*32-mc.getSize()/2-1);				
				}else{
					mc.setY(p2.y/32*32-mc.getSize()/2-1);	
				}
			}else if(direction.equals("WEST")){
				if(emptyP1){
					mc.setX((p1.x/32+1)*32+1+mc.getSize()/2);
				}else{
					mc.setX((p2.x/32+1)*32+1+mc.getSize()/2);
				}
			}else if(direction.equals("EAST")){
				if(emptyP1){
					mc.setX(p1.x/32*32-mc.getSize()/2-1);
				}else{
					mc.setX(p2.x/32*32-mc.getSize()/2-1);
				}
			}
		}
		
		//Si l'entité est un poulet et qu'il est sur un arbre, on le masque
		if(tc.getType().equalsIgnoreCase("Poulet")) {
			VisibilityComponent vc = vm.get(entity);
			vc.setVisible(!gameMap.isTree(typeP1));
		}
	}
	
	private List<Point> getHitboxForEntityAndDirection(Entity entity, String direction) {
		Position mc = mm.get(entity);
		List<Point> points = new ArrayList<Point>();
		int size = mc.getSize();
		int x1 = mc.getX() - (size/2);
		int y1 = mc.getY() - (size/2);
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
	}*/

}
