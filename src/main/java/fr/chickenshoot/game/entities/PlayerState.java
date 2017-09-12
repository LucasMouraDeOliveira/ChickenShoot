package fr.chickenshoot.game.entities;

public class PlayerState {
	
	protected boolean[] moving;
	
	protected boolean shooting;
	
	protected double angle;
	
	public PlayerState() {
		this.angle = 0;
		this.shooting = false;
		this.moving = new boolean[4];
	}
	
	/**
	 * Renvoie vrai si le joueur est en train de se déplacer dans la direction spécifiée.
	 * 
	 * @param direction la direction à tester
	 * 
	 * @return vrai si le joueur se déplace dans cette direction
	 */
	public boolean isMoving(int direction) {
		try{
			return moving[direction];
		}catch(ArrayIndexOutOfBoundsException e){
			return false;
		}
	}
	
	/**
	 * Met à jour la valeur de déplacement (vrai ou faux) du joueur pour une direction.
	 * 
	 * @param direction la direction à mettre à jour
	 * @param value la nouvelle valeur de déplacement pour la direction
	 */
	public void setMoving(int direction, boolean value) {
		try{
			moving[direction] = value;
		}catch(Exception e){}
	}

	/**
	 * @return vrai si le joueur est en train de tirer.
	 */
	public boolean isShooting() {
		return shooting;
	}
	
	/**
	 * Met à jour la valeur de tir du joueur.
	 * 
	 * @param shooting si le joueur est en train de tirer ou non
	 */
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

}
