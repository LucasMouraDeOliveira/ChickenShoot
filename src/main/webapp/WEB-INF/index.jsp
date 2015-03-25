<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ChickenShoot : le jeu de tir au poulet</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css"/>
</head>
<body>

	<c:import url="header.jsp"/>	
		
	<c:choose>
		<c:when test="${sessionScope.connecte != 'true'}">
			<c:import url="login.jsp"></c:import>		
		</c:when>
		<c:otherwise>
			<c:import url="welcome.jsp"></c:import>
		</c:otherwise>
	</c:choose>
	
	
	<section>
	
		<c:import  url="nav.jsp"/>
	
		<article id="main_article">
			
			<c:choose>
				<c:when test="${ page == 'game' }">
					<c:import url="canvas.jsp"></c:import>
				</c:when>
				<c:when test="${ page == 'inscription'}">
					<c:import url="inscription.jsp"></c:import>
				</c:when>
				<c:when test="${ page == 'creation'}">
					<c:import url="creationPartie.jsp"></c:import>
				</c:when>
				<c:when test="${ page == 'lobby'}">
					<c:import url="lobby.jsp"></c:import>
				</c:when>
				<c:when test="${ page == 'listeParties'}">
					<c:import url="listeParties.jsp"></c:import>
				</c:when>
				<c:otherwise>
					<c:import url="acceuil.jsp"></c:import>
				</c:otherwise>
			</c:choose>
			
		</article>
	</section>
	
	<c:import url="footer.jsp"/>
</body>
</html>