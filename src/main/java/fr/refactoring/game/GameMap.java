package fr.refactoring.game;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import fr.chickenshoot.game.entities.Parsable;

public class GameMap implements Parsable {
	
	protected int[][] positions;
	
	protected GameInstance gameInstance;
	
	public GameMap(GameInstance gameInstance) {
		this.gameInstance = gameInstance;
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

}
