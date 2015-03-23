<script src="../js/websocket.js"></script>
<script src="../js/jquery-2.1.3.js"></script>

<h2>Creation de la partie  : ${ nomPartie } </h2>

<p>Liste des joueurs :</p>

<ul id="list_joueurs">

</ul>

<button onclick="demarrerPartie();">Lancer la partie</button>

<script>

	
	var demarrerPartie = function(){
		alert("démarrage de la partie");
		var msg = {};
		msg.type = "demarrerPartie";
		msg.gameID = "${ nomPartie }";
		websocket.send(JSON.stringify(msg));	
	}
	
	var ajouterJoueur = function(joueur){
	
		alert(joueur);
		var ul = $('#list_joueurs');
		ul.append($('<li> Joueur de type : ' + joueur.data.type + ' </li>'));
		
	}
	
	connect("join");
	
</script>