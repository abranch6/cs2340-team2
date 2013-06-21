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

<div id=control_panel>
    <div id=selected_territory>
    </div>
</div>
<div id="hexmap"></div>

<div id=control_panel>
    <div id=selected_territory>
    
    </div>
</div> 
</body>

<div id="templates" class="hidden">
  <div class="tiles blue">
    <img src="images/tiles/hex-blue.png" width="72" height="72"/>
  </div>

  <div class="tiles brown">
    <img src="images/tiles/hex-brown.png" width="72" height="72"/>
  </div>

  <div class="tiles pepper">
    <img src="images/tiles/hex-pepper.png" width="72" height="72"/>
  </div>
</div>

</html>
