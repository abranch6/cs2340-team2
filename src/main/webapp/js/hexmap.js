
function placeTile(tile, x, y) {
  var tile = $("." + tile, "#templates").children().clone();
  tile.hexMapPosition(x, y).appendTo($('#hexmap'));
  console.log("place");
  return tile; 
}

function showHexMap() {
  
  jQuery.getJSON("/risk/get_js_map", function(array) {
	var cells = [];
  	for(var r = 0; r < array.length; r++)
  	{
    		for(var c = 0; c < array[r].length; c++)
    		{
			if(array[r][c] == 1)
			{
				var color = "blue";
				var cell = { "row": r, "col": c, "data": { "color": color } };
      				cells.push(cell);
			}
			else if(array[r][c] == 2)
			{
				var color = "brown";
                                var cell = { "row": r, "col": c, "data": { "color": color } };
                                cells.push(cell);

			}
    		}
  	}
	populate(cells);
  });
}

function populate(hexmap) {
  $('#hexmap').children().remove();
  console.log(hexmap); 
  for (index in hexmap) {
    var r = hexmap[index].row, c = hexmap[index].col;
    var data = hexmap[index].data;
    var color = data.color;

    var tile = placeTile(color, r, c);
    
    tile.bind("click", function(event) {
      var row = $(this).data("row");
      var column = $(this).data("column");
      alert("row: " + row + " col: " + column);    
    });

  }
}


jQuery.fn.hexMapPosition = function(row, col, additionalStyle) {
    this.data({"row": row, "column": col});
    
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


