package seng201.team0.services;

import seng201.team0.models.Cart;

public class CartThreads implements Runnable {
    private Cart cart;
    public CartThreads(Cart cart){
        this.cart = cart;
    }
    public void run(){
        while (cart.getTime() > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cart.decreaseTime();
        }
    }
}
