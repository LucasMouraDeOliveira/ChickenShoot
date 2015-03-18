package fr.remygenius.armechasseur;

import java.awt.Point;
import java.awt.Polygon;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.api.Session;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.entities.Poulet;
import fr.lordkadoc.launcher.ServerInstance;
import fr.lordkadoc.map.Carte;

public class Balle {
	private double x;
	private double y;
	private final double vx;
	private final double vy;
	private final int degat;
	
	private final double angle;
	
	private ServerInstance instance;
	
	public Balle(ServerInstance instance,double x, double y, double vx, double vy, int degat) {
		this.instance = instance;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.degat = degat;
		this.angle = Math.atan2(-vx,vy)+Math.PI;
	}

	public void deplacer(){
		x += vx;
		y += vy;
	}
	
	public boolean verifierCollision(){
		Carte carte = instance.getCarte();
		return !carte.estVide(carte.cellule(new Point((int)x, (int)y)));
	}
	
	public boolean verifierToucherPoulet(){
		Carte carte = instance.getCarte();
		Polygon poly;
		for(Player p : carte.getPlayers()){
			if(p instanceof Poulet){
				poly = p.hitbox();
				if(poly.contains(new Point((int)x,(int)y))){
					p.recevoirDegat(this.degat);
					if(!p.estEnVie()){
						carte.getPlayers().remove(p);
						for(Session s : instance.getUsers().keySet()){
							if(instance.getUsers().get(s).equals(p)){
								instance.getUsers().remove(s);
								break;
							}
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public int getDegat() {
		return degat;
	}
	
	public double getAngle(){
		return angle;
	}
	
}
