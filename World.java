class World
{
    public static int NORTH = 0;
    public static int NORTHEAST = 1;
    public static int EAST = 2;
    public static int SOUTHEAST = 3;
    public static int SOUTH = 4;
    public static int SOUTHWEST = 5;
    public static int WEST = 6;
    public static int NORTHWEST = 7;
    private Boat[][] map;

    public World(int x, int y)
    {
        map = new Boat[Math.min(Math.max(4,x), 10)][Math.min(Math.max(4,y), 10)];
    }

    public int getWidth() { return map.length; }
    public int getHeight() { return map[0].length; }
    public Boat getOccupant(Coordinates coordinates) 
    {
        return map[coordinates.getX()][getHeight()];
    }
    public boolean isLocationValid(Coordinates coordinates)
    {
        if (coordinates.getX() > getWidth()) return false;
        if (coordinates.getY() > getHeight()) return false;
        return true;
    }
    public boolean isLocationOccupied(Coordinates coordinates)
    {
        if (map[coordinates.getX()][coordinates.getY()] != null) return true;
        return false;
    }
    public boolean setOccupant(Boat boat, Coordinates coordinates)
    {
        if (isLocationOccupied(coordinates)) return false;
        map[coordinates.getX()][coordinates.getY()] = boat;
        return true;
    }
    public Coordinates getAdjacentLocation(Coordinates coordinates, int input)
    {
        Coordinates coordinatesTemp = new Coordinates();
        if (input == World.NORTH) coordinatesTemp.setCoordinates(coordinates.getX(), coordinates.getY()+1);
        if (input == World.NORTHEAST) coordinatesTemp.setCoordinates(coordinates.getX()+1, coordinates.getY()+1);
        if (input == World.EAST) coordinatesTemp.setCoordinates(coordinates.getX()+1, coordinates.getY());
        if (input == World.SOUTHEAST) coordinatesTemp.setCoordinates(coordinates.getX()+1, coordinates.getY()-1);
        if (input == World.SOUTH) coordinatesTemp.setCoordinates(coordinates.getX(), coordinates.getY()-1);
        if (input == World.SOUTHWEST) coordinatesTemp.setCoordinates(coordinates.getX()-1, coordinates.getY()-1);
        if (input == World.WEST) coordinatesTemp.setCoordinates(coordinates.getX()-1, coordinates.getY());
        if (input == World.NORTHWEST) coordinatesTemp.setCoordinates(coordinates.getX()-1, coordinates.getY()+1);
        if (!isLocationValid(coordinatesTemp)) return null;
        return coordinatesTemp;
    }
    public String drawTeamMap(Boat[][] boatarray, int view)
    {
        String mapTeam = "@ ";
        for (int i = 0; i < boatarray.length; i++)
        {
            mapTeam = " " + i+1 + " ";
        }
        if (view == 1)
        {
            for (int i = 0; i < boatarray.length; i++)
            {
                mapTeam = mapTeam + ("A"+i) + " ";
                for (int p = 0; p < boatarray[0].length; p++)
                {
                    mapTeam += "###";
                }
            }
        }
        return mapTeam;
    }
}