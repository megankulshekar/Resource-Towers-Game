package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.*;
import seng201.team0.services.UpgradeService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in UpgradeService class
 */
public class UpgradeServiceTest {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The upgrade service used for testing purposes
     */
    private UpgradeService testUpgradeService;

    /**
     * Tests the applyUpgrade method
     * Tests if the upgrade is applied to the correct tower
     * Tests if the tower's description is also updated
     */
    @Test
    public void testApplyUpgrade(){
        game = new GameEnvironment();
        testUpgradeService = new UpgradeService(game);
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
        Tower testIronTower = new IronTower();
        String ironDescription = testIronTower.getDescription();
        game.addToInventory(testIronTower, ironDescription);
        Item repairItem = new RepairItem();
        String upgradeRepairItemDescription = "Repair Item";
        game.addToUpgrades(repairItem, upgradeRepairItemDescription);
        Item upgradeReloadItem = new UpgradeReloadSpeedItem();
        String upgradeReloadSpeedDescription = "Upgrade Reload Speed";
        game.addToUpgrades(upgradeReloadItem, upgradeReloadSpeedDescription);

        assertEquals("Success! Upgrade applied!", testUpgradeService.applyUpgrade(0, 1, testGoldTower, 1, upgradeReloadSpeedDescription));
        assertEquals("Success! Upgrade applied!", testUpgradeService.applyUpgrade(1, 2, testCopperTower, 0, upgradeRepairItemDescription));
        assertEquals(3, testGoldTower.getReloadSpeed());
        assertEquals("Tower Type: Copper\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 3\n" +
                "Reload Speed: 3\n\n" +
                "Repair Item", game.getInventory().getMainTowerDescriptions(1));
        assertEquals("The tower does not exist.", testUpgradeService.applyUpgrade(0, 3, testCoalTower, 1, upgradeRepairItemDescription));
    }
}
