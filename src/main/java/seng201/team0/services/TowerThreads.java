package seng201.team0.services;

import seng201.team0.models.Cart;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;

/**
 * Class providing threads for round controller class
 */
public class TowerThreads implements Runnable {
    /**
     * The current round of the game
     */
    private Round currentRound;

    /**
     * The tower that will fill carts in a thread
     */
    private Tower fillingTower;

    /**
     * Constructor
     * @param game The game environment
     * @param fillingTower The tower that will fill carts in a thread
     */
    public TowerThreads(GameEnvironment game, Tower fillingTower){
        int currentRoundIndex = game.getCurrentRoundIndex();
        currentRound = game.getRounds().get(currentRoundIndex);
        this.fillingTower = fillingTower;
    }

    /**
     * Fills up carts in a round one at a time, pausing each time filling tower reloads
     */
    public void run(){
        for (Cart cart : currentRound.getCarts()){
            while (!cart.isFull() && cart.getResourceType().equals(fillingTower.getResourceType())){
                currentRound.fillCart(cart, fillingTower);
                try {
                    Thread.sleep(fillingTower.getReloadSpeed() * 1000L);
                } catch (InterruptedException e) {
                    return;
                }

            }
        }
    }
}
