package fr.remygenius.armepoulet;

import java.awt.Point;
import java.awt.Polygon;

import fr.lordkadoc.entities.Chasseur;
import fr.lordkadoc.entities.Joueur;
import fr.lordkadoc.launcher.ServerInstance;
import fr.lordkadoc.map.Carte;

/**
 * Classe qui defini une bombe de base
 * @author remy
 *
 */

public class Bombe {
	private ServerInstance instance;
	
	private int x;
	private int y;
	private int degat;
	private int tempsSurCarte;
	private int cptTemps = 0;
	private int rayonExplosion;

	public Bombe(ServerInstance instance, int x, int y, int degat, int tempsSurCarte, int rayonExplosion){
		this.instance = instance;
		this.x = x;
		this.y = y;
		this.degat= degat;
		this.tempsSurCarte = tempsSurCarte;
		this.rayonExplosion = rayonExplosion;
	}
	
	public int getTempsSurCarte() {
		return tempsSurCarte;
	}

	public void setTempsSurCarte(int tempsSurCarte) {
		this.tempsSurCarte = tempsSurCarte;
	}

	public int getCptTemps() {
		return cptTemps;
	}

	public void setCptTemps(int cptTemps) {
		this.cptTemps = cptTemps;
	}

	public int getRayonExplosion() {
		return rayonExplosion;
	}

	public void setRayonExplosion(int rayonExplosion) {
		this.rayonExplosion = rayonExplosion;
	}

	public boolean verifierTempsSurCarte(){
		if(cptTemps == (int)(tempsSurCarte/0.02)){
			/*A completer*/
			return true;
		}
		cptTemps ++;
		return false;
	}
	
	public boolean verifierToucherChasseur(){
		Carte carte = instance.getCarte();
		Polygon poly;
		for(Joueur p : carte.getPlayers()){
			if(p instanceof Chasseur){
				poly = p.hitbox();
				for(int i =0; i<this.rayonExplosion; i++){
					for(int j =0; j<this.rayonExplosion; j++) {
						if(poly.contains(new Point((int)x+i,(int)y+j))){
							p.recevoirDegat(this.degat);
							if(!p.estEnVie()){
								carte.getPlayers().remove(p);
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDegat() {
		return degat;
	}
	public void setDegat(int degat) {
		this.degat = degat;
	}

}
