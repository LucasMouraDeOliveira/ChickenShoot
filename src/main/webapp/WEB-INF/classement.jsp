<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<script src="../js/jquery-2.1.3.min.js"></script>

<h2>Classement de ChickenShoot</h2>
<div id ="classement">
	<table>
	<tr><td>Nom</td><td>Niveau</td><td>Expérience</td></tr>
	<c:forEach var="i" items="${classement}">
		<tr><td>${i.name}</td><td>${i.niveau}</td><td>${i.experience}</td></tr>
	</c:forEach>
	</table>
</div>


