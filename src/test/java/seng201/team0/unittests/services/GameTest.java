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
        testGame.setName("Megan");
        assertEquals("Megan", testGame.getName());

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
    }
}