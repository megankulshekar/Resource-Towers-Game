package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.*;
import seng201.team0.services.StartScreenService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in StartScreenService class
 */
public class StartScreenServiceTest {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The start screen service used for testing purposes
     */
    private StartScreenService testStartScreenService;

    /**
     * Tests the validCharacters method
     * Tests if the name is valid or invalid
     */
    @Test
    public void testValidCharacters(){
        game = new GameEnvironment();
        testStartScreenService = new StartScreenService(game);
        String name = "P!@#er";
        assertFalse(testStartScreenService.validCharacters(name));
        name = "Player";
        assertTrue(testStartScreenService.validCharacters(name));
        name = "123456789";
        assertTrue(testStartScreenService.validCharacters(name));
        name = "player";
        assertTrue(testStartScreenService.validCharacters(name));
        name = "!!!!";
        assertFalse(testStartScreenService.validCharacters(name));
        name = "P7684r";
        assertTrue(testStartScreenService.validCharacters(name));
        name = "P23!*%aplg";
        assertFalse(testStartScreenService.validCharacters(name));
    }

    /**
     * Tests the towerDescriptionCreation method
     * Tests if the correct description is created
     */
    @Test
    public void testTowerDescriptionCreation(){
        game = new GameEnvironment();
        testStartScreenService = new StartScreenService(game);
        assertEquals("Tower Type: Coal\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 3\n" +
                "Reload Speed: 3", testStartScreenService.towerDescriptionCreation(0));
        assertEquals("Tower Type: Coal\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 2\n" +
                "Reload Speed: 2", testStartScreenService.towerDescriptionCreation(1));
        assertEquals("Tower Type: Coal\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 1\n" +
                "Reload Speed: 1", testStartScreenService.towerDescriptionCreation(2));
        assertEquals(null, testStartScreenService.towerDescriptionCreation(-5));
    }

    /**
     * Tests the towerCreation method
     * Tests if the chosen Coal Tower is created with the correct resource amount and reload speed values
     */
    @Test
    public void testTowerCreation(){
        game = new GameEnvironment();
        testStartScreenService = new StartScreenService(game);
        assertEquals(3, testStartScreenService.towerCreation(0).getResourceAmount());
        assertEquals(3, testStartScreenService.towerCreation(0).getReloadSpeed());
        assertEquals(2, testStartScreenService.towerCreation(1).getResourceAmount());
        assertEquals(2, testStartScreenService.towerCreation(1).getReloadSpeed());
        assertEquals(1, testStartScreenService.towerCreation(2).getResourceAmount());
        assertEquals(1, testStartScreenService.towerCreation(2).getReloadSpeed());
    }

    /**
     * Tests the addingTower method
     * Tests if the correct tower and description is added to the player's inventory
     */
    @Test
    public void testAddingTower(){
        game = new GameEnvironment();
        testStartScreenService = new StartScreenService(game);
        String[] selectedDescriptions = {testStartScreenService.towerDescriptionCreation(0), testStartScreenService.towerDescriptionCreation(1), testStartScreenService.towerDescriptionCreation(2)};
        testStartScreenService.addingTower(selectedDescriptions);
        assertEquals(game.getInventory().getMainTowerDescriptions(0), testStartScreenService.towerDescriptionCreation(0));
        assertEquals(game.getInventory().getMainTowerDescriptions(1), testStartScreenService.towerDescriptionCreation(1));
        assertEquals(game.getInventory().getMainTowerDescriptions(2), testStartScreenService.towerDescriptionCreation(2));
        String[] newSelectedDescriptions = {testStartScreenService.towerDescriptionCreation(0), testStartScreenService.towerDescriptionCreation(1), testStartScreenService.towerDescriptionCreation(0)};
        testStartScreenService.addingTower(newSelectedDescriptions);
        assertEquals(game.getInventory().getMainTowerDescriptions(0), testStartScreenService.towerDescriptionCreation(0));
        assertEquals(game.getInventory().getMainTowerDescriptions(1), testStartScreenService.towerDescriptionCreation(1));
        assertEquals(game.getInventory().getMainTowerDescriptions(0), testStartScreenService.towerDescriptionCreation(0));
    }
}