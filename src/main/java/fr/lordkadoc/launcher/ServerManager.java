package fr.lordkadoc.launcher;

import java.util.HashMap;
import java.util.Map;


public class ServerManager {
	
	public static Map<String, ServerInstance> parties = new HashMap<String,ServerInstance>();
	
	public static Map<String,ServerInstance> getParties() {
		return parties;
	}
	
	public static ServerInstance ajouterInstance(String key){
		ServerInstance instance = new ServerInstance(2);
		parties.put(key, instance);	
		System.out.println("Ajout d'une partie : " + parties.size());
		return instance;
	}

	public static ServerInstance getFreeInstance() {
		ServerInstance instance;
		for(String s : parties.keySet()){
			instance  = parties.get(s);
			if(!instance.clientsTousConnectes()){
				return instance;
			}
		}
		return null;
	}

	public static ServerInstance getPlayerInstance(String key) {
		return parties.get(key);
	}
	
}