package seng201.team0;

/**
 * Class representing a coal tower
 */
public class CoalTower extends Tower {
    /**
     * Constructor
     */
    public CoalTower(){
        super("Coal", 2, 2);
    }

    public int getBuyingPrice(){
        return 6;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(){
        return "Tower that extracts and supplies coal to mine carts that carry coal";
    }
}
