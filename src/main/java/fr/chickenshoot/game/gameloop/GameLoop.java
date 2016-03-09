package fr.chickenshoot.game.gameloop;

import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.launcher.ServerInstance;
import fr.lordkadoc.launcher.ServerManager;
import fr.lordkadoc.map.Carte;

public class GameLoop extends Thread{
	
	protected ServerInstance instance;
	
	protected Carte carte;
	
	protected long delay;
	
	protected List<GameLoopOperation> operations;
	
	public GameLoop(ServerInstance instance, long delay) {
		this.instance = instance;
		this.carte = instance.getCarte();
		this.delay = delay;
		this.initOperations();
	}
	
	private void initOperations() {
		this.operations = new ArrayList<GameLoopOperation>();
		this.operations.add(new UpdatePlayer(carte, 20));
		this.operations.add(new UpdateWeapon(carte, 20));
		this.operations.add(new UpdateBullet(carte, 20));
		this.operations.add(new UpdateBombes(carte, 20));
		this.operations.add(new UpdateExplosions(carte, 10));
		this.operations.add(new UpdateRegen(carte, 2000));
		this.operations.add(new BroadcastMap(instance, 20));
	}

	@Override
	public void run(){
		
		long start;
		long end;
		
		instance.broadCastMessage("load", carte.getMapJSon());
		
		while(!carte.isGameFinished()){
			start = System.currentTimeMillis();
			update(delay);
			end = System.currentTimeMillis();
			if(delay-(end-start)>0){				
				try {
					Thread.sleep(Math.max(0, delay-(end-start)));
				} catch (InterruptedException e) {}
			}
		}
		
		instance.broadCastMessage("ended");
		ServerManager.getManager().removeServer(instance);
		
	}
	
	public void update(long delay){
		for(GameLoopOperation op : operations){
			op.trigger(delay);
		}
	}

}