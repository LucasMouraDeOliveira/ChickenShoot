package fr.refactoring.game;

import java.util.List;

import javax.json.JsonObjectBuilder;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import fr.refactoring.game.loop.GameLoop;
import fr.refactoring.game.system.HealthSystem;
import fr.refactoring.game.system.UpdateSystem;
import fr.refactoring.server.ServerInstance;

public class GameInstance {
	
	protected ServerInstance serverInstance;
	
	protected GameMap gameMap;
	
	protected GameLoop gameLoop;
	
	protected List<Entity> players;
	
	protected Engine engine;
	
	public GameInstance(ServerInstance serverInstance, List<Entity> players) {
		this.serverInstance = serverInstance;
		this.players = players;
		this.initMap();
		this.initEngine();
	}
	
	private void initMap() {
		this.gameMap = new GameMap(this);
	}
	
	private void initEngine() {
		this.engine = new Engine();
		this.engine.addSystem(new HealthSystem());
		this.engine.addSystem(new UpdateSystem(this));
		for(Entity entity : players) {
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

	public List<Entity> getPlayers() {
		return players;
	}

}