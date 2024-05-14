package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ItemTest {
    private Item testItem;

    @Test
    public void testItemUses(){
        Tower testTower = new UraniumTower();
        testItem = new UpgradeXPItem();
        testItem.useItem(testTower);
        assertEquals("Increases a tower's XP level", testItem.getDescription());
        assertEquals(5, testTower.getXP());
        testItem = new UpgradeResourceAmountItem();
        testItem.useItem(testTower);
        assertEquals("Increases a tower's resource amount", testItem.getDescription());
        assertEquals(12, testTower.getResourceAmount());
        testItem = new UpgradeReloadSpeedItem();
        testItem.useItem(testTower);
        assertEquals("Decreases a tower's reload speed", testItem.getDescription());
        assertEquals(6, testTower.getReloadSpeed());
        testItem = new RepairItem();
        testTower.setBroken(true);
        testItem.useItem(testTower);
        assertEquals("Repairs a tower back to working order", testItem.getDescription());
        assertFalse(testTower.isBroken());
    }

    @Test
    public void testPriceGetters(){
        testItem = new UpgradeXPItem();
        assertEquals(15, testItem.getBuyingPrice());
        assertEquals(7, testItem.getSellingPrice());
        testItem = new UpgradeResourceAmountItem();
        assertEquals(15, testItem.getBuyingPrice());
        assertEquals(7, testItem.getSellingPrice());
        testItem = new UpgradeReloadSpeedItem();
        assertEquals(15, testItem.getBuyingPrice());
        assertEquals(7, testItem.getSellingPrice());
        testItem = new RepairItem();
        assertEquals(15, testItem.getBuyingPrice());
        assertEquals(7, testItem.getSellingPrice());
    }
}
