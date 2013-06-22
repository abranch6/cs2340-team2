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
<div id = "player1"><p id="p1"> </p></div>
<div id = "player2"><p id="p2"> </p></div>
<div id = "player3"><p id="p3"> </p></div>
<div id = "player4"><p id="p4"> </p></div>
<div id = "player5"><p id="p5"> </p></div>
<div id = "player6"><p id="p6"> </p></div>
    <table>
        <% for (int i = 0; i < numPlayers; i++) { %>
        <% Player temp = players.poll(); %>
	 <p>
	    Turn Order # <%= i+1 %> :     <%= temp.getName() %> Armies: <%= temp.getArmies() %>
	 </p>
	 <% players.add(temp); %>
	 <% } %>        
    </table>
	
	
	
	
	
	
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
