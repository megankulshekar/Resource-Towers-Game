package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in Inventory class
 */
public class InventoryTest {
    /**
     * Inventory class
     */
    private Inventory testInventory;

    /**
     * Tests the tower array getter, add, and remove methods
     * Tests if towers and tower descriptions exist in array lists when tower is added or removed
     */
    @Test
    public void testTowerArrayGetters() {
        testInventory = new Inventory();
        assertEquals(5, testInventory.getAllMainTowers().length);
        assertEquals(5, testInventory.getAllReserveTowers().length);
        Tower testCoalTower = new CoalTower();
        String coalDescription = testCoalTower.getDescription();
        testInventory.add(testCoalTower, coalDescription);
        Tower testCopperTower = new CopperTower();
        String copperDescription = testCopperTower.getDescription();
        testInventory.add(testCopperTower, copperDescription);
        assertEquals("Tower Type: Coal\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 2\n" +
                "Reload Speed: 2", testInventory.getMainTowerDescriptions(0));
        assertEquals("Tower Type: Copper\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 3\n" +
                "Reload Speed: 3", testInventory.getMainTowerDescriptions(1));
        assertEquals(null, testInventory.getMainTowers(2));
        Tower testIronTower = new IronTower();
        String ironDescription = testIronTower.getDescription();
        testInventory.add(testIronTower, ironDescription);
        assertEquals("Tower Type: Iron\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 4\n" +
                "Reload Speed: 4", testInventory.getMainTowerDescriptions(2));
        testInventory.remove(testCoalTower);
        assertEquals(null, testInventory.getMainTowers(0));
        assertEquals("", testInventory.getMainTowerDescriptions(0));
        testInventory.add(testCoalTower, coalDescription);
        testInventory.add(testCoalTower, coalDescription);
        testInventory.add(testCoalTower, coalDescription);
        assertEquals(null, testInventory.getReserveTowers(0));
        assertEquals(null, testInventory.getReserveTowers(2));
        Tower testGoldTower = new GoldTower();
        String goldDescription = testGoldTower.getDescription();
        testInventory.add(testGoldTower, goldDescription);
        assertEquals("Tower Type: Gold\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 5\n" +
                "Reload Speed: 5", testInventory.getReserveTowerDescriptions(0));
        Tower testDiamondTower = new DiamondTower();
        String diamondDescription = testDiamondTower.getDescription();
        testInventory.add(testDiamondTower, diamondDescription);
        assertEquals("Tower Type: Diamond\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 8\n" +
                "Reload Speed: 8", testInventory.getReserveTowerDescriptions(1));
        Tower testUraniumTower = new UraniumTower();
        String uraniumDescription = testUraniumTower.getDescription();
        testInventory.add(testUraniumTower, uraniumDescription);
        assertEquals("Tower Type: Uranium\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 10\n" +
                "Reload Speed: 8", testInventory.getReserveTowerDescriptions(2));
        testInventory.remove(testUraniumTower);
        assertEquals(null, testInventory.getReserveTowers(2));
        assertEquals("", testInventory.getReserveTowerDescriptions(2));
    }

    /**
     * Tests the upgrade getter, addUpgrade, and removeUpgrade methods
     * Tests if an upgrade and its description exists in the array list when an upgrade is added or removed (mimics buying or selling an upgrade)
     */
    @Test
    public void testUpgradeGetters(){
        testInventory = new Inventory();
        Item repairItem = new RepairItem();
        String upgradeResourceAmountDescription = "Repair Item";
        testInventory.addUpgrade(repairItem, upgradeResourceAmountDescription);
        assertEquals(repairItem, testInventory.getItems().get(0));
        assertEquals(upgradeResourceAmountDescription, testInventory.getUpgradesBought().get(0));
        Item upgradeXPItem = new UpgradeXPItem();
        String upgradeXPDescription = "Upgrade XP";
        testInventory.addUpgrade(upgradeXPItem, upgradeXPDescription);
        assertEquals(upgradeXPItem, testInventory.getItems().get(1));
        assertEquals(upgradeXPDescription, testInventory.getUpgradesBought().get(1));
        testInventory.removeUpgrade(repairItem, 0);
        assertEquals(1, testInventory.getItems().size());
        assertEquals(1, testInventory.getUpgradesBought().size());
    }

    /**
     * Tests the upgradeTower method
     * Tests if an upgrade is applied to a tower
     */
    @Test
    public void testUpgradeApplied(){
        testInventory = new Inventory();
        Tower testCopperTower = new CopperTower();
        String copperDescription = testCopperTower.getDescription();
        testInventory.add(testCopperTower, copperDescription);
        Item upgradeReloadItem = new UpgradeReloadSpeedItem();
        String upgradeReloadSpeedDescription = "Upgrade Reload Speed";
        testInventory.addUpgrade(upgradeReloadItem, upgradeReloadSpeedDescription);
        Item upgradeResourceItem = new UpgradeResourceAmountItem();
        String upgradeResourceAmountDescription = "Upgrade Reload Amount";
        testInventory.addUpgrade(upgradeResourceItem, upgradeResourceAmountDescription);
        assertEquals(2, testInventory.getItems().size());
        assertEquals("Upgrade Reload Amount", testInventory.getUpgradesBought().get(1));
        testInventory.upgradeTower(0, testCopperTower);
        assertEquals(1, testCopperTower.getReloadSpeed());
        assertEquals(1, testInventory.getItems().size());
        assertEquals(1, testInventory.getUpgradesBought().size());
        Tower testIronTower = new IronTower();
        String ironDescription = testIronTower.getDescription();
        testInventory.add(testIronTower, ironDescription);
        testInventory.upgradeTower(0, testIronTower);
        assertEquals(6, testIronTower.getResourceAmount());
    }

    /**
     * Tests the tower description setter methods
     * Tests if the tower description can be changed and updated for future use (mimics adding an upgrade description to a tower)
     */
    @Test
    public void testNewTowerDescription(){
        testInventory = new Inventory();
        Tower testCoalTower1 = new CoalTower();
        Tower testGoldTower1 = new GoldTower();
        Tower testCoalTower2 = new CoalTower();
        Tower testGoldTower2 = new GoldTower();
        Tower testCoalTower3 = new CoalTower();

        Tower testGoldTower3 = new GoldTower();
        Tower testCoalTower4 = new CoalTower();
        Tower testGoldTower4 = new GoldTower();
        String coalDescription = testCoalTower1.getDescription();
        String goldDescription = testGoldTower1.getDescription();
        testInventory.add(testCoalTower1, coalDescription);
        testInventory.add(testGoldTower1, goldDescription);
        testInventory.add(testCoalTower2, coalDescription);
        testInventory.add(testGoldTower2, goldDescription);
        testInventory.add(testCoalTower3, coalDescription);
        testInventory.add(testGoldTower3, goldDescription);
        testInventory.add(testCoalTower4, coalDescription);
        testInventory.add(testGoldTower4, goldDescription);
        String upgradeReloadSpeedDescription = "Upgrade Reload Speed";
        String newCoalDescription = coalDescription + "\n\n" + upgradeReloadSpeedDescription;
        testInventory.setMainTowerDescriptions(0, newCoalDescription);
        assertEquals(newCoalDescription, testInventory.getMainTowerDescriptions(0));
        assertEquals("Tower Type: Coal\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 2\n" +
                "Reload Speed: 2", testInventory.getMainTowerDescriptions(2));
        String newGoldDescription = goldDescription + "\n\n" + upgradeReloadSpeedDescription;
        testInventory.setReserveTowerDescriptions(2, newGoldDescription);
        assertEquals(newGoldDescription, testInventory.getReserveTowerDescriptions(2));
    }

    /**
     * Tests the swapTowers method
     * Tests if two towers and their corresponding descriptions switch between array lists
     */
    @Test
    public void testSwappingTowers(){
        testInventory = new Inventory();
        Tower testCoalTower1 = new CoalTower();
        Tower testGoldTower1 = new GoldTower();
        Tower testCoalTower2 = new CoalTower();
        Tower testGoldTower2 = new GoldTower();
        Tower testCoalTower3 = new CoalTower();

        Tower testGoldTower3 = new GoldTower();
        Tower testCoalTower4 = new CoalTower();
        Tower testGoldTower4 = new GoldTower();
        String coalDescription = testCoalTower1.getDescription();
        String goldDescription = testGoldTower1.getDescription();
        testInventory.add(testCoalTower1, coalDescription);
        testInventory.add(testGoldTower1, goldDescription);
        testInventory.add(testCoalTower2, coalDescription);
        testInventory.add(testGoldTower2, goldDescription);
        testInventory.add(testCoalTower3, coalDescription);
        testInventory.add(testGoldTower3, goldDescription);
        testInventory.add(testCoalTower4, coalDescription);
        testInventory.add(testGoldTower4, goldDescription);
        testInventory.swapTowers(1, 1);
        assertEquals(testCoalTower4, testInventory.getMainTowers(1));
        assertEquals(testGoldTower1, testInventory.getReserveTowers(1));
        assertEquals(coalDescription, testInventory.getMainTowerDescriptions(1));
        assertEquals(goldDescription, testInventory.getReserveTowerDescriptions(1));
    }
}