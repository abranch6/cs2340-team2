var currentTurnId = 1;
function placeArmies()
{
    var r = selectedTerritory.row;
    var c = selectedTerritory.col;
    var armies = 5;
    if(r >= 0 && c >= 0 && window.mapArray[r][c].type == "land")
    {
        jQuery.post("/risk/place_armies", {"row":r, "col":c, "armies":armies,"player": window.currentTurnId}, function(success) {
            success = $.parseJSON(success);
            if(success)
            {
                jQuery.get("/risk/advance_turn",function(id) {
                    window.currentTurnId = id;
                });
            }
            updateTerritory(r,c);
        });
    }
}
