package seng201.team0.models;

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

    public String getDescription(){
        return "Increases a tower's resource amount";
    }

    public String getDescription(String typeTower, int resourceAmount, int reloadSpeed){
        return "Increases a tower's resource amount";
    }

    public String setDescription(String towerType, int resourceAmount, int reloadSpeed){
        return "Tower Type: " + towerType + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
    }

    /**
     * Increases resource amount of tower
     * @param tower Tower item is used on
     */
    public void useItem(Tower tower){
        tower.increaseResourceAmount(2);
        used = true;
    }

    public void noUseItem(Tower tower){
        tower.increaseResourceAmount(-2);
    }
}