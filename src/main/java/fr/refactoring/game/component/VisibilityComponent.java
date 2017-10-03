package fr.refactoring.game.component;

import com.badlogic.ashley.core.Component;

public class VisibilityComponent implements Component {
	
	protected boolean visible;
	
	public VisibilityComponent() {
		this.visible = true;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
