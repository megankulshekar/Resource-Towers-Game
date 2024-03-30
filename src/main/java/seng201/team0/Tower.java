package seng201.team0;

/**
 * Class representing the general functionality of any tower in game
 */
public class Tower {
    /**
     * The tower's level
     */
    private int level = 1;
    /**
     * The amount of resource the tower gives
     */
    private int resourceAmount;
    /**
     * The time for the tower to reload on its resource
     */
    private int reloadSpeed;

    /**
     * Constructor
     * @param startAmount The amount of resource the tower gives at the start
     * @param startSpeed The reload time in between when the tower gives resources
     *                   at the start
     */
    public Tower(int startAmount, int startSpeed){
        resourceAmount = startAmount;
        reloadSpeed = startSpeed;
    }

    /**
     * Gets the current level of the tower
     * @return Current level
     */
    public int getLevel(){
        return level;
    }

    /**
     * Increments tower's level by amount given
     * @param incrementAmount Amount level is incremented by
     */
    public void incrementLevel(int incrementAmount){
        level += incrementAmount;
    }

    /**
     * Gets the current resource amount of the tower
     * @return Current resource amount
     */
    public int getResourceAmount(){
        return resourceAmount;
    }

    /**
     * Increments tower's resource amount by amount given
     * @param incrementAmount Amount resource amount is incremented by
     */
    public void incrementResourceAmount(int incrementAmount){
        resourceAmount += incrementAmount;
    }

    /**
     * Gets the current reload speed of the tower
     * @return Current reload speed
     */
    public int getReloadSpeed(){
        return reloadSpeed;
    }

    /**
     * Increments tower's reload speed by amount given
     * @param incrementAmount Amount reload speed is incremented by
     */
    public void incrementReloadSpeed(int incrementAmount){
        reloadSpeed += incrementAmount;
    }
}
