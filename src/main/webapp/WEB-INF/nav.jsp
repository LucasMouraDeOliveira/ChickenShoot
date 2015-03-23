<nav>
		<ul>
			<li>
				<form method="post" action="/creationPartie">
					<input type="submit" value="Creer une partie"/>
					<input type="hidden" value="creation" name="lien"/>
 				</form>
			</li>
			<li>
				<form method="post" action="/rejoindrePartie">
					<input type="submit" value="Rejoindre une partie"/>
 				</form>
			</li>
			<li><button>Voir profil</button></li>
			<li><button>Options</button></li>
			<li>
				<form method="post" action="/formInscription">
					<input type="submit" value="Creer un compte"/>
					<input type="hidden" value="inscription" name="lien"/>
 				</form>
			</li>
			<li>
				<form method="post" action="/deconnexion">
					<input type="submit" value="Deconnexion"/>
					<input type="hidden" value="acceuil" name="lien"/> 
				</form>
			</li>
		</ul>
</nav>
	