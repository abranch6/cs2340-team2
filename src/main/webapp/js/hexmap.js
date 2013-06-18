function placeTile(color, r, c) {
  var tile = $("." + color, "#templates").children().clone();
  tile.positionTile(r, c).appendTo($('#hexmap'));
  return tile; 
}

function showHexMap() {
  $('#hexmap').children().remove(); 
  jQuery.getJSON("/risk/get_js_map", function(array) {
	window.mapArray = create2DArray(array.length);
	var cells = [];
  	for(var r = 0; r < array.length; r++)
  	{
    		for(var c = 0; c < array[r].length; c++)
    		{
      				placeJSMapTile(array[r][c], r, c);			
    		}
  	}
  });
}

function placeJSMapTile(var value, var row, var col)
{
	var color;
        if(value == 1)
        {
          color = "blue";
        }
        else if(value == 2)
        {
          color = "brown";
        }
	if(value != 0)
	{
		var tile = placeTile(color,row,col);
        	window.mapArray[r][c] = {"DOM":tile, "row":r, "col":c};
        	tile.bind("click", function(event) {
        	  var row = $(this).data("row");
        	  var column = $(this).data("column");
 	  	  console.log(window.mapArray[r][c]);
       		})
	}

}

function placeTerritoryMapTile()
{

}

jQuery.fn.positionTile = function(row, col, additionalStyle) {
    this.data({"row": row, "column": col}); //used to lookup the tile in window.mapArray on click
    
    var tile_width = this.attr("width");
    var tile_height = this.attr("height");

    var y_offset = tile_height / 4;
    var x_offset = tile_width / 2;

    var xpos = 0 + (col * tile_width);
    var ypos = 0 + (row * (tile_height - y_offset));

    ypos += 40; 

    if (row % 2 == 1) {
      xpos += x_offset;
    }

  
    var style = {"position": "absolute", 
                 "top": ypos, "left": xpos};
    return this.css(style);
  };

function create2DArray(rows) {
  var arr = [];

  for (var i=0;i<rows;i++) {
     arr[i] = [];
  }

  return arr;
}
