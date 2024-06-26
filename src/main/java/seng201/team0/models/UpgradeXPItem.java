package seng201.team0.models;

/**
 * Class representing an item for increasing a tower's XP level
 */
public class UpgradeXPItem extends Item {
    /**
     * Constructor
     */
    public UpgradeXPItem(){
        super();
    }

    public int getBuyingPrice(){
        return 15;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(){
        return "Increases a tower's XP level";
    }

    /**
     * Increases XP level of tower
     * @param tower Tower item is used on
     */
    public void useItem(Tower tower){
        tower.increaseXP(5);
        used = true;
    }
}