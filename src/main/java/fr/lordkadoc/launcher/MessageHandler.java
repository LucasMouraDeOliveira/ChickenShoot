package fr.lordkadoc.launcher;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import org.eclipse.jetty.websocket.api.Session;

import fr.refactoring.server.ServerInstance;
import fr.refactoring.server.ServerManager;

public class MessageHandler {
	
	public void handleServerCreation(Session user, String serverName, int maxPlayers, String login) {
		ServerInstance server = ServerManager.getInstance().addServer(serverName, maxPlayers);
		if(server == null){
			sendMessage(user, "error", "Un serveur de même nom existe déjà.");
		}else{
			server.addPlayer(user, login);
		}
	}
	
	public void handleServerJoin(Session user, String serverName, String login) {
		ServerInstance instance = ServerManager.getInstance().getServerWithName(serverName);
		if(instance == null){
			sendMessage(user, "error", "Ce serveur n'existe pas.");
		}else if(!instance.canAddPlayer()){
			sendMessage(user, "error", "Ce serveur est plein");
		}else{
			instance.addPlayer(user, login);  	
		}
	}
	
	public void handleServerMessage(Session user, String serverName, String message) {
		ServerInstance server = ServerManager.getInstance().getServerWithName(serverName);
		if(server == null){
			sendMessage(user, "error", "Ce serveur n'existe pas.");
		}else{
			server.receiveMessage(user, message);
		}
	}
	
	public void sendMessage(Session user, String type, String message) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject json = factory.createObjectBuilder().add("type", type).add("data", message).build();
		if(user.isOpen()){
			try {
				user.getRemote().sendString(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
