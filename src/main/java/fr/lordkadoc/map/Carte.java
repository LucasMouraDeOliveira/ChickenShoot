//package fr.lordkadoc.map;
//
//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.json.Json;
//import javax.json.JsonArrayBuilder;
//import javax.json.JsonObjectBuilder;
//
//import fr.chickenshoot.game.entities.Chicken;
//import fr.chickenshoot.game.entities.Hunter;
//import fr.chickenshoot.game.entities.Player;
//import fr.chickenshoot.game.projectiles.Bombe;
//import fr.chickenshoot.game.projectiles.Bullet;
//import fr.chickenshoot.game.projectiles.Explosion;
//import fr.lordkadoc.launcher.ServerInstance;
//
//public class Carte {
//
//	private int[][] positions;
//	
//	private List<Chicken> chickens;
//	
//	private List<Hunter> hunters;
//	
//	private List<Bullet> bullets;
//	
//	private List<Bombe> bombes;
//	
//	private List<Explosion> explosions;
//	
//	private ServerInstance instance;
//	
//	public Carte(ServerInstance instance){
//		this.instance = instance;
//		this.init(20);
//	}
//	
//	private void init(int size){
//		this.positions = new int[size][size];
//		this.chickens = new ArrayList<Chicken>();
//		this.hunters = new ArrayList<Hunter>();
//		this.bullets = new ArrayList<Bullet>();
//		this.bombes = new ArrayList<Bombe>();
//		this.explosions = new ArrayList<Explosion>();
//		for(int i=0;i<size;i++){
//			for(int j=0;j<size;j++){
//				//On crée les contours : on met des 2
//				if(i==0 || i==size-1 || j==0 || j==size-1){
//					positions[i][j] = 2;
//				}
//				else{
//					//On initialise la premiere case à 9
//					if(i==1 && j ==1){
//						positions[i][j] = 9;
//					}
//					if(positions[i][j] == 9){
//						int r = (int)(Math.random()*2);
//						if(i == size -2){
//							for(int k = 0; k<size-j; k++)
//								positions[i][j+k] = 9;
//						}
//						else if(j == size -2){
//							for(int k = 0; k<size-i; k++)
//								positions[i+k][j] = 9;
//						}
//						else if(i!=size-1 && j!=size-1){
//							if(r == 0) {
//								positions[i+1][j] = 9;
//							}
//							else {
//								positions[i][j+1] = 9;
//							}
//						}
//					}
//					else if(positions[i][j] != 9) {
//								positions[i][j] = 0;
//					}
//				}
//			}
//		}
//		
//		//On place les obstacles aux endroit où il y a des 0
//		//et on remplace les 0 par des " "
//		boolean regen = false;
//		for(int i =0; i<size; i++) {
//			for(int j = 0; j<size; j++){
//				if(i+1<size && j+1<size){
//					if(positions[i][j] == 0 && Math.random()*100>90 && regen == false && i>=size/2 && j>=size/2){
//						if(positions[i+1][j] == 0 && positions[i][j+1] == 0 && positions[i+1][j+1] == 0){
//							regen = true;
//							positions[i][j]=5;
//							positions[i+1][j]=5;
//							positions[i][j+1]=5;
//							positions[i+1][j+1]=5;
//						}
//					}
//				}
//				if(Math.random()*100 > 98 && positions[i][j]==0){
//					positions[i][j] = 3;
//						for(int l =0; l<3;l++){
//							if(i-l>1 && i+l<size-1 && j-l>1 && j+l<size-1){
//								positions[i+l][j]=3;
//								positions[i+l][j+l]=3;
//								positions[i-l][j-l]=3;
//								positions[i-l][j+l]=3;
//								positions[i+l][j-l]=3;
//								positions[i-l][j]=3;
//								positions[i][j+l]=3;
//								positions[i][j-l]=3;
//							}
//						}
//				}
//				else if(Math.random()*100 > 85 && positions[i][j]==0){
//					positions[i][j] = 4;
//				}
//				else if(positions[i][j]!=2 && positions[i][j]!=3 && positions[i][j]!=5) {
//					positions[i][j] = 1;
//				}
//			}
//		}
//	}
//	
//	public JsonObjectBuilder getMapJSon() {
//		
//		JsonObjectBuilder carte = Json.createObjectBuilder();
//		JsonArrayBuilder mapBuilder = Json.createArrayBuilder();
//		JsonArrayBuilder line;
//		
//		for(int i=0;i<positions.length;i++){ // Crée le tableau de positions
//			line = Json.createArrayBuilder();
//			for(int j=0;j<positions[i].length;j++){
//				line.add(positions[i][j]);
//			}
//			mapBuilder.add(line);
//		}	
//		
//		carte.add("carte", mapBuilder);
//		return carte;
//	}
//	
//	public JsonObjectBuilder getJSon(){
//		
//		JsonObjectBuilder carte = Json.createObjectBuilder();
//		JsonArrayBuilder playerBuilder = Json.createArrayBuilder();
//		JsonArrayBuilder balleBuilder = Json.createArrayBuilder();
//		JsonArrayBuilder bombeBuilder = Json.createArrayBuilder();
//		JsonArrayBuilder explosionBuilder = Json.createArrayBuilder();	
//		
//		JsonObjectBuilder builder;
//		
//		for(Chicken c : chickens){ // Crée les joueurs
//			builder = Json.createObjectBuilder();
//			c.getJSon(builder);
//			playerBuilder.add(builder);
//		}
//		
//		for(Hunter h : hunters){ // Crée les joueurs
//			builder = Json.createObjectBuilder();
//			h.getJSon(builder);
//			playerBuilder.add(builder);
//		}
//		
//		for(Bullet b : bullets){
//			balleBuilder.add(Json.createObjectBuilder()
//					.add("x", b.getX())
//					.add("y", b.getY())
//					.add("angle", b.getAngle()));
//		}
//		
//		for(Bombe b : bombes){
//			bombeBuilder.add(Json.createObjectBuilder()
//					.add("x", b.getX())
//					.add("y", b.getY()));
//		}
//		
//		for(Explosion e : explosions){
//			explosionBuilder.add(Json.createObjectBuilder()
//					.add("x", e.getX())
//					.add("y", e.getY())
//					.add("taille", e.getTailleActu())
//					.add("pourcentage",e.calculerPourcentage()));
//		}
//		
//		carte.add("players", playerBuilder);
//		carte.add("balles", balleBuilder);
//		carte.add("bombes", bombeBuilder);
//		carte.add("explosions", explosionBuilder);
//		carte.add("time",instance.getTime());
//		if(instance.getState() == ServerInstance.ENDED){
//			carte.add("victory", getWinMessage());
//		}
//		return carte;
//	}
//	
//	public String getWinMessage() {
//		if(hunters.isEmpty()){
//			if(chickens.size() == 1){
//				return chickens.get(0).getPlayerParams().getName() + " gagne !";
//			}else{
//				return "Les poulets gagnent !";	
//			}
//		}else if(chickens.isEmpty()){
//			if(hunters.size() == 1){
//				return hunters.get(0).getPlayerParams().getName() + " gagne !";
//			}else{
//				return "Les chasseurs gagnent !";
//			}
//		}else{
//			return "Egalité";
//		}
//	}
//	
//	
//	public void deplacer(boolean[] c, Player player){
//		
//		List<Point> points = null;
//		int x = 0;
//		int y = 0;
//				
//		if(c[0]){ // north
//			x = 0;
//			y = -player.getPlayerParams().getSpeed();
//			points = player.hitboxPoints("NORTH");
//			calculerDeplacement("NORTH",x,y,points,player);
//		}
//		if(c[1]){ //south
//			x = 0;
//			y = player.getPlayerParams().getSpeed();
//			points = player.hitboxPoints("SOUTH");
//			calculerDeplacement("SOUTH",x,y,points,player);
//		}
//		if(c[2]){ // west
//			x = -player.getPlayerParams().getSpeed();
//			y = 0;
//			points = player.hitboxPoints("WEST");
//			calculerDeplacement("WEST",x,y,points,player);
//		}
//		if(c[3]){ // east
//			x = player.getPlayerParams().getSpeed();
//			y = 0;
//			points = player.hitboxPoints("EAST");
//			calculerDeplacement("EAST",x,y,points,player);
//		}
//			
//	}
//		
//	private void calculerDeplacement(String direction, int x, int y, List<Point> points,Player player) {
//		Point p1 = new Point(points.get(0).x+x,points.get(0).y+y);
//		Point p2 = new Point(points.get(1).x+x,points.get(1).y+y);
//		
//		if(estVide(typeCellule(p1)) && estVide(typeCellule(p2))){
//			player.getPlayerParams().setX(player.getPlayerParams().getX()+x);
//			player.getPlayerParams().setY(player.getPlayerParams().getY()+y);
//		}else{
//			if(direction.equals("NORTH")){
//				if(estVide(typeCellule(p1))){
//					player.getPlayerParams().setY((p1.y/32+1)*32+1+player.getPlayerParams().getSize()/2);				
//				}else{
//					player.getPlayerParams().setY((p2.y/32+1)*32+1+player.getPlayerParams().getSize()/2);		
//				}
//			}else if(direction.equals("SOUTH")){
//				if(estVide(typeCellule(p1))){
//					player.getPlayerParams().setY(p1.y/32*32-player.getPlayerParams().getSize()/2-1);				
//				}else{
//					player.getPlayerParams().setY(p2.y/32*32-player.getPlayerParams().getSize()/2-1);	
//				}
//			}else if(direction.equals("WEST")){
//				if(estVide(typeCellule(p1))){
//					player.getPlayerParams().setX((p1.x/32+1)*32+1+player.getPlayerParams().getSize()/2);
//				}else{
//					player.getPlayerParams().setX((p2.x/32+1)*32+1+player.getPlayerParams().getSize()/2);
//				}
//			}else if(direction.equals("EAST")){
//				if(estVide(typeCellule(p1))){
//					player.getPlayerParams().setX(p1.x/32*32-player.getPlayerParams().getSize()/2-1);
//				}else{
//					player.getPlayerParams().setX(p2.x/32*32-player.getPlayerParams().getSize()/2-1);
//				}
//			}
//		}
//		if(player instanceof Chicken){
//			if(estArbre(typeCellule(p1))){
//				((Chicken) player).setVisible(false);
//			}else{
//				((Chicken) player).setVisible(true);
//			}
//		}
//	}
//	
//	public int getChickenNumber(){
//		return chickens.size();
//	}
//	
//	public int getHunterNumber(){
//		return hunters.size();
//	}
//
//
//
//	/**
//	 * Place un joueur aléatoirement sur la carte. Tente de trouver une position libre aléatoire sur la carte, et positionne le joueur dessus.
//	 * 
//	 * @param p le joueur à placer sur le terrain.
//	 */
//	public void placer(Player p) {
//		boolean b = false;
//		int x = 0, y = 0;
//		while(!b){
//			x = (int)(Math.random()*(this.positions.length-2)*32)+32;
//			y = (int)(Math.random()*(this.positions.length-2)*32)+32;
//			if(this.estVide(typeCellule(new Point(x,y)))){
//				b = true;
//			}
//		}
//		p.getPlayerParams().setX(x);
//		p.getPlayerParams().setY(y);
//	}
//	
//	public List<Chicken> getChickens(){
//		return this.chickens;
//	}
//	
//	public void addChicken(Chicken chicken){
//		chickens.add(chicken);
//	}
//	
//	public void removeChicken(Chicken chicken){
//		chickens.remove(chicken);
//	}
//	
//	public List<Hunter> getHunters(){
//		return this.hunters;
//	}
//	
//	public void addHunter(Hunter hunter){
//		hunters.add(hunter);
//	}
//	
//	public void removeHunter(Hunter hunter){
//		hunters.remove(hunter);
//	}
//	
//	public void removePlayer(Player player) {
//		if(player instanceof Chicken){
//			removeChicken((Chicken)player);
//		}else{
//			removeHunter((Hunter)player);
//		}
//	}
//	
//	public List<Bullet> getBullets() {
//		return bullets;
//	}
//	
//	public void addBullet(Bullet bullet) {
//		bullets.add(bullet);
//	}
//	
//	public void clearBullets(){
//		bullets.clear();
//	}
//	
//	public List<Bombe> getBombes() {
//		return bombes;
//	}
//	
//	public void addBombe(Bombe bombe){
//		bombes.add(bombe);
//	}
//	
//	public void clearBombes(){
//		bombes.clear();
//	}
//	
//	public List<Explosion> getExplosions() {
//		return explosions;
//	}
//	
//	public void addExplosion(Explosion explosion){
//		explosions.add(explosion);
//	}
//	
//	public int typeCellule(Point p){
//		return this.positions[p.x/32][p.y/32];
//	}
//	
//	public boolean estVide(int cellule){
//		return cellule == 1 || cellule == 3 || cellule == 5;
//	}
//	
//	public boolean estArbre(int cellule){
//		return cellule == 3;
//	}
//	
//	public boolean estRegen(int cellule){
//		return cellule == 5;
//	}
//
//	public boolean isGameFinished() {
//		return chickens.isEmpty() || hunters.isEmpty();
//	}
//
//	public List<Player> getSurvivor() {
//		List<Player> players = new ArrayList<Player>();
//		players.addAll(chickens);
//		players.addAll(hunters);
//		return players;
//	}
//	
//}