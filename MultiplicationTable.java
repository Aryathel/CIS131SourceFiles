/**
 *  <p>This program outputs a multiplication table given the low and high values of the table.</p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 2020-09-25
 */

public class MultiplicationTable {
    final static String VERTICAL = "|";
    final static String HORIZONTAL = "-";
    final static String CORNER = "*";
    final static int LOW_MIN = 1;
    final static int LOW_MAX = 200;
    final static int HIGH_MAX = 200;

    public static void main(String[] args) {
        System.out.println("----- Welcome to the Expanded Multiplicaton Table program -----\n");      

        // Declaring variables
        boolean doItAgain;
        int low, high, highMin;

        // Creating the question to ask for the low number in the range.
        String lowQuestion = String.format("What is the low value? (%d-%d)", LOW_MIN, LOW_MAX);
        String highQuestion;

        do {
            // Get the low value of the range
            low = HeroIR.getIntegerInRange(lowQuestion, LOW_MIN, LOW_MAX);

            if (low != HIGH_MAX) {
                // If the low value is not equal to the maximum high value,
                /// Set the minimum high value to the value of low,
                highMin = low;

                /// Create the question to ask the user,
                highQuestion = String.format("What is the high value? (%d-%d)", highMin, HIGH_MAX);

                /// Ask the user for the high value of the range.
                high = HeroIR.getIntegerInRange(highQuestion, highMin, HIGH_MAX);
            } else {
                // If the low value is equal to the maximum high value,
                /// Set the value of the high variable to the same as low
                high = low;

                /// And alert the user of this action
                System.out.printf("Only one high value was available, the high value was set to %d.\n", high);
            }

            printMultiplicationTable(low, high);

            doItAgain = HeroIR.getYorN("Do you want to do this again? (y/n)");
        } while (doItAgain);

        System.out.println("\n----- Program Complete -----");
    }
    
    //----------------------------------------------------------------------------------------
    /** Prints out a full multiplication table given a number range to use.
     *
     * @param low - The low end of the range for the multiplication table.
     * @param high - The high end of the range for the multiplication table.
     */
    private static void printMultiplicationTable(int low, int high) {
        int x, y; // Initialize variables for looping

        int gapNum = String.valueOf(high * high).length() + 1; // The number of spaces each printed number will contain

        int legendGapNum = String.valueOf(high).length() + 1;  // The number of spaces each legend number contains

        // Using those numbers, calculate the number of horizontal characters to print:
        /// (legendGapNum * 2) = The number of spaces for the legend, on each side
        /// (gapNum * (high - low + 1)) = The number of spaces each number takes up, multiplied by the number of numbers
        /// 4 = A constant number added in the form of a space and a vertical character on each end of a line
        String headerLine = String.format("%0" + ((legendGapNum * 2) + (gapNum * (high - low + 1)) + 4)  + "d", 0).replace("0", HORIZONTAL);

        printNumberLegend(low, high, gapNum, legendGapNum);
        System.out.println(headerLine);

        // Use nested for loop to loop through the 2d table
        for (x = low; x <= high; x++) {
            // Print the legend on the left side of a row
            System.out.printf("%-" + legendGapNum + "d%s", x, VERTICAL);

            // Print all the numbers in a row
            for (y = low; y <= high; y++) {
                System.out.printf("%" + gapNum + "d", x * y);
            }

            // Print the legend on the right side of a row
            System.out.printf(" %s%" + legendGapNum + "d\n", VERTICAL, x);
        }

        System.out.println(headerLine);
        printNumberLegend(low, high, gapNum, legendGapNum);
    }

    //----------------------------------------------------------------------------------------

    /** Prints the horizontal number legend for a range of numbers.
     *
     * @param low - The low value for the number range.
     * @param high - The high value for the number range.
     * @param gapNum - The number representing the amount of characters, including whitespace, a number takes up
     * @param legendGapNum - The number representing the amount of character, including whitespace,
     *                       a legend number takes up
     */
    private static void printNumberLegend(int low, int high, int gapNum, int legendGapNum) {
        int x;
        System.out.printf("%-" + legendGapNum + "s%s", CORNER, VERTICAL);
        for (x = low; x <= high; x++) {
            System.out.printf("%" + gapNum + "d", x);
        }
        System.out.printf(" %s%" + legendGapNum + "s\n", VERTICAL, CORNER);
    }
    
                                
}//end of the program