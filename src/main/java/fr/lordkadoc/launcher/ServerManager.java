package fr.lordkadoc.launcher;

import java.util.Map;


public class ServerManager {
	
	private static Map<String, ServerInstance> parties;
	
	public static Map<String,ServerInstance> getParties() {
		return parties;
	}
	
	public static ServerInstance ajouterInstance(String key){
		ServerInstance instance = new ServerInstance(1);
		parties.put(key, instance);	
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