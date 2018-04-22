import java.util.ArrayList;
import java.util.Arrays;

public class Coins {
    private int []min_coins;
    private int coin_option_size;
    private ArrayList<Integer> coins_in_optimal_solution= new ArrayList<>();

    public  int[]  makeChange(int[] coinOptions, int changeAmount) {
    	// we need some coins to be able to make change...
		if (coinOptions == null || coinOptions.length == 0) {
			throw new IllegalArgumentException("Must have at least one coin type");
		}
		else {
	        Arrays.sort(coinOptions);   //sorts the coin options from least to greatest

            //calls method to get coin_option_size
            coin_option_size = getCoin_option_size(coinOptions);

            //instantiate array to hold minimum number of coins
            min_coins = new int[changeAmount+1];

            // set index 0 to 0 since no coins can derive from 0
            min_coins[0] = 0;
            //sets all entries of array to MAX_Value, but entry 0
            this.setup_change_array_to_max_int(min_coins);
            this.fill_min_array(changeAmount, coinOptions);
		}
		return min_coins;
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
     *  This method is the dynamic process. It find the optimal solutions and adds to an array of minimum number of coins.
     * @param makeChangeof
     * @param coinOptions
     */
    public void fill_min_array(int makeChangeof, int [] coinOptions) {
        //iterates through all the change but entry 0
        for (int i = 1; i <= makeChangeof; i++) {
            //iterates through all the coin options
            for (int j = 0; j < coinOptions.length; j++) {

                //if the coin at entry j is < or equal to the current change to be made...
                if (coinOptions[j] <= i) {

                    //set a temp variable that contains the optimal solution to the current change to be made
                    int temp = min_coins[i - coinOptions[j]]; // in the minimum coins array, subtract the coin option to find previous optimal solution

                    //
                    if (temp != Integer.MAX_VALUE && temp + 1 < min_coins[i]) {
                        // add +1 to temp because it is adding another coin to previous optimal solutions
                        min_coins[i] = temp + 1;
                        coins_in_optimal_solution.add(coinOptions[j]);
                    }
                }
            }
        }
    }

    public int getCoin_option_size(int[] coin_options){
        return coin_options.length;
    }
    public int get_optimal_solution(int change){
      return  min_coins[change];
    }

}
