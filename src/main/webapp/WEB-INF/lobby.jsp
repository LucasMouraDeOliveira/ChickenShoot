<script src="../js/websocket.js"></script>

<h2>Creation de la partie  : ${ nomPartie } </h2>

<p>Liste des joueurs :</p>

<ul id="list_joueurs">

</ul>

<button value="Lancer la partie" onclick="demarrerPartie();"/>

<script>
	connect("join");
	
	var demarrerPartie = function(){
	
		websocket.send("demarrerPartie");
	
	}
	
</script>