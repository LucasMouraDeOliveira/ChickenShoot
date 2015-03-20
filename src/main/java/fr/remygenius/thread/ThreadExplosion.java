package fr.remygenius.thread;

import java.util.ArrayList;
import java.util.List;

import fr.remygenius.armepoulet.Explosion;

public class ThreadExplosion extends Thread {
	private int delai;
	private List<Explosion> explosions;
	
	public ThreadExplosion(List<Explosion> explosions){
		this.delai = 10;
		this.explosions = explosions;
	}
	
	@Override
	public void run(){
		while(true){
			
			try {
				Thread.sleep(delai);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			List<Explosion> tmp = new ArrayList<Explosion>();
			List<Explosion> tmp2 = new ArrayList<Explosion>();
			tmp.addAll(explosions);
			tmp2.addAll(tmp);
			for(Explosion e : tmp){
				e.setTailleActu(e.getTailleActu() + 1);
				if(e.getTailleActu() == e.getTailleMax()){
					tmp2.remove(e);
				}
			}
			explosions.clear();
			explosions.addAll(tmp2);
		}
	}
}
