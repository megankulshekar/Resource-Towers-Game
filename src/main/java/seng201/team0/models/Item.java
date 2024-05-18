package seng201.team0.models;

/**
 * Abstract class representing the general functionality of any item in game
 */
public abstract class Item implements Purchasable {
    /**
     * Variable for showing if item has been used
     */
    protected boolean used;

    /**
     * Constructor
     */
    public Item(){
        used = false;
    }

    /**
     * Abstract method for using the item on a tower
     * @param tower Tower the item is used on
     */
    public abstract void useItem(Tower tower);
}