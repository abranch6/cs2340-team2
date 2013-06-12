<%@ page import="edu.gatech.cs2340team2.risk.model.Player" %>
<%@ page import="java.util.*" %>

<% TreeMap<Integer, Player> players = (TreeMap<Integer, Player>) request.getAttribute("players"); %>
<% Integer numPlayers = (Integer) request.getAttribute("numPlayers"); %>

<html>
<head>
<title>RISK Start Screen</title>
</head>

<body>
<h1>How many players?</h1>
<table>
   <tr>
      <td>	
	 <table>
	    <tr>
	       <input type="button" value="3" />
	    </tr>
	    <tr>
	       <input type="button" value="4" />
	    </tr>
	    <tr>
	       <input type="button" value="5" />
	       </tr>
	    <tr>
	       <input type="button" value="6" />
	    </tr>
	    <tr>
	       <form action="/risk/update" method="POST">
		  <input type="hidden" name="operation" value="PUT" />
		  <input type="text" name="numplayers" value="<%= numPlayers %>" autofocus />
		  <input type="submit" value="SetNum" />
	       </form>
	     </tr>
	 </table>			
      </td>
      <td>
	 <table>
	    <form action="dummy.html" method="POST" >
	    <tr>
	       <input type="text" name="player1Name" />
	    </tr>
	    <tr>
	       <input type="text" name="player2Name" />
	    </tr>
	    <tr>
	       <input type="text" name="player3Name" />
	    </tr>
	    <% for (int i = 3; i < numPlayers; i++) { %>
	    <tr>
	       <input type="text" name="player<%= i %>Name"/>
	    </tr>
	    <% } %>
	    <tr>
	       <input type="submit" value="Begin" />
	    </tr>
	    </form>
	 </table>
      </td>
   </tr>
</table>

</body>
</html>
