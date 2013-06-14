function placeTile(tile, x, y) {
  var tile = $("." + tile, "#templates").children().clone();
  tile.hexMapPosition(x, y).appendTo($('#hexmap'));

  return tile; 
}

/*
  Generate a new hex map
  TESTING ONLY
  Actual map will come from the servlet
*/
function generateHexMap(numColumns, numRows) {
  var cells = [];
  for (var x = 0; x < numColumns; x++) {
    for (var y = 0; y < numRows; y++) {
      var color = "blue";
      var cell = { "x": x, "y": y, "data": { "color": color } };
      cells.push(cell);
    }
  }
  return cells;
}

function populate(hexmap) {

  $('#hexmap').children().remove();
  
  for (index in hexmap) {
    var x = hexmap[index].x, y = hexmap[index].y;
    var data = hexmap[index].data;
    var color = data.color;

    var tile = placeTile(color, x, y);
    
    tile.bind("click", function(event) {
      var row = $(this).data("row");
      var column = $(this).data("column");
      alert("row: " + row + " col: " + column);    
    });

  }
}


jQuery.fn.hexMapPosition = function(row, column, additionalStyle) {
    this.data({"row": row, "column": column});
    
    var tile_width = this.attr("width");
    var tile_height = this.attr("height");

    var y_offset = tile_height / 2;
    var x_offset = tile_width / 4;

    var xpos = 0 + (row * (tile_width - x_offset));
    var ypos = 0 + (column * tile_height);

    ypos += 40; 

    if (row % 2 == 1) {
      ypos += y_offset;
    }

  
    var style = {"position": "absolute", 
                 "top": ypos, "left": xpos};
    
    return this.css(style);
  };


