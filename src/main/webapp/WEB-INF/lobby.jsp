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

<canvas id="mon_canvas" width = 640 height = 640>
		Your browser does not support canvas. How sad ...
</canvas>


<script>

	connect("join");
	
	var ajouterJoueur = function(joueur){
	
		var ul = $('#list_joueurs');
		ul.append($('<li> Joueur de type : ' + joueur.data.type + ' </li>'));
		
	}
	
	var demarrerPartie = function(){
		var div = $('#div_list');
		div.empty();
		gameID = "${ nomPartie }";
		chargerCanvas();
		var msg = {};
		msg.type = "demarrerPartie";
		msg.gameID = "${ nomPartie }";
		websocket.send(JSON.stringify(msg));			
	}
	
	
</script>