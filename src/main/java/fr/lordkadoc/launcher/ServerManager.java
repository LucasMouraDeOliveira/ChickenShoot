package fr.lordkadoc.launcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.json.Json;

import org.eclipse.jetty.websocket.api.Session;

import fr.chickenshoot.game.entities.Chicken;
import fr.chickenshoot.game.entities.Player;

public class ServerManager {
	
	private final static ServerManager instance = new ServerManager();
	
	protected Map<String, ServerInstance> parties;
	
	public static ServerManager getManager(){
		return instance;
	}
	
	private ServerManager() {
		parties = new HashMap<String,ServerInstance>();
	}
	
	public Map<String,ServerInstance> getParties() {
		return parties;
	}
	
	/**
	 * Crée et retourne un nouveau serveur de jeu avec les paramètres spécifiés.
	 * Le serveur est ajouté à la liste des serveurs de jeu du ServerManager.
	 * Si un serveur du même nom existe déjà, le serveur n'est pas ajouté et la fonction renvoie null.
	 * 
	 * @param key le nom du serveur, qui est également son identifiant unique.
	 * @param nbJoueurs le nombre de joueurs pouvant se connecter au serveur au maximum.
	 * 
	 * @return le nouveau serveur de jeu, ajouté au manager
	 */
	public ServerInstance addInstance(String key, int nbJoueurs){
		if(parties.get(key) != null){
			return null;
		}
		ServerInstance instance = new ServerInstance(nbJoueurs);
		parties.put(key, instance);	
		return instance;
	}

	public ServerInstance getFreeInstance() {
		ServerInstance instance;
		for(String s : parties.keySet()){
			instance  = parties.get(s);
			if(instance.getState() == ServerInstance.WAITING_PLAYERS){
				return instance;
			}
		}
		return null;
	}
	
	public Map<String,ServerInstance> getSpecificInstances(int type){
		Map<String,ServerInstance> map = new HashMap<String, ServerInstance>();
		for(String s : parties.keySet()){
			if(parties.get(s).getState() == type){
				map.put(s,parties.get(s));
			}
		}
		return map;
	}
	
	public Set<InstanceInfo> getInstanceInfos(Map<String,ServerInstance> map){
		Set<InstanceInfo> set = new HashSet<InstanceInfo>();
		InstanceInfo info;
		ServerInstance instance;
		for(String s : map.keySet()){
			instance = map.get(s);
			info = new InstanceInfo(s, instance.getCurrentUsers(), instance.getMaxUsers());
			set.add(info);
		}
		return set;	
	}
	
	public Set<PlayerInfo> getPlayerInfos(Map<Session,Player> map){
		Set<PlayerInfo> set = new HashSet<PlayerInfo>();
		PlayerInfo info;
		Player joueur;
		for(Session s : map.keySet()){
			joueur = map.get(s);
			info = new PlayerInfo(joueur.getName(),joueur instanceof Chicken ? "Poulet":"Chasseur");
			set.add(info);
		}
		return set;
		
	}

	/**
	 * Retourne un serveur de jeu de ChickenShoot.
	 * 
	 * @param key l'identifiant du serveur
	 * 
	 * @return le serveur de jeu correspondant à l'identifiant, null s'il n'existe pas.
	 */
	public ServerInstance getServer(String key) {
		return parties.get(key);
	}
	
	public void disconnect(Session user){
		ServerInstance server;
		for(String s : parties.keySet()){
			server = parties.get(s);
			if(server.containsPlayer(user)){
				server.broadCastMessage("disconnect", Json.createObjectBuilder().add("login", server.getPlayer(user).getName()));
				server.removePlayer(user);
				if(server.serverIsEmpty()){
					parties.remove(s);
				}
				return;
			}
		}
	}

	public void removeServer(ServerInstance instance) {
		for(String s : parties.keySet()){
			if(parties.get(s).equals(instance)){
				parties.remove(s);
			}
		}
	}
	
}