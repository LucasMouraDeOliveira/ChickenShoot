package fr.remygenius.armepoulet;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;


public class Explosion {
	private int x;
	private int y;
	private int tailleMax;
	private int tailleActu;
	
	
	public Explosion(int x, int y, int tailleMax){
		this.x = x;
		this.y = y;
		this.tailleMax = tailleMax;
		this.tailleActu = 0;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getTailleMax() {
		return tailleMax;
	}

	public void setTailleMax(int tailleMax) {
		this.tailleMax = tailleMax;
	}

	public int getTailleActu() {
		return tailleActu;
	}

	public void setTailleActu(int tailleActu) {
		this.tailleActu = tailleActu;
	}
	
	public int calculerPourcentage(){
		double a = this.tailleActu;
		double b = this.tailleMax;
		return (int)(a/b*100);
	}
	
}
