package fr.chickenshoot.game.gameloop;

import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.launcher.ServerInstance;
import fr.lordkadoc.launcher.ServerManager;
import fr.lordkadoc.map.Carte;

public class GameLoop extends Thread{
	
	protected ServerInstance instance;
	
	protected long delay;
	
	protected List<GameLoopOperation> operations;
	
	public GameLoop(ServerInstance instance, long delay) {
		this.instance = instance;
		this.delay = delay;
		this.initOperations();
	}
	
	private void initOperations() {
		this.operations = new ArrayList<GameLoopOperation>();
		this.operations.add(new UpdatePlayer(instance, 20));
		this.operations.add(new UpdateWeapon(instance, 20));
		this.operations.add(new UpdateBullet(instance, 20));
		this.operations.add(new UpdateBombes(instance, 20));
		this.operations.add(new UpdateExplosions(instance, 10));
		this.operations.add(new UpdateTimer(instance, 1000));
		this.operations.add(new UpdateRegen(instance, 2000));
		//this.operations.add(new UpdateExperience(instance, 20));
		this.operations.add(new BroadcastMap(instance, 20));
	}

	@Override
	public void run(){
		
		Carte carte = this.instance.getCarte();
		
		//On diffuse une première fois la carte à tous les joueurs
		instance.broadCastMessage("load", carte.getMapJSon());
		
		long start;
		long end;
		
		//Boucle de jeu (tant que la partie n'est pas terminée)
		while(instance.getState() != ServerInstance.STOP){
			start = System.currentTimeMillis();
			update(delay);
			end = System.currentTimeMillis();
			if(delay-(end-start)>0){				
				try {
					Thread.sleep(Math.max(0, delay-(end-start)));
				} catch (InterruptedException e) {}
			}
		}
		
		//TODO refactoring du système d'expérience
		
		/*
		List<Player> players = carte.getSurvivor();
		for(Player player : players){
			UpdateBDD.gainXP(player.getName(), 50/players.size());
		}*/
		
		//On supprime la partie du serverManager
		ServerManager.getManager().removeServer(instance);
		
	}
	
	/**
	 * Calcule un tick de jeu de la partie.
	 * 
	 * @param delay le temps écoulé depuis la dernière MàJ du serveur
	 */
	public void update(long delay){
		for(GameLoopOperation op : operations){
			op.trigger(delay);
		}
	}

}