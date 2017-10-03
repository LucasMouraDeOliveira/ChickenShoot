package fr.refactoring.game.component;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
	
	protected double x, y;
	
	public PositionComponent(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

}