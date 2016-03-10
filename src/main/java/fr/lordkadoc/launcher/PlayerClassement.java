package fr.lordkadoc.launcher;

public class PlayerClassement {
	
	private String name;
	
	private int experience;
	
	public PlayerClassement() {
		
	}
	
	public PlayerClassement(String name, int experience) {
		this.name = name;
		this.experience = experience;
	}
	
	public String getName() {
		return name;
	}

	public int getExperience() {
		return experience;
	}
	
}
