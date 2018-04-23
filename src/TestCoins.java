
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

/*
 * Authors: Alex Harry, Cory Johns, Justin Keeling
 * Date: 4/23/2018
 * Overview: TestCoins contains 9 basic test cases for Coins.java.
 * The tests it runs are as follows:
 * - tests to make sure the correct Exception is thrown when the denominations array is empty
 * - runs same kind of test as above but for a null denominations array
 * - tests that the correct Exception is thrown when the change is not positive
 * - tests that the correct change is made for really large input
 * - tests that the minimum change is returned for a homogeneous case
 * - also for a non-homogeneous case
 * - tests that the minimum change is returned for a input that is known to fail greedy algorithms
 * - tests that {25, 5} is returned for change of 30 instead of {10, 10, 10} given standard denominations
 * - tests the example given in class
 */
class TestCoins {
    private static Coins testCoins;

    @BeforeEach
    void setUp() throws Exception {
        testCoins = new Coins();
    }

    @Test
    void testEmptyInput() {
        int[] input = {};
        // test exception is thrown when given no coin denominations
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            testCoins.makeChange(input, 221);
        });
        // test correct error message is generated
        assertEquals("Must have at least one coin type", e.getMessage());
    }

    @Test
    void testNullInput() {
        // test  exception is thrown when given null
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            testCoins.makeChange(null, 221);
        });
        // test correct error message is generated
        assertEquals("Must have at least one coin type", e.getMessage());
    }

    @Test
    void testNegativeChange() {
        int[] coins = {1, 4, 6, 11};
        // test  exception is thrown when given a negative change value
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            testCoins.makeChange(coins, -13);
        });
        // test correct error message is generated
        assertEquals("Change must be positive", e.getMessage());
    }

    @Test
    void testReallyLargeInput() {
        Random rand = new Random();
        int[] coins = new int[rand.nextInt(rand.nextInt(100) * rand.nextInt(100)) + 2];
        int change = rand.nextInt(90000);
        // set up coins
        for (int i = 0; i < coins.length; i++) {
            if (i > 0) {
                // increment denomination by as much as 15 but no less then 1
                coins[i] = coins[i - 1] + rand.nextInt(15) + 1;
            } else {
                coins[i] = 1; // assure the sum can be made
            }
        }
        int[] result = testCoins.makeChange(coins, change);

        // test that the sum is correct
        int sum = 0;
        for (int i : result) {
            sum += i;
        }
        assertEquals(change, sum);
    }

    @Test
    void testAll5s() {
        int[] coins = {1, 5, 17};
        int[] expected = {5, 5, 5};
        int change = 15;
        int[] result = testCoins.makeChange(coins, change);

        // test correct change is made
        int sum = 0;
        for (int i : result) {
            sum += i;
        }
        assertEquals(change, sum);

        // test correct array returns
        assertArrayEquals(expected, result);
    }

    @Test
    void testMixedReturn() {
        int[] coins = {1, 5, 10, 25};
        int[] expected = {25, 10, 1, 1};
        int change = 37;
        int[] result = testCoins.makeChange(coins, change);
        // test correct change is made
        int sum = 0;
        for (int i : result) {
            sum += i;
        }
        assertEquals(change, sum);

        // test correct coins return
        assertArrayEquals(expected, result);
    }

    @Test
    void testGreedyFails() {
        int[] coins = {1, 5, 11};
        // this is the correct answer but a greedy algorithm will give {11, 5, 1, 1, 1, 1}
        int[] expected = {5, 5, 5, 5};
        int change = 20;
        int[] result = testCoins.makeChange(coins, change);

        // test correct change is made
        int sum = 0;
        for (int i : result) {
            sum += i;
        }
        assertEquals(change, sum);

        // test correct coins return
        assertArrayEquals(expected, result);
    }

    @Test
    void testChangeOf30() { //tests for the optimal change of 30
        int[] coins = {1, 5, 10, 25};   //currency option for this test
        int[] expected = {25,5};    //expected output of this test
        int change = 30;    //change of 30 to test
        int[] result = testCoins.makeChange(coins, change); //runs the algo to get the change
        // test correct coins return
        assertArrayEquals(expected, result);    //checks if the expected change equals the change generated by the algo
    }

    @Test
    void testOf16FromClass() { //tests for the optimal change of 16
        int[] coins = {1, 5, 12, 25};   //currency option for this test
        int[] expected = {5,5,5,1};    //expected output of this test
        int change = 16;    //change of 16 to test
        int[] result = testCoins.makeChange(coins, change); //runs the algo to get the change
        // test correct coins return
        assertArrayEquals(expected, result);    //checks if the expected change equals the change generated by the algo
    }
}