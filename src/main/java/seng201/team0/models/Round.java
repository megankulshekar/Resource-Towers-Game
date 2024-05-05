package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing a round in game
 */
public class Round {
    /**
     * Difficulty of round
     */
    private String difficulty;

    /**
     * Number of carts in round
     */
    private static int numCarts;

    /**
     * Size of carts in round
     */
    private static int cartSize;

    /**
     * Speed of carts in round
     */
    private static int cartSpeed;

    /**
     * All cart resource types used in round
     */
    private static List<String> resourceTypes = new ArrayList<String>();

    /**
     * All carts used in round
     */
    private List<Cart> carts = new ArrayList<Cart>();

    /**
     * Constructor
     * @param roundDifficulty Difficulty round is set to
     */
    public Round(String roundDifficulty){
        difficulty = roundDifficulty;
        numCarts = 3;
        if (difficulty == "Easy"){
            cartSize = 4;
            cartSpeed = 4;
        } else if (difficulty == "Medium"){
            cartSize = 5;
            cartSpeed = 5;
        } else{
            cartSize = 6;
            cartSpeed = 6;
        }
        if (!resourceTypes.contains("Coal")){
            resourceTypes.add("Coal");
        }
    }

    public String getDifficulty(){
        return difficulty;
    }
    /**
     * Gets the number of carts in the round
     * @return Number of carts
     */
    public int getNumCarts(){
        return numCarts;
    }

    /**
     * Gets the size of the carts in the round
     * @return Size of the carts
     */
    public int getCartSize(){
        return cartSize;
    }

    /**
     * Gets the speed of the carts in the round
     * @return Speed of the carts
     */
    public int getCartSpeed(){
        return cartSpeed;
    }

    public List<String> getResourceTypes(){
        return resourceTypes;
    }
    /**
     * Gets the carts used in the round
     * @return List of carts used in round
     */
    public List<Cart> getCarts(){
        return carts;
    }

    /**
     * Creates carts for the round
     */
    public void createCarts(){
        Random random = new Random();
        for (int i = 0; i < numCarts; i++){
            int index = random.nextInt(resourceTypes.size());
            String cartResourceType = resourceTypes.get(index);
            Cart newCart = new Cart(cartSize, cartResourceType, cartSpeed);
            carts.add(newCart);
        }
    }

    public void setDifficulty(String difficultySetting){
        difficulty = difficultySetting;
    }
    /**
     * Increases number of carts
     * @param increaseAmount Amount number of carts increased by
     */
    public void increaseNumCarts(int increaseAmount){
        numCarts += increaseAmount;
    }

    /**
     * Increases size of carts
     * @param increaseAmount Amount cart size is increased by
     */
    public void increaseCartSize(int increaseAmount){
        cartSize += increaseAmount;
    }

    /**
     * Increases speed of carts
     * @param increaseAmount Amount cart speed is increased by
     */
    public void increaseCartSpeed(int increaseAmount){
        cartSpeed += increaseAmount;
    }

    /**
     * Adds new cart resource type
     * @param type Cart resource type that is added
     */
    public void addResourceType(String type){
        resourceTypes.add(type);
    }

    /**
     * Fills a cart with resources from a tower
     * @param cart Cart being filled
     * @param tower Tower used to fill cart
     */
    public void fillCart(Cart cart, Tower tower){
        if (!tower.isBroken()){
            cart.increaseCurrentLevel(tower.getResourceAmount());
        }
    }

    /**
     * Removes the cart at beginning of carts list
     */
    public void removeFirstCart(){
        carts.remove(0);
    }
}
