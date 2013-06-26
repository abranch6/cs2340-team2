package edu.gatech.cs2340team2.risk.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class RiskGame {
	
	private Queue<Player> list = new LinkedList<Player>();
	private Player players[];
	private int armies;
	private GameState state;
	private HexMap map;
	private Gson json;
	private int currPlayerTurnID;
	
	private final int CALC_NEW_ARMIES_BASE = 4;

	public RiskGame()
	{
		json = new Gson();
		state = GameState.INIT_PLAYERS;
		map = new HexMap(7);
	}

	public void setGameState(GameState state)
	{
		this.state = state;
	}

	public GameState getGameState()
	{
	    return state;
	}

	public void initPlayers(String[] names){
		players = new Player[names.length];
		int idCounter = 0;
		Random rand = new Random();
	
		while(list.size() < names.length){
			int index = rand.nextInt(names.length);
			if (names[index] != null){
			    Player temp = new Player(names[index], getStartingArmies(names.length), idCounter);
				list.add(temp);
			    players[idCounter] = temp;
			    idCounter++;
				names[index] = null;
			}
		}
		
		state = GameState.PRE_GAME;
	}
	
	public int getStartingArmies(int i){
		switch (i){
		case 3: 
			return 35;
		case 4:
			return 30;
		case 5: 
			return 25;
		case 6: 
			return 20;
		default: 
			return 30;
		}
	}
	
	public Queue<Player> getQueue(){
		return list;
	}
	
	public String getJSonTerritory(MapLocation loc)
	{
	    return map.getTerritory(loc).getJSon();
	}
	
	public HexMap getMap()
	{
	    return map;
	}
	
	public boolean placeArmies(int row, int col, int playerId, int armies)
	{
	    boolean success = false; 
	    Territory selectedTerr = map.getTerritory(new MapLocation(row, col));
	    
	    if(players[playerId].getArmies() >= armies)
	    {
	        if(selectedTerr.getPlayerId() == playerId)
	        {
	            selectedTerr.addArmies(armies);
	            players[playerId].addArmies(-armies);
	            success = true;
	        }
	        else if(selectedTerr.getPlayerId() == -1)
	        {
	            selectedTerr.addArmies(armies);
	            selectedTerr.setPlayerId(playerId);
	            players[playerId].addArmies(-armies);
		    players[playerId].incrementNumTerritoriesContolled();
	            success = true;
	        }
	    }

	    if (success)
	    {
		if (state == GameState.PRE_GAME)
		{
		    if (this.isPreGameComplete())
		    {
			state = GameState.GAME;
		    }
		    this.nextTurn(); 
		}
		else if (state == GameState.GAME)
		{
		    //TODO 
		}
	    }
	    
	    return success;
	}
	
	public void nextTurn()
	{
	    Player temp = list.poll();
	    list.add(temp);
	    currPlayerTurnID = list.peek().getId();
	    
	    if(state == GameState.GAME)
	    {
		this.givePlayerNewArmies(currPlayerTurnID);
	    }
	}
	
	public String getPlayerTurnJSON()
	{
	    Player[] queuetoArray = list.toArray(new Player[0]);
	    int[] turn = new int[queuetoArray.length];
    
	    for(int i = 0; i < queuetoArray.length; i++)
	    {
		turn[i] = queuetoArray[i].getId();
	    }
	    return json.toJson(turn);
	}
	
	public String getPlayerJSON(){
		return json.toJson(players);
	}
	
	private void givePlayerNewArmies(int playerID)
	{
		Player currPlayer = players[playerID];
		int numTerr = currPlayer.getNumTerritoriesControlled();
		
		if (numTerr <= CALC_NEW_ARMIES_BASE)
		{
			currPlayer.setArmies(CALC_NEW_ARMIES_BASE);
		}
		else
		{
			currPlayer.setArmies(numTerr / CALC_NEW_ARMIES_BASE);
		}

	}
	
	private boolean isPreGameComplete()
	{
		for (Player player : players)
		{
			if (player.getArmies() == 0)
			{
				return false;
			}
		}
		return true;
	}

}
