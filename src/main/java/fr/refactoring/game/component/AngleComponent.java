package fr.refactoring.game.component;

import com.badlogic.ashley.core.Component;

public class AngleComponent implements Component {

	protected double angle;
	
	public AngleComponent(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
