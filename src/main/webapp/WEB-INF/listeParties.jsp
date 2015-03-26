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
	
	<form method="post" action="/afficherParties">
		<input type="submit" value="Actualiser"/>
	</form>
	<br/>
</div>

<div id="list_parties"></div>
<br/>

<script>

	var div = $('#list_parties');

	var actualiser = function(){
		
		var vide = true;	
		var table = $('<table>');
		var line;
		var name;
		var button;
		
		line = $('<tr><td>Nom de la partie :</td><td>Nombre de joueur :</td></tr>');
		line.appendTo(table);
		
		<c:forEach var="i" items="${parties}">
			line = $('<tr><td>${i.nom}</td><td>'+${i.nbc}+'/'+${i.nbm}+
					'</td><td><form method="post" action="/rejoindreLobby"><input type="submit" value="Rejoindre"><input type="hidden" value="${i.nom}" name="gameid"/></form></td></tr>');
			line.appendTo(table);
			vide = false;
		</c:forEach>
		
		if(vide){
			table.append($('<tr><td>Aucune partie</td></tr>'));
		}
		
		div.empty();
		table.appendTo(div);
		
	}
	
	actualiser();

</script>
