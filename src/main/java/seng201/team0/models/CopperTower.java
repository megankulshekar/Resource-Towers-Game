package seng201.team0.models;

/**
 * Class representing a copper tower
 */
public class CopperTower extends Tower {
    /**
     * Constructor
     */
    public CopperTower(){
        super("Copper", 3, 3);
    }

    public int getBuyingPrice(){
        return 7;
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
        return "Tower that extracts and supplies copper to mine carts that carry copper";
    }
}
