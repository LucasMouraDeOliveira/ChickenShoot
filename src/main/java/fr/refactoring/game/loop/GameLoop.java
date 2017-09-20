
package fr.refactoring.game.loop;

import fr.refactoring.game.GameFactory;
import fr.refactoring.game.GameInstance;
import fr.refactoring.server.ServerInstance;

public class GameLoop extends Thread {
	
	protected GameInstance game;
	
	protected long delay;
	
	public GameLoop(GameInstance game, long delay) {
		this.game = game;
		this.delay = delay;
	}

	@Override
	public void run() {
		long start, end, elapsedTime;
		this.game.broadcast("load", GameFactory.getMapJson(this.game));
		while(this.game.getState() == ServerInstance.STARTED) {
			start = System.currentTimeMillis();
			this.game.update(delay);
			end = System.currentTimeMillis();
			elapsedTime = delay-(end-start);
			if(elapsedTime > 0){				
				try {
					Thread.sleep(elapsedTime);
				} catch (InterruptedException e) {}
			}
		}
	}

}
