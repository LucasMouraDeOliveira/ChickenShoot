package fr.refactoring.game.system;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.refactoring.game.GameFactory;
import fr.refactoring.game.component.AngleComponent;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.WeaponComponent;
import fr.refactoring.game.component.type.ChickenComponent;
import fr.refactoring.game.component.type.HunterComponent;

public class PlayerWeaponSystem extends EntitySystem {

	protected ImmutableArray<Entity> chickens;
	
	protected ImmutableArray<Entity> hunters;
	
	protected Engine engine;
	
	public PlayerWeaponSystem(Engine engine) {
		this.engine = engine;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		chickens = engine.getEntitiesFor(Family.all(ChickenComponent.class).get());
		hunters = engine.getEntitiesFor(Family.all(HunterComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		AngleComponent ac;
		PositionComponent pc;
		WeaponComponent wc;
		List<Entity> entitiesToAdd = new ArrayList<Entity>();
		for(Entity chicken: chickens) {
			wc = Mapper.weaponMapper.get(chicken);
			pc = Mapper.positionMapper.get(chicken);
			if(wc.isShooting()) {
				entitiesToAdd.add(GameFactory.createBomb(pc.getX(), pc.getY()));
			}
		}
		for(Entity hunter : hunters) {
			ac = Mapper.angleMapper.get(hunter);
			pc = Mapper.positionMapper.get(hunter);
			wc = Mapper.weaponMapper.get(hunter);
			if(wc.isShooting()) {
				entitiesToAdd.add(GameFactory.createArrow(pc.getX(), pc.getY(), 10*Math.cos(ac.getAngle()-Math.PI/2), 10*Math.sin(ac.getAngle()-Math.PI/2), ac.getAngle()));
			}
		}
		for(Entity entity : entitiesToAdd) {
			this.engine.addEntity(entity);
		}
	}

}
