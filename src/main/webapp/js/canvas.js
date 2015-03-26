var canvas;
var ctx;
var tc = 32;


var chargerCanvas = function(){
	canvas = document.getElementById("mon_canvas");
	ctx = canvas.getContext("2d");
	window.addEventListener("keyup", release, false );
	window.addEventListener("keydown", press, false );
	window.addEventListener("mousedown", clique, false);
	window.addEventListener("mouseup", releaseClique, false);
	window.addEventListener("mousemove", motion, false);
	console.log('chargement du canvas terminé');
}

var drawInfosJoueur = function(joueurTrouve,player, time, players){
	if(joueurTrouve){
		if(player.vie>0){
			ctx.fillStyle = "grey"; 
			ctx.font = '15pt Calibri'; 
			ctx.fillStyle = "blue"; 
			ctx.fillText(player.login,650, 120); 
			ctx.fillStyle = "black"; 
			ctx.fillText("Temps restant : " + time,650, 40);
			ctx.fillText("Munitions : " + player.munitions,650, 140); 
			ctx.fillText("Arme : " + player.arme,650, 160); 
			ctx.fillText("type : " + player.type,650, 180);
		}
	}
	else {
			ctx.drawImage(imgFondInfos,640,0); 
			ctx.fillStyle = "blue"; 
			ctx.fillText(pseudo + " : mort",650, 120);
			ctx.fillStyle = "black"; 
			ctx.fillText("Temps restant : " + time,650, 40);
		}
	ctx.fillStyle = "green";
	ctx.fillText("Joueurs en vie :",650, 220);
	for(var i=0;i<players.length;i++){
		player = players[i];
		ctx.fillStyle = "black";
		ctx.fillText(player.login + " ( " + player.vie + " )",650, 240+20*i);
	}
}

var afficherCarte = function(carte){	
	var map = carte.data.carte;
	var players = carte.data.players;	
	var balles = carte.data.balles;
	var bombes = carte.data.bombes;
	var explosions = carte.data.explosions;
	
	for(var i=0;i<map.length;i++){
		for(var j=0;j<map[i].length;j++){
			switch(map[i][j]){
			case BLOCK_GRASS:
				ctx.drawImage(imgGrass,i*tc,j*tc);
				break;
			case BLOCK_WALL:
				ctx.drawImage(imgWall,i*tc,j*tc);
				break;
			case BLOCK_TREE:
				ctx.drawImage(imgGrass,i*tc,j*tc);
				break;
			case BLOCK_ROCK:
				ctx.drawImage(imgGrass,i*tc,j*tc);
				ctx.drawImage(imgStone,i*tc,j*tc);
				break;
			}
		}
	}
	
	var player;
	for(var i=0;i<players.length;i++){
		player = players[i];
		if(player.type == "Poulet"){
			drawRotatedImage(player.x,player.y,player.angle,imgPoulet);
			if(player.barreDeVieVisible == true)
				drawBarreDeVie(player);
		}
	}
	
	//Pour que les persos puissent se cacher sous les arbres on les dessinent après
	for(var i=0;i<map.length;i++){
		for(var j=0;j<map[i].length;j++){
			switch(map[i][j]){
			case BLOCK_TREE:
				ctx.drawImage(imgTree,i*tc,j*tc);
				break;
			}
		}
	}
	
	for(var i=0;i<players.length;i++){
		player = players[i];
		if(player.type == "Chasseur"){
			drawRotatedImage(player.x,player.y,player.angle,imgChasseur);
			drawBarreDeVie(player);
		}
	}
	
	var bombe;
	for(var i=0;i<bombes.length;i++){
		bombe = bombes[i];
		ctx.drawImage(imgBombe,bombe.x-imgBombe.width/2,bombe.y-imgBombe.height/2);
	}
	
	var balle;
	for(var i=0;i<balles.length;i++){
		balle = balles[i];
		drawRotatedImage(balle.x,balle.y,balle.angle,imgFleche);
	}
	
	var explosion;
	for(var i=0;i<explosions.length;i++){
		explosion = explosions[i];
		if(explosion.pourcentage < 30){
			var blue = 255-(8*explosion.pourcentage);
			ctx.fillStyle = 'rgb(255,255,'+blue+')';
		}else if(explosion.pourcentage < 71){
			var green = 255-(3*explosion.pourcentage);
			ctx.fillStyle = 'rgb(255,'+green+',0)';
		}else{
			ctx.fillStyle = "black";
		}

		for(var j=0;j<200;j++){
			dessinerParticuleAleatoire(explosion);
		}
	}
	
	ctx.drawImage(imgFondInfos,640,0);
	var joueurTrouve = false;
	for(var i=0;i<players.length;i++){
		var playertmp = players[i];
		if(playertmp.login === pseudo){
			joueurTrouve = true;
			player = players[i]
		}
	}
	drawInfosJoueur(joueurTrouve,player,carte.data.time,players);
	
}

var dessinerParticuleAleatoire = function(explosion){
	var x = Math.floor(Math.random()*2*explosion.taille)-explosion.taille+explosion.x;
	var y = Math.floor(Math.random()*2*explosion.taille)-explosion.taille+explosion.y;
	if(dist(x,y,explosion.x,explosion.y) < explosion.taille){
		ctx.beginPath();
		ctx.arc(x,y,6,0,2*Math.PI);
		ctx.fill();
	}
}

var dist = function(x1,y1,x2,y2){
	var x = Math.abs(x1-x2);
	var y = Math.abs(y1-y2);
	return Math.sqrt(x*x+y*y);
}

var drawRotatedImage = function(x,y,angle,img){
	ctx.save(); 
	ctx.translate(x, y);
	ctx.rotate(angle);
	ctx.drawImage(img, -(img.width/2), -(img.height/2));
	ctx.restore();
}

var drawBarreDeVie = function(player){
	ctx.fillStyle = "black";
	ctx.strokeStyle = "black";
	ctx.fillRect(player.x-25,player.y-50,50,10);
	var pourcentageVita = (player.vie*100)/player.vieInitiale;
	
	if(pourcentageVita>player.vieInitiale*0.6)
		ctx.fillStyle = "#45E600";
	else if(pourcentageVita<=player.vieInitiale*0.6 && pourcentageVita>player.vieInitiale*0.2)
		ctx.fillStyle = "#F3AA00";
	else
		ctx.fillStyle = "#FF0000";
	
	ctx.fillRect(player.x-25,player.y-50,pourcentageVita/2,10);
	ctx.strokeRect(player.x-25,player.y-50,50,10);
	ctx.fillStyle = "black";
	ctx.fillText(player.login,player.x-25, player.y-55);
}
