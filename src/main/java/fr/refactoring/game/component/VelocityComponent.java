package fr.refactoring.game.component;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
	
	protected double dx, dy;
	
	public VelocityComponent(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public double getDx() {
		return dx;
	}
	
	public double getDy() {
		return dy;
	}
	
	public void setDx(double dx) {
		this.dx = dx;
	}
	
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	public boolean isVoid() {
		return this.dx == 0 && this.dy == 0;
	}

}
