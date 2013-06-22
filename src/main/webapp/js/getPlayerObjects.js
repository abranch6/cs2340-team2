function showPlayerInfo(){
	$.getJSON("/risk/get_player_array",function(array){
		for(var r=0; r < array.length; r++){
			placePlayerInfo();
		}
	}
}

function placePlayerInfo(array){
	var div = document.getElementById("textDiv");
	div.textContent = "MY TEXT PRINTED!";
	var text = div.textContent;
}