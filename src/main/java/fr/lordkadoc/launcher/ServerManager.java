package fr.lordkadoc.launcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServerManager {
	
	public static Map<String, ServerInstance> parties = new HashMap<String,ServerInstance>();
	
	public static Map<String,ServerInstance> getParties() {
		return parties;
	}
	
	public static ServerInstance ajouterInstance(String key){
		ServerInstance instance = new ServerInstance(6);
		parties.put(key, instance);	
		System.out.println("Ajout d'une partie : " + parties.size());
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
	
	public static List<String> getListOfID(Map<String,ServerInstance> map){
		List<String> list = new ArrayList<String>();
		for(String s : map.keySet()){
			list.add(s);
		}
		return list;
		
	}

	public static ServerInstance getPlayerInstance(String key) {
		return parties.get(key);
	}
	
}