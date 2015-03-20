package fr.lordkadoc.launcher;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.websocket.api.Session;

public class ServerManager {
	
	
	private List<ServerInstance> instances;
	
	public ServerManager(){
		this.instances = new ArrayList<ServerInstance>();
	}

	public List<ServerInstance> getInstances() {
		return instances;
	}

	public void setInstances(List<ServerInstance> instances) {
		this.instances = instances;
	}
	
	public ServerInstance ajouterInstance(){
		ServerInstance instance = new ServerInstance(2);
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