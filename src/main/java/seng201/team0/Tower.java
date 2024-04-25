package seng201.team0;

/**
 * Class representing the general functionality of any tower in game
 */
public abstract class Tower implements Purchasable {
    /**
     * The tower's level
     */
    protected int level = 1;

    /**
     * The tower's XP level
     */
    protected int XP = 0;

    /**
     * The variable for indicating whether tower is broken
     */
    protected boolean broken = false;

    /**
     * The type of resource the tower gives
     */
    protected String resourceType;

    /**
     * The amount of resource the tower gives
     */
    protected int resourceAmount;

    /**
     * The time for the tower to reload on its resource
     */
    protected int reloadSpeed;

    /**
     * Constructor
     * @param startAmount The amount of resource the tower gives at the start
     * @param startSpeed The reload time in between when the tower gives resources
     *                   at the start
     */
    public Tower(String type, int startAmount, int startSpeed){
        resourceType = type;
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
     * Gets the current XP level of the tower
     * @return Current XP level
     */
    public int getXP(){
        return XP;
    }

    /**
     * Gets the current resource amount of the tower
     * @return Current resource amount
     */
    public int getResourceAmount(){
        return resourceAmount;
    }

    /**
     * Gets the current reload speed of the tower
     * @return Current reload speed
     */
    public int getReloadSpeed(){
        return reloadSpeed;
    }

    /**
     * Increases tower's level by 1
     * Increases tower's resource amount by 2
     * Decreases tower's reload speed by 2
     */
    public void levelUp(){
        level++;
        increaseResourceAmount(2);
        decreaseReloadSpeed(2);
    }

    /**
     * Increases tower's XP level by amount given
     * @param increaseAmount Amount XP level is increased by
     */
    public void increaseXP(int increaseAmount){
        XP += increaseAmount;
    }

    /**
     * Sets broken variable to setting given
     * @param setting True/False variable
     */
    public void setBroken(boolean setting){
        broken = setting;
    }

    /**
     * Increases tower's resource amount by amount given
     * @param increaseAmount Amount resource amount is increased by
     */
    public void increaseResourceAmount(int increaseAmount){
        resourceAmount += increaseAmount;
    }

    /**
     * Decreases tower's reload speed by amount given
     * @param decreaseAmount Amount reload speed is decreased by
     */
    public void decreaseReloadSpeed(int decreaseAmount){
        reloadSpeed -= decreaseAmount;
    }
}