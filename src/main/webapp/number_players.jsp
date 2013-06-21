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
</head>
<h1>
Select the Number of Players
</h1>
<ul>

<li>
<form action="/risk/update_num_players" method="POST">
    <input type="hidden" name="operation" value="PUT" />
    <input type="submit" style="width: 100px" name="numplayers" value="3" />
</form>
</li>

<li>
<form action="/risk/update_num_players" method="POST">
     <input type="hidden" name="operation" value="PUT" />
     <input type="submit" style="width: 100px" name="numplayers" value="4" />
</form>
</li>

<li>
<form action="/risk/update_num_players" method="POST">
     <input type="hidden" name="operation" value="PUT" />
     <input type="submit" style="width: 100px" name="numplayers" value="5" />
</form>
</li>

<li>
<form action="/risk/update_num_players" method="POST">
     <input type="hidden" name="operation" value="PUT" />
     <input type="submit" style="width: 100px" name="numplayers" value="6" />
</form>
</li>
</ul>

</html>
