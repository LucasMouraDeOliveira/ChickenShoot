package fr.remygenius.armechasseur;

import fr.lordkadoc.launcher.ServerInstance;

/**
 * Classe qui gere le fusil
 * @author remy
 *
 */

public class Fusil extends ArmeChasseur {

	public Fusil(ServerInstance instance) {
		
		/**
		 * param1 : nom
		 * param2 : degat
		 * param3 : tempsderecharge
		 * param4 : munitions
		 * param5 : vitesseballe
		 */
		super(instance,"Fusil", 10, 1, 10, 20);
		// TODO Auto-generated constructor stub
	}
}
