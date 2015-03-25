package fr.remygenius.thread;

import fr.lordkadoc.launcher.ServerInstance;

public class ThreadTimer extends Thread{
	private int time;
	private ServerInstance instance;
	
	public ThreadTimer(ServerInstance instance, int time){
		this.time = time;
		this.instance = instance;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(time >0){
			try {
				Thread.sleep(1000);
				time--;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		instance.setState(ServerInstance.ENDED);
	}
	
	public int getTime(){
		return this.time;
	}
}
