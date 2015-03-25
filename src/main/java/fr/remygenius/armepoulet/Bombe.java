package fr.remygenius.armepoulet;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

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
	
	public void verifierToucherChasseur(){
		Carte carte = instance.getCarte();
		Polygon poly;
		List<Joueur> tmp = new ArrayList<Joueur>();
		for(Joueur p : carte.getPlayers()){
			if(p instanceof Chasseur){
				poly = this.hitbox();
				if(poly.contains(new Point(p.getX(),p.getY()))){
					p.recevoirDegat(this.getDegat());
					if(!p.estEnVie()){
						tmp.add(p);
					}
				}
			}
		}
		
		for(Joueur j : tmp){
			carte.getPlayers().remove(j);
		}
		
	}
	
	public Polygon hitbox(){
		List<Point> points = this.hitboxPoints();
		int[] x = new int[4];
		int[] y = new int[4];
		for(int i=0;i<points.size();i++){
			x[i] = points.get(i).x;
			y[i] = points.get(i).y;
		}
		return new Polygon(x, y, 4);
	}
	
	public List<Point> hitboxPoints(){
		List<Point> points = new ArrayList<Point>();
		int x1 = x -(rayonExplosion);
		int y1 = y -(rayonExplosion);
		points.add(new Point(x1,y1));
		points.add(new Point(x1+2*rayonExplosion,y1));
		points.add(new Point(x1+2*rayonExplosion,y1+2*rayonExplosion));
		points.add(new Point(x1,y1+2*rayonExplosion));
		return points;
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
