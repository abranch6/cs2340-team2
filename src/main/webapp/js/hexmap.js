var playerColors = ["brown", "cyan","fushia","green","pepper","red","tangerine"];

function placeTile(color, number, r, c) {
    var tileImg = $("." + color, "#templates").children().clone();
    var divStyle = getTilePosition(r, c, tileImg);
    var idNum = r + "_" + c;
    var idNumText = idNum + "_text"

  
   
    var div = $('<div>', {
        id : idNum,
    });

    if(number >= 0)
    {
        var textDiv = $('<div>', {
            id : idNumText,
            text : number
        });
  
        var textStyle = getTextPosition(tileImg, textDiv);

        textDiv.css(textStyle).appendTo(div);
    }

    tileImg.appendTo(div)

    div.data({"row": r, "column": c}); //used to lookup the tile in window.mapArray on click
    div.css(divStyle).appendTo($('#hexmap'));

    return div; 
}

function updateSelectedTerritoryInfo()
{   
    var loc = window.selectedTerritory;
    if(loc.row >= 0 && loc.col >= 0)
    {
        if(window.mapArray[loc.row][loc.col].type == "land")
        {
            $("#s_t_type").text("Type: Land");
            $("#s_t_player").text("Controlling Player: " + window.mapArray[loc.row][loc.col].player);
            $("#s_t_armies").text("Armies: " + window.mapArray[loc.row][loc.col].armies);
        }
        else if(window.mapArray[loc.row][loc.col].type == "water")
        {
            $("#s_t_type").text("Type: Water");
            $("#s_t_player").text("");
            $("#s_t_armies").text("");
        }
    }
}
function updateTerritory(r,c)
{
    jQuery.post("/risk/update_territory", {"row":r, "col":c}, function(territory) {
        var armies = territory.armies;
        var loc = territory.location;
        var player = territory.playerId;
        window.mapArray[loc.row][loc.col].DOM.remove();
        placeTerritoryTile(player, armies, loc.row, loc.col); 
    });
}

function showHexMap() {
  $('#hexmap').children().remove();
  window.selectedTerritory = {"row":-1, "col":-1};
  jQuery.getJSON("/risk/get_js_map", function(array) {
    window.mapArray = create2DArray(array.length);
    var cells = [];
    for(var r = 0; r < array.length; r++)
    {
        for(var c = 0; c < array[r].length; c++)
        {
            placeIntTile(array[r][c], r, c);          
        }
    }
  });
}

function placeIntTile(value, r, c)
{ 
    var color;
    var number;
    if(value == 1)
    {
         color = "blue";
         number = -1;
    }
    else if(value == 2)
    {
        color = "brown";
        number = 0;
    }
    if(value != 0)
    {
        var tile = placeTile(color,number,r,c);
            if(value == 1)
            {
                window.mapArray[r][c] = {"DOM":tile, "type": "water", "row":r, "col":c};
            }
            else
            {
                window.mapArray[r][c] = {"DOM":tile, "player":0, "type":"land", "armies":0, "row":r, "col":c};
            }
            tile.bind("click", function(event) {
              var row = $(this).data("row");
              var column = $(this).data("column");
              window.selectedTerritory = {"row":row, "col":column};
              updateSelectedTerritoryInfo();
            });
    }

}

function placeTerritoryTile(player, armies, r, c)
{
    color = window.playerColors[player];
    var tile = placeTile(color,armies,r,c);
    window.mapArray[r][c] = {"DOM":tile, "type":"land", "player":player, "armies":armies, "row":r, "col":c};
    updateSelectedTerritoryInfo();


    tile.bind("click", function(event) {
        var row = $(this).data("row");
        var column = $(this).data("column");
        window.selectedTerritory = {"row":row, "col":column};
        updateSelectedTerritoryInfo();
    });
}

function getTextPosition(image, text)
{
    var tile_width = image.attr("width");
    var tile_height = image.attr("height");
    ypos = (tile_height / 4);
    xpos = (tile_width / 2) - 16;

    var style = {
                    "position": "absolute",
                    "top": ypos, "left": xpos, 
                    "width": 32, "text-align":"center"
                };

    return style;
}
function getTilePosition(row, col, image) 
{
    
    var tile_width = image.attr("width");
    var tile_height = image.attr("height");

    var y_offset = tile_height / 4;
    var x_offset = tile_width / 2;

    var xpos = 0 + (col * tile_width);
    var ypos = 0 + (row * (tile_height - y_offset));

    ypos += 40; 

    if (row % 2 == 1) 
    {
        xpos += x_offset;
    }

  
    var style = {"position": "absolute", 
                 "top": ypos, "left": xpos};
    return style;
}


function create2DArray(rows)
{
    var arr = [];

    for (var i=0;i<rows;i++) 
    {
        arr[i] = [];
    }  

    return arr;
}
