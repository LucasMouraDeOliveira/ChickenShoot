package fr.remygenius.armechasseur;

import fr.lordkadoc.launcher.ServerInstance;

/**
 * Classe qui gère l'arbalette
 * @author remy
 *
 */

public class Arbalete extends ArmeChasseur {
	
	
	public Arbalete(ServerInstance instance) {
		/**
		 * param1 : nom
		 * param2 : degat
		 * param3 : tempsderecharge
		 * param4 : munitions
		 * param5 : vitesseballe
		 */
		super(instance,"Arbalete", 8, 0.5, 100, 15);
	}
}
