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

import fr.lordkadoc.entities.Chasseur;
import fr.lordkadoc.entities.Player;
import fr.lordkadoc.entities.Poulet;
import fr.lordkadoc.map.Carte;
import fr.remygenius.thread.ThreadBalle;
import fr.remygenius.thread.ThreadBombe;
import fr.remygenius.thread.ThreadExplosion;
import fr.remygenius.thread.ThreadGame;

public class ServerInstance {
	
	private Map<Session, Player> users;
	private Carte carte;

	private int maxUsers;

	public ServerInstance(int maxUsers){
		this.users = new HashMap<Session,Player>();
		this.carte = new Carte();
		this.maxUsers = maxUsers;
	}

	/**
	 * Ajoute un joueur à la partie, et démarre celle-ci si suffisament de joueurs sont connectés
	 * 
	 * @param user la session de l'utilisateur à ajouter
	 */
	public void ajouterJoueur(Session user){
		if(this.users.size() < this.maxUsers){
			Player p;
			if(this.carte.getNbChasseurs()<this.carte.getNbPoulets()){
				p = new Chasseur(this,100,100);			
			}
			else {
				p = new Poulet(this,50,50);
			}
			this.carte.getPlayers().add(p);
			this.users.put(user, p);
			
			if(clientsTousConnectes()){
				this.demarrerPartie();
			}
		}
	}

	/**
	 * 
	 * @return vrai si le nombre de clients connectés est égal à celui nécessaire pour commencer la partie
	 */
	public boolean clientsTousConnectes(){
		return this.users.size() == this.maxUsers;
	}

	public void demarrerPartie(){
		this.diffuserMessage("Carte",this.carte.getJSon());
		new ThreadGame(this,20).start();
		new ThreadBalle(this.getCarte().getBalles()).start();
		new ThreadBombe(this,this.getCarte().getBombes()).start();
		new ThreadExplosion(this.getCarte().getExplosions()).start();
	}

	public void recevoirMessage(Session user, String message){

		JsonReader jsonReader = Json.createReader(new StringReader(message));
		JsonObject object = jsonReader.readObject();

		if(object.getString("type").equals("playerUpdate")){

			JsonObject coords = object.getJsonObject("movement");
			boolean tir = object.getBoolean("tir");
			JsonObject souris = object.getJsonObject("souris");
			
			boolean[] c = new boolean[4];
			c[0] = coords.getBoolean("north");
			c[1] = coords.getBoolean("south");
			c[2] = coords.getBoolean("west");
			c[3] = coords.getBoolean("east");
			this.carte.deplacer(c,this.users.get(user));
			
			if(this.users.get(user) instanceof Chasseur && tir){
				this.users.get(user).attaqueChasseur(souris.getInt("x"),souris.getInt("y"));
			} else if(this.users.get(user) instanceof Poulet && tir){
				this.users.get(user).attaquePoulet(souris.getInt("x"),souris.getInt("y"));
			}
			
			this.users.get(user).pivoter(souris.getInt("x"),souris.getInt("y"));
		}
	}

	public void diffuserMessage(String type, JsonObjectBuilder message) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject json = factory.createObjectBuilder()
				.add("type", "Carte")
				.add("data", message).build();
		for(Session s : users.keySet()){
			try {
				s.getRemote().sendString(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Map<Session,Player> getUsers(){
		return this.users;
	}

	public Carte getCarte(){
		return this.carte;
	}

}
