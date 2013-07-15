<html>
<head>

<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script type="text/javascript" src="js/hexmap.js"></script>
<script type="text/javascript" src="js/gamecontrols.js"></script>
<script type="text/javascript" src="js/player.js"></script>
<link rel="stylesheet" type="text/css" href="css/hexmap.css" />

<title>RISK Display Screen</title>
</head>

<body style="user-select: none; "onselectstart="return false;" onLoad="showHexMap(); updatePlayerInfo(); fetchGameState();" ondragstart="return false;" ondrop="return false;">
<<<<<<< HEAD
<div id=player_panel style="width:1000px; position:relative; left:5px padding-left: 20px;text-indent: 20px;"">
<h2 style="padding-top:20px;">Turn Order</h2>
=======
<div id=player_panel style="width:1000px; position:relative; left:5px padding-left: 20px; text-indent: 20px;">
    <h2 style="padding-top:20px;">Turn Order</h2>
>>>>>>> merged attack
    <div id = "player1" class="player1">
        <p id="p1_name"></p>
        <p id="p1_armies"></p>
		<p id="p1_color"></p>
		<img src="images/blackRomanNumerals.png" style="width:700; height:20">
    </div>
    <div id = "player2" class="player2">
        <p id="p2_name"></p>
        <p id="p2_armies"></p>
        <p id="p2_color"></p>
    </div>
    <div id = "player3" class="player3">
        <p id="p3_name"></p>
        <p id="p3_armies"></p>
        <p id="p3_color"></p>
    </div>
    <div id = "player4" class="player4">
        <p id="p4_name"></p>
        <p id="p4_armies"></p>
        <p id="p4_color"></p>
    </div>
    <div id = "player5" class="player5">
        <p id="p5_name"></p>
        <p id="p5_armies"></p>
        <p id="p5_color"></p>
    </div>
    <div id = "player6" class="player6">
        <p id="p6_name"></p>
        <p id="p6_armies"></p>
        <p id="p6_color"></p>
    </div>
</div>
<img src="images/blackRomanNumerals.png" style="width:700; height:20">
<div id=control_panel class="hidden" style="width:350px; position:relative; top:125px">
    <div>
        <h3>Game Status</h3>
        <p id="p_game_state">Game State:</p>
        <p id="p_turn_phase">Turn Phase:</p>
    </div>
</div>
<div id=control_panel class="territoryInfo" style="background-image:URL('images/RomanNumerals.png'); background-size: 380px 306px;width:325px; position:relative; top:325px">
<<<<<<< HEAD
    <div id=selected_territory>
	</br>
        <h3>Selected Territory</h3>
        <p id="s_t_type">No Territory Selected</p>
        <p id="s_t_player">Controlling Player:</p>
        <p id="s_t_armies">Armies:</p>
    </div>
    <p>
    <br>Number of Armies: <input type="number" id="armies_textbox"><br></input>
	<button onclick="placeArmies()" >Place Armies</button>
    <button onclick="advanceTurn()" id="end_turn_button" style="background-image:URL('images/EndTurn.png')">Next Turn Phase</button>
    <button onclick="attack(1,1,window.selectedTerritory1.row,window.selectedTerritory1.col,window.selectedTerritory2.row,window.selectedTerritory2.col)">
        Attack
    </button>
=======
    <div id=selected_territory_1>
	</br>
        <h3>Selected Territory One</h3>
        <p id="s_t_type_1">No Territory Selected</p>
        <p id="s_t_player_1">Controlling Player:</p>
        <p id="s_t_armies_1">Armies:</p>
    </div>
    <div id=selected_territory_2>
    </br>
        <h3>Selected Territory Two</h3>
        <p id="s_t_type_2">No Territory Selected</p>
        <p id="s_t_player_2">Controlling Player:</p>
        <p id="s_t_armies_2">Armies:</p>
    </div>

    <p>
    <br>Number of Armies: <input type="number" id="armies_textbox"><br></input>
	<button onclick="placeArmies()" >Place Armies</button>
    <button onclick="advanceTurn()" id="end_turn_button" style="background-image:URL('images/EndTurn.png')">End Turn</button>
>>>>>>> merged attack
	</br>
</div>

<div id="hexmap" style="position:absolute; left:450px; top:195px" class="changeOnClick"></div>

</body>




<div id="templates" class="hidden">
    <div class="tiles Ocean">
        <img src="images/tiles/MovingOcean.gif" width="50" height="52"/>
    </div>

    <div class="tiles Brown">
        <img src="images/tiles/hex-brown.png" width="50" height="52" class="opacity"/>
    </div>

    <div class="tiles Yellow">
        <img src="images/tiles/hex-yellow.png" width="50" height="52" class="opacity"/>
    </div>
    
    <div class="tiles Blue">
        <img src="images/tiles/hex-cyan.png" width="50" height="52" class="opacity"/>
    </div>

    <div class="tiles Green">
        <img src="images/tiles/hex-green.png" width="50" height="52" class="opacity"/>
    </div>

    <div class="tiles Red">
        <img src="images/tiles/hex-red.png" width="50" height="52" class="opacity"/>
    </div>

    <div class="tiles Orange">
        <img src="images/tiles/hex-tangerine.png" width="50" height="52" class="opacity"/>
    </div>

    <div class="tiles Purple">
        <img src="images/tiles/hex-fushia.png" width="50" height="52" class="opacity"/>
    </div>
</div>

</html>
