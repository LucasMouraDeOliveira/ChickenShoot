<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<script src="../js/canvas.js"></script>
<script src="../js/websocket.js"></script>
<script src="../js/jquery-2.1.3.min.js"></script>
<script src="../js/player_update.js"></script>	
<script src="../js/blocks.js"></script>
<script src="../js/images.js"></script>

<div id ="actualiser">
	<h2>Liste des parties en attente de joueurs : </h2>
	
	<form method="post" action="/rejoindrePartie">
		<input type="submit" value="Actualiser"/>
	</form>
	</br>
</div>

<div id="list_parties"></div>
</br>

<script>

	var actualiser = function(){
		
		var div = $('#list_parties');
		var vide = true;
		div.empty();
		
		var table = $('<table>');
		var line;
		var name;
		
		<c:forEach var="i" items="${parties}">
			console.log('${i.nbc}');
			line = $('<tr><td>${i.nom}</td><td>Joueurs connectés : '+${i.nbc}+'/'+${i.nbm}+'</td><td><button onclick="rejoindre(\'${i.nom}\');nettoyer();">Rejoindre</button></td></tr>');
			line.appendTo(table);
			vide = false;
		</c:forEach>
		
		if(vide){
			table.append($('<tr><td>Aucune partie</td></tr>'));
		}
		
		table.appendTo(div);
		
	}
	
	var rejoindre = function(id){
		var canvas = $('<canvas id=\'mon_canvas\' width=872 height=640></canvas>');
		var div = $('#list_parties');
		div.empty();
		div.append(canvas);
		chargerCanvas();
		gameID = id;
		login = "${sessionScope.login}";
		connect("join",id,login);
	}
	
	var nettoyer = function(){
		var div = $('#actualiser');
		div.empty();
	}
	
	actualiser();

</script>
