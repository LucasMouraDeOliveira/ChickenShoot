package fr.lordkadoc.entities;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.launcher.ServerInstance;
import fr.remygenius.arme.Arme;

public class Player {
	
	private int x;
	private int y;
	
	private double angle;	
	
	private final int vieInitiale;
	private int vie;	
	private int size;
	
	private String type;
	
	private Arme arme;
	
	
	public Player(int x, int y, int size, String type, Arme arme){
		this.x = x;
		this.y = y;
		this.size = size;
		this.type = type;
		this.arme = arme;
		this.vieInitiale = 50;
		this.vie = 50;
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
		int x1 = x -(size/2);
		int y1 = y -(size/2);
		points.add(new Point(x1,y1));
		points.add(new Point(x1+size,y1));
		points.add(new Point(x1+size,y1+size));
		points.add(new Point(x1,y1+size));
		return points;
	}
	
	public List<Point> hitboxPoints(String direction){
		List<Point> points = new ArrayList<Point>();
		int x1 = x - (size/2);
		int y1 = y - (size/2);
		if(direction.equals("NORTH")){
			points.add(new Point(x1,y1));
			points.add(new Point(x1+size,y1));
		}else if(direction.equals("SOUTH")){
			points.add(new Point(x1,y1+size));
			points.add(new Point(x1+size,y1+size));		
		}else if(direction.equals("WEST")){
			points.add(new Point(x1,y1));
			points.add(new Point(x1,y1+size));
		}else if(direction.equals("EAST")){
			points.add(new Point(x1+size,y1));
			points.add(new Point(x1+size,y1+size));
		}
		return points;
	}
	
	public void recevoirDegat(int degat){
		if((this.vie-degat <= 0)){
			this.setVie(0);
		} else{
			this.setVie(this.vie-degat);
		}
	}
	
	public void attaqueChasseur(int x2, int y2){
		if(this.peutTirer()){
			this.arme.tirer(this.x, this.y, x2, y2);
			this.arme.setRechargeTermine(false);
		}	
	}

	public void attaquePoulet(int x2, int y2){
		if(this.peutTirer()){
			this.arme.poser(this.x, this.y);
			this.arme.setRechargeTermine(false);
		}	
	}
	
	public boolean peutTirer(){
		return (this.arme.getMunitions() > 0 && this.getArme().isRechargeTermine());
	}
	
	public void pivoter(int i, int j) {
		int x1 = i-this.x;
		int y1 = j-this.y;
		this.angle = -Math.atan2(x1, y1)-Math.PI;
	}
	
	public boolean estEnVie(){
		return this.vie > 0;
	}
	
	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Arme getArme(){
		return arme;
	} 
	
	public void setArme(Arme arme){
		this.arme = arme;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public int getVieInitiale() {
		return vieInitiale;
	}
	
}
