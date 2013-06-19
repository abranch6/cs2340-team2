package edu.gatech.cs2340team2.risk.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class RiskGame {

	Queue<Player> list = new LinkedList<Player>();
	int armies;
	GameState state;
	HexMap map;

	public RiskGame()
	{
		state = GameState.INIT_PLAYERS;
		map = new HexMap(20);
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
		Random rand = new Random();
		while(list.size() < names.length){
			int index = rand.nextInt(names.length);
			if (names[index] != null){
				list.add(new Player(names[index], getStartingArmies(names.length)));
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

	public HexMap getMap()
	{
	    return map;
	}

}