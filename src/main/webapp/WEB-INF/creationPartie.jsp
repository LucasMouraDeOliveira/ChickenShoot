<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2>Création de partie</h2>

${ erreur }

<form method="post" action="/create">
	<label>Nom de la partie : </label><br/>
	<input type="text" name="nomPartie""/><br/><br/>	
	<label>Nombre de joueurs : </label><br/>
	<input type="text" name="nbJoueurs"/><br/><br/>
	<input type="submit" value="Créer la partie"/>
</form>