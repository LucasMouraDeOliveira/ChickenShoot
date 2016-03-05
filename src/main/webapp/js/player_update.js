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
	if(event.which == 1){		
		tir = true;
	}
}

function releaseClique(event){
	if(event.which == 1){		
		tir = false;
	}
}

function press(event){
	switch(event.keyCode){
	case 65:
		explode = true;
		break;
	case 68: // droite
		movement.east = true;
		break;
	case 81: // gauche
		movement.west = true;
		break;
	case 83: // bas
		movement.south = true;
		break;
	case 90: // haut
		movement.north = true;
		break;
	}
}

function release(event){
	switch(event.keyCode){
	case 65:
		explode = false;
		break;
	case 68: // droite = d
		movement.east = false;
		break;
	case 81: // gauche = q
		movement.west = false;
		break;
	case 83: // bas = s
		movement.south = false;
		break;
	case 90: // haut = z
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