package seng201.team0;

/**
 * Class representing an item for increasing a tower's resource amount
 */
public class UpgradeResourceAmountItem extends Item {
    /**
     * Constructor
     */
    public UpgradeResourceAmountItem(){
        super();
    }

    public int getBuyingPrice(){
        return 15;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(String typeTower, int resourceAmount, int reloadSpeed){
        return "Increases a tower's resource amount";
    }

    /**
     * Increases resource amount of tower
     * @param tower Tower item is used on
     */
    public void useItem(Tower tower){
        tower.increaseResourceAmount(2);
        used = true;
    }
}