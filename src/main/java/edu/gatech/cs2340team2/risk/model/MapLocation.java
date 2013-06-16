//package edu.gatech.cs2340team2.risk.model;

public class MapLocation
{
    public final int row;
    public final int col;
    
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UPPER_LEFT = 2;
    public static final int UPPER_RIGHT = 3;
    public static final int LOWER_LEFT = 4;
    public static final int LOWER_RIGHT = 5;
    private static final int[][] neighborIndex = {{ 0,-1},
                                                  { 0, 1},
                                                  {-1,-1},
                                                  {-1, 1},
                                                  { 1,-1},
                                                  { 1, 1}};
    
    
    public MapLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public MapLocation getNeighbor(int neighbor)
    {
        int newRow = 0;
        int newCol = 0;
        
        if(neighbor == LEFT || neighbor == RIGHT)
        {
            newRow = row + neighborIndex[neighbor][0];
            newCol = col + neighborIndex[neighbor][1];
        }
        else if(neighbor == UPPER_LEFT || neighbor == LOWER_LEFT)
        {
            newRow = row + neighborIndex[neighbor][0];
            newCol = col + neighborIndex[neighbor][1] * ((row % 2) ^ 0x1);
        }
        else if(neighbor == UPPER_RIGHT || neighbor == LOWER_RIGHT)
        {
            newRow = row + neighborIndex[neighbor][0];
            newCol = col + (neighborIndex[neighbor][1] * row % 2);
        }
        
        return new MapLocation(newRow,newCol);
    }
}