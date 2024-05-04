package seng201.team0.models;

import java.util.Arrays;
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

    private String[] mainTowerDescriptions = new String[5];

    private String[] reserveTowerDescriptions = new String[5];

    /**
     * Array of items the user has bought
     */
    private List<Item> items = new ArrayList<Item>();

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
     * Prints the user's inventory
     */
    public void view(){
        System.out.println("Main Towers: " + Arrays.toString(mainTowers));
        System.out.println("Reserve Towers: " + Arrays.toString(reserveTowers));
        System.out.println("Items: " + items);
    }

    /**
     * Adds new purchasable to the inventory.
     * If purchasable is a tower, purchasable is added to mainTowers array.
     * If mainTowers is full, then purchasable is added to reserveTowers array.
     * If reserveTowers is also full, purchasable is not added.
     * Otherwise, if purchasable is an item, purchasable is added to items array.
     * @param purchasable The new purchasable being added to the inventory
     */
    public void add(Purchasable purchasable, String description){
        if (purchasable instanceof Tower){
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
            System.out.println("Could not add tower due to maximum number of towers reached");
        } else {
            Item item = (Item) purchasable;
            items.add(item);
        }
    }

    /**
     * Removes purchasable from inventory.
     * If purchasable is a tower, purchasable is removed from mainTowers array.
     * If purchasable not in mainTowers, purchasable is removed from reserveTowers array.
     * If purchasable not in reserveTowers, tower is not removed.
     * Otherwise, if purchasable is an item, purchasable is removed from items array.
     * @param purchasable The purchasable being removed from the inventory
     */
    public void remove(Purchasable purchasable){
        if (purchasable instanceof Tower) {
            Tower tower = (Tower) purchasable;
            for (int i = 0; i < 5; i++) {
                if (mainTowers[i] == tower) {
                    mainTowers[i] = null;
                    return;
                }
            }
            for (int i = 0; i < 5; i++) {
                if (reserveTowers[i] == tower) {
                    reserveTowers[i] = null;
                    return;
                }
            }
            System.out.println("Could not remove tower as it is not in tower arrays");
        } else {
            Item item = (Item) purchasable;
            items.remove(item);
        }
    }

    public String getMainTowerDescriptions(int indexValue){
        if(indexValue >= 0 && indexValue <= 4){
            return mainTowerDescriptions[indexValue];
        }
        return null;
    }

    public Tower getReserveTowers(int indexValue){
        if(indexValue >= 0 && indexValue <= 4){
            return reserveTowers[indexValue];
        }
        return null;
    }

    public String getReserveTowerDescriptions(int indexValue){
        if(indexValue >= 0 && indexValue <= 4){
            return reserveTowerDescriptions[indexValue];
        }
        return null;
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
    }

    /**
     * Sells purchasable by increasing user's money by the selling price
     * and removing purchasable from user's inventory
     * @param purchasable Purchasable being sold
     * @param game The game environment
     */
    public void sell(Purchasable purchasable, GameEnvironment game){
        game.increaseMoney(purchasable.getSellingPrice());
        remove(purchasable);
    }
}