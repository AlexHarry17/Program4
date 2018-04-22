
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;


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
		Exception e = assertThrows(IllegalArgumentException.class, () -> {  testCoins.makeChange(input, 221); } );
		// test correct error message is generated
		assertEquals("Must have at least one coin type", e.getMessage());
	}
    
    @Test
    void testNullInput() {
		// test  exception is thrown when given null
		Exception e = assertThrows(IllegalArgumentException.class, () -> {  testCoins.makeChange(null, 221); } );
		// test correct error message is generated
		assertEquals("Must have at least one coin type", e.getMessage());
    }
    
    @Test
    void testReallyLargeInput() {
		Random rand = new Random();
    	int[] coins = new int[rand.nextInt(rand.nextInt(100) * rand.nextInt(100)) + 2];
    	int change = rand.nextInt(90000);
    	// set up coins
    	for (int i=0; i<coins.length; i++) {
    		if (i>0) {
    			// increment denomination by as much as 15 but no less then 1
    			coins[i] = coins[i -1] + rand.nextInt(15) + 1;
    		}
    		else {
    			coins[i] = 1; // assure the sum can be made
    		}
    	}
    	int[] result =  testCoins.makeChange(coins, change);
		
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
		int[] result =  testCoins.makeChange(coins, change);
		
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
		int[] result =  testCoins.makeChange(coins, change);
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
		int[] result =  testCoins.makeChange(coins, change);
		
		// test correct change is made
		int sum = 0;
		for (int i : result ) {
			sum += i;
		}
		assertEquals(change, sum);
		
		// test correct coins return
		assertArrayEquals(expected, result);
	}
}