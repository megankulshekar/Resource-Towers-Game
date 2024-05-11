package seng201.team0.services;
// Some of the following code is reused from Tutorial 3 - Advanced Java
import seng201.team0.models.Cart;

/**
 * Class providing threads for the carts for the round controller class
 */
public class CartThreads implements Runnable {
    /**
     * The cart used in the thread
     */
    private Cart cart;

    /**
     * Constructor
     * @param cart Cart that will be used in the thread
     */
    public CartThreads(Cart cart){
        this.cart = cart;
    }

    /**
     * Counts down the time the cart has until is reaches the end of the track
     */
    public void run(){
        while (cart.getTime() > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
            cart.decreaseTime();
        }
    }
}
