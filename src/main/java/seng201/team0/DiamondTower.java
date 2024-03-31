package seng201.team0;

/**
 * Class representing a diamond tower
 */
public class DiamondTower extends Tower implements Purchasable {
    /**
     * Constructor
     */
    public DiamondTower(){
        super(10, 8);
    }

    public int getBuyingPrice(){
        return 14;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(){
        return "Tower that extracts and supplies diamonds to mine carts that carry diamonds";
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
