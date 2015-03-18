package fr.lordkadoc.entities;

import fr.lordkadoc.launcher.ServerInstance;
import fr.remygenius.armechasseur.Arbalete;

public class Chasseur extends Player {

	public Chasseur(ServerInstance instance,int x, int y) {
		super(x, y, 32, "Chasseur", new Arbalete(instance));
	}
}
