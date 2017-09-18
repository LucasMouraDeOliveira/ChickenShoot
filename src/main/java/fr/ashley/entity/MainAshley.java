package fr.ashley.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import fr.refactoring.game.component.HealthComponent;
import fr.refactoring.game.system.HealthSystem;

public class MainAshley {
	
	public static void main(String[] args) {
		Engine engine = new Engine();
		Entity chicken = new Entity();
		engine.addEntity(chicken);
		engine.addSystem(new HealthSystem());
		chicken.add(new HealthComponent(100));
		engine.update(0);
		engine.update(0);
		engine.update(0);
	}

}
