package edu.gatech.cs2340team2.risk.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class RiskGame {
	
	private Queue<Player> list = new LinkedList<Player>();
	private Player players[];
	private int armies;
	private GameState state;
	private HexMap map;

	public RiskGame()
	{
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
	    players = new Player[names.length + 1];
	    int idCounter = 1;
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
	    Territory temp = map.getTerritory(new MapLocation(row, col));
	    
	    if(players[playerId].getArmies() >= armies)
	    {
	        if(temp.getPlayerId() == playerId)
	        {
	            temp.addArmies(armies);
	            players[playerId].addArmies(-armies);
	            return true;
	        }
	        else if(temp.getPlayerId() == 0)
	        {
	            temp.addArmies(armies);
	            temp.setPlayerId(playerId);
	            players[playerId].addArmies(-armies);
	            return true;
	        }
	    }
	    return false;
	}
	
	public int nextTurn()
	{
	    Player temp = list.poll();
	    list.add(temp);
	    return list.peek().getId();
	}
		
}

