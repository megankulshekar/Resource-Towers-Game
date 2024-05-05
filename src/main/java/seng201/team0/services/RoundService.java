package seng201.team0.services;

import seng201.team0.models.*;

/**
 * Class for providing services to round controller class
 */
public class RoundService {
    /**
     * The current round of the game
     */
    private Round currentRound;

    /**
     * Constructor
     * @param game The game environment
     */
    public RoundService(GameEnvironment game){
        int currentRoundIndex = game.getCurrentRoundIndex();
        currentRound = game.getRounds().get(currentRoundIndex);
    }

    /**
     * Gets the current round of the game
     * @return The current round
     */
    public Round getCurrentRound(){
        return currentRound;
    }

    /**
     * Checks if all carts in the round are full
     * @return Boolean value representing if all carts are full
     */
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
