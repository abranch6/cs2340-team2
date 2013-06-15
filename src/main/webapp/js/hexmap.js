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
function generateHexMap(numRows, numCols) {
  var cells = [];
  for (var r = 0; r < numRows; r++) {
    for (var c = 0; c < numCols; c++) {
      var color = "blue";
      var cell = { "row": r, "col": c, "data": { "color": color } };
      cells.push(cell);
    }
  }
  return cells;
}

function populate(hexmap) {

  $('#hexmap').children().remove();
  
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


