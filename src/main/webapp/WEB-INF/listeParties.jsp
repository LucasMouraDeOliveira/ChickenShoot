<h2>Liste des parties en attente de joueurs : </h2>

<table id="table_parties">

	<c:forEach items="${ parties }" var="element"> 
 	 <tr>
	    <td>${element}</td>
	    <td><button>Rejoindre</button></td>
 	 </tr>
</c:forEach>
	
</table>