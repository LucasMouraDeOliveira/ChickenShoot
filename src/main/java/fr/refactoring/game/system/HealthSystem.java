package fr.refactoring.game.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.refactoring.game.component.HealthComponent;

public class HealthSystem extends EntitySystem {
	
	private ImmutableArray<Entity> entities;
	
	@Override
	public void addedToEngine(Engine engine) {
		this.entities = engine.getEntitiesFor(Family.all(HealthComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		HealthComponent entityHealth;
		for(Entity entity : entities) {
			entityHealth = Mapper.healthMapper.get(entity);
			//TODO faire perdre ou regagner de la vie aux entités
		}
	}

}
