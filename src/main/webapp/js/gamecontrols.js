function placeArmies()
{
    var r = selectedTerritory.row;
    var c = selectedTerritory.col;
    var armies = 5;
    if(r >= 0 && c >= 0 && window.mapArray[r][c].type == "land")
    {

        $.ajax({
        async: false,
        url: "/risk/place_armies",
        type: "post",
        data: {"row":r, "col":c, "armies":armies,"player": window.currentPlayer},
        success: function(success) {
            success = $.parseJSON(success);
            if(success)
            {
                $.ajax({
                    async: false,
                    url: "/risk/advance_turn",
                    type: "get"
                });
            }
            updateTerritory(r,c);
            }});
    }
    fetchGameState();
    updatePlayerInfo();
    fetchGameState();
}

function advanceTurn(){
	$.ajax({
		async: false,
		url: "/risk/advance_turn",
		type: "get"
	});
 	updatePlayerInfo(); 

}

function fetchGameState()
{
    $.ajax({
        async: false,
        url: "/risk/get_game_state",
        type: "get",
        success: function(state) {
            window.gameState = state;
            updateControl();
        }});
}

function updateControl()
{
    if(window.gameState == "PRE_GAME")
    {
        $("#end_turn_button").hide();
    }
}
