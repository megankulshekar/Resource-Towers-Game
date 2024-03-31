package seng201.team0;

/**
 * Class representing the general functionality of any tower in game
 */
public class Tower {
    /**
     * The tower's level
     */
    protected int level = 1;
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
     * Increments tower's level by amount given
     * Increments tower's resource amount by 2
     * Decrements tower's reload speed by 2
     */
    public void levelUp(){
        level++;
        incrementResourceAmount(2);
        decrementReloadSpeed(2);
    }

    /**
     * Increments tower's resource amount by amount given
     * @param incrementAmount Amount resource amount is incremented by
     */
    public void incrementResourceAmount(int incrementAmount){
        resourceAmount += incrementAmount;
    }

    /**
     * Decrements tower's reload speed by amount given
     * @param decrementAmount Amount reload speed is decremented by
     */
    public void decrementReloadSpeed(int decrementAmount){
        reloadSpeed -= decrementAmount;
    }

    public static void main(String[] args){
        Tower tower1 = new Tower(1, 1);
        DiamondTower tower2 = new DiamondTower();
        tower1.levelUp();
        tower2.levelUp();
        System.out.println(tower1.getResourceAmount());
        System.out.println(tower2.getResourceAmount());
        
    }
}