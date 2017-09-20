package fr.refactoring.game;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import com.badlogic.ashley.core.Entity;

import fr.refactoring.game.component.HealthComponent;

public class GameFactory {
	
	public static Entity createRandomPlayerEntity() {
		Entity entity = new Entity();
		entity.add(new HealthComponent(100));
		return entity;
	}

	public static JsonObjectBuilder getMapJson(GameInstance gameInstance) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		gameInstance.getGameMap().getJSon(builder); 
		return builder;
	}

}
