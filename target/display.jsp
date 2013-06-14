<%@ page import="edu.gatech.cs2340team2.risk.model.Player" %>
<%@ page import="java.util.*" %>


<% Queue<Player> players = (Queue<Player>) request.getAttribute("players"); %>
<% int numPlayers = (Integer) request.getAttribute("numPlayers"); %>


<html>
<head>
<title>RISK Display Screen</title>
</head>

<body>
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
</html>
