package fr.ashley.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;

public class AshleyTesting {
	
	public static void main(String[] args) {
		Engine engine = new Engine();
		Entity chicken = new Entity();
		engine.addEntity(chicken);
		chicken.add(new PositionComponent(100, 100));
		chicken.add(new PositionComponent(200, 50));
		PositionComponent pc = chicken.getComponent(PositionComponent.class);
		System.out.println(pc.getX()+ "/" + pc.getY());
		Family chickens = Family.all(PositionComponent.class).get();
		System.out.println(engine.getEntitiesFor(chickens).size() + " chicken(s)");
	}

}
