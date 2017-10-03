package fr.refactoring.game.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.refactoring.game.component.AngleComponent;
import fr.refactoring.game.component.KeyboardComponent;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.SpeedComponent;
import fr.refactoring.game.component.VelocityComponent;
import fr.refactoring.game.component.WeaponComponent;
import fr.refactoring.game.component.type.ChickenComponent;
import fr.refactoring.game.component.type.HunterComponent;

public class PlayerInputSystem extends EntitySystem {
	
	protected ImmutableArray<Entity> entities;
	
	protected ImmutableArray<Entity> chickens;
	
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(KeyboardComponent.class).one(ChickenComponent.class, HunterComponent.class).get());
		chickens = engine.getEntitiesFor(Family.all(ChickenComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		KeyboardComponent kc;
		AngleComponent ac;
		SpeedComponent sc;
		VelocityComponent vc;
		PositionComponent pc;
		WeaponComponent wc;
		for(Entity entity : this.entities) {
			kc = Mapper.keyboardMapper.get(entity);
			ac = Mapper.angleMapper.get(entity);
			vc = Mapper.velocityMapper.get(entity);
			sc = Mapper.speedMapper.get(entity);
			pc = Mapper.positionMapper.get(entity);
			wc = Mapper.weaponMapper.get(entity);
			//Angle
			ac.setAngle(-Math.atan2(kc.getMouseX()-pc.getX(), kc.getMouseY()-pc.getY())-Math.PI);
			//Movement
			double dx = 0, dy = 0;
			if(kc.isMoving(0)) {
				dy-=sc.getSpeed();
			}
			if(kc.isMoving(1)) {
				dy+=sc.getSpeed();
			}
			if(kc.isMoving(2)) {
				dx-=sc.getSpeed();
			}
			if(kc.isMoving(3)) {
				dx+=sc.getSpeed();
			}
			vc.setDx(dx);
			vc.setDy(dy);
			//Shooting
			wc.setShooting(kc.isShooting());
		}
		
		for(Entity entity : chickens) {
			kc = Mapper.keyboardMapper.get(entity);
			if(kc.isExploding()) {
				//TODO gérer l'explosion des bombes
			}
		}
	}

}
