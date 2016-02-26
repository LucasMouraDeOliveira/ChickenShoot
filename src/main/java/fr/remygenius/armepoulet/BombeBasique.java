package fr.remygenius.armepoulet;

import fr.lordkadoc.entities.Poulet;
import fr.lordkadoc.launcher.ServerInstance;


public class BombeBasique extends ArmePoulet {
	
	public BombeBasique(Poulet poulet, ServerInstance instance) {
		super(poulet, instance,"Bombe", 10, 0.33, 50, 1.0, 80);
	}
}
