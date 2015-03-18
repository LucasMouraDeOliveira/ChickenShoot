var websocket;

var connect = function(){
	websocket = new WebSocket("ws://frene17:9876/");
	websocket.onopen = function(message){ 
		websocket.send("join");
		processOpen(message);
	};
	websocket.onclose = function(message){ processClose(message);};
	websocket.onmessage = function(message){ processMessage(message);};
}

var create = function(){
	websocket = new WebSocket("ws://frene17:9876/");
	websocket.onopen = function(message){ 
		websocket.send("create");
		processOpen(message);
	};
	websocket.onclose = function(message){ processClose(message);};
	websocket.onmessage = function(message){ processMessage(message);};
}

var processOpen = function(message){
	setInterval("envoiPersoUpdate()", 50);
}

var processClose = function(message){
}

var processError = function(message){
}

var processMessage = function(message){
	var json = JSON.parse(message.data);
	if(json.type == "Carte"){
		afficherCarte(json);
	}else{
		alert(message.data);
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
