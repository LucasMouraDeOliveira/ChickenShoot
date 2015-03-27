package fr.remygenius.thread;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.entities.Joueur;
import fr.lordkadoc.launcher.ServerInstance;

public class ThreadRegen extends Thread{
	private ServerInstance instance;
	private int tempsRegen = 1000;
	private List<Joueur> p;
	
	public ThreadRegen(ServerInstance instance, int tempsRegen){
		this.tempsRegen = tempsRegen;
		this.p = instance.getCarte().getPlayers();
	}
	
	public void verifierJoueurRegen(){
		for(Joueur p : p){
			/*condition à compléter*/
			System.out.println("x :" + p.getX() +" y :" + p.getY());
			if(this.instance.getCarte().estRegen(this.instance.getCarte().cellule(new Point(p.getX(),p.getY())))){
				p.getArme().setMunitions(p.getArme().getMunitions()+1);
			}
		}
		
	}
	
	@Override
	public void run() {
		while(true){
			try {
				sleep(tempsRegen);
				verifierJoueurRegen();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}