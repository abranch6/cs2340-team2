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
        data: {"row":r, "col":c, "armies":armies,"player": window.currentPlayer}
        });
        updateTerritory(r,c);    
    }
    updatePlayerInfo();
}
