package fr.ashley.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class MovementSystem extends EntitySystem {
	
	private ImmutableArray<Entity> entities;

	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class).get());
		
	}
	
	@Override
	public void update(float deltaTime) {
		for(Entity entity : entities) {
			PositionComponent pc = pm.get(entity);
			pc.setX(pc.getX()+10);
			pc.setY(pc.getY()+10);
		}
	}

}
