<nav>
		<ul>
			<li>
				<form method="post" action="/createGame">
					<input class="button" type="submit" value="Creer une partie"/>
					<input type="hidden" value="creation" name="lien"/>
 				</form>
			</li>
			<li>
				<form method="post" action="/rejoindrePartie">
					<input class="button" type="submit" value="Rejoindre une partie"/>
 				</form>
			</li>
			<li><button class="button">Voir profil</button></li>
			<li><button class="button">Options</button></li>
			<li>
				<form method="post" action="/formInscription">
					<input class="button" type="submit" value="Creer un compte"/>
					<input type="hidden" value="inscription" name="lien"/>
 				</form>
			</li>
			<li>
				<form method="post" action="/deconnexion">
					<input class="button" type="submit" value="Deconnexion"/>
					<input type="hidden" value="acceuil" name="lien"/> 
				</form>
			</li>
		</ul>
</nav>
	