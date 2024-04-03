package seng201.team0;

/**
 * Abstract class representing the general functionality of any item in game
 */
public abstract class Item {
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
    public void buyItem() {

    }
    public abstract void useItem(Tower tower);
}