package seng201.team0.models;

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
     * @param type The type of resource the tower gives
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
     * Gets the variable for showing if tower is broken
     * @return Boolean broken value
     */
    public boolean isBroken(){
        return broken;
    }

    /**
     * Gets the resource type of the tower
     * @return Resource type
     */
    public String getResourceType(){
        return resourceType;
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
     * Gets description of tower statistics
     * @return Tower statistics
     */
    public String getDescription(){
        if (broken){
            return "Tower is broken";
        } else{
            return "Tower Type: " + resourceType + "\n" +
                    "Level: " + level + "\n" +
                    "XP: " + XP + "\n" +
                    "Resource Amount: " + resourceAmount + "\n" +
                    "Reload Speed: " + reloadSpeed;
        }
    }

    /**
     * Increases tower's level by 1
     * Reduces XP modulo 10
     * Increases tower's resource amount by 2
     * Decreases tower's reload speed by 2
     */
    public void levelUp(){
        level++;
        XP = XP % 10;
        increaseResourceAmount(2);
        decreaseReloadSpeed(2);
    }

    /**
     * Increases tower's XP level by amount given
     * @param increaseAmount Amount XP level is increased by
     */
    public void increaseXP(int increaseAmount){
        XP += increaseAmount;
        if (XP >= 10){
            levelUp();
        }
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
        if (reloadSpeed < 1){
            reloadSpeed = 1;
        }
    }
}