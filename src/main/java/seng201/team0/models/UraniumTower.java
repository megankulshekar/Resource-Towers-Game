package seng201.team0.models;

/**
 * Class representing a uranium tower
 */
public class UraniumTower extends Tower {
    /**
     * Constructor
     */
    public UraniumTower(){
        super("Uranium", 10, 8);
    }

    public int getBuyingPrice(){
        return 12;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }
}
