package seng201.team0.models;

/**
 * Class representing a uranium tower
 */
public class UraniumTower extends Tower {
    /**
     * Constructor
     */
    public UraniumTower(){
        super("Uranium", 10, 8);
    }

    public int getBuyingPrice(){
        return 12;
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
        return "Tower that extracts and supplies uranium to mine carts that carry uranium";
    }

    public String setDescription(String towerType, int resourceAmount, int reloadSpeed){
        return "Tower Type: " + towerType + "\n\nResource Amount: " + resourceAmount + "\n\nReload Speed: " + reloadSpeed;
    }

    /**
     * Increases tower's level by 1
     * Increases tower's resource amount by 5
     * Decreases tower's reload speed amount by 5
     */
    @Override
    public void levelUp() {
        level++;
        increaseResourceAmount(5);
        decreaseReloadSpeed(5);
    }
}
