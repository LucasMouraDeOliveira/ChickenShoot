<nav>
		<ul>
			<li>
				<form method="post" action="/creationPartie">
					<input type="submit" value="Créer une partie"/>
 				</form>
			</li>
			<li>
				<form method="post" action="/chickenShoot">
					<input type="submit" value="Rejoindre une partie"/>
 				</form>
			</li>
			<li><button>Voir profil</button></li>
			<li><button>Options</button></li>
			<li>
				<form method="post" action="/formInscription">
					<input type="submit" value="Créer un compte"/>
					<input type="hidden" value="inscription" name="lien"/>
 				</form>
			</li>
			<li>
				<form method="post" action="/deconnexion">
					<input type="submit" value="Déconnexion"/>
					<input type="hidden" value="acceuil" name="lien"/> 
				</form>
			</li>
		</ul>
</nav>
	