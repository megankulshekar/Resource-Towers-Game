package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;
import seng201.team0.services.CartThreads;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing units in CartThreads class
 */
public class CartThreadsTest {
    /**
     * The cart used for testing purposes
     */
    private Cart testCart;

    /**
     * The car threads used for testing purposes
     */
    private CartThreads testCartThreads;

    /**
     * Before each test, the test cart is created
     */
    @BeforeEach
    public void createTestCart(){
        testCart = new Cart(10, "Coal", 10);
    }

    /**
     * Tests if the cart threads reduce the cart time down to zero
     * @throws InterruptedException
     */
    @Test
    public void testThreads() throws InterruptedException {
        testCartThreads = new CartThreads(testCart);
        Thread testThread = new Thread(testCartThreads);
        testThread.start();
        testThread.join();
        assertEquals(0, testCart.getTime());
    }

    /**
     * Tests threads when they are interrupted
     * @throws InterruptedException
     */
    @Test
    public void testInterruptedThreads() throws InterruptedException {
        testCartThreads = new CartThreads(testCart);
        Thread testThread = new Thread(testCartThreads);
        testThread.start();
        Thread.sleep(5000);
        testThread.interrupt();
        assertNotEquals(0, testCart.getTime());

        Cart testCart2 = new Cart(10, "Coal", 15);
        testCartThreads = new CartThreads(testCart);
        CartThreads testCartThreads2 = new CartThreads(testCart2);
        Thread testThread1 = new Thread(testCartThreads);
        Thread testThread2 = new Thread(testCartThreads2);
        testThread1.start();
        testThread2.start();
        Thread.sleep(5000);
        testThread1.interrupt();
        assertNotEquals(0, testCart.getTime());
        testThread2.join();
        assertEquals(0, testCart2.getTime());
    }
}
