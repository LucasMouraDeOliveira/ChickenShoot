package fr.lordkadoc.launcher;

import java.io.IOException;

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
		if(message.equals("join")){
			ServerInstance instance = ServerManager.getFreeInstance();
			if(instance != null){
				instance.ajouterJoueur(user,message);			
			}else{
				try {
					user.getRemote().sendString("Toutes les parties sont pleines. Créez une partie ou réessayez plus tard.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else if(message.equals("create")){
			ServerManager.ajouterInstance("").ajouterJoueur(user, message);
		}else{
			ServerManager.getPlayerInstance("").recevoirMessage(user, message);
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
