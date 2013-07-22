var players;
var currentPlayer;

function updatePlayerTurnInfo(){
    $.getJSON("/risk/get_player_turn_json", function(array){
        console.log(array);
        window.currentPlayer = array[0];
		for (var r = 0; r < array.length; r++)
        {
			$("#p" + (r + 1) + "_name").text("Name: " + window.players[array[r]].name);
            $("#p" + (r + 1) + "_armies").text("Armies: " + window.players[array[r]].armies);
            $("#p" + (r + 1) + "_color").text("Color: " + window.playerColors[array[r]]);
		}
		for (var s = array.length; s < 6; s++)
		{
			clearNonActivePlayerTurnInfo(s);
		}
	});
}

function updatePlayerInfo()
{
$.ajax({
     async: false,
     url: "/risk/get_player_json",
     dataType: "json",
     success: function(array){
        window.players = new Array(array.length);
        for (var r = 0; r < array.length; r++)
        {
        	if (array[r] != null)
        	{
	            var name = array[r].name;
	            var armies = array[r].armies;
	            var id = array[r].id;
	            window.players[r] = {"name":name, "armies":armies, "id":id};        		
        	}
        }
    }});
    updatePlayerTurnInfo();
    
}

function clearNonActivePlayerTurnInfo(index)
{
	$("#p" + (index + 1) + "_name").text("");
    $("#p" + (index + 1) + "_armies").text("");
    $("#p" + (index + 1) + "_color").text("");	
}

