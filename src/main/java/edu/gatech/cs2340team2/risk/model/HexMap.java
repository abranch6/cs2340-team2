package edu.gatech.cs2340team2.risk.model;


//Odd Rows have the offset added to them

import java.util.Stack;
import java.util.LinkedList;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HexMap
{
    int[][] jsMap;
    Territory[][] territoryMap;
    Gson json;
    
    public HexMap(int size)
    {
        createJSMap(size);
        generateHexMap(size);
        json = new Gson();
    }

    /**
     * Creates the int map for javascript
     * @param size
     *      size of the map to make
     */
    public void createJSMap(int size)
    {

        jsMap = new int[(size * 2) - 1][(size * 2) - 1];
        
        
        for(int i = 0; i < jsMap[0].length; i++)
        {
            jsMap[size - 1][i] = 1;
        }
        
        for(int r = size - 1; r > 0; r--)
        {
            boolean firstOne = false;
            for(int c = 0; c < jsMap[0].length; c++)
            {
                MapLocation loc = new MapLocation(r, c);
                
                if(firstOne && jsMap[r][c] == 1)
                {
                    loc = loc.getNeighbor(MapLocation.UPPER_LEFT);
                    jsMap[loc.row][loc.col] = 1;
                }
                
                firstOne = (jsMap[r][c] == 1);
            }
        }
        
        for(int r = 0; r < size - 1; r++)
        {
            for(int c = 0; c < jsMap[0].length; c++)
            {
                jsMap[jsMap.length - 1 - r][c] = jsMap[r][c];
            }
        }
    }
    
    public void generateHexMap(int size)
    {
        territoryMap = new Territory[(size * 2) - 1][(size * 2) - 1];
        Stack<MapLocation> locations = new Stack<MapLocation>();
        MapLocation currentLocation = new MapLocation(territoryMap.length / 2, territoryMap[0].length / 2);
        Random rnd = new Random();
        int territories = (int)(Math.sqrt(3) / 4.0 * size * size * 6.0 * 0.60);
        int rand = 0;
        int neighborCount;
        MapLocation[] neighbors = new MapLocation[6];
        
        jsMap[currentLocation.row][currentLocation.col] = 2;
        territoryMap[currentLocation.row][currentLocation.col] = new Territory(currentLocation.row,currentLocation.col);
        locations.push(currentLocation);
       
        
        while(locations.size() > 0 && territories > 0)
        {
            neighborCount = 0;
            currentLocation = locations.peek();
            
            for(int i = 0; i < 6; i++)
            {
                MapLocation temp = currentLocation.getNeighbor(i);
                
                if(temp.row >= 0 && temp.col >= 0 && temp.row < jsMap.length && temp.col < jsMap[0].length && jsMap[temp.row][temp.col] == 1)
                {
                    neighbors[neighborCount] = temp;
                    neighborCount++;
                }
            }
            
            if(neighborCount > 0)
            {
                rand = rnd.nextInt(neighborCount);
                locations.push(neighbors[rand]);
                int r = neighbors[rand].row;
                int c = neighbors[rand].col;
                jsMap[r][c] = 2;
                territoryMap[r][c] = new Territory(r,c);
                territories--;
            }
            else
            {
                locations.pop();
            }
            
            
        }
    }
    
    /**
     * gets the int map for javascript
     */
    public int[][] getJSMap()
    {
        return jsMap;
    }
    
    /**
     * gets the json String to return to the browser javascript
     */
    public String getJsonMap()
    {
        return json.toJson(jsMap);
    }
    public Territory[][] getTerritoryArray()
    {
	return territoryMap;
    }
    public Territory getTerritory(MapLocation loc)
    {
        return territoryMap[loc.row][loc.col];
    }
    
    public boolean areTerritoriesAdjacent(Territory terrOne, Territory terrTwo)
    {
    	boolean terrOneCheck = false;
    	boolean terrTwoCheck = false;
    	
    	for (int i = 0; i < 6; i++)
    	{
    		MapLocation neighborOfTerrOne = terrOne.getMapLocation().getNeighbor(i);
    		if (getTerritory(neighborOfTerrOne) == terrTwo)
    			terrOneCheck = true;
    		
    		MapLocation neighborOfTerrTwo = terrTwo.getMapLocation().getNeighbor(i);
    		if (getTerritory(neighborOfTerrTwo) == terrOne)
    			terrTwoCheck = true;
    	}
    	
    	if (terrOneCheck && terrTwoCheck)
    		return true;
    	
    	return false;
    }
}
