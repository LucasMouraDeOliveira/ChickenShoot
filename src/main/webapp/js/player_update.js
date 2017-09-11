var movement = {};
movement.north = false;
movement.south = false;
movement.west = false;
movement.east = false;
var tir = false;
var explode = false;
var souris = {};
souris.x = 0;
souris.y = 0;


function clique(event){
	switch(event.which){
	case 1:		
		tir = true;
		break;
	case 3:
		event.preventDefault();
		explode = true;
		break;
	}
}

function releaseClique(event){
	switch(event.which){
	case 1:		
		tir = false;
		break;
	case 3:
		explode = false;
		break;
	}
}

function press(event){
	switch(event.keyCode){
	case 65: // explose une bombe (A)
		explode = true;
		break;
	case 69: // tire ou pose une bombe (E)
		tir = true;
		break;
	case 39: // fleche droite
		event.preventDefault();
		movement.east = true;
		break;
	case 68: // droite (D)
		movement.east = true;
		break;
	case 37: // fleche gauche
		event.preventDefault();
		movement.west = true;
		break;
	case 81: // gauche (Q)
		movement.west = true;
		break;
	case 40: // fleche bas
		event.preventDefault();
		movement.south = true;
		break;
	case 83: // bas (S)
		movement.south = true;
		break;
	case 38: // fleche haut
		event.preventDefault();
		movement.north = true;
		break;
	case 90: // haut (Z)
		movement.north = true;
		break;
	}
}

function release(event){
	switch(event.keyCode){
	case 65:
		explode = false;
		break;
	case 69:
		tir = false;
		break;
	case 39: // fleche droite
	case 68: // droite (D)
		movement.east = false;
		break;
	case 37: // fleche gauche
	case 81: // gauche (Q)
		movement.west = false;
		break;
	case 40: // fleche bas
	case 83: // bas (S)
		movement.south = false;
		break;
	case 38: // fleche haut
	case 90: // haut (Z)
		movement.north = false;
		break;
	}
}

function motion(event){
	var x = event.pageX - canvas.offsetLeft;
	var y = event.pageY - canvas.offsetTop;
	souris.x = x;
	souris.y = y;
}
