//package edu.gatech.cs2340team2.risk.model;


//Odd Rows have the offset added to them

import java.util.Queue;
import java.util.LinkedList;


public class HexMap
{
    int[][] jsMap;
    
    public HexMap(int size)
    {
        createJSMap(size);
    }

    public void createJSMap(int size)
    {
        jsMap = new int[(size * 2) - 1][(size * 2) - 1];
        MapLocation temp = null;
        
        boolean finalRing = false;
        boolean finished = false;
        Queue<MapLocation> currentRing = new LinkedList<MapLocation>();
        Queue<MapLocation> prevRing = new LinkedList<MapLocation>();
        
        MapLocation currentLocation = new MapLocation(jsMap.length / 2, jsMap[0].length / 2);
        currentRing.add(currentLocation);
        
        for(int i = 0; i < size; i++)
        {
            finished = finalRing;
            prevRing = currentRing;
            currentRing = new LinkedList<MapLocation>();
            while(prevRing.size() > 0)
            {
                currentLocation = prevRing.poll();
                jsMap[currentLocation.row][currentLocation.col] = 1;
                for(int j = 0; j < 6; j++)
                {
                    temp = currentLocation.getNeighbor(j);
                    if(temp.row >= 0 && temp.col >= 0 && temp.row < jsMap.length && temp.col < jsMap[0].length && jsMap[temp.row][temp.col] == 0)
                    {
                        currentRing.add(temp);
                    }
                    finalRing = (temp.row == 0 || temp.col == 0 || temp.row == jsMap.length - 1 || temp.col == jsMap[0].length - 1);
                }
            }
        }
        
    }
    
    public int[][] getJSMap()
    {
        return jsMap;
    }
    
    public static void main(String[] args)
    {
        HexMap map = new HexMap(6);
        int[][] temp = map.getJSMap();
        
        for(int r = 0; r < temp.length; r++)
        {
            for(int c = 0; c < temp[r].length; c++)
            {
                System.out.print(temp[r][c] + " ");
            }
            System.out.println();
        }
      /*  
        MapLocation temp = new MapLocation(1,1);
        MapLocation temp2 = temp.getNeighbor(MapLocation.UPPER_LEFT);
        System.out.println("row: " + temp2.row + " col: " + temp2.col);
    */
    }
}

