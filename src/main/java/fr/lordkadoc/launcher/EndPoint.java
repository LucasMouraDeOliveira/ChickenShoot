package fr.lordkadoc.launcher;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;


@WebSocket()
public class EndPoint {
	
	@OnWebSocketConnect
	public void handleConnect(Session user){
		System.out.println("Utilisateur connecté");	
	}
	
	@OnWebSocketMessage
	public void handleMessage(Session user, String message){
		
		JsonReader jsonReader = Json.createReader(new StringReader(message));
		JsonObject object = jsonReader.readObject();
		
		String type = object.getString("type");
		String gameID = object.getString("gameID");
	
		if(type.equals("demarrerPartie")){
			ServerManager.getPlayerInstance(gameID).demarrerPartie();
		}else if(message.equals("create")){
			ServerManager.ajouterInstance(gameID).ajouterJoueur(user);
		}else if(message.equals("demarrerPartie")){
			ServerManager.getPlayerInstance(gameID).recevoirMessage(user, message);
		}
	}
	
	@OnWebSocketClose
	public void handleClose(Session user, int code, String reason){
		System.out.println("Utilisateur déconnecté");
	}
	
	@OnWebSocketError
	public void handleError(Throwable t){
		System.out.println("Utilisateur erreur");
	}

}
