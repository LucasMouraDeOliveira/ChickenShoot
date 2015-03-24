
<script src="../js/canvas.js"></script>
<script src="../js/websocket.js"></script>
<script src="../js/jquery-2.1.3.js"></script>
<script src="../js/player_update.js"></script>	
<script src="../js/blocks.js"></script>
<script src="../js/images.js"></script>


<div id="div_list">

	<h2>Creation de la partie  : ${ nomPartie } </h2>
	
	<p>Liste des joueurs :</p>
	
	<ul id="list_joueurs"></ul>
	
	<input type="submit" onclick="demarrerPartie()"/>
	
</div>


<script>

	
	connect("create","${ nomPartie }", "${ sessionScope.login }");
	
	var ajouterJoueur = function(joueur){
		console.log(joueur.data);
		var ul = $('#list_joueurs');
		ul.append($('<li> Joueur ' + joueur.data.login + ' de type : ' + joueur.data.type + ' </li>'));
		
	}
	
	var demarrerPartie = function(){
		var div = $('#div_list');
		var canvas = $('<canvas id=mon_canvas width=640 height=640></canvas>');
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