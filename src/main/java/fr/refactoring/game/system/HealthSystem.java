package fr.refactoring.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.refactoring.game.component.HealthComponent;

public class HealthSystem extends EntitySystem {
	
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);

	@Override
	public void addedToEngine(Engine engine) {
		this.entities = engine.getEntitiesFor(Family.all(HealthComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		HealthComponent entityHealth;
		for(Entity entity : entities) {
			entityHealth = healthMapper.get(entity);
			entityHealth.hit(20);
			entityHealth.display();
		}
	}

}
