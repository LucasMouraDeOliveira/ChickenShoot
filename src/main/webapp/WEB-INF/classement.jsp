<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<script src="../js/jquery-2.1.3.min.js"></script>

<h2>Classement de ChickenShoot</h2>
<div id ="classement">
	<table>
	<c:forEach var="i" items="${classement}">
		<tr><td>${i.name}</td><td>${i.experience}</td></tr>
	</c:forEach>
	</table>
</div>


