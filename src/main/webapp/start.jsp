<%@ page import="edu.gatech.cs2340team2.risk.model.Player" %>
<%@ page import="java.util.*" %>


<% List<Integer> POSSIBLE_NUM_PLAYERS = (List<Integer>) request.getAttribute("POSSIBLE_NUM_PLAYERS"); %>
<% Integer numPlayers = (Integer) request.getAttribute("numPlayers"); %>


<html>
<head>
<title>RISK Start Screen</title>
</head>

<body>
<h1>How many players?</h1>
<table>
   <td>	
      <table>
	 <tr><input type="button" value="3" /></tr>
	 <tr><input type="button" value="4" /></tr>
	 <tr><input type="button" value="5" /></tr>
	 <tr><input type="button" value="6" /></tr>
	 <tr>
	    <form action="/risk/update_num_players" method="POST">
	       <input type="hidden" name="operation" value="PUT" />
	       <input type="text" name="numplayers" value="<%= numPlayers %>" autofocus pattern="[3-6]" />
	       <input type="submit" value="SetNum" />
	    </form>
	  </tr>
      </table>			
   </td>
   <td>
      <table>
	 <form action="/risk/load_game" method="POST" >
	 <% for (int i = 0; i < numPlayers; i++) { %>
	 <tr>
	    <input type="hidden" name="operation" value="POST" />
	    <input type="text" name="player<%= i + 1 %>Name" placeholder="Player <%= i + 1 %>" required />
	 </tr>
	 <% } %>
	 <tr>
	    <input type="submit" value="Start Game!" />
	 </tr>
	 </form>
      </table>
   </td>
</table>

</body>
</html>
