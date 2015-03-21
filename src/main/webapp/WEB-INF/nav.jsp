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
				<form method="post" action="/deconnexion">
					<input type="submit" value="Déconnexion"/>
					<input type="hidden" value="acceuil" name="lien"/> 
				</form>
			</li>
		</ul>
</nav>
	