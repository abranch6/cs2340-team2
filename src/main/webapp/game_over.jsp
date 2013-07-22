<%@ page import="edu.gatech.cs2340team2.risk.model.Player" %>
<%@ page import="java.util.*" %>


<% Player winner = (Player) request.getAttribute("winner"); %>



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
<title>RISK Game-Over Screen</title>
</head>

<body>
<h1>GAME OVER!</h1>
<form action="/risk/reset_game" method="POST" >
<ul>
    <li>
        The Winner is: <% winner.getName() %>
     </li>
    <input type="submit" value="Play Again?" />
</ul>
</form>

</body>
</html>