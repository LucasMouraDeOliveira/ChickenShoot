package fr.chickenshoot.game.gameloop;

/**
 * GameLoopOperation permet de découper les opérations de la boucle de jeu.
 * Chaque opération est définie par un contenu à exécuter et un délai d'exécution (2 opérations de la boucle peuvent être appelées avec des fréquences différentes).
 *  
 * @author lucasmouradeoliveira
 *
 */
public abstract class GameLoopOperation {
	
	protected final long delay;
	
	protected long currentDelay;
	
	public GameLoopOperation(long delay) {
		this.delay = delay;
		this.currentDelay = delay;
	}
	
	/**
	 * Lance l'exécution de l'opération, en lui passant en paramètre le temps passé depuis le dernier appel. 
	 * Si ce temps est inférieur au délai d'attente de l'opération, son temps d'attente est décrémenté.
	 * Si ce temps est supérieur ou égal au délai d'attente de l'opération, celle-ci est déclenchée, et son délai d'attente est réinitialisé.
	 * 
	 * @param d le temps passé depuis le dernier trigger, en millisecondes.
	 */
	public void trigger(long d){
		currentDelay-=d;
		if(currentDelay <= 0){
			currentDelay = delay;
			update();
		}
	}
	
	public long getDelay() {
		return delay;
	}
	
	protected abstract void update();

}
