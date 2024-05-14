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
}
