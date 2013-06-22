function showPlayerInfo(){
/* 	$("#player1").children().remove();
	$("#player2").children().remove();
	$("#player3").children().remove();
    $("#player4").children().remove();
	$("#player5").children().remove();
	$("#player6").children().remove(); */

	$.get("/risk/get_player_json", function(array){
		for (var r=0;r < array.length;r++){
			$("#p" + r).text(array[0].name);
		}
		
	});
}

