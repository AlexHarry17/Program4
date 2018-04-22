import java.util.Arrays;

public class Coins {
	// a list of the minimum number of coins for each change amount given by the index
    private int[] min_number_of_coins;
    // a list of the last added coin in the minimum change for the given index
    private int[] last_coin_added_in_solution;

    public  int[]  makeChange(int[] coinOptions, int changeAmount) {
    	// we need some coins to be able to make change...
		if (coinOptions == null || coinOptions.length == 0) {
			throw new IllegalArgumentException("Must have at least one coin type");
		}
		else {
	        Arrays.sort(coinOptions);   //sorts the coin options from least to greatest
	        
            //instantiate array to hold minimum number of coins
            min_number_of_coins = new int[changeAmount+1];
            last_coin_added_in_solution = new int[changeAmount+1];
            
            // set index 0 to 0 since no coins can derive from 0
            min_number_of_coins[0] = 0;
            //sets all entries of array to MAX_Value, but entry 0
            this.setup_change_array_to_max_int(min_number_of_coins);
            this.fill_min_array(changeAmount, coinOptions);
		}
		return this.get_optimal_solution(changeAmount);
    }

    /** Sets all values in min_coins array, but index 0, to MAX_Value
     * for future comparison.
     *
     * @param min_coins
     */
    private void setup_change_array_to_max_int(int [] min_coins) {
        //iterate through all spots in array but index 0 to set to MAX_Value
        for(int i = 1; i <= min_coins.length-1; i++) {
            min_coins[i] = Integer.MAX_VALUE;
        }
    }

    /**
     *  This method is the dynamic process. It finds the optimal solutions and adds to an array of minimum number of coins.
     * @param makeChangeof
     * @param coinOptions
     */
    private void fill_min_array(int makeChangeof, int [] coinOptions) {
    	// add empty coin
    	 last_coin_added_in_solution[0] = 0;
        //iterates through all the change but entry 0
        for (int i = 1; i <= makeChangeof; i++) {
            //iterates through all the coin options
            for (int j = 0; j < coinOptions.length; j++) {

                //if the coin at entry j is < or equal to the current change to be made...
                if (coinOptions[j] <= i) {

                    //set a temp variable that contains the optimal solution to the current change to be made
                    int temp = min_number_of_coins[i - coinOptions[j]]; // in the minimum coins array, subtract the coin option to find previous optimal solution

                    if (temp != Integer.MAX_VALUE && temp + 1 < min_number_of_coins[i]) {
                        // add +1 to temp because it is adding another coin to previous optimal solutions
                        min_number_of_coins[i] = temp + 1;
                        last_coin_added_in_solution[i] = coinOptions[j];
                    }
                }
            }
        }
    }
    
    /**
     * Gets the optimal solution for the given change amount from the solutions to the sub problems
     * @param change to find the minimum change for
     * @return int[] of the minimum set of coins
     */
    private int[] get_optimal_solution(int change){
    	// aggregate the solution from the sub problems
    	int local_change = change;
    	int[] tmp1 = new int[min_number_of_coins[change]];
    	
    	for (int i=0; i<tmp1.length; i++) {
    		tmp1[i] = last_coin_added_in_solution[local_change];
    		local_change -= last_coin_added_in_solution[local_change];
    	}
    	// sort the solution int array
    	Arrays.sort(tmp1);
    	
    	// flip the array order
    	int[] tmp2 = new int[min_number_of_coins[change]];
    	
    	for (int i=0; i<tmp1.length; i++ ) {
    		tmp2[i] = tmp1[tmp1.length - i - 1];
    	}
    	return tmp2;
    }
}
