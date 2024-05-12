package seng201.team0.models;

/**
 * Class representing a diamond tower
 */
public class DiamondTower extends Tower{
    /**
     * Constructor
     */
    public DiamondTower(){
        super("Diamond", 8, 8);
    }

    public int getBuyingPrice(){
        return 14;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    /**
     * Increases tower's level by 1
     * Reduces XP modulo 10
     * Increases tower's resource amount by 5
     * Decreases tower's reload speed amount by 5
     */
    @Override
    public void levelUp() {
        level++;
        XP = XP % 10;
        increaseResourceAmount(5);
        decreaseReloadSpeed(5);
    }
}
