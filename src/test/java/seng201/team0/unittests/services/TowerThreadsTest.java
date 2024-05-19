package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.*;
import seng201.team0.services.TowerThreads;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in TowerThreads class
 */
public class TowerThreadsTest {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The current round of the game used for testing purposes
     */
    private Round testCurrentRound;

    /**
     * The tower threads used for testing purposes
     */
    private TowerThreads testTowerThreads;

    /**
     * Before each test, a new game environment is set up,
     * the rounds are created for it,
     * gets the current round,
     * and creates the carts for that round
     */
    @BeforeEach
    public void createGameEnvironment(){
        game = new GameEnvironment();
        game.setDifficulty("Easy");
        for (int i = 0; i < game.getNumberRounds(); i++) {
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        int index = game.getCurrentRoundIndex();
        testCurrentRound = game.getRounds().get(index);
        testCurrentRound.resetResourceTypes();
        testCurrentRound.createCarts();
    }

    /**
     * Tests if tower threads work with a tower that is the same resource type as the carts
     * @throws InterruptedException
     */
    @Test
    public void testThreadsUsingSameResourceType() throws InterruptedException {
        Tower testTower = new CoalTower();
        testTowerThreads = new TowerThreads(game, testTower);
        Thread testThread = new Thread(testTowerThreads);
        testThread.start();
        testThread.join();
        List<Cart> carts = testCurrentRound.getCarts();
        for (Cart cart : carts){
            assertTrue(cart.isFull());
            assertEquals(cart.getSize(), cart.getCurrentLevel());
        }
    }

    /**
     * Tests if tower threads work with a tower that is a different resource type as the carts
     * @throws InterruptedException
     */
    @Test
    public void testThreadsUsingDifferentResourceType() throws InterruptedException {
        Tower testTower = new UraniumTower();
        testTowerThreads = new TowerThreads(game, testTower);
        Thread testThread = new Thread(testTowerThreads);
        testThread.start();
        testThread.join();
        for (Cart cart : testCurrentRound.getCarts()){
            assertFalse(cart.isFull());
            assertEquals(0, cart.getCurrentLevel());
        }
    }

    /**
     * Tests threads when they are interrupted
     * @throws InterruptedException
     */
    @Test
    public void testInterruptedThreads() throws InterruptedException {
        Tower testTower = new CoalTower();
        testTowerThreads = new TowerThreads(game, testTower);
        Thread testThread = new Thread(testTowerThreads);
        testThread.start();
        Thread.sleep(2000);
        testThread.interrupt();
        boolean allFull = true;
        for (Cart cart : testCurrentRound.getCarts()){
            if (!cart.isFull()) {
                allFull = false;
                break;
            }
        }
        assertFalse(allFull);
    }
}
