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
     * Test the towerCreation method
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
}