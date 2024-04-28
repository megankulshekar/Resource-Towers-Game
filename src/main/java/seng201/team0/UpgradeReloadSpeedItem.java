package seng201.team0;

/**
 * Class representing an item for decreasing a tower's reload speed
 */
public class UpgradeReloadSpeedItem extends Item {
    /**
     * Constructor
     */
    public UpgradeReloadSpeedItem(){
        super();
    }

    public int getBuyingPrice(){
        return 15;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(String typeTower, int resourceAmount, int reloadSpeed){
        return "Decreases a tower's reload speed";
    }

    /**
     * Decreases reload speed of tower
     * @param tower Tower item is used on
     */
    public void useItem(Tower tower){
        tower.decreaseReloadSpeed(2);
        used = true;
    }
}