package seng201.team0.services;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import seng201.team0.models.*;

import java.util.List;

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
            if (cart.getResourceType().equals(tower.getResourceType())) {
                currentRound.fillCart(cart, tower);
            }
        }
    }
}
