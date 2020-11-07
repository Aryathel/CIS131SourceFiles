/**
 *  <h1>Houghton Mayfield Lab 4 - Arrays</h1>
 *  <p>
 *      This program is used to demonstrate operations and parsing of arrays.
 *  </p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 10-09-2020
 *
 *  NOTES:
 *  - For the function calculating the average, I was not sure whether I should pass the already calculated
 *    array sum as a parameter or not, so I decided on calling the sum function again inside of the average method.
 *    This was just to keep each function capable of running on their own with just the array being passed in.
 */

public class Lab4Arrays {
    final static int SIZE = 10;            // The size of the 1D array, and the N size of the 2D array NxN
    final static int MINIMUM_VALUE = 100;  // The minimum value to populate the arrays with
    final static int MAXIMUM_VALUE = 999;  // The maximum value to populate the arrays with
    final static int MENU_WIDTH = 45;      // The width of the menu, to keep things consistent
    final static int ARRAY_1D = 1;         // The menu option number for a 1 dimensional array
    final static int ARRAY_2D = 2;         // The menu option number for a 2 dimensional array
    final static int EXIT = 3;             // The menu option number for quitting

    // Strings used in printing
    final static String UNSORTED_1D_ARRAY_MSG = "Auto-Generated Array Elements: (unsorted)";
    final static String SORTED_1D_ARRAY_MSG = "Auto-Generated Array Elements: (sorted)";
    final static String UNSORTED_2D_ARRAY_MSG = "Auto-Generated %dx%d Array Elements: (unsorted)\n";
    final static String SORTED_2D_ARRAY_MSG = "Auto-Generated %dx%d Array Elements: (sorted)\n";
    final static String MAX_1D_VALUE_MSG = "The index of the highest value is %d. Its value is %d.\n";
    final static String MAX_2D_VALUE_MSG = "The index of the highest value is %d,%d. Its value is %d.\n";
    final static String MIN_1D_VALUE_MSG = "The index of the lowest value is %d. Its value is %d.\n";
    final static String MIN_2D_VALUE_MSG = "The index of the lowest value is %d,%d. Its value is %d.\n";
    final static String SUM_1D_MSG = "The sum of the %d array elements is %d.\n";
    final static String SUM_2D_MSG = "The sum of the %dx%d array elements is %d.\n";
    final static String AVE_1D_MSG = "The average of the %d array elements is %.2f.\n";
    final static String AVE_2D_MSG = "The average of the %dx%d array elements is %.3f.\n";
    final static String GOODBYE = "--- Program Ended Normally ---";

    public static void main(String[] args) {
        int selection; // Menu selection variable

        // Creating the main menu
        HeroMenu menu = new HeroMenu(MENU_WIDTH,"Array Program");
        menu.addOption(ARRAY_1D, "One-Dimensional Array");
        menu.addOption(ARRAY_2D, "Two-Dimensional Array");
        menu.addOption(EXIT, "Exit");

        do {
            // Show the main menu and get the selection from that
            menu.display();
            selection = menu.getSelection();

            // Reacting to the selection
            switch(selection) {
                case ARRAY_1D:
                    process1DArray(); // Processing a 1 Dimensional array
                    break;
                case ARRAY_2D:

                    process2DArray(); // Processing 2 Dimensional array
                    break;
            }
        } while (selection != EXIT); // Loop until the user enters the exit number

        // Print the goodbye message
        System.out.println(GOODBYE);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Processes and prints information about the processes using a 1 dimensional array.
     *
     */
    public static void process1DArray() {
        // Declaring variables to use
        int[] array1D;
        int smallest1DElem, largest1DElem, arraySum;
        double arrayAve;

        // Used just for printing a header before processing each array type, to keep things looking consistent
        HeroMenu header1D = new HeroMenu(MENU_WIDTH, "One-Dimensional Processing");

        header1D.display();

        // Create the empty array
        array1D = new int[SIZE];

        // Populate the array with random values
        populate(array1D, MINIMUM_VALUE, MAXIMUM_VALUE);

        // Print the freshly populated array
        System.out.println(UNSORTED_1D_ARRAY_MSG);
        displayArray(array1D);

        // Run calculations for smallest index, largest index, sum, and average of the array
        smallest1DElem = getSmallestIndex(array1D);
        largest1DElem = getLargestIndex(array1D);
        arraySum = getArraySum(array1D);
        arrayAve = getArrayAverage(array1D);
        // Display those calculations
        displayArrayCalculations(array1D, smallest1DElem, largest1DElem, arraySum, arrayAve);

        // Sort the array
        sortArray(array1D);

        // Print the sorted array
        System.out.println(SORTED_1D_ARRAY_MSG);
        displayArray(array1D);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Processes and prints information about the processes using a 2 dimensional array.
     *
     */
    public static void process2DArray() {
        // Declaring variables to use
        int[][] array2D;
        int arraySum;
        double arrayAve;
        Coordinate2D smallest2DElem, largest2DElem;

        // Used just for printing a header before processing each array type, to keep things looking consistent
        HeroMenu header2D = new HeroMenu(MENU_WIDTH, "Two-Dimensional Processing");

        // Processing 2 Dimensional array
        header2D.display();

        // Create the empty 2D array
        array2D = new int[SIZE][SIZE];

        // Populate the array with random values
        populate(array2D, MINIMUM_VALUE, MAXIMUM_VALUE);

        // Print the freshly populated array
        System.out.printf(UNSORTED_2D_ARRAY_MSG, SIZE, SIZE);
        displayArray(array2D);

        // Run calculations for smallest index, largest index, sum, and average of the array
        smallest2DElem = getSmallestIndex(array2D);
        largest2DElem = getLargestIndex(array2D);
        arraySum = getArraySum(array2D);
        arrayAve = getArrayAverage(array2D);
        // Display those calculations
        displayArrayCalculations(array2D, smallest2DElem, largest2DElem, arraySum, arrayAve);

        // Sort the array
        sortArray(array2D);

        // Print the sorted array
        System.out.printf(SORTED_2D_ARRAY_MSG, SIZE, SIZE);
        displayArray(array2D);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Populates a given 1 dimensional integer array with random unique values between the given low and high.
     *
     * @param array The 1 dimensional integer array to be populated.
     * @param low An integer representing the low end range of numbers to populate with.
     * @param high An integer representing the high end range of numbers to populate with.
     */
    public static void populate(int[] array, int low, int high) {
        if (high - (low - 1) < array.length) {
            System.err.println("Warning: The program cannot find enough unique values to fill the array, " +
                    "and will loop infinitely if it attempts to do so.");
            return;
        }

        int num;
        for (int i = 0; i < array.length; i++) {
            num = getUniqueInteger(array, low, high);
            array[i] = num;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Populates a given 2 dimensional integer array with random unique values between the given low and high.
     *
     * @param array The 2 dimensional integer array to be populated.
     * @param low An integer representing the low end range of numbers to populate with.
     * @param high An integer representing the high end range of numbers to populate with.
     */
    public static void populate(int[][] array, int low, int high) {
        if (high - (low - 1) < (array.length) * (array[0].length)) {
            System.err.println("Warning: The program cannot find enough unique values to fill the array, " +
                    "and will loop infinitely if it attempts to do so.");
            return;
        }

        int num;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                num = getUniqueInteger(array, low, high);
                array[i][j] = num;
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a unique integer that is not already present in the array.
     *
     * @param array The 1 dimensional integer array to be populated.
     * @param low An integer representing the low end range of numbers to populate with.
     * @param high An integer representing the high end range of numbers to populate with.
     * @return An integer containing the unique number to be added ot the array.
     */
    public static int getUniqueInteger(int[] array, int low, int high) {
        int num = HeroIR.getRandomNumber(low, high);
        while (existsInArray(array, num)) {
            num = HeroIR.getRandomNumber(low, high);
        }

        return num;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a unique integer that is not already present in the array.
     *
     * @param array The 2 dimensional integer array to be populated.
     * @param low An integer representing the low end range of numbers to populate with.
     * @param high An integer representing the high end range of numbers to populate with.
     * @return An integer containing the unique number to be added ot the array.
     */
    public static int getUniqueInteger(int[][] array, int low, int high) {
        int num = HeroIR.getRandomNumber(low, high);
        while (existsInArray(array, num)) {
            num = HeroIR.getRandomNumber(low, high);
        }

        return num;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Checks to see if a number exists an a given 1 dimensional array.
     *
     * @param array A 1 dimensional integer array to be checked.
     * @param num An integer to check for in the array.
     */
    public static boolean existsInArray(int[] array, int num) {
        for (int j : array) {
            if (j == num) {
                return true;
            }
        }

        return false;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Checks to see if a number exists an a given 2 dimensional array.
     *
     * @param array A 2 dimensional integer array to be checked.
     * @param num An integer to check for in the array.
     */
    public static boolean existsInArray(int[][] array, int num) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt == num) {
                    return true;
                }
            }
        }

        return false;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints out a 1 dimensional array, delimited by spaces.
     *
     * @param array The 1 dimensional integer array to print.
     */
    public static void displayArray(int[] array) {
        for (int j : array) {
            System.out.printf("%3d ", j);
        }
        System.out.println("\n");
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints out a 2 dimensional array, delimited by spaces.
     *
     * @param array The 2 dimensional integer array to print.
     */
    public static void displayArray(int[][] array) {
        for (int[] i : array) {
            for (int j : i) {
                System.out.printf("%3d  ", j);
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets the index of the smallest element in an array of integers.
     *
     * @param array The 1 dimensional integer array to search through.
     * @return An integer index of the smallest number in the array.
     */
    public static int getSmallestIndex(int[] array) {
        int min = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < array[min]) {
                min = i;
            }
        }
        return min;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets the index of the smallest element in an array of integers.
     *
     * @param array The 2 dimensional integer array to search through.
     * @return An 2D coordinate representing the index of the smallest number in the array.
     */
    public static Coordinate2D getSmallestIndex(int[][] array) {
        Coordinate2D min = new Coordinate2D(0, 0);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < array[min.X][min.Y]) {
                    min.setCoordinate(i, j);
                }
            }
        }
        return min;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets the index of the largest element in an array of integers.
     *
     * @param array The 1 dimensional integer array to search through.
     * @return An integer index of the largest number in the array.
     */
    public static int getLargestIndex(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[max]) {
                max = i;
            }
        }
        return max;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets the index of the largest element in an array of integers.
     *
     * @param array The 2 dimensional integer array to search through.
     * @return An 2D coordinate representing the index of the largest number in the array.
     */
    public static Coordinate2D getLargestIndex(int[][] array) {
        Coordinate2D max = new Coordinate2D(0, 0);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > array[max.X][max.Y]) {
                    max.setCoordinate(i, j);
                }
            }
        }
        return max;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets the sum of an integer array.
     *
     * @param array A 1 dimensional array to get the sum of.
     * @return An integer containing the sum of all integers in the array.
     */
    public static int getArraySum(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets the sum of an integer array.
     *
     * @param array A 2 dimensional array to get the sum of.
     * @return An integer containing the sum of all integers in the array.
     */
    public static int getArraySum(int[][] array) {
        int sum = 0;
        for (int[] i : array) {
            for (int j : i) {
                sum += j;
            }
        }
        return sum;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets the average of an integer array.
     *
     * @param array A 1 dimensional array to get the average of.
     * @return An double containing the average of all integers in the array.
     */
    public static double getArrayAverage(int[] array) {
        int sum = getArraySum(array);
        return (double)sum / array.length;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets the average of an integer array.
     *
     * @param array A 2 dimensional array to get the average of.
     * @return An double containing the average of all integers in the array.
     */
    public static double getArrayAverage(int[][] array) {
        int sum = getArraySum(array);
        return (double)sum / (array.length * array[0].length);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Print out information on the given one dimensional array according to the previous
     *
     * @param array A 1 dimensional integer array that was used in the calculations.
     * @param minI An integer representing the index of the minimum array value.
     * @param maxI An integer representing the index of the maximum array value.
     * @param sum An integer representing the sum of the array.
     * @param ave A double representing the average of the array.
     */
    public static void displayArrayCalculations(int[] array, int minI, int maxI, int sum, double ave) {
        System.out.printf(MIN_1D_VALUE_MSG, minI, array[minI]);
        System.out.printf(MAX_1D_VALUE_MSG, maxI, array[maxI]);
        System.out.printf(SUM_1D_MSG, SIZE, sum);
        System.out.printf(AVE_1D_MSG, SIZE, ave);
        System.out.println();
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Print out information on the given one dimensional array according to the previous
     *
     * @param array A 2 dimensional integer array that was used in the calculations.
     * @param minI An integer representing the index of the minimum array value.
     * @param maxI An integer representing the index of the maximum array value.
     * @param sum An integer representing the sum of the array.
     * @param ave A double representing the average of the array.
     */
    public static void displayArrayCalculations(int[][] array, Coordinate2D minI, Coordinate2D maxI, int sum, double ave) {
        System.out.printf(MIN_2D_VALUE_MSG, minI.X, minI.Y, array[minI.X][minI.Y]);
        System.out.printf(MAX_2D_VALUE_MSG, maxI.X, maxI.Y, array[maxI.X][maxI.Y]);
        System.out.printf(SUM_2D_MSG, SIZE, SIZE, sum);
        System.out.printf(AVE_2D_MSG, SIZE, SIZE, ave);
        System.out.println();
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Sorts a given 1 dimensional integer array from least to greatest value.
     * This uses a standard injection sort model.
     *
     * @param array A 1 dimensional integer array to be sorted.
     */
    public static void sortArray(int[] array) {
        // Integer variables
        int i, j, tmp;
        // Looping through all array indices starting at 1
        for (i = 1; i < array.length; i++) {
            // Set j equal to the index right before the current index (will be 0 first)
            j = i - 1;
            // Set tmp to store the current value of the index while values are adjusted
            tmp = array[i];

            // Continue shifting the j index down to lower indices until the next number before it is less than tmp
            while (j >= 0 && array[j] > tmp) {
                array[j + 1] = array[j];
                j = j - 1;
            }

            // Assign tmp to that place in the list
            array[j + 1] = tmp;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Sorts a given 2 dimensional integer array from least to greatest value.
     * This flattens the 2 dimensional array into a one dimensional array and passes that to the
     * one dimensional sorting method.
     *
     * @param array A 2 dimensional integer array to be sorted.
     */
    public static void sortArray(int[][] array) {
        // Integer variables
        int i = 0, x, y, tmp;
        // Create a 1 dimensional array to store the 2 dimensional array
        int[] temp = new int[array.length * array.length];

        // Flatten the 2 dimensional array
        for (x = 0; x < array.length; x++) {
            for (y = 0; y < array[x].length; y++) {
                temp[i] = array[x][y];
                i++;
            }
        }

        // Sort the 1 dimensional array
        sortArray(temp);

        // Update the 2 dimensional array to reflect the sorted 1 dimensional array
        i = 0;
        for (x = 0; x < array.length; x++) {
            for (y = 0; y < array[x].length; y++) {
                array[x][y] = temp[i];
                i++;
            }
        }
    }
}
