package seng201.team0.models;

/**
 * Class representing a gold tower
 */
public class GoldTower extends Tower {
    /**
     * Constructor
     */
    public GoldTower(){
        super("Gold", 5, 5);
    }

    public int getBuyingPrice(){
        return 9;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }
}
