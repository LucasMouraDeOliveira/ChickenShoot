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


<div id="div_list">

	<h2>Création de la partie  : ${ nomPartie } </h2>
	
	<p>Liste des joueurs connectés :</p>
	
	<div id="players">
		<p class="cell">Liste des joueurs connectés : </p>
	</div>
	<br/><br/>
	<c:if test="${creator == 'true' }">	
		<input type="submit" onclick="demarrerPartie()"/>
	</c:if>
</div>


<script>

	var players = $('#players');
	var div = $('#div_list');
	var name;
	var type;
	var line;

	var connectToServer = function(){
		console.log(${creator});
		//players.append($('<tr><td>Joueur :</td><td>Type :</td></tr>'));
		if(${ creator }){
			connect("create","${ nomPartie }", "${ sessionScope.login }", ${nbJoueurs});
		}else{
			connect("join","${ nomPartie }", "${ sessionScope.login }", ${nbJoueurs});
			<c:forEach var="p" items="${players}">
				appendPlayer("${p.name}", "${p.type}");
			</c:forEach>		
		}	
	}
	
	var ajouterJoueur = function(joueur){
		appendPlayer(joueur.data.login, joueur.data.type);	
	}
	
	var retirerJoueur = function(joueur){
		$("#"+joueur.data.login).remove();
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
	
	function appendPlayer(name, type){
		var div = $("<div>").text(name + " : " + type).attr("class", "cell");
		players.append(div);
	}
	
	window.onload = function(){
		connectToServer();
	}
		
</script>