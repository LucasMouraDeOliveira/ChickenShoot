package fr.refactoring.game.physics;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.Entity;

import fr.refactoring.game.GameMap;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.SizeComponent;
import fr.refactoring.game.component.VelocityComponent;

public interface ICollisionEngine {
	
	/**
	 * Retourne une map contenant les entités qui rentreront en collision avec l'entité courante si celle-ci se déplace.
	 * La map retournée contient, pour chaque entité la distance parcourue avant de rentrer en collision.
	 * 
	 * @param otherEntities  la liste des entités à vérifier pour la collision
	 * @param entity l'entité courante
	 * @param position la position actuelle de l'entité
	 * @param size la taille de l'entité
	 * @param velocity le vecteur de déplacement de l'entité
	 * 
	 * @return une map contenant la distance relative à chaque entité avec laquelle l'entité va rentrer en collision
	 */
	public Map<Entity, Point2D.Double> getCollisionWithEntities(List<Entity> otherEntities, Entity entity, PositionComponent position, SizeComponent size, VelocityComponent velocity);

	/**
	 * Retourne le point de collision d'une entité avec le terrain si l'entité se déplace.
	 * 
	 * @param gameMap le terrain de jeu
	 * @param entity l'entité
	 * @param position la position actuelle de l'entité
	 * @param size la taille de l'entité
	 * @param velocity le vecteur de déplacement de l'entité
	 * 
	 * @return le point de collision avec le décor et le point de la hitbox qui va rentrer en collision. Si pas de collision, retourne null
	 */
	public CollisionParameters getCollisionWithGameMap(GameMap gameMap, Entity entity, PositionComponent position, SizeComponent size, VelocityComponent velocity);
}
