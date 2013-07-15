package edu.gatech.cs2340team2.risk.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class RiskGame {
	/*  Signifies the min # armies each player will recieve
	 *  per turn AS-WELL-AS the # or territories that must be
	 *  controlled to recieve 1 new army.
	 *  i.e. player.numTerrContoled / #  =  the num new armies
	 *  that player recieves that turn <= #   
	 */
    private final int CALC_NEW_ARMIES_BASE = 1;
    
    private final int NEUTRAL = -1;
    private final int ATTACKER = 0;
    private final int DEFENDER = 1;
	
    private Queue<Player> list = new LinkedList<Player>();
	private Player players[];	
	private int armies;
	private HexMap map;
	private Gson json;
	
	//Current State of the Game variables
	private GameState state;
	private int currPlayerTurnID;
	private TurnPhase turnPhase;
		
	
	public RiskGame()
	{
        this(new HexMap(7));
	}

    public RiskGame(HexMap map)
    {
        this.map = map;
        json = new Gson();
        state = GameState.INIT_PLAYERS;
        turnPhase = TurnPhase.NULL;
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
	    boolean success = false; //tracks if the armies were successfully placed
	    Territory selectedTerr = map.getTerritory(new MapLocation(row, col));
	    
	    if (state == GameState.PRE_GAME)
	    {
	        success = this.placeArmiesPRE_GAME(selectedTerr, playerId, armies);
	    }
	    else if (state == GameState.GAME)
	    {
	        success = this.placeArmiesGAME(selectedTerr, playerId, armies);
	    }
	    
	    return success;
	}
	
	private boolean placeArmiesGAME(Territory selectedTerr, int playerId, int armies)
	{
	    boolean success = false;
	    
	    if (turnPhase == TurnPhase.PLACE_NEW_ARMIES)
	    {
            if(players[playerId].getArmies() >= armies)
            {
                if(selectedTerr.getPlayerId() == playerId)
                {
                   selectedTerr.addArmies(armies);
                   players[playerId].addArmies(-armies);
                   success = true;
                }
                else if(selectedTerr.getPlayerId() == NEUTRAL)
                {
                	boolean valid = false;
                	int adjacentPlayerId = NEUTRAL;
                	for (int i = 0; i < 6; i++)
                	{
                		MapLocation neighbor = selectedTerr.getMapLocation().getNeighbor(i);
                		if (neighbor != null)
                		{
                			Territory adjacent = map.getTerritory(neighbor);
	                 		if (adjacent != null)
	                 		{
	                 			adjacentPlayerId = adjacent.getPlayerId();
	                 			if (adjacentPlayerId == playerId)
	                 				valid = true; 
	                 		}
                		}
                	}
                	
                	if (valid)
                	{
	                    selectedTerr.addArmies(armies);
	                    selectedTerr.setPlayerId(playerId);
	                    players[playerId].addArmies(-armies);
	                    players[playerId].incrementNumTerritoriesContolled();
	                    success = true;               		
                	}
                } 
            }
	    }
	   
        if(success)
        {
            updateTurnPhase();
        } 
	    return success;
	}
	
	private boolean placeArmiesPRE_GAME(Territory selectedTerr, int playerId, int armies)
	{
	    boolean success = false;
	    
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
            updateGameState();
            nextTurn(); 
	    }
	    
	    return success;
	}
	
	
	public void nextTurn()
	{
        if(turnPhase != TurnPhase.PLACE_NEW_ARMIES)
        {
	        Player temp = list.poll();
	        list.add(temp);
	        currPlayerTurnID = list.peek().getId();
	    }

    
	    if(state == GameState.GAME)
	    {
	        turnPhase = TurnPhase.PLACE_NEW_ARMIES;
	        this.givePlayerNewArmies(currPlayerTurnID);
	    }
        else if(state == GameState.PRE_GAME)
        {
            if(players[currPlayerTurnID].getArmies() == 0)
            {
                nextTurn();
            }
        }
	}
	
	public void updateTurnPhase()
	{
	    if (turnPhase == TurnPhase.PLACE_NEW_ARMIES)
	    {
	        if (players[currPlayerTurnID].getArmies() == 0)
	        {
	            turnPhase = TurnPhase.ATTACK;
	        }
	    }
	    else if (turnPhase == TurnPhase.ATTACK)
	    {
            turnPhase = TurnPhase.FORTIFY;
	    }
	    else if (turnPhase == TurnPhase.FORTIFY)
	    {
            nextTurn();
	    }
	    else if (turnPhase == TurnPhase.NULL)
	    {
	        
	        return;
	    }
	}

    public void updateGameState()
    {
        switch(state)
        {
            case PRE_GAME:
                if(isPreGameComplete())
                {
                    state = GameState.GAME;
                }
            break;
        }
    }
    
    public int[][] attack(int attackDieNum, int defendDieNum, int attackRow, int attackCol, int defendRow, int defendCol)
    {
    	int[][] diceValues = new int[2][3];
    	//int invalidAttack = false;
    	
    	Territory attackTerr = map.getTerritory(new MapLocation(attackRow, attackCol));
    	Territory defendTerr = map.getTerritory(new MapLocation(defendRow, defendCol));
    	
    	//Check if Territories are Invalid:
    	// - AttackingTerritory must belong to currentTurnPlayer
    	// - AttackingTerritory must have more than 1 armies
    	// - DefendingTerritory must not belong to currentTurnPlayer OR be neutral
    	// - DefendingTerritory must be adjacent to AttackingTerritory
    	if (attackTerr.getPlayerId() != currPlayerTurnID || attackTerr.getArmies() == 1)
    	{
    		return diceValues;
    	}
    	if (defendTerr.getPlayerId() == currPlayerTurnID || defendTerr.getPlayerId() == NEUTRAL || !map.areTerritoriesAdjacent(attackTerr, defendTerr))
    	{
    		return diceValues;
    	}
    	
    	//Check if Dice are InValid:
    	// - 0 < numAttackDie < 4
    	// - AttackingTerritory must have at least 1 more armies than numAttackDie rolled
    	// - 0 < numDefendDie < 3
    	// - DefendingTerrotory must have at least as many armies as numDefendDie rolled
    	if (attackDieNum > 3 || attackDieNum < 1 || attackTerr.getArmies() - attackDieNum < 1)
    	{
    		return diceValues;
    	}
    	if (defendDieNum > 2 || defendDieNum < 1 || defendTerr.getArmies() - defendDieNum < 0)
    	{
    		return diceValues;
    	}
    	
    	//Everything is valid by now, so HandleAttack:
    	diceValues = handleDiceRoll(attackDieNum, defendDieNum);
    	
    	/*
    	System.out.println("ATTACKER DICE (in order):");
    	for (int i = 0; i < 3; i++)
    	{
    		System.out.println(diceValues[ATTACKER][i]);
    	}

    	System.out.println("\nDEFENDER DICE (in order):");
    	for (int i = 0; i < 3; i++)
    	{
    		System.out.println(diceValues[DEFENDER][i]);
    	}
    	*/
    	
    	int numDiceComparisons = 0;
    	
    	if (attackDieNum < defendDieNum)
    	{
    		numDiceComparisons = attackDieNum;
    		for (int i = 0; i < numDiceComparisons; i++)
    		{
    			//handle Dice Comparisson:
    			if (diceValues[ATTACKER][i] > diceValues[DEFENDER][i]) 		
    				defendTerr.addArmies(-1);
    			else
    				attackTerr.addArmies(-1);
    		}    		
    	}
    	else 
    	{
    		if (attackDieNum == defendDieNum)
    			numDiceComparisons = attackDieNum;
    		
    		else if (attackDieNum > defendDieNum)
    			numDiceComparisons = defendDieNum;
    		
    		for (int i = 0; i < numDiceComparisons; i++)
    		{
    			//handle Dice Comparisson:
    			if (diceValues[ATTACKER][i] > diceValues[DEFENDER][i]) 		
    				defendTerr.addArmies(-1);
    			else
    				attackTerr.addArmies(-1);
    		}

    		//Handle if DefendingTerritory has lost all armies
    		if (defendTerr.getArmies() == 0)
    		{
    			handleTerritoryCaptured(attackTerr, defendTerr, attackDieNum);
    		}
    		
    	}
    	
    	return diceValues;
    }
    
    private void handleTerritoryCaptured(Territory attackTerr, Territory defendTerr, int attackDieNum)
    {
		//AttackingPlayer takes over this territory
		if (attackTerr.getArmies() <= 1)
		{
			//**DEBUGGING ONLY**// THIS SHOULD NEVER BE ABLE TO HAPPEN, BUT JUST TO MAKE SURE!
			defendTerr.setArmies(-10);
			attackTerr.setArmies(-15);
		}
		else
		{
			//move #attackDieNum to new terr
			if (attackTerr.getArmies() >= attackDieNum + 1)
			{
				attackTerr.addArmies(-attackDieNum);    					
				defendTerr.setArmies(attackDieNum);	
			}
			//move only 1 to new terr
			else
			{
				attackTerr.addArmies(-1);    					
				defendTerr.setArmies(1);	    					
			}
			
			players[defendTerr.getPlayerId()].decrementNumTerritoriesContolled();
			defendTerr.setPlayerId(currPlayerTurnID);
			players[currPlayerTurnID].incrementNumTerritoriesContolled();
		}    	
    }
    
    private int[][] handleDiceRoll(int attackDieNum, int defendDieNum)
    {
    	int[][] dice = new int[2][3];
    	
     	//Roll Dice:
    	for (int i = 0; i < attackDieNum; i++)
    	{
    		dice[ATTACKER][i] = getRandomDieValue();
    	}
    	for (int i = 0; i < defendDieNum; i++)
    	{
    		dice[DEFENDER][i] = getRandomDieValue();
    	}
    	
    	//Order Rolled Dice Values:
    	//sorts into ascending order.
    	Arrays.sort(dice[0]);
    	Arrays.sort(dice[1]);
    	
    	//takes sorted array in ascending order, and turns it into sorted descending order
    	int temp = dice[0][0];
    	dice[0][0] = dice[0][2];
    	dice[0][2] = temp;
    	
    	dice[1][0] = dice[1][2];
    	dice[1][2] = 0;
    	
    	return dice;
    }
    
    private int getRandomDieValue()
    {
    	Random rand = new Random();
    	int value = rand.nextInt(6) + 1;
    	return value;
    }
    
	public void fortify()
	{
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
	
	public String getGameStateJSON()
	{
	    return json.toJson(state);
	}

    public String getTurnPhaseJSON()
    {
        return json.toJson(turnPhase);
    }
	
	private void givePlayerNewArmies(int playerID)
	{
		Player currPlayer = players[playerID];
		int numTerr = currPlayer.getNumTerritoriesControlled();
		
		System.out.println("USER: " + currPlayer.getName() + " | Has " + numTerr + " Territories.");
		
		if (numTerr <= (CALC_NEW_ARMIES_BASE * CALC_NEW_ARMIES_BASE))
		{
			
			currPlayer.setArmies(CALC_NEW_ARMIES_BASE);
		}
		else
		{
			int newArmies = (numTerr / CALC_NEW_ARMIES_BASE);
			currPlayer.setArmies(newArmies);
		}

	}
	
	private boolean isPreGameComplete()
	{
		boolean complete = true;
		
		for (Player player : players)
		{
			if (player.getArmies() != 0)
			{
				complete = false;
			}
		}
		return complete;
	}

}
