<nav>
		<ul>
			<li>
				<form method="post" action="/game">
					<input type="submit" value="Lancer une partie"/>
					<input type="hidden" value="game" name="lien"/>
 				</form>
			</li>
			
			<li><button>Voir profil</button></li>
			<li><button>Options</button></li>
			<li>
				<form method="post" action="/formInscription">
					<input type="submit" value="Cr�er un compte"/>
					<input type="hidden" value="inscription" name="lien"/>
 				</form>
			</li>
			<li>
				<form method="post" action="/deconnexion">
					<input type="submit" value="D�connexion"/>
					<input type="hidden" value="acceuil" name="lien"/> 
				</form>
			</li>
		</ul>
</nav>
	