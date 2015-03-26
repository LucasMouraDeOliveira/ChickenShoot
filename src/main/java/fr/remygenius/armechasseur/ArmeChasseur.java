package fr.remygenius.armechasseur;

import fr.lordkadoc.launcher.ServerInstance;
import fr.remygenius.arme.Arme;
import fr.remygenius.thread.ThreadRecharge;

/**
 * Classe qui définit les attributs qu'a une arme de chasseur
 * @author remy
 *
 */

public class ArmeChasseur extends Arme {
	private int vitesseBalle;
	private String nomTireur;
	
	private ServerInstance instance;

	public ArmeChasseur(ServerInstance instance, String nomTireur, String nom, int degat, double tempsDeRecharge, int munitions, int vitesseBalle) {
		super(nom, degat, tempsDeRecharge, munitions);
		this.vitesseBalle = vitesseBalle;
		this.nomTireur = nomTireur;
		this.instance = instance;
	}
	
	public void tirer(int x1, int y1, int x2, int y2){
		double vx = x2-x1;
		double vy = y2-y1;
		int d = (int)Math.sqrt(vx*vx+vy*vy);
		vx = vx/d;
		vy = vy/d;
		vx = vx*vitesseBalle;
		vy = vy*vitesseBalle;
		this.setMunitions(this.getMunitions()-1);
		
		instance.getCarte().ajouterBalle(new Balle(instance,nomTireur,x1, y1, vx, vy, this.getDegat()));
		new ThreadRecharge(this.getTempsDeRecharge(), this).start();
	}
	
	public int getVitesseBalle() {
		return vitesseBalle;
	}

	public void setVitesseBalle(int vitesseBalle) {
		this.vitesseBalle = vitesseBalle;
	}
	
	/**
	 * Inutiliser par le chasseur
	 */
	@Override
	public void poser(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
