package fr.lordkadoc.launcher;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import org.eclipse.jetty.websocket.api.Session;

import fr.chickenshoot.game.entities.Player;

public class MessageHandler {
	
	public void handleServerCreation(Session user, String serverName, int maxPlayers, String login) {
		ServerInstance instance = ServerManager.getManager().addInstance(serverName, maxPlayers);
		if(instance == null){
			sendMessage(user, "error", "Un serveur de même nom existe déjà.");
		}else{
			instance.addPlayer(user, login);
		}
	}
	
	public void handleServerJoin(Session user, String serverName, String login) {
		ServerInstance instance = ServerManager.getManager().getServer(serverName);
		if(instance == null){
			sendMessage(user, "error", "Ce serveur n'existe pas.");
		}else if(instance.serverIsFull()){
			sendMessage(user, "error", "Ce serveur est plein");
		}else{
			instance.addPlayer(user,login);  	
		}
	}
	
	public void handleServerMessage(Session user, String serverName, String message) {
		ServerInstance instance = ServerManager.getManager().getServer(serverName);
		if(instance == null){
			sendMessage(user, "error", "Ce serveur n'existe pas.");
		}else{
			Player player = instance.getPlayer(user);
			if(player == null){
				sendMessage(user, "error", "Vous n'êtes pas connecté à ce serveur");
			}else{
				instance.receiveMessage(player, message);
			}
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
