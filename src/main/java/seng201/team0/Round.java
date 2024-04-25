package seng201.team0;

import java.util.ArrayList;
import java.util.Collections;
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
    private static ArrayList<String> resourceTypes = new ArrayList<String>();

    /**
     * All carts used in round
     */
    private ArrayList<Cart> carts = new ArrayList<Cart>();

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
        Collections.addAll(resourceTypes, "Coal");
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
        cart.increaseCurrentLevel(tower.getResourceAmount());
    }

    /**
     * Removes the cart at beginning of carts list
     */
    public void removeFirstCart(){
        carts.remove(0);
    }
}
