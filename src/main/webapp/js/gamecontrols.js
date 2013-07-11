function placeArmies()
{
    var r = window.selectedTerritory1.row;
    var c = window.selectedTerritory1.col;
    var armies = $("#armies_textbox").val();

    console.log(r);
    if(r >= 0 && c >= 0 && window.mapArray[r][c].type == "land")
    {
        $.ajax({
            async: false,
            url: "/risk/place_armies",
            type: "post",
            data: {"row":r, "col":c, "armies":armies,"player": window.currentPlayer}
            });
        updateTerritory(r,c);
        deSelectTerritories(); 
    }
    fetchGameState();
    updatePlayerInfo();
    fetchTurnPhase();
}

function advanceTurn()
{
	$.ajax({
		async: false,
		url: "/risk/advance_turn",
		type: "get"
	});
 	updatePlayerInfo(); 
    fetchGameState();
    fetchTurnPhase();
    updateControl();
}

function fetchGameState()
{
    $.ajax({
        async: false,
        url: "/risk/get_game_state",
        type: "get",
        success: function(state) {
            window.gameState = state;
            $("#p_game_state").text("Game State: " + state);
            updateControl();
        }});
}

function fetchTurnPhase()
{
    $.ajax({
        async: false,
        url: "/risk/get_turn_phase",
        type: "get",
        success: function(state) {
            window.turnPhase = state;
            $("#p_turn_phase").text("Turn Phase: " + state);
            updateControl();
        }});
}

function updateControl()
{
    if(window.gameState == "PRE_GAME")
    {
        $("#end_turn_button").hide();
    }
    else if(window.gameState == "GAME")
    {
        if(window.turnPhase == "PLACE_NEW_ARMIES")
        {
            $("#end_turn_button").hide();
        }
        else if(window.turnPhase == "ATTACK")
        {
            $("#end_turn_button").show();
        }
    }
}

function attack(attackDieNum, defendDieNum, attackRow, attackCol, defendRow, defendCol)
{
    $.ajax({
        async: true,
        url: "/risk/attack",
        type: "post",
        data: {"attackDieNum" : attackDieNum, "defendDieNum" : defendDieNum, "attackRow" : attackRow,
               "attackCol" : attackCol, "defendRow" : defendRow,"defendCol" : defendCol,},
        success: function(die) {
            updatePlayerInfo();
            updateControl();
            updateTerritory(defendRow, defendCol);
            updateTerritory(attackRow, attackCol);
            deSelectTerritories(); 
        }});
}
