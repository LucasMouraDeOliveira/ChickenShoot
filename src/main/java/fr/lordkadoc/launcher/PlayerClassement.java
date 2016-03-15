package fr.lordkadoc.launcher;

public class PlayerClassement {
	
	private String name;
	
	private int niveau;
	
	private int experience;
	
	public PlayerClassement() {
		
	}
	
	public PlayerClassement(String name, int niveau, int experience) {
		this.name = name;
		this.niveau = niveau;
		this.experience = experience;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNiveau() {
		return niveau;
	}

	public int getExperience() {
		return experience;
	}
	
}
