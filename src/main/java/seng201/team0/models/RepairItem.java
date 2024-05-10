package seng201.team0.models;

/**
 * Class representing an item for repairing towers
 */
public class RepairItem extends Item {
    /**
     * Constructor
     */
    public RepairItem(){
        super();
    }

    public int getBuyingPrice(){
        return 15;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(){
        return "Repairs a tower back to working order";
    }

    public String setDescription(String towerType, int resourceAmount, int reloadSpeed){
        return "Tower Type: " + towerType + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
    }

    /**
     * Repairs tower by setting broken variable to false
     * @param tower Tower item is used on
     */
    public void useItem(Tower tower){
        tower.setBroken(false);
        used = true;
    }

    public void noUseItem(Tower tower){
        tower.setBroken(true);
    }
}