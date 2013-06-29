import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
//import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class RiskJUnitTests extends RiskGame {
//    public void prepare() {
//        setBaseUrl("http://localhost:8080/risk");
//    }
    
    //script out a game
    
    
    @Test(timeout = 100)
    public void gettingStartingArmies(){
        //test what they have left, test what they have in the territories
        //after one turn
        //if there are 3 players
        HexMap testMap= new HexMap(2);
        RiskGame testingGame= new RiskGame(testMap);
        
        testingGame.initPlayers(new String[]{"Tam","Jay","Arthur"});
          
        assertEquals(true,testingGame.getStartingArmies(35));
        
    }
    public void placingNewArmies(){
        HexMap testMap= new HexMap(2);
        RiskGame testingGame= new RiskGame(testMap);
        
        testingGame.initPlayers(new String[]{"Tam","Jay","Arthur"});
          
        //assertEquals(true,testingGame.getStartingArmies(35));
        
        testingGame.placeArmies(1,1,0,5);
        testingGame.nextTurn();
        testingGame.placeArmies(2,5,1,5);
        testingGame.nextTurn();
        testingGame.placeArmies(3,3,2,5);
        
        //testing 1st player after one turn
        assertEquals(true,testingGame.placeArmies(1, 1, 0, 5));
        assertEquals(false,testingGame.placeArmies(2, 2, 0, 5));
        assertEquals(false,testingGame.placeArmies(1,1, 0, 15));
        
        //testing 2nd player after one turn
        assertEquals(true,testingGame.placeArmies(2, 5, 1, 5));
        assertEquals(false,testingGame.placeArmies(2, 2, 1, 5));
        assertEquals(false,testingGame.placeArmies(1,1, 1, 15));
        
        //testing third player after one turn
        assertEquals(true,testingGame.placeArmies(3, 3, 2, 5));
        assertEquals(false,testingGame.placeArmies(2, 2, 2, 5));
        assertEquals(false,testingGame.placeArmies(1,1, 2, 15));
    }
    
    public void getNextTurnArmies(){
        HexMap testMap= new HexMap(2);
        RiskGame testingGame= new RiskGame(testMap);
        
        testingGame.initPlayers(new String[]{"Tam","Jay","Arthur"});
        //player 1's first turn
        testingGame.placeArmies(1,1,0,5);
        testingGame.nextTurn();
        
        //player 2's first turn 
        testingGame.placeArmies(2,5,1,5);
        testingGame.nextTurn();
        
        //player 3's first turn
        testingGame.placeArmies(3,3,2,5);
        testingGame.nextTurn();
        
        
        //player 1's second turn
        testingGame.placeArmies(2,1,0,5);
        testingGame.nextTurn();
      
        //player 2's second turn 
        testingGame.placeArmies(1,5,1,5);
        testingGame.nextTurn();
        
        //player 3's second turn 
        testingGame.placeArmies(3,3,2,5); //remember to change the value to 10
        testingGame.nextTurn();
      
        //testing player one after two turns
        assertEquals(true,testingGame.placeArmies(2, 1, 0, 5));
        assertEquals(false,testingGame.placeArmies(2, 1, 0, 10)); //should only be 5
        
        //testing player two after two turns
        assertEquals(true,testingGame.placeArmies(1, 5, 1, 5));
        assertEquals(false,testingGame.placeArmies(1, 5, 1, 10)); //should be 5
        
        //testing player three after two turns
        assertEquals(true,testingGame.placeArmies(3, 3, 2, 10)); 
        assertEquals(false,testingGame.placeArmies(3, 3, 2, 5)); //should be 10
    }
}
