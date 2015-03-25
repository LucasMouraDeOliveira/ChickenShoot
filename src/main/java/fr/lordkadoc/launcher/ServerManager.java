package fr.lordkadoc.launcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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
	
	public static Set<InstanceInfo> getInstanceInfos(Map<String,ServerInstance> map){
		Set<InstanceInfo> set = new HashSet<InstanceInfo>();
		InstanceInfo info;
		ServerInstance instance;
		for(String s : map.keySet()){
			instance = parties.get(s);
			info = new InstanceInfo(s, instance.getCurrentUsers(), instance.getMaxUsers());
			set.add(info);
		}
		return set;
		
	}

	public static ServerInstance getPlayerInstance(String key) {
		return parties.get(key);
	}
	
}