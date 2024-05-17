package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.*;
import seng201.team0.services.InventoryService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in InventoryService class
 */
public class InventoryServiceTest {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The inventory service used for testing purposes
     */
    private InventoryService testInventoryService;

    /**
     * Tests the selectTowers method
     * Tests if there is at least one main tower in the inventory before swapping towers
     */
    @Test
    public void testSelectTowers(){
        game = new GameEnvironment();
        testInventoryService = new InventoryService(game);
        Tower testCoalTower = new CoalTower();
        String coalDescription = testCoalTower.getDescription();
        game.addToInventory(testCoalTower, coalDescription);
        Tower testCopperTower = new CopperTower();
        String copperDescription = testCopperTower.getDescription();
        game.addToInventory(testCopperTower, copperDescription);
        Tower testCoalTower2 = new CoalTower();
        Tower testCoalTower3 = new CoalTower();
        Tower testCoalTower4 = new CoalTower();
        game.addToInventory(testCoalTower2, coalDescription);
        game.addToInventory(testCoalTower3, coalDescription);
        game.addToInventory(testCoalTower4, coalDescription);
        Tower testGoldTower = new GoldTower();
        String goldDescription = testGoldTower.getDescription();
        game.addToInventory(testGoldTower, goldDescription);
        assertTrue(testInventoryService.selectTowers(game, 1, 0));

        game.removeFromInventory(testCoalTower);
        game.removeFromInventory(testCoalTower2);
        game.removeFromInventory(testCoalTower3);
        game.removeFromInventory(testCoalTower4);
        game.removeFromInventory(testGoldTower);
        assertFalse(testInventoryService.selectTowers(game, 0, 0));
    }
}