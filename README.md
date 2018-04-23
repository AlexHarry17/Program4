# Program4 #
**Authors: Alex Harry, Cory Johns, Justin Keeling**
**Date: 4/23/2018**
**Overview:**
Coins contains all methods to find the minimum number of coins (and what coins) for a given change. Coins will return an int[] containing the minimum number of coins in order from largest to smallest for a given change value and denominations set. Coins accomplishes this with a dynamic programming approach, using the solutions to the sub problems to solve the next larger one.
TestCoins contains 9 basic test cases for Coins.java. The tests it runs are as follows:
* tests to make sure the correct Exception is thrown when the denominations array is empty 
* runs same kind of test as above but for a null denominations array
* tests that the correct Exception is thrown when the change is not positive
* tests that the correct change is made for really large input
* tests that the minimum change is returned for a homogeneous case
* also for a non-homogeneous case
* tests that the minimum change is returned for a input that is known to fail greedy algorithms
* tests that {25, 5} is returned for change of 30 instead of {10, 10, 10} given standard denominations
* tests the example given in class
