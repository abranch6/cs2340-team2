<%@ page import="edu.gatech.cs2340team2.risk.model.Player" %>
<%@ page import="java.util.*" %>


<% int numPlayers = (Integer) request.getAttribute("numPlayers"); %>



<html>
<head>
<style>
ul
{
text-align:center;
}
ul {list-style-type: none}
h1
{
text-align:center;
}
</style>
<title>RISK Start Screen</title>
</head>

<body>
<h1>Enter Player Names</h1>
<form action="/risk/start_game" method="POST" >
<ul>
	<% for (int i = 0; i < numPlayers; i++) { %>
	<li>
        <input type="hidden" name="operation" value="POST" />
	<input type="text" name="player<%= i %>Name" placeholder="Player <%= i  %>" required />
	 </li>
         <% } %>
	<input type="submit" value="Start Game!" />
</ul>
</form>

</body>
</html>
