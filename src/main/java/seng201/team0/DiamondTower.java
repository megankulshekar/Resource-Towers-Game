package seng201.team0;

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
        return 12;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(int resourceAmount, int reloadSpeed){
        return "Tower that extracts and supplies diamonds to mine carts that carry diamonds";
    }

    /**
     * Increases tower's level by 1
     * Increases tower's resource amount by 5
     * Decreases tower's reload speed amount by 5
     */
    @Override
    public void levelUp() {
        level++;
        increaseResourceAmount(5);
        decreaseReloadSpeed(5);
    }
}
