package fr.lordkadoc.launcher;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
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

public class ServerInstance {
	
	//Le temps en secondes d'une partie
	public final static int DEFAULT_GAME_DURATION = 120;
	
	public final static int WAITING_PLAYERS = 0;
	
	public final static int FULL = 1;
	
	public final static int RUNNING = 2;
	
	public final static int ENDED = 3;

	public final static int STOP = 4;
	
	private Map<Session, Player> users;	
	
	private Carte carte;

	private int maxUsers;	
	
	private int state;
	
	private int time;
	
	private GameLoop gameLoop;

	/**
	 * Crée une nouvelle partie de jeu de ChickenShoot qui attends que des joueurs se connectent.
	 * 
	 * @param maxUsers le nombre maximum de joueurs qui peuvent se connecter à la partie.
	 */
	public ServerInstance(int maxUsers){
		this.users = new HashMap<Session,Player>();
		this.carte = new Carte(this);
		this.maxUsers = maxUsers;
		this.state = WAITING_PLAYERS;
		this.time = DEFAULT_GAME_DURATION;
	}

	/**
	 * Ajoute un joueur à la partie s'il reste des places.
	 * Crée un joueur (soit poulet, soit chasseur) en fonction des classes des joueurs déjà présents.
	 *  
	 * 
	 * @param user la session de l'utilisateur à ajouter
	 * @param login le nom du joueur dans la partie
	 */
	public void addPlayer(Session user, String login){
		
		String type;
		Player p;
		
		//à déplacer vers une méthode spécialisée
		if(this.carte.getHunterNumber()<this.carte.getChickenNumber()){
			p = new Hunter(login, 0,0);
			p.setWeapon(new Gun(this, p, 10, 100));
			type = "Chasseur";
			this.carte.getHunters().add((Hunter) p);
		}
		else {
			p = new Chicken(login,0,0);
			p.setWeapon(new ChickenBomb(this, p, 10, 100));
			type = "Poulet";
			this.carte.getChickens().add((Chicken) p);
		}
		
		this.carte.placer(p);
		
		if(serverIsFull())
			this.state = FULL;
		
		JsonObjectBuilder player = Json.createObjectBuilder()
				.add("type", type)
				.add("login", login);
		
		//Signale à tous les joueurs dans le lobby qu'un nouveau joueur s'est connecté
		this.broadCastMessage("connect", player);
		
		this.users.put(user, p);
		
		//Envoyer au joueur la liste des joueurs connectés
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject json = factory.createObjectBuilder()
				.add("type", "list")
				.add("data", getPlayersInfo()).build();
		try {
			user.getRemote().sendString(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public JsonArrayBuilder getPlayersInfo(){
		JsonArrayBuilder players = Json.createArrayBuilder();
		JsonObjectBuilder pl;
		for(Player player : users.values()){
			pl = Json.createObjectBuilder();
			pl.add("type", player instanceof Chicken ? "Poulet" : "Chasseur");
			pl.add("login", player.getPlayerParams().getName());
			players.add(pl);
		}
		return players;
	}

	/**
	 * Retourne vrai si le serveur est plein.
	 * 
	 * @return vrai si le nombre maximum de clients est atteint
	 */
	public boolean serverIsFull(){
		return this.users.size() == this.maxUsers;
	}
	
	/**
	 * Retourne vrai si le serveur ne contient aucun joueurs.
	 * 
	 * @return vrai si le serveur est vide.
	 */
	public boolean serverIsEmpty() {
		return users.isEmpty();
	}

	/**
	 * Démarre la partie en lançant la boucle de jeu et le timer.
	 * Signale à tous les joueurs que la partie à commencé.
	 */
	public void startGame(){
		this.state = RUNNING;
		this.broadCastMessage("start");
		this.gameLoop = new GameLoop(this, 10);
		this.gameLoop.start();
	}

	/**
	 * Reçoit et interprète un message d'un joueur de la partie.
	 * 
	 * @param user la session de l'utilisateur
	 * @param message le message envoyé par le joueur
	 */
	public void receiveMessage(Player player, String message){

		//Si le joueur est mort, on ne prends pas en compte son message
		if(!player.isAlive()){
			return;
		}
		
		JsonReader jsonReader = Json.createReader(new StringReader(message));
		JsonObject object = jsonReader.readObject();
		
		String type = object.getString("type");

		if(type.equals("playerUpdate")){			
			this.handlePlayerAction(player,object);		
		}
		
	}

	private void handlePlayerAction(Player player,JsonObject object) {
		
		JsonObject coords = object.getJsonObject("movement");
		JsonObject souris = object.getJsonObject("souris");
		
		boolean tir = object.getBoolean("tir");
			
		boolean detonate = object.getBoolean("explode");
		
		boolean[] c = new boolean[4];
		c[0] = coords.getBoolean("north");
		c[1] = coords.getBoolean("south");
		c[2] = coords.getBoolean("west");
		c[3] = coords.getBoolean("east");
		
		this.carte.deplacer(c,player);
			
		if(tir){
			player.handle(Action.SHOOT);
		}	
		
		if(detonate){
			player.handle(Action.DETONATE);
		}
		
		player.shift(souris.getInt("x"),souris.getInt("y"));
	}

	/**
	 * Diffuse un message à tous les joueurs connectés sur la partie.
	 * 
	 * @param type le type du message à diffuser, servant d'identifiant pour l'interpétation côté client
	 * @param message le contenu du message
	 */
	public void broadCastMessage(String type, JsonObjectBuilder message) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject json = factory.createObjectBuilder()
				.add("type", type)
				.add("data", message).build();
		for(Session s : users.keySet()){
			if(s.isOpen()){
				try {
					s.getRemote().sendString(json.toString());
				} catch (IOException e) {}
			}
		}
	}
	
	/**
	 * Diffuse un signal à tous les joueurs connectés sur la partie.
	 * Contrairement à {@link ServerInstance#broadCastMessage(type,message)}, 
	 * 	le message envoyé n'a pas de contenu, et sert généralement à envoyer une information de changement d'état de la partie.
	 * 
	 * @param type le type du message à diffuser
	 */
	public void broadCastMessage(String type) {
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

	public Player getPlayer(Session user){
		return this.users.get(user);
	}

	public boolean containsPlayer(Session user) {
		return this.users.containsKey(user);
	}
	
	public void removePlayer(Session user){
		Player player = this.users.get(user);
		this.users.remove(user);
		this.carte.removePlayer(player);
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

	public int getTime() {
		return time;
	}
	
	public void setTime(int time){
		this.time = time;
	}

	@Deprecated
	public Map<Session, Player> getUsers() {
		return users;
	}
	
}