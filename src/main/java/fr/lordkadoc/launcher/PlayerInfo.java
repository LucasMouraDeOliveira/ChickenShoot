package fr.lordkadoc.launcher;

public final class PlayerInfo {
	
	private final String name;
	private final String type;
	
	public PlayerInfo(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
