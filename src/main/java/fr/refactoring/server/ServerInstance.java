package fr.refactoring.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.eclipse.jetty.websocket.api.Session;

import com.badlogic.ashley.core.Entity;

import fr.refactoring.game.GameFactory;
import fr.refactoring.game.GameInstance;

public class ServerInstance {

	/**
	 * Les états possibles pour le serveur sont : en attente de joueur et
	 * démarré
	 */
	public final static int WAITING_PLAYERS = 1;

	public final static int STARTED = 2;

	protected int serverState;

	protected Map<Session, Entity> clients;

	protected final int maxPlayers;

	protected GameInstance game;

	public ServerInstance(int maxPlayers) {
		this.clients = new HashMap<Session, Entity>();
		this.maxPlayers = maxPlayers;
		this.serverState = WAITING_PLAYERS;
	}

	public int getMaxPlayers() {
		return this.maxPlayers;
	}

	public boolean canAddPlayer() {
		return this.clients.size() < this.maxPlayers;
	}

	public void addPlayer(Session session, String login) {
		Entity playerEntity = GameFactory.createRandomPlayerEntity();
		this.clients.put(session, playerEntity);
		JsonObjectBuilder playerMessage = Json.createObjectBuilder()
				.add("type", "Chicken")
				.add("login", login);
		this.broadcastMessage("connect", playerMessage);
	}

	public void removePlayer(Session session) {
		this.clients.remove(session);
	}

	public void startGame() {
		List<Entity> players = new ArrayList<Entity>(clients.values());
		this.game = new GameInstance(this, players);
		this.serverState = STARTED;
		this.game.start();
		this.broadcastMessage("start");
	}

	public void broadcastMessage(String type, JsonObjectBuilder message) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject json = factory.createObjectBuilder().add("type", type).add("data", message).build();
		for (Session s : clients.keySet()) {
			if (s.isOpen()) {
				try {
					s.getRemote().sendString(json.toString());
				} catch (IOException e) {
				}
			}
		}
	}

	public void broadcastMessage(String type) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject json = factory.createObjectBuilder().add("type", type).build();
		for (Session s : clients.keySet()) {
			if (s.isOpen()) {
				try {
					s.getRemote().sendString(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void receiveMessage(Session user, String message) {
		// TODO traiter les messages des clients
	}

	public int getState() {
		return this.serverState;
	}

}