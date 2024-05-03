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

    public String getDescription(){
        if (broken){
            return "Tower is broken";
        } else{
            return "Tower Type: " + resourceType + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
        }
    }

    public String getDescription(String typeTower, int resourceAmount, int reloadSpeed){
        return "Tower that extracts and supplies iron to mine carts that carry iron";
    }

    public String setDescription(String towerType, int resourceAmount, int reloadSpeed){
        return "Tower Type: " + towerType + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
    }
}