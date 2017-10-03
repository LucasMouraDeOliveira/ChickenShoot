package fr.refactoring.game;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import com.badlogic.ashley.core.Entity;

import fr.refactoring.game.component.AngleComponent;
import fr.refactoring.game.component.HealthComponent;
import fr.refactoring.game.component.KeyboardComponent;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.SizeComponent;
import fr.refactoring.game.component.SpeedComponent;
import fr.refactoring.game.component.VelocityComponent;
import fr.refactoring.game.component.VisibilityComponent;
import fr.refactoring.game.component.WeaponComponent;
import fr.refactoring.game.component.type.ChickenComponent;
import fr.refactoring.game.component.type.HunterComponent;

public class GameFactory {
	
	public static Entity createChicken(double x, double y) {
		Entity entity = new Entity();
		entity.add(new ChickenComponent());
		entity.add(new AngleComponent(0));
		entity.add(new HealthComponent(100));
		entity.add(new KeyboardComponent());
		entity.add(new PositionComponent(x, y));
		entity.add(new SizeComponent(24, 24));
		entity.add(new SpeedComponent(12));
		entity.add(new VisibilityComponent());
		entity.add(new VelocityComponent(0, 0));
		entity.add(new WeaponComponent("Bomb", 10, 100, 1000));
		return entity;
	}
	
	public static Entity createHunter(double x, double y) {
		Entity entity = new Entity();
		entity.add(new HunterComponent());
		entity.add(new AngleComponent(0));
		entity.add(new HealthComponent(100));
		entity.add(new KeyboardComponent());
		entity.add(new PositionComponent(x, y));
		entity.add(new SizeComponent(30, 30));
		entity.add(new SpeedComponent(8));
		entity.add(new VelocityComponent(0, 0));
		entity.add(new WeaponComponent("Ghetto blasta", 10, 100, 500));
		return entity;
	}
	
	public static Entity createBomb() {
		Entity bomb = new Entity();
		return bomb;
	}
	
	public static Entity createArrow(double x, double y, int dx, int dy) {
		Entity arrow = new Entity();
		arrow.add(new PositionComponent(x, y));
		arrow.add(new SizeComponent(4, 4));
		arrow.add(new VelocityComponent(dx, dy));
		arrow.add(new AngleComponent(0));
		return arrow;
	}

	public static JsonObjectBuilder getMapJson(GameInstance gameInstance) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		gameInstance.getGameMap().getJSon(builder); 
		return builder;
	}

}
