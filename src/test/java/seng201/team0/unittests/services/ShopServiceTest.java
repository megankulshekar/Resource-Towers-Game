package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.*;
import seng201.team0.services.ShopService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in ShopService class
 */
public class ShopServiceTest {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The shop service used for testing purposes
     */
    private ShopService testShopService;

    /**
     * Tests the buyTower, sellMainTower, and sellReserveTower methods
     * Tests if the correct tower is bought and sold
     */
    @Test
    public void testBuySellTowers(){
        game = new GameEnvironment();
        testShopService = new ShopService(game);
        game.setDifficulty("Easy");
        assertEquals("Tower bought", testShopService.buyTower(4));
        assertEquals("Tower bought", testShopService.buyTower(0));
        assertEquals("You do not have enough money", testShopService.buyTower(3));
        assertEquals("You haven't selected a valid tower", testShopService.buyTower(50));
        game.decreaseMoney(10);
        assertEquals("You do not have enough money", testShopService.buyTower(1));

        assertEquals("Main Tower Sold", testShopService.sellMainTower(0));
        assertEquals("Only one tower left! You cannot sell this tower.", testShopService.sellMainTower(1));
        assertEquals("Index value does not exist", testShopService.sellMainTower(-1));

        Tower ironTower1 = new IronTower();
        String ironDescription = ironTower1.getDescription();
        Tower ironTower2 = new IronTower();
        Tower ironTower3 = new IronTower();
        Tower ironTower4 = new IronTower();
        Tower ironTower5 = new IronTower();
        game.addToInventory(ironTower1, ironDescription);
        game.addToInventory(ironTower2, ironDescription);
        game.addToInventory(ironTower3, ironDescription);
        game.addToInventory(ironTower4, ironDescription);
        game.addToInventory(ironTower5, ironDescription);
        Tower goldTower = new GoldTower();
        String goldDescription = goldTower.getDescription();
        Tower diamondTower = new DiamondTower();
        String diamondDescription = diamondTower.getDescription();
        game.addToInventory(goldTower, goldDescription);
        game.addToInventory(diamondTower, diamondDescription);
        assertEquals("Reserve Tower Sold", testShopService.sellReserveTower(1));
        assertEquals("Index value does not exist", testShopService.sellReserveTower(-10));
    }

    /**
     * Tests the buyUpgrade and sellUpgrade methods
     * Tests if the correct upgrade is bought and sold
     */
    @Test
    public void testBuySellUpgrade(){
        game = new GameEnvironment();
        testShopService = new ShopService(game);
        game.setDifficulty("Easy");
        assertEquals("Upgrade bought", testShopService.buyUpgrade(3, "Upgrade Label"));
        assertEquals("Upgrade bought", testShopService.buyUpgrade(1, "Upgrade Label"));
        assertEquals("You do not have enough money", testShopService.buyUpgrade(2, "Upgrade Label"));
        assertEquals("You do not have enough money", testShopService.buyUpgrade(0, "Upgrade Label"));
        assertEquals("You do not have enough money", testShopService.buyUpgrade(13, "Upgrade Label"));

        assertEquals("Upgrade sold", testShopService.sellUpgrade(0));
        assertEquals("Upgrade sold", testShopService.sellUpgrade(0));
        assertEquals("Index value does not exist", testShopService.sellUpgrade(1));
    }
}