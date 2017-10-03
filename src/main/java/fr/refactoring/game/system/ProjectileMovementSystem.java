package fr.refactoring.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.refactoring.game.GameMap;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.VelocityComponent;

public class ProjectileMovementSystem extends EntitySystem {
	
	private ImmutableArray<Entity> projectiles;

	private ComponentMapper<PositionComponent> mm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	
	private GameMap gameMap;
	
	public ProjectileMovementSystem(GameMap gameMap) {
		this.gameMap = gameMap;
	}

	@Override
	public void addedToEngine(Engine engine) {
		projectiles = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		VelocityComponent vc;
		PositionComponent mc;
		for(Entity projectile : projectiles) {
			vc = vm.get(projectile);
			mc = mm.get(projectile);
			mc.setX((int)(mc.getX()+vc.getDx()));
			mc.setY((int)(mc.getY()+vc.getDy()));
		}
	}
	
}
