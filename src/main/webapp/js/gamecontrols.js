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
        $("#fortify_button").hide();
		$("#place_armies_button").show();
		$("#st1_title").text("Place Armies");

		$("#attack_button").hide();
		$("#defend_info").hide();
		$("#selected_territory_2").hide();
        $("#st1_textbox_title").text("Number of armies to place:");

        $("#fortify_select").hide()
    }
    else if(window.gameState == "GAME")
    {
        if(window.turnPhase == "PLACE_NEW_ARMIES")
        {
			$("#attack_button").hide();
            $("#fortify_button").hide();
            $("#end_turn_button").hide();
			$("#place_armies_button").show();
		
            $("#st1_title").text("Place Armies");	
			$("#selected_territory_2").hide();
            $("#st1_textbox_title").text("Number of armies to place:");

            $("#fortify_select").hide()
        }
        else if(window.turnPhase == "ATTACK")
        {
			$("#attack_button").show();
            $("#fortify_button").hide();
            $("#end_turn_button").show();
			$("#place_armies_button").hide();

            $("#st1_title").text("Attack");
            $("#st2_title").text("Defend");
			$("#selected_territory_2").show();
            
            $("#st1_textbox_title").text("Number of armies to attack with:");

            $("#fortify_select").hide()
        }
        else if(window.turnPhase == "FORTIFY")
        {
            $("#attack_button").hide();
            $("#fortify_button").show();
            $("#end_turn_button").show();
            $("#place_armies_button").hide();

            $("#st1_title").text("Fortify Source");
            $("#st2_title").text("Fortify Destination");

            $("#selected_territory_2").show();
            $("#st1_textbox_title").text("Number of armies to move:");
            $("#place_defend_armies").hide();
            $("#fortify_select").show();  
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

function fortify()
{
    var armyNum = $("#armies_textbox1").val();
    var srcRow = selectedTerritory1.row;
    var srcCol = selectedTerritory1.col;
    var destRow = selectedTerritory2.row;
    var destCol = selectedTerritory2.col;
    $.ajax({
        async: true,
        url: "/risk/fortify",
        type: "post",
        data: {"armyNum" : armyNum, "srcRow" : srcRow,
               "srcCol" : srcCol, "destRow" : destRow,"destCol" : destCol,},
        success: function(die) {
            fetchGameState();
            fetchTurnPhase();
            updatePlayerInfo();
            updateControl();
            updateTerritory(srcRow, srcCol);
            updateTerritory(destRow, destCol);
            deSelectTerritories();
        }});
}
