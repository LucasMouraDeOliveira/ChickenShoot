package fr.lordkadoc.launcher;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import org.eclipse.jetty.websocket.api.Session;

import fr.chickenshoot.game.entities.Action;
import fr.chickenshoot.game.entities.Chicken;
import fr.chickenshoot.game.entities.Hunter;
import fr.chickenshoot.game.entities.Player;
import fr.chickenshoot.game.gameloop.GameLoop;
import fr.chickenshoot.game.weapons.ChickenBomb;
import fr.chickenshoot.game.weapons.Gun;
import fr.lordkadoc.map.Carte;
import fr.remygenius.thread.ThreadTimer;

public class ServerInstance {
	
	private Map<Session, Player> users;	
	private Carte carte;

	private int maxUsers;	
	private int state;
	
	public final static int WAITING_PLAYERS = 0;
	public final static int FULL = 1;
	public final static int RUNNING = 2;
	public final static int ENDED = 3;
	
	private ThreadTimer timer;
	
	private GameLoop gameLoop;

	public ServerInstance(int maxUsers){
		this.users = new HashMap<Session,Player>();
		this.carte = new Carte(this);
		this.maxUsers = maxUsers;
		this.state = WAITING_PLAYERS;
	}

	/**
	 * Ajoute un joueur à la partie, et démarre celle-ci si suffisament de joueurs sont connectés
	 * 
	 * @param user la session de l'utilisateur à ajouter
	 * @param login 
	 */
	public void ajouterJoueur(Session user, String login){
		String type;
		if(this.users.size() < this.maxUsers){
			Player p;
			if(this.carte.getHunterNumber()<this.carte.getChickenNumber()){
				p = new Hunter(login, 0,0);
				p.setWeapon(new Gun((Hunter)p, 10, 100));
				type = "Chasseur";
				this.carte.getHunters().add((Hunter) p);
			}
			else {
				p = new Chicken(login,0,0);
				p.setWeapon(new ChickenBomb((Chicken)p, 10, 100));
				type = "Poulet";
				this.carte.getChickens().add((Chicken) p);
			}
			
			this.carte.placer(p);
			this.users.put(user, p);
			
			if(this.users.size() == this.maxUsers){
				this.state = FULL;
			}
			
			JsonObjectBuilder player = Json.createObjectBuilder()
					.add("type", type)
					.add("login", login);
			
			this.diffuserMessage("connect", player);
		}
		
		
	}
	
	/*public void determinerArmeJoueur(Player joueur, String labelArme){
		
		if(joueur instanceof Hunter){
			if(labelArme == "arbalete"){
				joueur.setArme(new Arbalete(this, (Chasseur)joueur));
			}else if(labelArme == "fusil"){
				joueur.setArme(new Fusil(this, (Chasseur)joueur));
			}else{
				joueur.setArme(new Mitraillette(this, (Chasseur)joueur));
			}
		}else if(joueur instanceof Poulet){
			joueur.setArme(new BombeBasique((Poulet)joueur, this));
		}
		
	}*/
	
	/**
	 * 
	 * @return vrai si le nombre maximum de clients est atteint
	 */
	public boolean clientsTousConnectes(){
		return this.users.size() == this.maxUsers;
	}

	public void demarrerPartie(){
		this.state = RUNNING;
		this.timer = new ThreadTimer(this,60);
		this.timer.start();
		this.diffuserMessage("start");
		this.gameLoop = new GameLoop(this, 10);
		this.gameLoop.start();
	}


	public void recevoirMessage(Session user, String message){

		JsonReader jsonReader = Json.createReader(new StringReader(message));
		JsonObject object = jsonReader.readObject();
		
		if(this.getUsers().get(user).isAlive()){		
			String type = object.getString("type");
	
			if(type.equals("playerUpdate")){			
				this.gererActionJoueur(user,object);		
			}/*else if(type.equals("armeUpdate")){
				this.determinerArmeJoueur(this.getUsers().get(user), object.getString("labelArme"));
			}*/
		}
		
	}

	private void gererActionJoueur(Session user,JsonObject object) {
		
		JsonObject coords = object.getJsonObject("movement");
		JsonObject souris = object.getJsonObject("souris");
		
		boolean tir = object.getBoolean("tir");
			
		boolean detonate = object.getBoolean("explode");
		
		boolean[] c = new boolean[4];
		c[0] = coords.getBoolean("north");
		c[1] = coords.getBoolean("south");
		c[2] = coords.getBoolean("west");
		c[3] = coords.getBoolean("east");
		
		this.carte.deplacer(c,this.users.get(user));
		
		Player player = this.users.get(user);
		
		if(tir){
			player.handle(Action.SHOOT);
		}	
		
		if(detonate){
			player.handle(Action.DETONATE);
		}
		
		this.users.get(user).shift(souris.getInt("x"),souris.getInt("y"));
	}

	public void diffuserMessage(String type, JsonObjectBuilder message) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject json = factory.createObjectBuilder()
				.add("type", type)
				.add("data", message).build();
		for(Session s : users.keySet()){
			if(s.isOpen()){
				try {
					s.getRemote().sendString(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void diffuserMessage(String type) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject json = factory.createObjectBuilder()
				.add("type", type).build();
		for(Session s : users.keySet()){
			if(s.isOpen()){
				try {
					s.getRemote().sendString(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Map<Session,Player> getUsers(){
		return this.users;
	}

	public Carte getCarte(){
		return this.carte;
	}
	
	public int getState(){
		return this.state;
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	public int getMaxUsers(){
		return this.maxUsers;
	}
	
	public int getCurrentUsers(){
		return this.users.size();
	}

	public ThreadTimer getTimer() {
		return timer;
	}

}
