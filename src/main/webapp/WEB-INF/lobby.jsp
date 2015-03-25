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
	<br/><br/>
	<c:if test="${creator == 'true' }">	
		<input type="submit" onclick="demarrerPartie()"/>
	</c:if>
</div>


<script>

	var table = $('#list_joueurs');
	var div = $('#div_list');
	

	var connectToServer = function(){
		console.log(${creator});
		if(${ creator }){
			connect("create","${ nomPartie }", "${ sessionScope.login }", ${nbJoueurs});
		}else{
			connect("join","${ nomPartie }", "${ sessionScope.login }", ${nbJoueurs});
			console.log("join");
			<c:forEach var="p" items="${players}">
				table.append($('<tr><td>Joueur : ' +"${p.name}" + ' </td><td> Type : ' + "${p.type}" + '</td></tr>'));
			</c:forEach>		
		}	
	}
	
	var ajouterJoueur = function(joueur){
		console.log(joueur.data);
		
		table.append($('<tr><td> Joueur : ' + joueur.data.login + ' </td><td> Type : ' + joueur.data.type + ' </td></tr>'));	
	}
	
	var demarrerPartie = function(){
		var msg = {};
		msg.type = "demarrerPartie";
		msg.gameID = "${ nomPartie }";
		websocket.send(JSON.stringify(msg));			
	}
	
	var afficherCanvas = function(){
		var cnv = $('<canvas id=mon_canvas width=872 height=640></canvas>');
		div.empty();
		div.append(cnv);
		chargerCanvas();
	}
	
	window.onload = function(){
		connectToServer();
	}
	
	
</script>