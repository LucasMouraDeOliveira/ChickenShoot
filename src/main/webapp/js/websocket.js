var websocket;

var connect = function(type){
		
	websocket = new WebSocket("ws://"+window.location.hostname+":8080/websocket");
	websocket.onopen = function(message){ 
		websocket.send(type);
		processOpen(message);
	};
	websocket.onclose = function(message){ processClose(message);};
	websocket.onmessage = function(message){ processMessage(message);};
	
}

var processOpen = function(message){
	setInterval("envoiPersoUpdate()", 50);
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
	}else{
		alert(json);
	}
}

function envoiPersoUpdate(){
	var msg = {};
	msg.type = "playerUpdate";
	msg.movement = movement;
	msg.tir = tir;
	msg.souris = souris;
	var json = JSON.stringify(msg);
	websocket.send(json);
}
