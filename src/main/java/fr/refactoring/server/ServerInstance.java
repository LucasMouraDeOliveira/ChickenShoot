package fr.refactoring.server;

import java.util.List;

import org.eclipse.jetty.websocket.api.Session;

import fr.refactoring.game.GameInstance;

public class ServerInstance {
	
	/**
	 * Les états possibles pour le serveur sont : en attente de joueur et démarré
	 */
	public final static int WAITING_PLAYERS = 1;
	
	public final static int STARTED = 2;
	
	protected int serverState;
	
	protected List<Session> clients;
	
	protected final int maxPlayers;
	
	protected GameInstance game;
	
	public ServerInstance(int maxPlayers) {
		this.maxPlayers = maxPlayers;
		this.serverState = WAITING_PLAYERS;
	}
	
	public int getMaxPlayers() {
		return this.maxPlayers;
	}
	
	public boolean canAddPlayer() {
		return this.clients.size() < this.maxPlayers;
	}
	
	public void addPlayer(Session session) {
		this.clients.add(session);
	}
	
	public void startGame() {
		this.game = new GameInstance();
		this.game.start();
		this.serverState = STARTED;
	}
	
	public void broadcastMessage(String type, Object content) {
		
	}

}