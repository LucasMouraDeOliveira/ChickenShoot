<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<script src="../js/canvas.js"></script>
<script src="../js/websocket.js"></script>
<script src="../js/jquery-2.1.3.js"></script>
<script src="../js/player_update.js"></script>	
<script src="../js/blocks.js"></script>
<script src="../js/images.js"></script>


<div id="div_list">

	<h2>Création de la partie  : ${ nomPartie } </h2>
	
	<p>Liste des joueurs connectés :</p>
	
	<table id="list_joueurs"></table>
	</br></br>	
	<input type="submit" onclick="demarrerPartie()"/>
	
</div>


<script>

	
	connect("create","${ nomPartie }", "${ sessionScope.login }", ${ nbJoueurs });
	
	var ajouterJoueur = function(joueur){
		console.log(joueur.data);
		var table = $('#list_joueurs');
		table.append($('<tr><td> Joueur : ' + joueur.data.login + ' </td><td> Type : ' + joueur.data.type + ' </td></tr>'));
		
	}
	
	var demarrerPartie = function(){
		var div = $('#div_list');
		var canvas = $('<canvas id=mon_canvas width=872 height=640></canvas>');
		div.empty();
		div.append(canvas);
		gameID = "${ nomPartie }";
		chargerCanvas();
		var msg = {};
		msg.type = "demarrerPartie";
		msg.gameID = "${ nomPartie }";
		websocket.send(JSON.stringify(msg));			
	}
	
	
</script>