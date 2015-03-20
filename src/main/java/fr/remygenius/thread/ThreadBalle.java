package fr.remygenius.thread;

import java.util.ArrayList;
import java.util.List;

import fr.remygenius.armechasseur.Balle;

public class ThreadBalle extends Thread{
	
	private List<Balle> balles;
	private int delai;
	
	public ThreadBalle(List<Balle> balles){
		this.balles = balles;
		this.delai = 20;
	}
	
	@Override
	public void run(){
		
		while(true){
					
			try {
				Thread.sleep(delai);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			List<Balle> tmp = new ArrayList<Balle>();
			List<Balle> tmp2 = new ArrayList<Balle>();
			tmp.addAll(balles);
			tmp2.addAll(balles);
			for(Balle b : tmp){
				b.deplacer();
				if(b.verifierCollision() || b.verifierToucherPoulet()){
					tmp2.remove(b);
				}
			}
			balles.clear();
			balles.addAll(tmp2);
			
		}
		
	}
	
}
