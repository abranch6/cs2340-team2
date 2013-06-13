<%@ page import="edu.gatech.cs2340team2.risk.model.Player" %>
<%@ page import="java.util.*" %>


<% LinkedList<Player> players = (LinkedList<Player>) request.getAttribute("players"); %>
<% Integer numPlayers = (Integer) request.getAttribute("numPlayers"); %>


<html>
<head>
<title>RISK Display Screen</title>
</head>

<body>
    <table>
        <% for (int i = 0; i < numPlayers; i++) { %>
	 <tr>
	    Turn Order # <%= i+1 %> :     <%= players.get(i).getName() %>
	 </tr>
	 <% } %>        
    </table>

</body>
</html>
