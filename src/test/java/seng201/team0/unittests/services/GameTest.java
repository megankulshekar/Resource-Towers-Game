package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in testGame class
 */
public class GameTest {
    /**
     * testGame class
     */
    private GameEnvironment testGame;

    /**
     * Tests the getter and setter methods for game statistics
     * Tests if game statistics can be accessed and modified to new values
     */
    @Test
    public void testGameStatistics(){
        testGame = new GameEnvironment();
        assertEquals("Calan", testGame.getName());
        testGame.setName("Player");
        assertEquals("Player", testGame.getName());

        assertEquals(15, testGame.getNumberRounds());
        testGame.setNumberRounds(8);
        assertEquals(8, testGame.getNumberRounds());

        assertEquals(0, testGame.getCurrentRoundIndex());
        testGame.increaseCurrentRoundIndex();
        assertEquals(1, testGame.getCurrentRoundIndex());

        assertEquals(0, testGame.getRounds().size());
        Round easyRound = new Round("Easy");
        Round mediumRound = new Round("Medium");
        testGame.addRound(easyRound);
        testGame.addRound(mediumRound);
        assertEquals(2, testGame.getRounds().size());

        testGame.setDifficulty("Easy");
        assertEquals("Easy", testGame.getDifficulty());
        testGame.setDifficulty("Medium");
        assertEquals("Medium", testGame.getDifficulty());
        testGame.setDifficulty("Hard");
        assertEquals("Hard", testGame.getDifficulty());

        assertEquals(10, testGame.getMoney());
        testGame.increaseMoney(25);
        assertEquals(35, testGame.getMoney());
        testGame.decreaseMoney(12);
        assertEquals(23, testGame.getMoney());
    }

    /**
     * Tests the getInventory, addToInventory, removeFromInventory, buyTowerInShop, and sellTowerInShop methods
     * Tests if the inventory can be accessed and if towers can be added and removed from the inventory
     */
    @Test
    public void testInventory(){
        testGame = new GameEnvironment();
        Tower testUraniumTower = new UraniumTower();
        String uraniumDescription = testUraniumTower.getDescription();
        Tower testDiamondTower = new DiamondTower();
        String diamondDescription = testDiamondTower.getDescription();
        Tower testCoalTower = new CoalTower();
        String coalDescription = testCoalTower.getDescription();
        testGame.addToInventory(testUraniumTower, uraniumDescription);
        testGame.addToInventory(testDiamondTower, diamondDescription);
        testGame.addToInventory(testCoalTower, coalDescription);
        assertEquals(testUraniumTower, testGame.getInventory().getMainTowers(0));
        assertEquals(testDiamondTower, testGame.getInventory().getMainTowers(1));
        assertEquals(testCoalTower, testGame.getInventory().getMainTowers(2));
        assertEquals(diamondDescription, testGame.getInventory().getMainTowerDescriptions(1));

        testGame.removeFromInventory(testCoalTower);
        assertEquals(null, testGame.getInventory().getMainTowers(2));

        testGame.buyTowerInShop(testCoalTower, testGame, coalDescription);
        Tower testGoldTower = new GoldTower();
        String goldDescription = testGoldTower.getDescription();
        testGame.buyTowerInShop(testGoldTower, testGame, goldDescription);
        assertEquals(testCoalTower, testGame.getInventory().getMainTowers(2));
        assertEquals(testGoldTower, testGame.getInventory().getMainTowers(3));
        assertEquals(goldDescription, testGame.getInventory().getMainTowerDescriptions(3));

        testGame.sellTowerInShop(testGoldTower, testGame);
        assertEquals(null, testGame.getInventory().getMainTowers(3));
        assertEquals("", testGame.getInventory().getMainTowerDescriptions(3));
        testGame.sellTowerInShop(testUraniumTower, testGame);
        assertEquals(null, testGame.getInventory().getMainTowers(0));
        assertEquals("", testGame.getInventory().getMainTowerDescriptions(0));
    }

    /**
     * Tests the addToUpgrades, buyUpgrades, sellUpgrades, and removeFromUpgrades methods
     * Tests if upgrades can be added and removed from the inventory
     */
    @Test
    public void testUpgrades(){
        testGame = new GameEnvironment();
        Item upgradeXPItem = new UpgradeXPItem();
        String upgradeXPDescription = "Upgrade XP";
        Item upgradeReloadItem = new UpgradeReloadSpeedItem();
        String upgradeReloadSpeedDescription = "Upgrade Reload Speed";
        Item upgradeResourceItem = new UpgradeResourceAmountItem();
        String upgradeResourceAmountDescription = "Upgrade Reload Amount";
        Item repairItem = new RepairItem();
        String upgradeRepairItemDescription = "Repair Item";
        testGame.addToUpgrades(upgradeXPItem, upgradeXPDescription);
        testGame.addToUpgrades(upgradeReloadItem, upgradeReloadSpeedDescription);
        testGame.addToUpgrades(upgradeResourceItem, upgradeResourceAmountDescription);
        assertTrue(testGame.getInventory().getItems().contains(upgradeXPItem));
        assertFalse(testGame.getInventory().getItems().contains(repairItem));

        testGame.removeFromUpgrades(upgradeReloadItem, 1);
        testGame.removeFromUpgrades(upgradeResourceItem, 1);
        assertEquals(1, testGame.getInventory().getItems().size());

        testGame.buyUpgrades(repairItem, testGame, upgradeRepairItemDescription);
        assertTrue(testGame.getInventory().getItems().contains(repairItem));

        testGame.sellUpgrades(upgradeXPItem, 0, testGame);
        assertEquals(repairItem, testGame.getInventory().getItems().get(0));
    }
}