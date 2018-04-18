import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class TestCoins {
	private static Coins testCoins;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testCoins = new Coins();
	}
	
    @Test
    void testInClassExampleMakeChangeOf16(){

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
	void testAll5s() {
		int[] coins = {1, 5, 17};
		int[] expected = {5, 5, 5};
		
		// test correct change is made
		int sum = 0;
		for (int i : testCoins.makeChange(coins, 15) ) {
			sum += i;
		}
		assertEquals(15, sum);
			
		// test correct array returns
		assertArrayEquals(expected,   testCoins.makeChange(coins, 15));
	}
	
	@Test
	void testMixedReturn() {
		int[] coins = {1, 5, 10, 25};
		int[] expected = {25, 10, 1, 1};
		
		// test correct change is made
		int sum = 0;
		for (int i : testCoins.makeChange(coins, 37) ) {
			sum += i;
		}
		assertEquals(37, sum);
		
		// test correct coins return
		assertArrayEquals(expected,   testCoins.makeChange(coins, 37));
	}
	
	@Test
	void testGreedyFails() {
		int[] coins = {1, 5, 11};
		// this is the correct answer but a greedy algorithm will give {11, 5, 1, 1, 1, 1}
		int[] expected = {5, 5, 5, 5};
		
		// test correct change is made
		int sum = 0;
		for (int i : testCoins.makeChange(coins, 20) ) {
			sum += i;
		}
		assertEquals(20, sum);
		
		// test correct coins return
		assertEquals(expected,   testCoins.makeChange(coins, 20));
	}
}