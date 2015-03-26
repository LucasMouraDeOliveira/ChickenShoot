package fr.remygenius.armepoulet;

import fr.lordkadoc.launcher.ServerInstance;
import fr.remygenius.arme.Arme;
import fr.remygenius.thread.ThreadRecharge;

/**
 * Classe qui définit les attributs qu'a une arme de poulet
 * @author remy
 *
 */

public class ArmePoulet extends Arme{
	private double tempsSurCarte;
	private int rayonExplosion;
	
	private ServerInstance instance;

	public ArmePoulet(ServerInstance instance,String nom, int degat, double tempsDeRecharge, int munitions, double tempsSurCarte, int rayonExplosion) {
		super(nom, degat, tempsDeRecharge, munitions);
		this.tempsSurCarte = tempsSurCarte;
		this.rayonExplosion = rayonExplosion;
		this.instance = instance;
	}

	public int getRayonExplosion() {
		return rayonExplosion;
	}

	public void setRayonExplosion(int rayonExplosion) {
		this.rayonExplosion = rayonExplosion;
	}

	public double getTempsSurCarte() {
		return tempsSurCarte;
	}

	public void setTempsSurCarte(double tempsSurCarte) {
		this.tempsSurCarte = tempsSurCarte;
	}

	@Override
	public void tirer(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void poser(int x, int y) {
		// TODO Auto-generated method stub
		this.setMunitions(this.getMunitions()-1);
		instance.getCarte().ajouterBombe(new Bombe(instance,x, y, this.getDegat(), this.getTempsSurCarte(), this.rayonExplosion));
		new ThreadRecharge(this.getTempsDeRecharge(), this).start();
	}

}
