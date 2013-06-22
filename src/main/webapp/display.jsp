<%@ page import="edu.gatech.cs2340team2.risk.model.Player" %>
<%@ page import="java.util.*" %>


<% Queue<Player> players = (Queue<Player>) request.getAttribute("players"); %>
<% int numPlayers = (Integer) request.getAttribute("numPlayers"); %>


<html>
<head>

<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script type="text/javascript" src="js/hexmap.js"></script>
<script type="text/javascript" src="js/getPlayerObjects.js"></script>
<link rel="stylesheet" type="text/css" href="css/hexmap.css" />

<title>RISK Display Screen</title>
</head>

<body onLoad="showHexMap()"; showPlayerInformation();>

<div id="hexmap"></div>
<div id = "player1"></div>
<div id = "player2"></div>
<div id = "player3"></div>
<div id = "player4"></div>
<div id = "player5"></div>
<div id = "player6"></div>
    <table>
        <% for (int i = 0; i < numPlayers; i++) { %>
        <% Player temp = players.poll(); %>
	 <p>
	    Turn Order # <%= i+1 %> :     <%= temp.getName() %> Armies: <%= temp.getArmies() %>
	 </p>
	 <% players.add(temp); %>
	 <% } %>        
    </table>
	<p id="player1"> </p>
	<p id="player2"> </p>
	<p id="player3"> </p>
	<p id="player4"> </p>
	<p id="player5"> </p>
	<p id="player6"> </p>
</body>




<div id="templates" class="hidden">
  <div class="tiles blue">
    <img src="images/tiles/hex-blue.png" width="72" height="72"/>
  </div>

  <div class="tiles brown">
    <img src="images/tiles/hex-brown.png" width="72" height="72"/>
  </div>
<<<<<<< HEAD
=======

  <div class="tiles ican">
    <img src="images/tiles/hex-pepper.png" width="72" height="72"/>
  </div>
>>>>>>> upstream/master
</div>

</html>
