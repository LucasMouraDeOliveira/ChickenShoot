package fr.remygenius.thread;

import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.launcher.ServerInstance;
import fr.remygenius.armepoulet.Bombe;
import fr.remygenius.armepoulet.Explosion;

public class ThreadBombe extends Thread {
	private List<Bombe> bombes;
	private int delai;
	
	private ServerInstance instance;
	
	public ThreadBombe(ServerInstance instance, List<Bombe> bombes){
		this.instance = instance;
		this.bombes = bombes;
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
			
			List<Bombe> tmp = new ArrayList<Bombe>();
			List<Bombe> tmp2 = new ArrayList<Bombe>();
			tmp.addAll(bombes);
			tmp2.addAll(bombes);
			for(Bombe b : tmp){
				if(b.verifierTempsSurCarte()){
					b.verifierToucherChasseur();
					tmp2.remove(b);
					instance.getCarte().ajouterExplosions(new Explosion(b.getX(), b.getY(), b.getRayonExplosion()));
				}
			}
			bombes.clear();
			bombes.addAll(tmp2);
			
		}
		
	}
}
