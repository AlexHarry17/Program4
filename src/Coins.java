public class Coins {
    int[] changeArray = new int[100];

    public int[] getChangeArray() {    //getter for change array;
        return changeArray;
    }

    public int[] makeChange(int[] coinOptions, int changeAmount) {
        Arrays.sort(coinOptions);   //sorts the coin options from least to greatest
        this.setupChangeArray(coinOptions);

    }

    public void setupChangeArray(int[] coinOptions) {
        changeArray[0] = 0; //instantiates the 0 spot
        changeArray[1] = 1; //instantiates the 1 spot
        for (int i = 2; i < changeArray.length; i++) {  //loops through the change array starting at location 2
            for (int j = coinOptions.length - 1; j >= 0; j--) { //loops through the denominations, starting with the highest
                if (changeArray[i] % coinOptions[j] == 0) { //checks what denomination is divisiable by the location i in the array
                    changeArray[i] = coinOptions[j];    //sets location of change array to the denomination chosen
                    break;  //breaks out of inner loop once location in array is set
                }
            }
        }
    }
}
