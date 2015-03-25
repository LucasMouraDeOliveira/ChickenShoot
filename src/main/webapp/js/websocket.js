var websocket;
var gameID;
var pseudo;

var connect = function(type,gameID,login,nbJoueurs){
	
	console.log(gameID);
	websocket = new WebSocket("ws://"+window.location.hostname+":8080/websocket");
	websocket.onopen = function(message){ 
		var msg = {};
		msg.type = type;
		msg.gameID = gameID;
		msg.login = login;
		pseudo = login;
		if(type == "create"){
			msg.nbJoueurs = nbJoueurs;
		}
		websocket.send(JSON.stringify(msg));
		processOpen(message);
	};
	websocket.onclose = function(message){ processClose(message);};
	websocket.onmessage = function(message){ processMessage(message);};
	
}

var processOpen = function(message){
	console.log("connexion réussie");
}

var processClose = function(message){
	alert("Votre connexion au serveur s'est coupée. Essayez de recharger la page.");
}

var processError = function(message){
	console.log(message);
}

var processMessage = function(message){
	var json = JSON.parse(message.data);
	if(json.type == "Carte"){
		afficherCarte(json);
	}else if(json.type == "newPlayer"){
		console.log("Ajout d'un utilisateur");
		ajouterJoueur(json);
	}else if(json.type == "start"){
		setInterval("envoiPersoUpdate()", 50);
	}else{
		console.log("Message : " + json);
	}
}

function envoiPersoUpdate(){
	var msg = {};
	msg.type = "playerUpdate";
	msg.gameID = gameID;
	msg.movement = movement;
	msg.tir = tir;
	msg.souris = souris;
	var json = JSON.stringify(msg);
	websocket.send(json);
}

