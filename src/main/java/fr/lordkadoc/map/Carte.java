package fr.lordkadoc.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import fr.lordkadoc.entities.Joueur;
import fr.remygenius.armechasseur.Balle;
import fr.remygenius.armepoulet.Bombe;
import fr.remygenius.armepoulet.Explosion;

public class Carte {
	
	public List<Explosion> getExplosions() {
		return explosions;
	}

	public void setExplosions(List<Explosion> explosions) {
		this.explosions = explosions;
	}

	private int[][] positions;
	private List<Joueur> players;
	private List<Balle> balles;
	private List<Bombe> bombes;
	private List<Explosion> explosions;
	
	public Carte(){
		this.init(20);
	}
	
	private void init(int size){
		this.positions = new int[size][size];
		this.players = new ArrayList<Joueur>();
		this.balles = new ArrayList<Balle>();
		this.bombes = new ArrayList<Bombe>();
		this.explosions = new ArrayList<Explosion>();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				//On crée les contours : on met des 2
				if(i==0 || i==size-1 || j==0 || j==size-1){
					positions[i][j] = 2;
				}
				else{
					//On initialise la premiere case à " "
					if(i==1 && j ==1){
						positions[i][j] = 9;
					}
					if(positions[i][j] == 9){
						int r = (int)(Math.random()*2);
						if(i == size -2){
							for(int k = 0; k<size-j; k++)
								positions[i][j+k] = 9;
						}
						else if(j == size -2){
							for(int k = 0; k<size-i; k++)
								positions[i+k][j] = 9;
						}
						else if(i!=size-1 && j!=size-1){
							if(r == 0) {
								positions[i+1][j] = 9;
							}
							else {
								positions[i][j+1] = 9;
							}
						}
					}
					else if(positions[i][j] != 9) {
								positions[i][j] = 0;
					}
				}
			}
		}
		//On place les obstacles aux endroit où il y a des 0
		//et on remplace les 0 par des " "
		for(int i =0; i<size; i++) {
			for(int j = 0; j<size; j++){
				if(Math.random()*100 > 98 && positions[i][j]==0){
					positions[i][j] = 3;
						for(int l =0; l<3;l++){
							if(i-l>1 && i+l<size-1 && j-l>1 && j+l<size-1){
								positions[i+l][j]=3;
								positions[i+l][j+l]=3;
								positions[i-l][j-l]=3;
								positions[i-l][j+l]=3;
								positions[i+l][j-l]=3;
								positions[i-l][j]=3;
								positions[i][j+l]=3;
								positions[i][j-l]=3;
							}
						}
				}
				else if(Math.random()*100 > 85 && positions[i][j]==0){
					positions[i][j] = 4;
				}
				else if(positions[i][j]!=2 && positions[i][j]!=3) {
					positions[i][j] = 1;
				}
			}
		}
	}
	
	public int[][] getPositions() {
		return positions;
	}

	public void setPositions(int[][] positions) {
		this.positions = positions;
	}
	
	public List<Joueur> getPlayers(){
		return this.players;
	}
	
	public void ajouterBalle(Balle balle){
		this.balles.add(balle);
	}
	
	public void ajouterBombe(Bombe bombe){
		this.bombes.add(bombe);
	}
	
	public void ajouterExplosions(Explosion explosion){
		this.explosions.add(explosion);
	}
	
	public JsonObjectBuilder getJSon(){
		
		JsonObjectBuilder carte = Json.createObjectBuilder();
		JsonArrayBuilder mapBuilder = Json.createArrayBuilder();
		JsonArrayBuilder playerBuilder = Json.createArrayBuilder();
		JsonArrayBuilder balleBuilder = Json.createArrayBuilder();
		JsonArrayBuilder bombeBuilder = Json.createArrayBuilder();
		JsonArrayBuilder explosionBuilder = Json.createArrayBuilder();
		
		JsonArrayBuilder line;
		
		for(int i=0;i<positions.length;i++){ // Crée le tableau de positions
			line = Json.createArrayBuilder();
			for(int j=0;j<positions[i].length;j++){
				line.add(positions[i][j]);
			}
			mapBuilder.add(line);
		}
		for(Joueur p : players){ // Crée les joueurs
			playerBuilder.add(Json.createObjectBuilder()
					.add("type",p.getType())
					.add("x", p.getX())
					.add("y", p.getY())
					.add("vieInitiale", p.getVieInitiale())
					.add("vie", p.getVie())
					.add("angle", p.getAngle())
					.add("arme", p.getArme().getNom()));
		}
		List<Balle> tmp = new ArrayList<Balle>();
		tmp.addAll(balles);
		for(Balle b : tmp){
			balleBuilder.add(Json.createObjectBuilder()
					.add("x", b.getX())
					.add("y", b.getY())
					.add("angle", b.getAngle()));
		}
		List<Bombe> tmpBombe = new ArrayList<Bombe>();
		tmpBombe.addAll(bombes);
		for(Bombe b : tmpBombe){
			bombeBuilder.add(Json.createObjectBuilder()
					.add("x", b.getX())
					.add("y", b.getY()));
		}
		List<Explosion> tmpExplosion = new ArrayList<Explosion>();
		tmpExplosion.addAll(explosions);
		for(Explosion e : tmpExplosion){
			explosionBuilder.add(Json.createObjectBuilder()
					.add("x", e.getX())
					.add("y", e.getY())
					.add("taille", e.getTailleActu()));
		}
		carte.add("carte", mapBuilder);
		carte.add("players", playerBuilder);
		carte.add("balles", balleBuilder);
		carte.add("bombes", bombeBuilder);
		carte.add("explosions", explosionBuilder);
		return carte;
	}
	
	
	public void deplacer(boolean[] c, Joueur player){
		
		List<Point> points = null;
		int x = 0;
		int y = 0;
				
		if(c[0]){ // north
			x = 0;
			y = -player.getVitesse();
			points = player.hitboxPoints("NORTH");
			calculerDeplacement("NORTH",x,y,points,player);
		}
		if(c[1]){ //south
			x = 0;
			y = player.getVitesse();
			points = player.hitboxPoints("SOUTH");
			calculerDeplacement("SOUTH",x,y,points,player);
		}
		if(c[2]){ // west
			x = -player.getVitesse();
			y = 0;
			points = player.hitboxPoints("WEST");
			calculerDeplacement("WEST",x,y,points,player);
		}
		if(c[3]){ // east
			x = player.getVitesse();
			y = 0;
			points = player.hitboxPoints("EAST");
			calculerDeplacement("EAST",x,y,points,player);
		}
			
	}
		
	private void calculerDeplacement(String direction, int x, int y, List<Point> points,Joueur player) {
		Point p1 = new Point(points.get(0).x+x,points.get(0).y+y);
		Point p2 = new Point(points.get(1).x+x,points.get(1).y+y);
		
		if(estVide(cellule(p1)) && estVide(cellule(p2))){
			player.setX(player.getX()+x);
			player.setY(player.getY()+y);
		}else{
			if(direction.equals("NORTH")){
				if(estVide(cellule(p1))){
					player.setY((p1.y/32+1)*32+1+player.getSize()/2);				
				}else{
					player.setY((p2.y/32+1)*32+1+player.getSize()/2);		
				}
			}else if(direction.equals("SOUTH")){
				if(estVide(cellule(p1))){
					player.setY(p1.y/32*32-player.getSize()/2-1);				
				}else{
					player.setY(p2.y/32*32-player.getSize()/2-1);	
				}
			}else if(direction.equals("WEST")){
				if(estVide(cellule(p1))){
					player.setX((p1.x/32+1)*32+1+player.getSize()/2);
				}else{
					player.setX((p2.x/32+1)*32+1+player.getSize()/2);
				}
			}else if(direction.equals("EAST")){
				if(estVide(cellule(p1))){
					player.setX(p1.x/32*32-player.getSize()/2-1);
				}else{
					player.setX(p2.x/32*32-player.getSize()/2-1);
				}
			}
		}
	}
	
	public int getNbPoulets(){
		int i =0;
		for(Joueur p : players){
			if(p.getType() =="Poulet"){
				i++;
			}
		}
		return i;
	}
	
	public int getNbChasseurs(){
		int i =0;
		for(Joueur p : players){
			if(p.getType() =="Chasseur"){
				i++;
			}
		}
		return i;
	}

	public int cellule(Point p){
		return this.positions[p.x/32][p.y/32];
	}
	
	public boolean estVide(int cellule){
		return cellule == 1 || cellule == 3;
	}

	public List<Balle> getBalles() {
		return balles;
	}

	public void setBalles(List<Balle> balles) {
		this.balles = balles;
	}

	public void setPlayers(List<Joueur> players) {
		this.players = players;
	}

	public List<Bombe> getBombes() {
		return bombes;
	}

	public void setBombes(List<Bombe> bombes) {
		this.bombes = bombes;
	}

	public void placer(Joueur p) {
		boolean b = false;
		int x = 0, y = 0;
		while(!b){
			x = (int)(Math.random()*(this.positions.length-2)*32)+32;
			y = (int)(Math.random()*(this.positions.length-2)*32)+32;
			if(this.estVide(cellule(new Point(x,y)))){
				b = true;
			}
		}
		p.setX(x);
		p.setY(y);
	}
}
