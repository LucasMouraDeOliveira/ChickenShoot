package fr.refactoring.game.system;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;

import fr.refactoring.game.GameInstance;
import fr.refactoring.game.component.HealthComponent;

public class UpdateSystem extends EntitySystem {
	
	protected GameInstance game;
	
	protected ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class); 
	
	public UpdateSystem(GameInstance game) {
		this.game = game;
	}
	
	@Override
	public void update(float deltaTime) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		//Map
		//this.game.getGameMap().getJSon(builder);
		//Players
		JsonArrayBuilder playerListBuilder = Json.createArrayBuilder();
		JsonObjectBuilder playerBuilder;
		HealthComponent hc;
		for(Entity entity : this.game.getPlayers()) {
			playerBuilder = Json.createObjectBuilder();
			hc = healthMapper.get(entity);
			playerBuilder.add("name", "Kadoc");
			playerBuilder.add("x", 50);
			playerBuilder.add("y", 50);
			//playerBuilder.add("ammos", weapon.getAmmos());
			playerBuilder.add("maxHealth", hc.getMaxHealth());
			playerBuilder.add("health", hc.getHealth());
			playerBuilder.add("angle", 0);
			playerBuilder.add("type", "Poulet");
			//playerBuilder.add("weapon", weapon.getName());
			playerListBuilder.add(playerBuilder);
		}
		builder.add("players", playerListBuilder);
		this.game.broadcast("update", builder);
	}

}
