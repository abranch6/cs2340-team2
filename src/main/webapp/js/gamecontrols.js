function placeArmies()
{
    var r = window.selectedTerritory1.row;
    var c = window.selectedTerritory1.col;
    var armies = $("#armies_textbox1").val();
    
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
		$("#place_armies_button").show();
		$("#place_armies_info").show();
		$("#attack_button").hide();
		$("#attack_info").hide();
		$("#defend_info").hide();
		$("#selected_territory_2").hide();
		$("#place_num_armies").show();
		$("#place_attack_armies").hide();
    }
    else if(window.gameState == "GAME")
    {
        if(window.turnPhase == "PLACE_NEW_ARMIES")
        {
			$("#attack_button").hide();
            $("#end_turn_button").hide();
			$("#place_armies_button").show();
			$("#place_armies_info").show();
			$("#attack_info").hide();
			$("#defend_info").hide();
			$("#selected_territory_2").hide();
			$("#place_num_armies").show();
			$("#place_attack_armies").hide();
        }
        else if(window.turnPhase == "ATTACK")
        {
			$("#attack_button").show();
            $("#end_turn_button").show();
			$("#place_armies_button").hide();
			$("#place_armies_info").hide();
			$("#attack_info").show();
			$("#defend_info").show();
			$("#selected_territory_2").show();
			$("#place_num_armies").hide();
			$("#place_attack_armies").show();
        }
    }
}

function attack()
{
	var attackDieNum = $("#armies_textbox1").val();
	var defendDieNum =$("#armies_textbox2").val();
	var attackRow = selectedTerritory1.row;
	var attackCol = selectedTerritory1.col;
	var defendRow = selectedTerritory2.row;
	var defendCol = selectedTerritory2.col;
    $.ajax({
        async: true,
        url: "/risk/attack",
        type: "post",
        data: {"attackDieNum" : attackDieNum, "defendDieNum" : defendDieNum, "attackRow" : attackRow,
               "attackCol" : attackCol, "defendRow" : defendRow,"defendCol" : defendCol,},
        success: function(die) {
		console.log("die" + die);
            updatePlayerInfo();
            updateControl();
            updateTerritory(defendRow, defendCol);
            updateTerritory(attackRow, attackCol);
            deSelectTerritories(); 
			for(var i = 0; i < die.length; i++){
				for(var j = 0; j < die[i].length; j++){
					$("#spot_" + i + "_" + j).children().remove();
					var image;
						image = $('<img>', {
							src: "/risk/images/die" + die[i][j] + ".png"
						});
						if(die[i][j] != 0){
							$("#spot_" + i + "_" + j).append(image);
						}
				}
			}
        }});
}
