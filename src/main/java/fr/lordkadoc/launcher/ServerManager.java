package fr.lordkadoc.launcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jetty.websocket.api.Session;

import fr.lordkadoc.entities.Joueur;


public class ServerManager {
	
	public static Map<String, ServerInstance> parties = new HashMap<String,ServerInstance>();
	
	public static Map<String,ServerInstance> getParties() {
		return parties;
	}
	
	public static ServerInstance ajouterInstance(String key, int nbJoueurs){
		ServerInstance instance = new ServerInstance(nbJoueurs);
		parties.put(key, instance);	
		return instance;
	}

	public static ServerInstance getFreeInstance() {
		ServerInstance instance;
		for(String s : parties.keySet()){
			instance  = parties.get(s);
			if(instance.getState() == ServerInstance.WAITING_PLAYERS){
				return instance;
			}
		}
		return null;
	}
	
	public static Map<String,ServerInstance> getSpecificInstances(int type){
		Map<String,ServerInstance> map = new HashMap<String, ServerInstance>();
		for(String s : parties.keySet()){
			if(parties.get(s).getState() == type){
				map.put(s,parties.get(s));
			}
		}
		return map;
	}
	
	public static Set<InstanceInfo> getInstanceInfos(Map<String,ServerInstance> map){
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
	
	public static Set<PlayerInfo> getPlayerInfos(Map<Session,Joueur> map){
		Set<PlayerInfo> set = new HashSet<PlayerInfo>();
		PlayerInfo info;
		Joueur joueur;
		for(Session s : map.keySet()){
			joueur = map.get(s);
			info = new PlayerInfo(joueur.getNom(),joueur.getType());
			set.add(info);
		}
		return set;
		
	}

	public static ServerInstance getPlayerInstance(String key) {
		return parties.get(key);
	}
	
}