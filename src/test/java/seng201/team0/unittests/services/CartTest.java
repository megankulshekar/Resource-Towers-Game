package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in Cart class
 */
public class CartTest {
    /**
     * The cart used for testing purposes
     */
    private Cart testCart;

    /**
     * Tests the getter and setter methods
     */
    @Test
    public void testGettersAndSetters(){
        testCart = new Cart(10, "Coal", 2);
        assertEquals(10, testCart.getSize());
        assertEquals("Coal", testCart.getResourceType());
        assertEquals(2, testCart.getSpeed());
        assertEquals(50, testCart.getTime());
        assertFalse(testCart.isFull());
        assertEquals(0, testCart.getCurrentLevel());
        assertEquals("Cart Type: Coal\n\nAmount full: 0/10\n\n" +
                "Time left: 50", testCart.getDescription());
        testCart.decreaseTime();
        testCart.decreaseTime();
        testCart.decreaseTime();
        testCart.increaseCurrentLevel(5);
        testCart.increaseCurrentLevel(12);
        testCart.increaseCurrentLevel(1);
        assertTrue(testCart.isFull());
        assertEquals("Cart Type: Coal\n\nAmount full: 10/10\n\n" +
                "Time left: 47", testCart.getDescription());
    }
}
