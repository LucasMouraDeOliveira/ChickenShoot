package fr.remygenius.armepoulet;

/**
 * Classe qui defini une bombe de base
 * @author remy
 *
 */

public class Bombe {
	private int x;
	private int y;
	private int degat;
	private int tempsSurCarte;
	private int cptTemps = 0;
	private int rayonExplosion;

	public Bombe(int x, int y, int degat, int tempsSurCarte, int rayonExplosion){
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
