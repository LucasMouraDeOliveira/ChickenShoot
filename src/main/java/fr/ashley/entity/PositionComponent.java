package fr.ashley.entity;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
	
	protected double x;
	
	protected double y;
	
	public PositionComponent(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

}
