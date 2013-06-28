package edu.gatech.cs2340team2.risk.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class RiskGame {
	/*  Signifies the min # armies each player will recieve
	 *  per turn AS-WELL-AS the # or territories that must be
	 *  controlled to recieve 1 new army.
	 *  i.e. player.numTerrContoled / #  =  the num new armies
	 *  that player recieves that turn <= #   
	 */
    private final int CALC_NEW_ARMIES_BASE = 2;
	
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
		json = new Gson();
		map = new HexMap(7);
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
	        /* NOTE: # armies to place needs to be ALL of the new armies 'player' just recieved
	         *     NOT the default '5' from PRE_GAME.
	         * TODO: if we want to allow players to put new armies on more than 1 territory, we 
	         *     need to add in a text-input to Display, specifying # armies player wishes to 
	         *     place on the selected territory (obviously we must check # <= player's # armies    
	         */
	        success = this.placeArmiesGAME(selectedTerr, playerId, players[playerId].getArmies());
	    }
	    
	    return success;
	}
	
	private boolean placeArmiesGAME(Territory selectedTerr, int playerId, int armies)
	{
	    boolean success = false;
	    
	    if (turnPhase == TurnPhase.PLACE_NEW_ARMIES)
	    {
            if(selectedTerr.getPlayerId() == playerId)
            {
                selectedTerr.addArmies(armies);
                players[playerId].addArmies(-armies);
                success = true;
                
                this.advanceTurnPhase();
                //turnPhase = TurnPhase.ATTACK;
            }
	    }
	    else if (turnPhase == TurnPhase.ATTACK)
	    {
	        //TESTING// System.out.println("DEBUG: placeArmies() -> turnPhase=ATTACK");
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
	        if (this.isPreGameComplete())
            {
	          //TESTING// System.out.println("GAME STATE TO --> GameState.GAME");
                state = GameState.GAME;
            }
	        
            this.nextTurn(); 
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
	        turnPhase = TurnPhase.PLACE_NEW_ARMIES;
	        this.givePlayerNewArmies(currPlayerTurnID);
	    }
	}
	
	public void advanceTurnPhase()
	{
	    if (turnPhase == TurnPhase.PLACE_NEW_ARMIES)
	    {
	        //TESTING// System.out.println("DEBUG: advanceTurnPhase() -> turnPhase=PLACE_NEW_ARMIES");
	        
	        if (players[currPlayerTurnID].getArmies() == 0)
	        {
	            //TESTING// System.out.println("DEBUG: advanceTurnPhase() -> turnPhase=PLACE_NEW_ARMIES  -->  phase complete[goto ATTACK]");
	            
	            turnPhase = TurnPhase.ATTACK;
	        }
	        else
	        {
	            //TESTING// System.out.println("DEBUG: advanceTurnPhase() -> turnPhase=PLACE_NEW_ARMIES  -->  phase incomplete");
	            
	            return;
	        }
	    }
	    else if (turnPhase == TurnPhase.ATTACK)
	    {
	        //TESTING// System.out.println("DEBUG: advanceTurnPhase() -> turnPhase=ATTACK");
	        //TESTING// System.out.println("DEBUG: advanceTurnPhase() -> turnPhase=ATTACK  -->  phase complete[goto FORTIFY]");
	        
	        turnPhase = TurnPhase.FORTIFY;
	        this.fortify(); //TEMPORARY M3
	    }
	    else if (turnPhase == TurnPhase.FORTIFY)
	    {
	        //TESTING// System.out.println("DEBUG: advanceTurnPhase() -> turnPhase=FORTIFY");
	        //TESTING// System.out.println("DEBUG: advanceTurnPhase() -> turnPhase=FORTIFY  -->  phase complete -> nextTurn()");
	        
	        this.nextTurn();
	    }
	    else if (turnPhase == TurnPhase.NULL)
	    {
	        //TESTING// System.out.println("DEBUG: advanceTurnPhase() -> turnPhase=NULL");
	        
	        return;
	    }
	}
	
	public void fortify()
	{
	    //TESTING//System.out.println("DEBUG: fortify() -> advanceTurnPhase()");
	    
	    this.advanceTurnPhase(); //TEMPORARY M3
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
