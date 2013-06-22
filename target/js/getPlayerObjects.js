function showPlayerInfo(){
	$.get("/risk/get_player_array",function(){
		for(var r=0; r < array.length; r++){
			placePlayerInfo(array);
		}
	});
}

function placePlayerInfo(array){
	var div = document.getElementById("textDiv");
	div.textContent = "MY TEXT PRINTED!";
	var text = div.textContent;
} 