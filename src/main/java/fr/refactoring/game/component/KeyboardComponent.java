package fr.refactoring.game.component;

import com.badlogic.ashley.core.Component;

public class KeyboardComponent implements Component {
	
	protected boolean[] moving;
	
	protected boolean shooting, exploding;

	protected int mouseX, mouseY;
	
	public KeyboardComponent() {
		this.moving = new boolean[4];
		this.mouseX = 0;
		this.mouseY = 0;
	}
	
	public boolean[] getMoving() {
		return moving;
	}
	
	public boolean isMoving(int direction) {
		try {
			return this.moving[direction];
		} catch(ArrayIndexOutOfBoundsException e) {
			//TODO log error
			return false;
		}
	}
	
	public void setMoving(boolean moving, int direction) {
		try {
			this.moving[direction] = moving;
		} catch(ArrayIndexOutOfBoundsException e) {
			//TODO log error
		}
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	
	public boolean isShooting() {
		return shooting;
	}
	
	public boolean isExploding() {
		return exploding;
	}
	
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	
	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

}
