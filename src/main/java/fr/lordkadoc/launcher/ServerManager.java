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
	
	public void ajouterInstance(Session session){
		ServerInstance instance = new ServerInstance(2);
		this.instances.add(instance);
		instance.ajouterJoueur(session);	
		System.out.println(this.instances);
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