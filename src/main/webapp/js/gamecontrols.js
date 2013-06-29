function placeArmies()
{
    var r = selectedTerritory.row;
    var c = selectedTerritory.col;
    var armies = $("#armies_textbox").val();
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
    updatePlayerInfo();
}
function advanceTurn(){
	$.ajax({
		async: false,
		url: "/risk/advance_turn",
		type: "get"
	});
 	updatePlayerInfo(); 
}
