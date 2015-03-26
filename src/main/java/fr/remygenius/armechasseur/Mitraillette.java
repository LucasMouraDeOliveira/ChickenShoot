package fr.remygenius.armechasseur;

import fr.lordkadoc.launcher.ServerInstance;

public class Mitraillette extends ArmeChasseur{

	public Mitraillette(ServerInstance instance, String nomTireur) {
		super(instance, nomTireur, "Mitraillette", 5, 0.2, 100, 20);
	}

}
