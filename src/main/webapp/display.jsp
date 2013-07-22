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
<div id=player_panel style="width:1000px; position:relative; left:5px padding-left: 20px; top: 600px; text-indent: 20px;">
    <h2>Turn Order</h2>
    <img src="images/blackRomanNumerals.png" style="width:700; height:20; padding-left: 10px;">
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
<div id=control_panel class="hidden" style="width:350px; position:relative;">
    <div>
        <h3>Game Status</h3>
        <p id="p_game_state">Game State:</p>
        <p id="p_turn_phase">Turn Phase:</p>
    </div>
</div>
<div id=control_panel class="territoryInfo" style="width:325px;  height: 580px; position:relative; left:710px; top: -50;">
   
    <div id=fortify_select>
        <h3>Select Source or Destination for Territory</h3>
        <input type="radio" name="radio_fortify" id="fortify_source" value="Source" checked> Source<br>
        <input type="radio" name="radio_fortify" id="fortify_destination" value="Destination"> Destination  
    </div>    
    <div id=selected_territory_1>
        </br>
        </br>
        <h3 id="st1_title"></h3>
        <p id="s_t_type_1">No Territory Selected</p>
        <p id="s_t_player_1">Controlling Player:</p>
        <p id="s_t_armies_1">Armies:</p>
        
        <p id="st1_textbox_title"> </p>
        <input type="number" id="armies_textbox1"></input>
        <br>
        <button onclick="placeArmies()" id="place_armies_button" >Place Armies</button>
        <button onclick="attack()" id="attack_button">  Attack  </button>
        <button onclick="fortify()" id="fortify_button"> Fortify </button>
        <button onclick="advanceTurn()" id="end_turn_button"> End Phase</button>
    </div>
    <div id=selected_territory_2 class="player2Territory">
        <h3 id="st2_title"></h3>
        <p id="s_t_type_2">No Territory Selected</p>
        <p id="s_t_player_2">Controlling Player:</p>
        <p id="s_t_armies_2">Armies:</p>
        <p id="place_defend_armies"> Number of armies to defend with: <input type="number" id="armies_textbox2"></input></p>
    </div>
</div>

<div id="hexmap" style="position:absolute; left:15px;" class="changeOnClick"></div>

<div id=dice_panel style="left:1400px;">
    <div id=spot_0_0 class="die_0_0">
    </div>
    <div id=spot_0_1 class="die_0_1">
    </div>
    <div id=spot_0_2 class="die_0_2">
    </div>
    <div id=spot_1_0 class="die_1_0">
    </div>
    <div id=spot_1_1 class="die_1_1">
    </div>
    <div id=spot_1_2 class="die_1_2">
    </div>
</div>
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
