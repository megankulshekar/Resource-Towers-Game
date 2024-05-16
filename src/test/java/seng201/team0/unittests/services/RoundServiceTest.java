package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.*;
import seng201.team0.services.RoundService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in RoundService class
 */
public class RoundServiceTest {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The round service used for testing purposes
     */
    private RoundService testRoundService;

    /**
     * Tests the cartTimeRunOut method
     */
    @Test
    public void testCartTimeRunOut(){
        game = new GameEnvironment();
        game.setDifficulty("Hard");
        for (int i = 0; i < game.getNumberRounds(); i++) {
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        testRoundService = new RoundService(game);
        Round testCurrentRound = testRoundService.getCurrentRound();
        testCurrentRound.createCarts();
        assertFalse(testRoundService.cartTimeRunOut());
        List<Cart> testCarts = testCurrentRound.getCarts();
        for (Cart cart : testCarts){
            cart.decreaseTime();
        }
        assertFalse(testRoundService.cartTimeRunOut());
        for (Cart cart : testCarts){
            cart.decreaseTime();
        }
        assertFalse(testRoundService.cartTimeRunOut());
        for (Cart cart : testCarts){
            while (cart.getTime() > 0){
                cart.decreaseTime();
            }
        }
        assertTrue(testRoundService.cartTimeRunOut());

        game.increaseCurrentRoundIndex();
        testRoundService = new RoundService(game);
        testCurrentRound = testRoundService.getCurrentRound();
        testCurrentRound.createCarts();
        assertFalse(testRoundService.cartTimeRunOut());
        testCarts = testCurrentRound.getCarts();
        for (Cart cart : testCarts){
            cart.decreaseTime();
        }
        assertFalse(testRoundService.cartTimeRunOut());
        Tower testTower = new CoalTower();
        for (Cart cart : testCarts){
            while (!cart.isFull()) {
                testCurrentRound.fillCart(cart, testTower);
            }
        }
        assertFalse(testRoundService.cartTimeRunOut());
        for (Cart cart : testCarts){
            while (cart.getTime() > 0){
                cart.decreaseTime();
            }
        }
        assertFalse(testRoundService.cartTimeRunOut());
    }

    /**
     * Tests the allCartsFull method
     */
    @Test
    public void testAllCartsFull(){
        game = new GameEnvironment();
        game.setDifficulty("Hard");
        for (int i = 0; i < game.getNumberRounds(); i++) {
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        testRoundService = new RoundService(game);
        Round testCurrentRound = testRoundService.getCurrentRound();
        testCurrentRound.createCarts();
        assertFalse(testRoundService.allCartsFull());
        List<Cart> testCarts = testCurrentRound.getCarts();
        Tower testTower = new CoalTower();
        for (Cart cart : testCarts){
            testCurrentRound.fillCart(cart, testTower);
        }
        assertFalse(testRoundService.allCartsFull());
        for (Cart cart : testCarts){
            while (!cart.isFull()){
                testCurrentRound.fillCart(cart, testTower);
            }
        }
        assertTrue(testRoundService.allCartsFull());
    }
}
