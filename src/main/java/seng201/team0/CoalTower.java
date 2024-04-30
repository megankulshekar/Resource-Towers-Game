package seng201.team0;

/**
 * Class representing a coal tower
 */
public class CoalTower extends Tower {
    /**
     * Constructor
     */
    public CoalTower(){
        super("Coal", 2, 2);
    }

    public int getBuyingPrice(){
        return 6;
    }

    public int getSellingPrice(){
        return getBuyingPrice() / 2;
    }

    public String getDescription(){
        return "Tower Type: " + resourceType + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
    }
    public String getDescription(String type, int resourceAmount, int reloadSpeed){
        return "Tower Type: " + type + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
    }
}
