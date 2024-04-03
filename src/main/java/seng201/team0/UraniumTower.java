package seng201.team0;

/**
 * Class representing a uranium tower
 */
public class UraniumTower extends Tower implements Purchasable{
    /**
     * Constructor
     */
    public UraniumTower(){
        super(10, 8);
    }
    public int getBuyingPrice(){
        return 14;
    }
    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }
    public String getDescription(){
        return "Tower that extracts and supplies uranium to mine carts that carry uranium";
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
