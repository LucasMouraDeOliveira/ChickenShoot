package fr.remygenius.thread;

import fr.lordkadoc.launcher.ServerInstance;

public class ThreadGame extends Thread {
	
	private ServerInstance instance;
	private int delai;
	
	public ThreadGame(ServerInstance instance, int delai){
		this.instance = instance;
		this.delai = delai;
	}

	@Override
	public void run(){
		while(true){
			try {
				Thread.sleep(delai);
				instance.diffuserMessage("Carte", instance.getCarte().getJSon());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
