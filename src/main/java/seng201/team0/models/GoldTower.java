package seng201.team0.models;

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

    public String getDescription(){
        if (broken){
            return "Tower is broken";
        } else{
            return "Tower Type: " + resourceType + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
        }
    }

    public String getDescription(String typeTower, int resourceAmount, int reloadSpeed){
        return "Tower that extracts and supplies gold to mine carts that carry gold";
    }

    public String setDescription(String towerType, int resourceAmount, int reloadSpeed){
        return "Tower Type: " + towerType + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
    }
}
