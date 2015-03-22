package fr.lordkadoc.launcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.websocket.api.Session;

public class ServerManager {
	
	
	private List<ServerInstance> instances;
	
	private Map<String, ServerInstance> parties;
	
	public ServerManager(){
		this.instances = new ArrayList<ServerInstance>();
		this.parties = new HashMap<String, ServerInstance>();
	}

	public List<ServerInstance> getInstances() {
		return instances;
	}

	public void setInstances(List<ServerInstance> instances) {
		this.instances = instances;
	}
	
	public ServerInstance ajouterInstance(){
		ServerInstance instance = new ServerInstance(1);
		this.instances.add(instance);	
		return instance;
	}

	public ServerInstance getFreeInstance() {
		for(ServerInstance instance : this.instances){
			if(!instance.clientsTousConnectes()){
				return instance;
			}
		}
		return null;
	}

	public ServerInstance getPlayerInstance(Session session) {
		return this.instances.get(0);
	}
	
}