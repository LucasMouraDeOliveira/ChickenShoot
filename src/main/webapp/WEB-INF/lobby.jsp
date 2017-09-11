<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<script src="../js/canvas.js"></script>
<script src="../js/websocket.js"></script>
<script src="../js/jquery-2.1.3.js"></script>
<script src="../js/player_update.js"></script>	
<script src="../js/blocks.js"></script>
<script src="../js/images.js"></script>

<link href="../css/lobby.css" rel="stylesheet">


<div id="div_list" oncontextmenu="return false;">

	<h2>Création de la partie  : ${ nomPartie } </h2>
	
	<div id="players">
		<div class="cell">Liste des joueurs connectés : </div>
	</div>
	<br/><br/>
	<c:if test="${creator == 'true' }">	
		<input type="submit" onclick="startGame()"/>
	</c:if>
	<form method="post" action="/afficherParties">
		<input type="submit" value="Retour"/>
	</form>
</div>


<script>

	var players = $('#players');
	var div = $('#div_list');
	var name;
	var type;
	var line;

	var connectToServer = function(){
		if(${ creator }){
			connect("create","${ nomPartie }", "${ sessionScope.login }", ${nbJoueurs});
		}else{
			connect("join","${ nomPartie }", "${ sessionScope.login }");
		}	
	}
	
	var listerJoueurs = function(joueurs){
		console.log(joueurs);
		var joueur;
		for(var i in joueurs.data){
			joueur = joueurs.data[i];
			console.log(joueur);
			appendPlayer(joueur.login,joueur.type);
		}
	}
	
	var ajouterJoueur = function(joueur){
		appendPlayer(joueur.data.login, joueur.data.type);	
	}
	
	var retirerJoueur = function(joueur){
		$("#"+joueur.data.login).remove();
	}
	
	var startGame = function(){
		var msg = {};
		msg.type = "start";
		msg.gameID = "${ nomPartie }";
		websocket.send(JSON.stringify(msg));			
	}
	
	var afficherCanvas = function(){
		var cnv = $('<canvas id=mon_canvas width=872 height=640></canvas>');
		div.empty();
		div.append(cnv);
		chargerCanvas();
	}
	
	function appendPlayer(name, type){
		var div = $("<div>").text(name + " : " + type).attr("class", "cell").attr("id", name);
		players.append(div);
	}
	
	function appendButton(name){
		var div = $("#"+name);
		console.log(div);
		var button = $("<button>").text("Changer de groupe");
		div.append(button);
	}
	
	window.onload = function(){
		connectToServer();
	}
		
</script>
