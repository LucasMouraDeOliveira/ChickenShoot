package fr.remygenius.armechasseur;

import fr.lordkadoc.launcher.ServerInstance;

/**
 * Classe qui gère l'arbalette
 * @author remy
 *
 */

public class Arbalete extends ArmeChasseur {
	
	
	public Arbalete(ServerInstance instance, String nomTireur) {
		/**
		 * param1 : nom
		 * param2 : degat
		 * param3 : tempsderecharge
		 * param4 : munitions
		 * param5 : vitesseballe
		 */
		super(instance,nomTireur,"Arbalete", 10, 0.33, 100, 15);
	}
}
