package fr.lordkadoc.entities;

import fr.lordkadoc.launcher.ServerInstance;
import fr.remygenius.armepoulet.BombeBasique;

public class Poulet extends Player {

	public Poulet(ServerInstance instance,int x, int y) {
		super(x, y, 30, "Poulet", new BombeBasique(instance));
	}

}
