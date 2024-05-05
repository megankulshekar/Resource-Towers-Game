package seng201.team0.services;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import seng201.team0.models.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoundService {
    private GameEnvironment game;
    private int currentRoundIndex;
    private Round currentRound;

    public RoundService(GameEnvironment game){
        this.game = game;
        currentRoundIndex = game.getCurrentRoundIndex();
        currentRound = game.getRounds().get(currentRoundIndex);
    }

    public Round getCurrentRound(){
        return currentRound;
    }

    public void fillCart() {
        List<Cart> currentRoundCarts = currentRound.getCarts();
        Inventory inventory = game.getInventory();
        for (Cart cart : currentRoundCarts) {
            Tower tower = inventory.getMainTowers(0);
            if (!cart.isFull() && cart.getResourceType().equals(tower.getResourceType())) {
                currentRound.fillCart(cart, tower);
            }
        }
    }
    public boolean allCartsFull(){
        boolean allFull = true;
        for (Cart cart : currentRound.getCarts()){
            if (!cart.isFull()){
                allFull = false;
            }
        }
        return allFull;
    }
}
