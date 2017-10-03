package fr.refactoring.game.component;

import com.badlogic.ashley.core.Component;

public class SpeedComponent implements Component {
	
	protected double speed;
	
	public SpeedComponent(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
