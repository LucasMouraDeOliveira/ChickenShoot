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

import fr.refactoring.server.ServerManager;


@WebSocket()
public class EndPoint {
	
	protected MessageHandler handler;
	
	public EndPoint() {
		this.handler = new MessageHandler();
	}
	
	@OnWebSocketConnect
	public void handleConnect(Session user) {}
	
	@OnWebSocketMessage
	public void handleMessage(Session user, String message){
		
		JsonReader jsonReader = Json.createReader(new StringReader(message));
		JsonObject object = jsonReader.readObject();
		
		String type = object.getString("type");
		String gameID = object.getString("gameID");
		
		if(type.equals("start")){
			ServerManager.getInstance().getServerWithName(gameID).startGame();
		}else if(type.equals("create")){
			String login = object.getString("login");
			int maxPlayers = object.getInt("nbJoueurs");
			handler.handleServerCreation(user, gameID, maxPlayers, login);
		}else if(type.equals("join")){
			String login = object.getString("login");
			handler.handleServerJoin(user, gameID, login);
		}else{ //envoi du message à la partie
			handler.handleServerMessage(user, gameID, message);
		}
		
	}
	
	@OnWebSocketClose
	public void handleClose(Session user, int code, String reason){
//		ServerManager.getInstance().disconnect(user);
	}
	
	@OnWebSocketError
	public void handleError(Throwable t){}

}
