package fr.refactoring.game;

import java.awt.Point;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import com.badlogic.ashley.core.Entity;

import fr.chickenshoot.game.entities.Parsable;
import fr.refactoring.game.component.PositionComponent;
import fr.refactoring.game.component.SizeComponent;
import fr.refactoring.game.system.Mapper;

public class GameMap implements Parsable {
	
	protected int[][] positions;
	
	protected int cellSize;
	
	protected GameInstance gameInstance;
	
	public GameMap(GameInstance gameInstance) {
		this.gameInstance = gameInstance;
		this.cellSize = 32;
		this.init(20);
	}
	
	@Deprecated
	private void init(int size){
		this.positions = new int[size][size];
		
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				//On crée les contours : on met des 2
				if(i==0 || i==size-1 || j==0 || j==size-1){
					positions[i][j] = 2;
				}
				else{
					//On initialise la premiere case à 9
					if(i==1 && j ==1){
						positions[i][j] = 9;
					}
					if(positions[i][j] == 9){
						int r = (int)(Math.random()*2);
						if(i == size -2){
							for(int k = 0; k<size-j; k++)
								positions[i][j+k] = 9;
						}
						else if(j == size -2){
							for(int k = 0; k<size-i; k++)
								positions[i+k][j] = 9;
						}
						else if(i!=size-1 && j!=size-1){
							if(r == 0) {
								positions[i+1][j] = 9;
							}
							else {
								positions[i][j+1] = 9;
							}
						}
					}
					else if(positions[i][j] != 9) {
								positions[i][j] = 0;
					}
				}
			}
		}
		
		//On place les obstacles aux endroit où il y a des 0
		//et on remplace les 0 par des " "
		boolean regen = false;
		for(int i =0; i<size; i++) {
			for(int j = 0; j<size; j++){
				if(i+1<size && j+1<size){
					if(positions[i][j] == 0 && Math.random()*100>90 && regen == false && i>=size/2 && j>=size/2){
						if(positions[i+1][j] == 0 && positions[i][j+1] == 0 && positions[i+1][j+1] == 0){
							regen = true;
							positions[i][j]=5;
							positions[i+1][j]=5;
							positions[i][j+1]=5;
							positions[i+1][j+1]=5;
						}
					}
				}
				if(Math.random()*100 > 98 && positions[i][j]==0){
					positions[i][j] = 3;
						for(int l =0; l<3;l++){
							if(i-l>1 && i+l<size-1 && j-l>1 && j+l<size-1){
								positions[i+l][j]=3;
								positions[i+l][j+l]=3;
								positions[i-l][j-l]=3;
								positions[i-l][j+l]=3;
								positions[i+l][j-l]=3;
								positions[i-l][j]=3;
								positions[i][j+l]=3;
								positions[i][j-l]=3;
							}
						}
				}
				else if(Math.random()*100 > 85 && positions[i][j]==0){
					positions[i][j] = 4;
				}
				else if(positions[i][j]!=2 && positions[i][j]!=3 && positions[i][j]!=5) {
					positions[i][j] = 1;
				}
			}
		}
	}
	
	public int getTypeForPosition(Point point) {
		try {
			return this.positions[point.x/this.cellSize][point.y/this.cellSize];
		} catch(ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}
	
	public boolean isEmpty(int cellule) {
		return cellule == 1 || cellule == 3 || cellule == 5;
	}
	
	public boolean isTree(int cellule) {
		return cellule == 3;
	}

	@Override
	public void getJSon(JsonObjectBuilder builder) {
		JsonArrayBuilder mapBuilder = Json.createArrayBuilder();
		JsonArrayBuilder line;
		// Crée le tableau de positions
		for(int i=0; i < positions.length; i++) { 
			line = Json.createArrayBuilder();
			for(int j = 0; j < positions[i].length; j++) {
				line.add(positions[i][j]);
			}
			mapBuilder.add(line);
		}	
		builder.add("map", mapBuilder);
	}
	
	/**
	 * Retourne vrai si une entité est en collision avec un ou plusieurs élements du terrain de jeu.
	 * @param entity l'entité
	 * @return true en cas de collision, false sinon
	 */
	public boolean collides(Entity entity) {
		PositionComponent pc = Mapper.positionMapper.get(entity);
		SizeComponent sc = Mapper.sizeMapper.get(entity);
		double x1 = pc.getX()-sc.getWidth()/2;
		double x2 = x1+sc.getWidth();
		double y1 = pc.getY()-sc.getHeight()/2;
		double y2 = y1+sc.getHeight();
		return !isEmpty(getTypeForPosition(new Point((int)(x1/this.cellSize), (int)(y1/this.cellSize))))
				|| !isEmpty(getTypeForPosition(new Point((int)(x1/this.cellSize), (int)(y2/this.cellSize))))
				|| !isEmpty(getTypeForPosition(new Point((int)(x2/this.cellSize), (int)(y1/this.cellSize))))
				|| !isEmpty(getTypeForPosition(new Point((int)(x2/this.cellSize), (int)(y2/this.cellSize))));
	}
	
	public int getTypeForCell(int x, int y) {
		try {
			return this.positions[x][y];
		} catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int getCellSize() {
		return this.cellSize;
	}
	
	public int getWidth() {
		return this.positions.length;
	}
	
	public int getHeight() {
		return this.positions[0].length;
	}

}
