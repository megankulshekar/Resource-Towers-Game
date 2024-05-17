package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in Shop class
 */
public class ShopTest {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * Shop class
     */
    private Shop testShop;

    /**
     * Tests the buy and sell methods
     * Tests if a tower that is bought is added to the player's inventory
     * Tests if a tower that is sold is removed from the player's inventory
     * Tests if the player's amount of money increases or decreases depending on buying or selling a tower
     */
    @Test
    public void testBuySellTower(){
        game = new GameEnvironment();
        testShop = new Shop();
        game.setDifficulty("Easy");
        assertEquals(30, game.getMoney());
        Tower testCoalTower1 = new CoalTower();
        Tower testCoalTower2 = new CoalTower();
        Tower testCoalTower3 = new CoalTower();
        String coalDescription = testCoalTower1.getDescription();
        game.addToInventory(testCoalTower1, coalDescription);
        game.addToInventory(testCoalTower2, coalDescription);
        game.addToInventory(testCoalTower3, coalDescription);

        Tower testCopperTower = new CopperTower();
        String copperDescription = testCopperTower.getDescription();
        testShop.buy(testCopperTower, game, copperDescription);
        assertEquals(7, testCopperTower.getBuyingPrice());
        assertEquals(23, game.getMoney());
        assertEquals(testCoalTower1, game.getInventory().getMainTowers(0));
        assertEquals(testCoalTower2, game.getInventory().getMainTowers(1));
        assertEquals(testCoalTower3, game.getInventory().getMainTowers(2));
        assertEquals(testCopperTower, game.getInventory().getMainTowers(3));
        assertNull(game.getInventory().getMainTowers(4));
        assertEquals(copperDescription, game.getInventory().getMainTowerDescriptions(3));

        Tower testCoalTower4 = new CoalTower();
        Tower testCoalTower5 = new CoalTower();
        Tower testCoalTower6 = new CoalTower();
        game.getInventory().add(testCoalTower4, coalDescription);
        game.getInventory().add(testCoalTower5, coalDescription);
        game.getInventory().add(testCoalTower6, coalDescription);
        Tower testGoldTower = new GoldTower();
        String goldDescription = testGoldTower.getDescription();
        testShop.buy(testGoldTower, game, goldDescription);
        assertEquals(testGoldTower, game.getInventory().getReserveTowers(2));
        assertEquals(goldDescription, game.getInventory().getReserveTowerDescriptions(2));
        assertEquals(14, game.getMoney());

        testShop.sell(testCoalTower5, game);
        assertEquals(17, game.getMoney());
        assertNull(game.getInventory().getReserveTowers(0));
        assertEquals("", game.getInventory().getReserveTowerDescriptions(0));
        testShop.sell(testCopperTower, game);
        assertEquals(20, game.getMoney());
        assertNull(game.getInventory().getMainTowers(3));
        assertEquals("", game.getInventory().getMainTowerDescriptions(3));
        testShop.sell(testGoldTower, game);
        assertEquals(24, game.getMoney());
        assertNull(game.getInventory().getReserveTowers(2));
        assertEquals("", game.getInventory().getReserveTowerDescriptions(0));
    }

    /**
     * Tests the buyUpgrade and sellUpgrade methods
     * Tests if an upgrade that is bought is added to the player's inventory
     * Tests if an upgrade that is sold is removed from the player's inventory
     * Tests if the player's amount of money increases or decreases depending on buying or selling an upgrade
     */
    @Test
    public void testBuySellUpgrade(){
        game = new GameEnvironment();
        testShop = new Shop();
        Item upgradeReloadItem = new UpgradeReloadSpeedItem();
        String upgradeReloadSpeedDescription = "Upgrade Reload Speed";
        game.addToUpgrades(upgradeReloadItem, upgradeReloadSpeedDescription);
        Item upgradeResourceItem = new UpgradeResourceAmountItem();
        String upgradeResourceAmountDescription = "Upgrade Reload Amount";
        game.addToUpgrades(upgradeResourceItem, upgradeResourceAmountDescription);
        Item boughtUpgradeReloadItem = new UpgradeReloadSpeedItem();
        game.setDifficulty("Medium");

        testShop.buyUpgrade(boughtUpgradeReloadItem, game, upgradeReloadSpeedDescription);
        assertEquals(5, game.getMoney());
        assertEquals(upgradeReloadItem, game.getInventory().getItems().get(0));
        assertEquals(upgradeReloadSpeedDescription, game.getInventory().getUpgradesBought().get(0));
        assertEquals(upgradeResourceItem, game.getInventory().getItems().get(1));
        assertEquals(upgradeResourceAmountDescription, game.getInventory().getUpgradesBought().get(1));
        assertEquals(boughtUpgradeReloadItem, game.getInventory().getItems().get(2));
        assertEquals(upgradeReloadSpeedDescription, game.getInventory().getUpgradesBought().get(2));
        assertEquals(3, game.getInventory().getUpgradesBought().size());

        testShop.sellUpgrade(upgradeReloadItem, 0, game);
        assertEquals(12, game.getMoney());
        assertEquals(upgradeResourceItem, game.getInventory().getItems().get(0));
        assertEquals(boughtUpgradeReloadItem, game.getInventory().getItems().get(1));
        assertEquals(upgradeResourceAmountDescription, game.getInventory().getUpgradesBought().get(0));
        assertEquals(upgradeReloadSpeedDescription, game.getInventory().getUpgradesBought().get(1));
        assertEquals(2, game.getInventory().getUpgradesBought().size());
    }
}
