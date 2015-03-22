<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<h2>Formulaire d'inscription</h2>

<p>Merci de bien vouloir remplir les champs suivants : </p>

<c:out value="${ message_erreur }"></c:out> 

<form method="post" action="/inscription">
		<label>Nom d'utilisateur : </label><br/>
		<input type="text" value="${ login }" name="login"/><br/><br/>
		<label>Mot de passe :</label><br/>
		<input type="password" value="${ password }" name="password"/><br/><br/>
		<label>Confirmer mot de passe : </label><br/>
		<input type="password" value="${ cpassword }" name="cpassword"/><br/><br/>
		<label>Adresse mail : </label><br/>
		<input type="text" value="${ mail }" name="mail"><br/><br/>
		<input type="submit" value="Valider l'inscription"/>
</form>