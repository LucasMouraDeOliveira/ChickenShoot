package fr.refactoring.game;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import fr.refactoring.game.component.KeyboardComponent;
import fr.refactoring.game.loop.GameLoop;
import fr.refactoring.game.system.HealthSystem;
import fr.refactoring.game.system.PlayerInputSystem;
import fr.refactoring.game.system.PlayerMovementSystem;
import fr.refactoring.game.system.PlayerWeaponSystem;
import fr.refactoring.game.system.ProjectileMovementSystem;
import fr.refactoring.game.system.UpdateSystem;
import fr.refactoring.game.system.UpdateWeaponSystem;
import fr.refactoring.server.ServerInstance;

public class GameInstance {
	
	protected ServerInstance serverInstance;
	
	protected GameMap gameMap;
	
	protected GameLoop gameLoop;
	
	protected List<Entity> entities;
	
	protected Engine engine;
	
	public GameInstance(ServerInstance serverInstance, List<Entity> players) {
		this.serverInstance = serverInstance;
		this.entities = new ArrayList<Entity>();
		this.entities.addAll(players);
		this.initMap();
		this.initEngine();
	}
	
	private void initMap() {
		this.gameMap = new GameMap(this);
	}
	
	private void initEngine() {
		this.engine = new Engine();
		this.engine.addSystem(new HealthSystem());
		this.engine.addSystem(new PlayerInputSystem());
		this.engine.addSystem(new PlayerMovementSystem(this.gameMap));
		this.engine.addSystem(new PlayerWeaponSystem(this.engine));
		this.engine.addSystem(new ProjectileMovementSystem(this.gameMap, this.engine));
		this.engine.addSystem(new UpdateWeaponSystem(this.engine, this));
		//On ajoute l'update en dernier pour le traiter en fin de boucle
		this.engine.addSystem(new UpdateSystem(this));
		for(Entity entity : entities) {
			this.engine.addEntity(entity);
		}
	}

	public void start() {
		this.gameLoop = new GameLoop(this, 50);
		this.gameLoop.start();
	}
	
	public void update(long delay) {
		this.engine.update(delay);
	}
	
	public void broadcast(String type, JsonObjectBuilder data) {
		this.serverInstance.broadcastMessage(type, data);
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public ServerInstance getServerInstance() {
		return serverInstance;
	}
	
	public int getState() {
		return this.serverInstance.getState();
	}

	public GameMap getGameMap() {
		return this.gameMap;
	}

	public List<Entity> getEntities() {
		return entities;
	}
	
	public void handlePlayerAction(Entity player, JsonObject action) {
		JsonObject coords = action.getJsonObject("movement");
		JsonObject souris = action.getJsonObject("souris");
		ComponentMapper<KeyboardComponent> keyboardMapper = ComponentMapper.getFor(KeyboardComponent.class);
		KeyboardComponent kc = keyboardMapper.get(player);
		kc.setMouseX(souris.getInt("x"));
		kc.setMouseY(souris.getInt("y"));
		kc.setShooting(action.getBoolean("tir"));
		kc.setExploding(action.getBoolean("explode"));
		kc.setMoving(coords.getBoolean("north"), 0);
		kc.setMoving(coords.getBoolean("south"), 1);
		kc.setMoving(coords.getBoolean("west"), 2);
		kc.setMoving(coords.getBoolean("east"), 3);
	}

}