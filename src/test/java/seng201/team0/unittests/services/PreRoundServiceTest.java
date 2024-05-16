package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.CoalTower;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;
import seng201.team0.services.PreRoundService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in PreRoundService class
 */
public class PreRoundServiceTest {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The pre round service used for testing purposes
     */
    private PreRoundService testPreRoundService;

    /**
     * Tests the giveMoney method with easy game difficulty
     */
    @Test
    public void testGiveMoneyEasyDifficulty() {
        game = new GameEnvironment();
        game.setDifficulty("Easy");
        assertEquals(30, game.getMoney());
        for (int i = 0; i < game.getNumberRounds(); i++) {
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        game.getRounds().get(0).createCarts();
        testPreRoundService = new PreRoundService(game);
        String moneyGiven = testPreRoundService.giveMoney();
        assertEquals(35, game.getMoney());
        assertEquals("$5", moneyGiven);
        moneyGiven = testPreRoundService.giveMoney();
        assertEquals(40, game.getMoney());
        assertEquals("$5", moneyGiven);
    }

    /**
     * Tests the giveMoney method with medium game difficulty
     */
    @Test
    public void testGiveMoneyMediumDifficulty() {
        game = new GameEnvironment();
        game.setDifficulty("Medium");
        assertEquals(20, game.getMoney());
        for (int i = 0; i < game.getNumberRounds(); i++) {
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        game.getRounds().get(0).createCarts();
        testPreRoundService = new PreRoundService(game);
        String moneyGiven = testPreRoundService.giveMoney();
        assertEquals(30, game.getMoney());
        assertEquals("$10", moneyGiven);
        moneyGiven = testPreRoundService.giveMoney();
        assertEquals(40, game.getMoney());
        assertEquals("$10", moneyGiven);
    }

    /**
     * Tests the giveMoney method with hard game difficulty
     */
    @Test
    public void testGiveMoneyHardDifficulty(){
        game = new GameEnvironment();
        game.setDifficulty("Hard");
        assertEquals(10, game.getMoney());
        for (int i = 0; i < game.getNumberRounds(); i++){
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        game.getRounds().get(0).createCarts();
        testPreRoundService = new PreRoundService(game);
        String moneyGiven = testPreRoundService.giveMoney();
        assertEquals(25, game.getMoney());
        assertEquals("$15", moneyGiven);
        moneyGiven = testPreRoundService.giveMoney();
        assertEquals(40, game.getMoney());
        assertEquals("$15", moneyGiven);
    }

    /**
     * Tests the random events methods towerGainsXP and towerBreaks
     */
    @Test
    public void testRandomEvents(){
        game = new GameEnvironment();
        for (int i = 0; i < game.getNumberRounds(); i++){
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        Tower testTower = new CoalTower();
        int XPBefore = testTower.getXP();
        game.addToInventory(testTower, testTower.getDescription());
        testPreRoundService = new PreRoundService(game);
        int randomAmount = testPreRoundService.towerGainsXP();
        testTower = game.getInventory().getMainTowers(0);
        int XPAfter = testTower.getXP();
        assertEquals(randomAmount, XPAfter - XPBefore);

        testPreRoundService.towerBreaks();
        assertTrue(testTower.isBroken());
    }

    /**
     * Tests the setEasyDifficulty method
     */
    @Test
    public void testSetEasyDifficulty(){
        game = new GameEnvironment();
        game.setDifficulty("Hard");
        for (int i = 0; i < game.getNumberRounds(); i++){
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        testPreRoundService = new PreRoundService(game);
        Round currentRound = testPreRoundService.getCurrentRound();
        assertEquals("Hard", currentRound.getDifficulty());
        assertEquals(6, currentRound.getCartSize());
        assertEquals(3, currentRound.getCartSpeed());
        testPreRoundService.setEasyDifficulty();
        assertEquals("Easy", currentRound.getDifficulty());
        assertEquals(7, currentRound.getCartSize());
        assertEquals(4, currentRound.getCartSpeed());
    }

    /**
     * Tests the setMediumDifficulty method
     */
    @Test
    public void testSetMediumDifficulty(){
        game = new GameEnvironment();
        game.setDifficulty("Easy");
        for (int i = 0; i < game.getNumberRounds(); i++){
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        testPreRoundService = new PreRoundService(game);
        Round currentRound = testPreRoundService.getCurrentRound();
        assertEquals("Easy", currentRound.getDifficulty());
        assertEquals(4, currentRound.getCartSize());
        assertEquals(1, currentRound.getCartSpeed());
        testPreRoundService.setMediumDifficulty();
        assertEquals("Medium", currentRound.getDifficulty());
        assertEquals(6, currentRound.getCartSize());
        assertEquals(2, currentRound.getCartSpeed());
    }

    /**
     * Tests the setHardDifficulty method
     */
    @Test
    public void testSetHardDifficulty(){
        game = new GameEnvironment();
        game.setDifficulty("Medium");
        for (int i = 0; i < game.getNumberRounds(); i++){
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        testPreRoundService = new PreRoundService(game);
        Round currentRound = testPreRoundService.getCurrentRound();
        assertEquals("Medium", currentRound.getDifficulty());
        assertEquals(5, currentRound.getCartSize());
        assertEquals(2, currentRound.getCartSpeed());
        testPreRoundService.setHardDifficulty();
        assertEquals("Hard", currentRound.getDifficulty());
        assertEquals(8, currentRound.getCartSize());
        assertEquals(3, currentRound.getCartSpeed());
    }

    /**
     * Tests the addResourceType method by validating that new resource types are added at specific rounds
     */
    @Test
    public void testAddResourceType(){
        game = new GameEnvironment();
        for (int i = 0; i < game.getNumberRounds(); i++){
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        int index = game.getCurrentRoundIndex();
        assertEquals(0, index);
        testPreRoundService = new PreRoundService(game);
        Round testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertFalse(testCurrentRound.getResourceTypes().contains("Copper"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Iron"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Gold"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(1, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Iron"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Gold"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(2, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Iron"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Gold"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(3, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Iron"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Gold"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(4, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Iron"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Gold"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(5, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Iron"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Gold"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(6, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Iron"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Gold"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(7, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Iron"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Gold"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(8, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Iron"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Gold"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertFalse(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(9, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Iron"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Gold"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Uranium"));

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(10, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewResourceType();
        assertTrue(testCurrentRound.getResourceTypes().contains("Copper"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Iron"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Gold"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Diamond"));
        assertTrue(testCurrentRound.getResourceTypes().contains("Uranium"));
    }

    @Test
    public void testAddNewCart(){
        game = new GameEnvironment();
        game.setDifficulty("Easy");
        for (int i = 0; i < game.getNumberRounds(); i++){
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        int index = game.getCurrentRoundIndex();
        assertEquals(0, index);
        testPreRoundService = new PreRoundService(game);
        Round testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewCart();
        testCurrentRound.createCarts();
        assertEquals(3, testCurrentRound.getCarts().size());

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(1, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewCart();
        testCurrentRound.createCarts();
        assertEquals(4, testCurrentRound.getCarts().size());

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(2, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewCart();
        testCurrentRound.createCarts();
        assertEquals(4, testCurrentRound.getCarts().size());

        game.increaseCurrentRoundIndex();
        index = game.getCurrentRoundIndex();
        assertEquals(3, index);
        testPreRoundService = new PreRoundService(game);
        testCurrentRound = testPreRoundService.getCurrentRound();
        testPreRoundService.addNewCart();
        testCurrentRound.createCarts();
        assertEquals(5, testCurrentRound.getCarts().size());
    }
}
