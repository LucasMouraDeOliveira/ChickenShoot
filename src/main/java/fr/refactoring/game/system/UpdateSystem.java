package fr.refactoring.game.system;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.refactoring.game.GameInstance;
import fr.refactoring.game.component.AngleComponent;
import fr.refactoring.game.component.HealthComponent;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.VisibilityComponent;
import fr.refactoring.game.component.WeaponComponent;
import fr.refactoring.game.component.type.ChickenComponent;
import fr.refactoring.game.component.type.HunterComponent;

public class UpdateSystem extends EntitySystem {
	
	protected ImmutableArray<Entity> chickens;
	protected ImmutableArray<Entity> hunters;
	protected ImmutableArray<Entity> projectiles;
	
	protected GameInstance game;
	
	public UpdateSystem(GameInstance game) {
		this.game = game;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		this.chickens = engine.getEntitiesFor(Family.all(ChickenComponent.class).get());
		this.hunters = engine.getEntitiesFor(Family.all(HunterComponent.class).get());
		this.projectiles = engine.getEntitiesFor(Family.exclude(ChickenComponent.class, HunterComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		
		//JSON Builders
		JsonObjectBuilder builder = Json.createObjectBuilder();
		JsonArrayBuilder playerListBuilder = Json.createArrayBuilder();
		JsonArrayBuilder projectileListBuilder = Json.createArrayBuilder();
		JsonObjectBuilder playerBuilder;
		JsonObjectBuilder projectileBuilder;
		
		//Poulets
		for(Entity entity : this.chickens) {
			playerBuilder = Json.createObjectBuilder();
			this.addDefaultPlayerProperties(entity, playerBuilder);
			this.addChickenProperties(entity, playerBuilder);
			playerListBuilder.add(playerBuilder);
		}
		
		//Chasseurs
		for(Entity entity : this.hunters) {
			playerBuilder = Json.createObjectBuilder();
			this.addDefaultPlayerProperties(entity, playerBuilder);
			this.addHunterProperties(entity, playerBuilder);
			playerListBuilder.add(playerBuilder);
		}
		
		builder.add("players", playerListBuilder);
		
		//Projectiles
		for(Entity projectile : this.projectiles) {
			PositionComponent pc = Mapper.positionMapper.get(projectile);
			AngleComponent ac = Mapper.angleMapper.get(projectile);
			projectileBuilder = Json.createObjectBuilder();
			projectileBuilder.add("x", pc.getX());
			projectileBuilder.add("y", pc.getY());
			projectileBuilder.add("angle", ac.getAngle());
			projectileListBuilder.add(projectileBuilder);
		}
		builder.add("projectiles", projectileListBuilder);
		
		this.game.broadcast("update", builder);
	}
	
	public void addDefaultPlayerProperties(Entity entity, JsonObjectBuilder playerBuilder) {
		AngleComponent ac = Mapper.angleMapper.get(entity);
		HealthComponent hc = Mapper.healthMapper.get(entity);
		PositionComponent mc = Mapper.positionMapper.get(entity);
		WeaponComponent wc = Mapper.weaponMapper.get(entity);
		playerBuilder.add("name", "Kadoc");
		//Position
		playerBuilder.add("x", mc.getX());
		playerBuilder.add("y", mc.getY());
		playerBuilder.add("angle", ac.getAngle());
		//Health
		playerBuilder.add("maxHealth", hc.getMaxHealth());
		playerBuilder.add("health", hc.getHealth());
		//Armement
		playerBuilder.add("ammos", wc.getAmmo());
		playerBuilder.add("weapon", wc.getWeaponName());
	}
	
	public void addChickenProperties(Entity entity, JsonObjectBuilder playerBuilder) {
		VisibilityComponent vc = Mapper.visibilityMapper.get(entity);
		//Type
		playerBuilder.add("type", "Poulet");
		//Visibility
		playerBuilder.add("visible", vc.isVisible());
	}
	
	public void addHunterProperties(Entity entity, JsonObjectBuilder playerBuilder) {
		playerBuilder.add("type", "Chasseur");
	}

}
