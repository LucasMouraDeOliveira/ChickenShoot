package fr.refactoring.game.system;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.refactoring.game.GameMap;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.SizeComponent;
import fr.refactoring.game.component.VelocityComponent;
import fr.refactoring.game.component.type.BulletComponent;
import fr.refactoring.game.physics.CollisionEngine;
import fr.refactoring.game.physics.CollisionParameters;

public class ProjectileMovementSystem extends EntitySystem {
	
	protected GameMap gameMap;
	
	protected Engine engine;
	
	protected ImmutableArray<Entity> bullets;

	public ProjectileMovementSystem(GameMap gameMap, Engine engine) {
		this.gameMap = gameMap;
		this.engine = engine;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		bullets = engine.getEntitiesFor(Family.all(BulletComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		VelocityComponent vc;
		PositionComponent pc;
		SizeComponent sc;
		CollisionEngine collisionEngine = CollisionEngine.getInstance();
		List<Entity> entitiesToDelete = new ArrayList<Entity>();
		for(Entity bullet : bullets) {
			vc = Mapper.velocityMapper.get(bullet);
			pc = Mapper.positionMapper.get(bullet);
			sc = Mapper.sizeMapper.get(bullet);
			pc.setX((int)(pc.getX()+vc.getDx()));
			pc.setY((int)(pc.getY()+vc.getDy()));
			CollisionParameters collisionParams = collisionEngine.getCollisionWithGameMap(gameMap, bullet, pc, sc, vc);
			if(collisionParams != null) {
				entitiesToDelete.add(bullet);
			}
		}
		for(Entity bullet : entitiesToDelete) {
			this.engine.removeEntity(bullet);
		}
	}
	
}
