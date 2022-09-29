package test;

import model.Counter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterTest {

    private Counter testCounter;

    @BeforeEach
    public void setUp() {
        testCounter = new Counter();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testCounter.getCount());
    }

    @Test
    public void testIncrease() {
        testCounter.increase();
        assertEquals(1, testCounter.getCount());
        testCounter.increase();
        assertEquals(2, testCounter.getCount());
    }

    @Test
    public void testDecrease() {
        testCounter.increase();
        assertEquals(1, testCounter.getCount());
        testCounter.decrease();
        assertEquals(0, testCounter.getCount());
    }

    @Test
    public void testReset() {
        testCounter.increase();
        assertEquals(1, testCounter.getCount());
        testCounter.reset();
        assertEquals(0, testCounter.getCount());
    }
}
