package seng201.team0;

import java.util.ArrayList;

public class Round {
    private int numCarts;
    private ArrayList<Cart> carts = new ArrayList<Cart>();
    private String difficulty;

    private int cartSize;
    private String cartResourceType;
    private ArrayList<String> resourceTypes = new ArrayList<String>();
    private int cartSpeed;

    public Round(int roundNumCarts){
        numCarts = roundNumCarts;
        for (int i = 0; i < numCarts; i++){
            Cart newCart = new Cart(cartSize, );
            carts.add(newCart);
        }
    }
    public void fillCart(Cart cart, Tower tower){
        cart.increaseCurrentLevel(tower.getResourceAmount());
    }

    public void removeFirstCart(){
        carts.remove(0);
    }
}
