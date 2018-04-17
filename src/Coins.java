import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Coins {
    int[] changeArray = new int[100];
    private ArrayList<Integer> changeList = null;

    public int[] getChangeArray() {    //getter for change array;
        return changeArray;
    }

    public void makeChange(int[] coinOptions, int changeAmount) {
        Arrays.sort(coinOptions);   //sorts the coin options from least to greatest
        this.setupChangeArray(coinOptions);

    }

    private void setupChangeArray(int[] coinOptions) {
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

    public ArrayList<Integer> getChange(int makeChangeof) {  //method that will find the change and added it to the array
        while (makeChangeof >= 0) {//loops until correct change has been made
            changeList.add(changeArray[makeChangeof]);  //adds the coin used at that array location to an arraylist
            makeChangeof -= changeArray[makeChangeof];  //removes coin used from makeChangeof to update the array location on the next loop

        }
        return changeList;
    }

}
