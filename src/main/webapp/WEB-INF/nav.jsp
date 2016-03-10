<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="../js/jquery-2.1.3.min.js"></script>

<nav id="nav">
	<ul>
		<li>
			<form method="get" action="/chickenShoot">
				<input class="button" type="submit" value="Accueil" />
			</form>
		</li>
		<li>
			<form method="post" action="/createGame">
				<input class="button" type="submit" value="Créer une partie" /> <input
					type="hidden" value="creation" name="lien" />
			</form>
		</li>
		<li>
			<form method="post" action="/afficherParties">
				<input class="button" type="submit" value="Rejoindre une partie" />
			</form>
		</li>
		<li>
			<form method="post" action="/classement">
				<input class="button" type="submit" value="Classement" /> <input
					type="hidden" value="classement" name="lien" />
			</form>
		</li>
		<li>
			<form method="post" action="/options">
				<input class="button" type="submit" value="Options" /> <input
					type="hidden" value="options" name="lien" />
			</form>
		</li>
		<li>
			<form method="post" action="/formInscription">
				<input class="button" type="submit" value="Créer un compte" /> <input
					type="hidden" value="inscription" name="lien" />
			</form>
		</li>
		<li>
			<form method="post" action="/deconnexion">
				<input class="button" type="submit" value="Déconnexion" /> <input
					type="hidden" value="acceuil" name="lien" />
			</form>
		</li>
	</ul>
</nav>

<script>
	$(window).scroll(function() {
		if ($(this).scrollTop() > 157) {
			$('#nav').addClass("fixNavigation");
		} else {
			$('#nav').removeClass("fixNavigation");
		}
	});
</script>