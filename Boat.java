abstract class Boat
{
    private int team;
    private Coordinates location;
    private int direction;
    private int health;
    private int strength;
    private int vision;

    public Boat(int team, Coordinates location, int direction, int health, int strength, int vision)
    {
        this.team = team;
        this.location = location;
        this.direction = direction;
        this.health = health;
        this.strength = strength;
        this.vision = vision;
    }
    public int getTeam() { return team; }
    public Coordinates getLocation() { return location; }
    public int getHealth() { return health; }
    public int getStrength() { return strength;} 
    public int getVision() { return vision; }
    public abstract String getID();
    public String getDirection()
    {
        if (direction == 0) return "↑";
        if (direction == 1) return "↗";
        if (direction == 2) return "→";
        if (direction == 3) return "↘";
        if (direction == 4) return "↓";
        if (direction == 5) return "↙";
        if (direction == 6) return "←";
        if (direction == 7) return "↖";
        return " ";
    }
    public abstract String act(int[] choices, World world);
    public abstract String getActions();
    public String move(World world)
    {
        if (!world.isLocationValid(world.getAdjacentLocation(location, direction))) 
            return getID() + " cannot move off the map.";
        if (world.isLocationOccupied(world.getAdjacentLocation(location, direction)))
            return getID() + " cannot move from " + location + " to " + world.getAdjacentLocation(location, direction).toString() + "as it is occupied.";
        else if (!world.isLocationOccupied(world.getAdjacentLocation(location, direction)))
        {
            world.setOccupant(this, world.getAdjacentLocation(location, direction));
            world.setOccupant(null, location);
            String returnString = getID() + " moved from " + location + " to " + world.getAdjacentLocation(location, direction).toString();
            location = world.getAdjacentLocation(location, direction);
            return returnString;
        }
        return "Error";
    }
    public String turn(int direction)
    {
        this.direction += direction;
        String turnDirection = "";
        if (direction > 0) turnDirection = "right";
        else turnDirection = "left";
        String directionString = "";
        if (this.direction == 1) directionString = "N";
        if (this.direction == 2) directionString = "NE";
        if (this.direction == 3) directionString = "E";
        if (this.direction == 4) directionString = "SE";
        if (this.direction == 5) directionString = "S";
        if (this.direction == 6) directionString = "SW";
        if (this.direction == 7) directionString = "W";
        return getID() + " turned " + turnDirection + " and is now facing " + directionString;
    }
    public String takeHit(int strength)
    {
        this.health -= strength;
        if (health <= 0) 
        {
            this.health = 0;
            return getID() + " has been sunk!";
        }
        else return getID() + " takes " + strength + " damage.";
    }
    public void setLocation(Coordinates coordinates) { location = coordinates; }
    public String toString() { return getTeam() + getID(); }
}