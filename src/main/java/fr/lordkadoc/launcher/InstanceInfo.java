package fr.lordkadoc.launcher;

public final class InstanceInfo {
	
	private final String nom;
	private final int nbc;
	private final int nbm;
	
	public InstanceInfo(String nom, int nbc, int nbm) {
		this.nom = nom;
		this.nbc = nbc;
		this.nbm = nbm;
	}

	public String getNom() {
		return nom;
	}

	public int getNbc() {
		return nbc;
	}

	public int getNbm() {
		return nbm;
	}

}
