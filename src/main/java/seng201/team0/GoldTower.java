package seng201.team0;

/**
 * Class representing a gold tower
 */
public class GoldTower extends Tower implements Purchasable {
    /**
     * Constructor
     */
    public GoldTower(){
        super(8, 5);
    }

    public int getBuyingPrice(){
        return 12;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(){
        return "Tower that extracts and supplies gold to mine carts that carry gold";
    }
}
