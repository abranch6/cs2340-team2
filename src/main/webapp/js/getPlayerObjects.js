function showPlayerInfo(){
	$("#player1").children().remove();
	$("#player2").children().remove();
	$("#player3").children().remove();
    $("#player4").children().remove();
	$("#player5").children().remove();
	$("#player6").children().remove();
	$.getJSON("/risk/get_player_array",function(array){
		for(var r=0; r < array.length; r++){
			placePlayerInfo();
		}
	});
	$.get("/risk/get_player_json", function(array){
		for (var r=1; r < array.length+1; r++){
			var name = "player" + r
			var text = $('<p>', {
				text : name
			});
			
			text.appentTo($("#player1"));
		}
		$("#player1").text("Type: Land");
	});
}
function placePlayerInfo(){
	var div = document.getElementById("player1");
	div.textContent = "MY TEXT PRINTED!";
	var textl = div.textContent;
	text.appendTo($("#player1"));
} 