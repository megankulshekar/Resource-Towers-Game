package seng201.team0;

/**
 * Class representing a diamond tower
 */
public class DiamondTower extends Tower {
    /**
     * Constructor
     */
    public DiamondTower(){
        super(10, 5);
    }

    /**
     * Increments tower's level by 1
     * Increments tower's resource amount by 5
     * Decrements tower's reload speed amount by 5
     */
    @Override
    public void levelUp() {
        level++;
        incrementResourceAmount(5);
        decrementReloadSpeed(5);
    }
}
