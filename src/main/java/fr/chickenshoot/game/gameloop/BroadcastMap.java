package fr.chickenshoot.game.gameloop;

import fr.lordkadoc.launcher.ServerInstance;

public class BroadcastMap extends GameLoopOperation {
	
	private ServerInstance instance;

	public BroadcastMap(ServerInstance instance,long delay) {
		super(delay);
		this.instance = instance;
	}

	@Override
	protected void update() {
		instance.diffuserMessage("update", instance.getCarte().getJSon());	
	}

}
