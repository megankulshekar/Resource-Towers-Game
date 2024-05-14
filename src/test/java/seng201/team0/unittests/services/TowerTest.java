package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {
    private Tower testTower;

    @Test
    public void testMainGettersAndSetters(){
        testTower = new CoalTower();
        assertEquals(1, testTower.getLevel());
        assertEquals(0, testTower.getXP());
        assertFalse(testTower.isBroken());
        assertEquals("Coal", testTower.getResourceType());
        assertEquals(2, testTower.getResourceAmount());
        assertEquals(2, testTower.getReloadSpeed());
        assertEquals("Tower Type: Coal\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 2\n" +
                "Reload Speed: 2", testTower.getDescription());
        testTower.increaseXP(6);
        assertEquals(6, testTower.getXP());
        testTower.increaseXP(6);
        assertEquals(2, testTower.getXP());
        assertEquals(2, testTower.getLevel());
        assertEquals(4, testTower.getResourceAmount());
        assertEquals(1, testTower.getReloadSpeed());
        assertEquals("Tower Type: Coal\n" +
                "Level: 2\n" +
                "XP: 2\n" +
                "Resource Amount: 4\n" +
                "Reload Speed: 1", testTower.getDescription());
        testTower.setBroken(true);
        assertTrue(testTower.isBroken());
        assertEquals("Tower is broken", testTower.getDescription());
    }

    @Test
    public void testPriceGetters(){
        testTower = new CoalTower();
        assertEquals(6, testTower.getBuyingPrice());
        assertEquals(3, testTower.getSellingPrice());
        testTower = new CopperTower();
        assertEquals(7, testTower.getBuyingPrice());
        assertEquals(3, testTower.getSellingPrice());
        testTower = new IronTower();
        assertEquals(8, testTower.getBuyingPrice());
        assertEquals(4, testTower.getSellingPrice());
        testTower = new GoldTower();
        assertEquals(9, testTower.getBuyingPrice());
        assertEquals(4, testTower.getSellingPrice());
        testTower = new DiamondTower();
        assertEquals(14, testTower.getBuyingPrice());
        assertEquals(7, testTower.getSellingPrice());
        testTower = new UraniumTower();
        assertEquals(12, testTower.getBuyingPrice());
        assertEquals(6, testTower.getSellingPrice());
    }
}
