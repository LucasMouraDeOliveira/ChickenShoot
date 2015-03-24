<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<script src="../js/canvas.js"></script>
<script src="../js/websocket.js"></script>
<script src="../js/jquery-2.1.3.min.js"></script>
<script src="../js/player_update.js"></script>	
<script src="../js/blocks.js"></script>
<script src="../js/images.js"></script>

<h2>Liste des parties en attente de joueurs : </h2>

<button onclick="actualiser();">Actualiser</button>

<div id="list_parties">
</div>

<script>

	var actualiser = function(){
		
		var div = $('#list_parties');
		div.empty();
		
		var table = $('<table>');
		var line;
		var name;
		<c:forEach var="i" items="${parties}">
			name = '${i}';
			line = $('<tr><td>'+name+'</td><td><button onclick="rejoindre(\'${i}\');">Rejoindre</button></td></tr>');
			line.appendTo(table);
		</c:forEach>
		
		table.appendTo(div);
		
	}
	
	var rejoindre = function(name){
		var canvas = $('<canvas id=\'mon_canvas\' width=640 height=640></canvas>');
		var div = $('#list_parties');
		div.empty();
		div.append(canvas);
		chargerCanvas();
		gameID = name;
		connect("join",name);
	}

</script>
