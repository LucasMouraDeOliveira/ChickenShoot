package fr.chickenshoot.game.entities;

import javax.json.JsonObjectBuilder;

public interface Parsable {
	
	/**
	 * Ajoute les informations relatives à l'entité parsable dans le builder passé en paramètre.
	 * 
	 * @param builder le builder de l'entité
	 */
	public void getJSon(JsonObjectBuilder builder);

}
