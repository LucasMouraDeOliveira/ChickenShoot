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
		
		//Si pas de déplacement, on quitte
		if(vc.isVoid()) {
			return;
		}
		
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

}
