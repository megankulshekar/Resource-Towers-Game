package seng201.team0;

/**
 * Class representing a copper tower
 */
public class CopperTower extends Tower {
    /**
     * Constructor
     */
    public CopperTower(){
        super(3, 3);
    }

    public int getBuyingPrice(){
        return 7;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(){
        return "Tower that extracts and supplies copper to mine carts that carry copper";
    }
}
