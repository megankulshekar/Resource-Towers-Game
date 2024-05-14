package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;
import seng201.team0.models.CoalTower;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoundTest {
    private Round testRound;

    @Test
    public void testGettersAndSetters(){
        testRound = new Round("Medium");
        assertEquals("Medium", testRound.getDifficulty());
        assertEquals(3, testRound.getNumCarts());
        assertEquals(5, testRound.getCartSize());
        assertEquals(2, testRound.getCartSpeed());
        assertEquals(List.of("Coal"), testRound.getResourceTypes());
        testRound.setDifficulty("Easy");
        testRound.increaseNumCarts(1);
        testRound.increaseCartSize(1);
        testRound.increaseCartSpeed(1);
        testRound.addResourceType("Copper");
        testRound.addResourceType("Coal");
        assertEquals("Easy", testRound.getDifficulty());
        assertEquals(4, testRound.getNumCarts());
        assertEquals(6, testRound.getCartSize());
        assertEquals(3, testRound.getCartSpeed());
        assertEquals(List.of("Coal", "Copper"), testRound.getResourceTypes());
        testRound.createCarts();
        List<Cart> carts = testRound.getCarts();
        for (Cart cart : carts){
            assertEquals(6, cart.getSize());
            assertTrue(testRound.getResourceTypes().contains(cart.getResourceType()));
            assertEquals(3, cart.getSpeed());
        }
        assertEquals(4, carts.size());
    }

    @Test
    public void testFillCart(){
        testRound = new Round("Easy");
        Cart testCart = new Cart(10, "Coal", 2);
        Tower testTower = new CoalTower();
        testRound.fillCart(testCart, testTower);
        assertEquals(2, testCart.getCurrentLevel());
        testTower.setBroken(true);
        testRound.fillCart(testCart, testTower);
        assertEquals(2, testCart.getCurrentLevel());
    }
}
