package seng201.team0.models;

/**
 * Class representing an iron tower
 */
public class IronTower extends Tower {
    /**
     * Constructor
     */
    public IronTower(){
        super("Iron", 4, 4);
    }

    public int getBuyingPrice(){
        return 8;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String setDescription(String towerType, int resourceAmount, int reloadSpeed){
        return "Tower Type: " + towerType + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
    }
}