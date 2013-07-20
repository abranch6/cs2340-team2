package edu.gatech.cs2340team2.risk.model;

import edu.gatech.cs2340team2.risk.model.RiskGame;
import edu.gatech.cs2340team2.risk.model.HexMap;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class RiskJUnitTest extends RiskGame {
//    public void prepare() {
//        setBaseUrl("http://localhost:8080/risk");
//    }
    
    //script out a game
    
    
    @Test
    public void gettingStartingArmies(){
        //test what they have left, test what they have in the territories
        //after one turn
        //if there are 3 players
        HexMap testMap= new HexMap(2);
        RiskGame testingGame= new RiskGame(testMap);
        
        testingGame.initPlayers(new String[]{"Tam","Jay","Arthur"});
          
        assertEquals(true,testingGame.getStartingArmies(35));
        
    }

    @Test
    public void placingNewArmies(){
        HexMap testMap= new HexMap(2);
        RiskGame testingGame= new RiskGame(testMap);
        
        testingGame.initPlayers(new String[]{"Tam","Jay","Arthur"});
          
        //assertEquals(true,testingGame.getStartingArmies(35));
        
        testingGame.placeArmies(1,1,0,5);
        testingGame.nextTurn();
        testingGame.placeArmies(1,2,1,5);
        testingGame.nextTurn();
        testingGame.placeArmies(0,1,2,5);
        
        //testing 1st player after one turn
        assertEquals(true,testingGame.placeArmies(1, 1, 0, 5));
        assertEquals(false,testingGame.placeArmies(2, 2, 0, 5));
        assertEquals(false,testingGame.placeArmies(1,1, 0, 15));
        
        //testing 2nd player after one turn
        assertEquals(true,testingGame.placeArmies(1, 2, 1, 5));
        assertEquals(false,testingGame.placeArmies(2, 2, 1, 5));
        assertEquals(false,testingGame.placeArmies(1,1, 1, 15));
        
        //testing third player after one turn
        assertEquals(true,testingGame.placeArmies(0, 1, 2, 5));
        assertEquals(false,testingGame.placeArmies(2, 2, 2, 5));
        assertEquals(false,testingGame.placeArmies(1,1, 2, 15));
    }
    
    @Test
    public void getNextTurnArmies(){
        HexMap testMap= new HexMap(2);
        RiskGame testingGame= new RiskGame(testMap);
        
        testingGame.initPlayers(new String[]{"Tam","Jay","Arthur"});
        //player 1's first turn
        testingGame.placeArmies(1,1,0,5);
        testingGame.nextTurn();
        
        //player 2's first turn 
        testingGame.placeArmies(1,2,1,5);
        testingGame.nextTurn();
        
        //player 3's first turn
        testingGame.placeArmies(0,1,2,5);
        testingGame.nextTurn();
        
        
        //player 1's second turn
        testingGame.placeArmies(2,1,0,5);
        testingGame.nextTurn();
      
        //player 2's second turn 
        testingGame.placeArmies(0,2,1,5);
        testingGame.nextTurn();
        
        //player 3's second turn 
        testingGame.placeArmies(0,1,2,5); //remember to change the value to 10
        testingGame.nextTurn();
      
        //testing player one after two turns
        assertEquals(true,testingGame.placeArmies(2, 1, 0, 5));
        assertEquals(false,testingGame.placeArmies(2, 1, 0, 10)); //should only be 5
        
        //testing player two after two turns
        assertEquals(true,testingGame.placeArmies(1, 2, 1, 5));
        assertEquals(false,testingGame.placeArmies(1, 2, 1, 10)); //should be 5
        
        //testing player three after two turns
        assertEquals(true,testingGame.placeArmies(0, 1, 2, 10)); 
        assertEquals(false,testingGame.placeArmies(0, 1, 2, 5)); //should be 10
    }
    
    public void testFirstPlayerAttacking(){
        HexMap testMap= new HexMap(2);
        RiskGame testingGame= new RiskGame(testMap);
        testingGame.initPlayers(new String[]{"Tam","Jay","Arthur"});
        Territory playerOnesSpot= new Territory(0,1);
        Territory playerTwosSpot1= new Territory (2,2);
        Territory playerTwosSpot2= new Territory (1,2);
        Territory playerThreesSpot =new Territory (1,0);
       
        //SET ALL ARMIES FIRST (35) THEN CHECK IF ARMIES ARE ADJACENT
        //IF THEY ARE ADJACENT, THEY ARE ABLE TO ATTACK
        
        
        //FIRST ROUND
        //player 1's first turn
        testingGame.placeArmies(0,1,0,5);
        testingGame.nextTurn();
       
        //player 2's first turn 
        testingGame.placeArmies(2,2,1,5);
        testingGame.nextTurn();
        
        //player 3's first turn
        testingGame.placeArmies(1,0,2,5);
        testingGame.nextTurn();
        
        //2nd ROUND
        
        testingGame.placeArmies(0,1,0,5);
        testingGame.nextTurn();
             
        testingGame.placeArmies(2,2,1,5);
        testingGame.nextTurn();
        
        testingGame.placeArmies(1,0,2,5);
        testingGame.nextTurn();
        
        //3rd Round
        testingGame.placeArmies(0,1,0,5);
        testingGame.nextTurn();
             
        testingGame.placeArmies(1,2,1,5);
        testingGame.nextTurn();
        
        testingGame.placeArmies(1,0,2,5);
        testingGame.nextTurn();
        
        //4th round
        testingGame.placeArmies(0,1,0,5);
        testingGame.nextTurn();
             
        testingGame.placeArmies(1,2,1,5);
        testingGame.nextTurn();
        
        testingGame.placeArmies(1,0,2,5);
        testingGame.nextTurn();
        
        //5th round
        testingGame.placeArmies(0,1,0,5);
        testingGame.nextTurn();
             
        testingGame.placeArmies(1,2,1,5);
        testingGame.nextTurn();
        
        testingGame.placeArmies(1,0,2,5);
        testingGame.nextTurn();
        
        // 6th round
        testingGame.placeArmies(0,1,0,5);
        testingGame.nextTurn();
             
        testingGame.placeArmies(2,2,1,5);
        testingGame.nextTurn();
        
        testingGame.placeArmies(1,0,2,5);
        testingGame.nextTurn();
        
        //7th round- all armies are done
        testingGame.placeArmies(0,1,0,5);
        testingGame.nextTurn();
             
        testingGame.placeArmies(2,2,1,5);
        testingGame.nextTurn();
        
        testingGame.placeArmies(1,0,2,5);
        testingGame.nextTurn();
        
        //testing attacking
        if (testMap.areTerritoriesAdjacent(playerOnesSpot,playerTwosSpot1)==true){
            //they cannot attack one another
            //player 1 attacks
            testingGame.attack(5, 4, 0, 1, 2, 2);
        } 
        else{
            //they cannot attack one another
           testingGame.nextTurn();
        }
        
        if(testMap.areTerritoriesAdjacent(playerOnesSpot, playerThreesSpot)==true){
            //they are able to attack one another
            //player 1 attacks
            testingGame.attack(5, 4, 0, 1, 1, 0);
        }
        else{
            testingGame.nextTurn();
        }
        
    }
}