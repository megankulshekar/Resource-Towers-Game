package seng201.team0;

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

    public String getDescription(String typeTower, int resourceAmount, int reloadSpeed){
        return "Tower that extracts and supplies gold to mine carts that carry gold";
    }
}
