package seng201.team0.models;

/**
 * Class representing a cart
 */
public class Cart {
    /**
     * Size of the cart
     */
    private int size;

    /**
     * The type of resource the cart can carry
     */
    private String resourceType;

    /**
     * Speed of the cart
     */
    private int speed;

    /**
     * Variable for showing if cart is full
     */
    private boolean full = false;

    /**
     * The current amount of resources in the cart
     */
    private int currentLevel = 0;

    /**
     * The amount of time until cart reaches the end of the track
     */
    private int time;

    /**
     * Constructor
     * @param cartSize Size of cart
     * @param cartResourceType Type of resource the cart can carry
     * @param cartSpeed Speed of the cart
     */
    public Cart(int cartSize, String cartResourceType, int cartSpeed){
        size = cartSize;
        resourceType = cartResourceType;
        speed = cartSpeed;
        time = 100 / speed;
    }

    /**
     * Gets size of the cart
     * @return Size of the cart
     */
    public int getSize(){
        return size;
    }

    /**
     * Gets the resource type of the cart
     * @return Resource type of the cart
     */
    public String getResourceType(){
        return resourceType;
    }

    /**
     * Gets the speed of the cart
     * @return Speed of the cart
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * Gets whether cart if full
     * @return Variable for showing if cart is full
     */
    public boolean isFull() {
        return full;
    }

    /**
     * Sets cart as full
     */
    public void setFullToTrue(){
        full = true;
    }

    /**
     * Gets the current amount of resources in the cart
     * @return Current amount of resources in the cart
     */
    public int getCurrentLevel(){
        return currentLevel;
    }

    /**
     * Gets the description of the cart's type, how full it is and the amount of time it has left
     * @return Description of the cart
     */
    public String getDescription(){
        return "Cart Type: "+resourceType+"\n\nAmount full: "+currentLevel+"/"+size+"\n\n" +
                "Time left: "+time;
    }

    /**
     * Increases current amount of resources in the cart.
     * If current level is greater than or equal to size, cart is set to full
     * @param increaseAmount Amount current level is increased by
     */
    public void increaseCurrentLevel(int increaseAmount){
        if (!full) {
            currentLevel += increaseAmount;
            if (currentLevel >= size) {
                currentLevel = size;
                setFullToTrue();
            }
        }
    }

    /**
     * Gets the time of the cart
     * @return Time of the cart
     */
    public int getTime(){
        return time;
    }

    /**
     * Decreases the cart's time by 1
     */
    public void decreaseTime(){
        time--;
    }
}