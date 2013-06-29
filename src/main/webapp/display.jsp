<html>
<head>

<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script type="text/javascript" src="js/hexmap.js"></script>
<script type="text/javascript" src="js/gamecontrols.js"></script>
<script type="text/javascript" src="js/player.js"></script>
<link rel="stylesheet" type="text/css" href="css/hexmap.css" />

<title>RISK Display Screen</title>
</head>

<body style="user-select: none;" onselectstart="return false;" onLoad="showHexMap(); updatePlayerInfo();" ondragstart="return false;" ondrop="return false;">

<div id=control_panel style="width:200px; background:#CCC; float:left;">
    <div id=selected_territory>
        <h3>Selected Territory</h3>
        <p id="s_t_type">No Territory Selected</p>
        <p id="s_t_player">Controlling Player:</p>
        <p id="s_t_armies">Armies:</p>
    </div>
	<p>
    <button onclick="placeArmies()">Place Armies</button>
	<button onclick="advanceTurn()">End Turn</button>
	Number of Armies: <input type="number" id="armies_textbox"><br></input>
    <h3>Turn Order</h3>
    <div id = "player1">
        <p id="p1_name"></p>
        <p id="p1_armies"></p>
		<p id="p1_color"></p>
    </div>
    <br/>
    <div id = "player2">
        <p id="p2_name"></p>
        <p id="p2_armies"></p>
		<p id="p2_color"></p>
    </div>
    <br/>
    <div id = "player3">
        <p id="p3_name"></p>
        <p id="p3_armies"></p>
		<p id="p3_color"></p>
    </div>
    <br/>
    <div id = "player4">
        <p id="p4_name"></p>
        <p id="p4_armies"></p>
		<p id="p4_color"></p>
    </div>
    <br/>
    <div id = "player5">
        <p id="p5_name"></p>
        <p id="p5_armies"></p>
		<p id="p5_color"></p>
    </div>
    <br/>
    <div id = "player6">
        <p id="p6_name"></p>
        <p id="p6_armies"></p>
		<p id="p6_color"></p>
    </div>
</div>

<div id="hexmap" style="float:left;"></div>

</body>




<div id="templates" class="hidden">
    <div class="tiles blue">
        <img src="images/tiles/hex-blue.png" width="40" height="40"/>
    </div>

    <div class="tiles brown">
        <img src="images/tiles/hex-brown.png" width="40" height="40"/>
    </div>

    <div class="tiles yellow">
        <img src="images/tiles/hex-yellow.png" width="40" height="40"/>
    </div>
    
    <div class="tiles cyan">
        <img src="images/tiles/hex-cyan.png" width="40" height="40"/>
    </div>

    <div class="tiles green">
        <img src="images/tiles/hex-green.png" width="40" height="40"/>
    </div>

    <div class="tiles red">
        <img src="images/tiles/hex-red.png" width="40" height="40"/>
    </div>

    <div class="tiles tangerine">
        <img src="images/tiles/hex-tangerine.png" width="40" height="40"/>
    </div>

    <div class="tiles fushia">
        <img src="images/tiles/hex-fushia.png" width="40" height="40"/>
    </div>
</div>

</html>
