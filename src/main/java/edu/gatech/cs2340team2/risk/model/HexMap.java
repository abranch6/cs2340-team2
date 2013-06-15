public class HexMap
{
    int[][] jsMap;
    
    public HexMap(int size)
    {
	
    }

    public void createJSMap(int size)
    {
        jsMap = new int[(size * 2) - 1][(size * 2) - 1];
        MapLocation temp = null;
        
        Queue<MapLocation> locations = new LinkedList<MapLocation>();
        
        MapLocation currentLocation = new MapLocation(jsMap.length / 2, jsMap[0].length / 2);
        locations.add(currentLocation);
        
        while(locations.size() > 0)
        {
            temp = currentLocation.getNeighbor();
            if(!(temp.row == 0 || temp.col == 0 || temp.row == jsMap.length || temp.col == jsMap[0].length))
            {
                locations.add(currentLocation);
            }
            currentLocation = locations.poll();
        }
    }
}
