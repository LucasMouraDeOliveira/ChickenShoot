package fr.remygenius.thread;

import fr.remygenius.arme.Arme;

public class ThreadRecharge extends Thread {
	private double delai;
	private Arme arme;
	
	public ThreadRecharge(double delai, Arme arme){
		this.delai = delai;
		this.arme = arme;
	}
	
	@Override
	public void run(){
		try {
			Thread.sleep((int)(delai*1000));
			arme.setRechargeTermine(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
