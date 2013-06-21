<%@ page import="edu.gatech.cs2340team2.risk.model.Player" %>
<%@ page import="java.util.*" %>


<% Queue<Player> players = (Queue<Player>) request.getAttribute("players"); %>
<% int numPlayers = (Integer) request.getAttribute("numPlayers"); %>


<html>
<head>

<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script type="text/javascript" src="js/hexmap.js"></script>
<link rel="stylesheet" type="text/css" href="css/hexmap.css" />

<title>RISK Display Screen</title>
</head>

<body style="user-select: none;" onselectstart="return false;" onLoad="showHexMap()" ondragstart="return false;" ondrop="return false;">

<div id=control_panel style="width:200px; background:#CCC; float:left;">
    <div id=selected_territory>
        <h3>Selected Territory</h3>
        <p id=s_t_player>Controlling Player:</p>
        <p id=s_t_armies>Armies:</p>
    </div>
</div>
<div id="hexmap" style="float:left;"></div>

<div id=control_panel>
    <div id=selected_territory>
    
    </div>
</div> 
</body>

<div id="templates" class="hidden">
  <div class="tiles blue">
    <img src="images/tiles/hex-blue.png" width="40" height="40"/>
  </div>

  <div class="tiles brown">
    <img src="images/tiles/hex-brown.png" width="40" height="40"/>
  </div>

  <div class="tiles pepper">
    <img src="images/tiles/hex-pepper.png" width="40" height="40"/>
  </div>
</div>

</html>
