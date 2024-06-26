package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the user's inventory
 */
public class Inventory {
    /**
     * Array of towers being used in game
     */
    private Tower[] mainTowers = new Tower[5];

    /**
     * Array of towers not being used in game
     */
    private Tower[] reserveTowers = new Tower[5];

    /**
     * Array of descriptions for each tower in mainTowers
     */
    private String[] mainTowerDescriptions = new String[5];

    /**
     * Array of descriptions for each tower in reserveTowers
     */
    private String[] reserveTowerDescriptions = new String[5];

    /**
     * Stores upgrade descriptions for use in other classes
     */
    private List<String> upgradesBought = new ArrayList<String>();

    /**
     * Array of items the user has bought
     */
    private List<Item> items = new ArrayList<Item>();

    /**
     * Gets all main towers in the array
     * @return Main towers
     */
    public Tower[] getAllMainTowers() {
        return mainTowers;
    }

    /**
     * Gets all reserve towers in the array
     * @return Reserve towers
     */
    public Tower[] getAllReserveTowers() {
        return reserveTowers;
    }

    /**
     * Gets a tower at specified index in main towers list
     * @param indexValue Index used to get tower
     * @return Tower at specified index
     */
    public Tower getMainTowers(int indexValue){
        if(indexValue >= 0 && indexValue <= 4){
            return mainTowers[indexValue];
        }
        return null;
    }

    /**
     * Gets corresponding tower description at specified index in main tower descriptions list
     * @param indexValue Index used to get tower description
     * @return Tower description at specified index
     */
    public String getMainTowerDescriptions(int indexValue){
        if(indexValue >= 0 && indexValue <= 4){
            return mainTowerDescriptions[indexValue];
        }
        return null;
    }

    /**
     * Sets new tower description if upgrade has been applied to a main tower
     * @param indexValue The main tower's description that will be modified
     * @param description New description of main tower
     */
    public void setMainTowerDescriptions(int indexValue, String description){
        mainTowerDescriptions[indexValue] = description;
    }

    /**
     * Gets a tower at specified index in reserve towers list
     * @param indexValue Index used to get tower
     * @return Tower at specified index
     */
    public Tower getReserveTowers(int indexValue){
        if(indexValue >= 0 && indexValue <= 4){
            return reserveTowers[indexValue];
        }
        return null;
    }

    /**
     * Gets corresponding tower description at specified index in reserve tower descriptions list
     * @param indexValue Index used to get tower description
     * @return Tower description at specified index
     */
    public String getReserveTowerDescriptions(int indexValue){
        if(indexValue >= 0 && indexValue <= 4){
            return reserveTowerDescriptions[indexValue];
        }
        return null;
    }

    /**
     * Sets new tower description if upgrade has been applied to a reserve tower
     * @param indexValue The reserve tower's description that will be modified
     * @param description New description of reserve tower
     */
    public void setReserveTowerDescriptions(int indexValue, String description){
        reserveTowerDescriptions[indexValue] = description;
    }

    /**
     * Gets list of upgrades descriptions
     * @return Descriptions of all upgrades the player has
     */
    public List<String> getUpgradesBought() {
        return upgradesBought;
    }

    /**
     * Gets list of upgrades
     * @return Upgrades the player has
     */
    public List<Item> getItems(){
        return items;
    }

    /**
     * Adds new purchasable to the inventory.
     * If purchasable is a tower, purchasable is added to mainTowers array.
     * If mainTowers is full, then purchasable is added to reserveTowers array.
     * If reserveTowers is also full, purchasable is not added.
     * @param purchasable The new purchasable being added to the inventory
     * @param description The description of the purchasable being added
     */
    public void add(Purchasable purchasable, String description){
        Tower tower = (Tower) purchasable;
        for (int i = 0; i < 5; i++){
            if (mainTowers[i] == null){
                mainTowers[i] = tower;
                mainTowerDescriptions[i] = description;
                return;
            }
        }
        for (int i = 0; i < 5; i++){
            if (reserveTowers[i] == null){
                reserveTowers[i] = tower;
                reserveTowerDescriptions[i] = description;
                return;
            }
        }
    }

    /**
     * Removes purchasable from inventory.
     * If purchasable is a tower, purchasable is removed from mainTowers array.
     * If purchasable not in mainTowers, purchasable is removed from reserveTowers array.
     * If purchasable not in reserveTowers, tower is not removed.
     * @param purchasable The purchasable being removed from the inventory
     */
    public void remove(Purchasable purchasable){
        Tower tower = (Tower) purchasable;
        for (int i = 0; i < 5; i++) {
            if (mainTowers[i] == tower) {
                mainTowers[i] = null;
                mainTowerDescriptions[i] = "";
                return;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (reserveTowers[i] == tower) {
                reserveTowers[i] = null;
                reserveTowerDescriptions[i] = "";
                return;
            }
        }
    }

    /**
     * Adds upgrade to upgrades array list and the corresponding description to upgrades description array list
     * @param item Upgrade
     * @param description Description of upgrade
     */
    public void addUpgrade(Item item, String description){
        items.add(item);
        upgradesBought.add(description);
    }

    /**
     * Removes upgrade from upgrades array list and removes the corresponding description from upgrades description array list
     * @param item Upgrade
     * @param index Location of upgrade description in array list
     */
    public void removeUpgrade(Item item, int index){
        items.remove(item);
        upgradesBought.remove(index);
    }

    /**
     * Swaps a main tower and a reserve tower
     * @param mainTowerIndex Index of main tower being swapped
     * @param reserveTowerIndex Index of reserve tower being swapped
     */
    public void swapTowers(int mainTowerIndex, int reserveTowerIndex){
        Tower mainToReserve = mainTowers[mainTowerIndex];
        Tower reserveToMain = reserveTowers[reserveTowerIndex];
        mainTowers[mainTowerIndex] = reserveToMain;
        reserveTowers[reserveTowerIndex] = mainToReserve;
        String newReservedTowerDescription = mainTowerDescriptions[mainTowerIndex];
        mainTowerDescriptions[mainTowerIndex] = reserveTowerDescriptions[reserveTowerIndex];
        reserveTowerDescriptions[reserveTowerIndex] = newReservedTowerDescription;
    }

    /**
     * Upgrades a tower by using an item, then removes item from inventory
     * @param itemIndex List index of the item being used
     * @param tower Tower being upgraded
     */
    public void upgradeTower(int itemIndex, Tower tower){
        items.get(itemIndex).useItem(tower);
        items.remove(itemIndex);
        upgradesBought.remove(itemIndex);
    }
}