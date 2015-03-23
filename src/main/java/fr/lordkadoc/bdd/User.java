package fr.lordkadoc.bdd;

public class User {
	
	private final String nom;	
	private final String type;
	private int level;
	
	public User(String nom, String type, int level) {
		this.nom = nom;
		this.type = type;
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getNom() {
		return nom;
	}

	public String getType() {
		return type;
	}
	
	

}
