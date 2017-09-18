package fr.refactoring.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.lordkadoc.launcher.InstanceInfo;

public class ServerManager {
	
	private final static ServerManager instance = new ServerManager();
	
	protected Map<String, ServerInstance> servers;
	
	private ServerManager() {
		this.servers = new HashMap<String, ServerInstance>();
	}
	
	public static ServerManager getInstance() {
		return instance;
	}
	
	public Map<String, ServerInstance> getServers() {
		return servers;
	}
	
	public ServerInstance addServer(String serverName, int maxPlayers) {
		if(this.servers.containsKey(serverName)) {
			return null;
		}
		ServerInstance server = new ServerInstance(maxPlayers);
		this.servers.put(serverName, server);
		return server;
	}
	
	public ServerInstance getServerWithName(String serverName) {
		return this.servers.get(serverName);
	}
	
	public List<ServerInstance> getWaitingServers() {
		return null;
	}
	
	public List<InstanceInfo> getServersInformation(List<ServerInstance> servers) {
		return null;
	}

}
