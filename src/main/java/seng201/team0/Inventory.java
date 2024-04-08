package seng201.team0;

import java.util.Arrays;
import java.util.ArrayList;

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
     * Array of items the user has bought
     */
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Prints the user's inventory
     */
    public void view(){
        System.out.println("Main Towers: " + Arrays.toString(mainTowers));
        System.out.println("Reserve Towers: " + Arrays.toString(reserveTowers));
        System.out.println("Items: " + items);
    }

    /**
     * Adds new tower to mainTowers array.
     * If mainTowers is full, then method adds new tower to reserveTowers array.
     * If reserveTowers is also full, new tower is not added
     * @param tower The new tower being added to the inventory
     */
    public void add(Tower tower){
        for (int i = 0; i < 5; i++){
            if (mainTowers[i] == null){
                mainTowers[i] = tower;
                return;
            }
        }
        for (int i = 0; i < 5; i++){
            if (reserveTowers[i] == null){
                reserveTowers[i] = tower;
                return;
            }
        }
        System.out.println("Could not add tower due to maximum number of towers reached");
    }

    /**
     * Adds new item to items array
     * @param item The new item being added to the inventory
     */
    public void add(Item item){
        items.add(item);
    }

    /**
     * Removes tower in mainTowers array.
     * If tower not in mainTowers, then tower is removed from reserveTowers array.
     * If tower not in reserveTowers, tower is not removed
     * @param tower Tower being removed from inventory
     */
    public void remove(Tower tower){
        for (int i = 0; i < 5; i++){
            if (mainTowers[i] == tower){
                mainTowers[i] = null;
                return;
            }
        }
        for (int i = 0; i < 5; i++){
            if (reserveTowers[i] == tower){
                reserveTowers[i] = null;
                return;
            }
        }
        System.out.println("Could not remove tower as it is not in tower arrays");
    }

    /**
     * Removes item from items array
     * @param item Item being removed from inventory
     */
    public void remove(Item item){
        items.remove(item);
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
    }
}