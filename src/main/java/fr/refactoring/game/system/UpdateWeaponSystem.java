package fr.refactoring.game.system;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.refactoring.game.GameFactory;
import fr.refactoring.game.GameInstance;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.WeaponComponent;

public class UpdateWeaponSystem extends EntitySystem {
	
	protected ImmutableArray<Entity> entities;

	protected Engine engine;
	
	protected GameInstance game;
	
	public UpdateWeaponSystem(Engine engine, GameInstance game) {
		this.engine = engine;
		this.game = game;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		this.entities = engine.getEntitiesFor(Family.all(PositionComponent.class, WeaponComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		WeaponComponent wc;
		List<Entity> entitiesToAdd = new ArrayList<Entity>();
		Entity projectile;
		for(Entity entity : entities) {
			/*wc = weaponMapper.get(entity);
			tc = typeMapper.get(entity);
			mc = movementMapper.get(entity);
			if(wc.isShooting()) {
				if(tc.getType().equalsIgnoreCase("Poulet")){
					projectile = GameFactory.createBomb();
				} else {
					projectile = GameFactory.createArrow(mc.getX(), mc.getY());
				}
				entitiesToAdd.add(projectile);
			}*/
		}
		for(Entity e : entitiesToAdd) {
			this.engine.addEntity(e);
		}
	}

}
