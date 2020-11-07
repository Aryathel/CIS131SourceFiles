/**
 *  <h1>Hero's Input Routines</h1>
 *  <p>
 *      This program is used to managing user input and base level input validation.
 *      This program is based of of the work of David Freitag's "IR4.java".
 *  </p>
 *  @author Houghton Mayfield
 *  @version 1.1
 *  @since 2020-09-16
 */

import java.util.Scanner;

public class HeroIR {
    //Putting the Scanner object here makes it global so it does not have to be passed to modules.
    static Scanner keyboard = new Scanner(System.in);

    /** This program demonstrates various generalized input routines.
     *
     * @param args Arguments can be passed to this program but they are not used.
     */
    public static void main(String[] args) {
        do {
            String stringData = getString("Please enter a word or two");
            System.out.println("This is what you entered: " + stringData);

            int intData = getInteger("Please enter an integer");
            System.out.println("This is what you entered: " + intData);

            //Generating random numbers.
            for (int i = 0; i < 10; i++) {
                System.out.println("random number " + (i + 1) + ": " + getRandomNumber(0, 10));
            }

        } while (getYorN("\nDo you want to start over? (y/n)"));

        //close the Scanner
        closeScanner();

        System.out.println("Program Terminating Normally");
    }

    public static void displayGoodbye(){
        System.out.println("Goodbye!");
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERALIZED INPUT FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** Gets a String from the keyboard. Rejects null entry or any number of spaces.
     *
     * @param msg is the text that will be displayed the user to ask them to enter a value.
     * @return Returns a String from the keyboard. 
     */
    public static String getString(String msg) {
        String answer = "";
        System.out.print(msg);
        try {
            answer = keyboard.nextLine();
        }
        catch (Exception e) {
            System.err.println("Error reading input from user. Ending program.");
            System.exit(-1);
        }

        while (answer.replace(" ", "").equals("")) {
            System.err.println("Error: Missing input.");
            try {
                System.out.print(msg);
                answer = keyboard.nextLine();
            }
            catch (Exception e) {
                System.err.println("Error reading input from user. Ending program.");
                System.exit(-1);
            }
        }
        return answer;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets an Integer from the keyboard. Rejects null, spaces and non-integers.
     *
     * @param msg is the text that will be displayed the user to ask them to enter a number.
     * @return Returns an int from the keyboard. 
     */
    public static int getInteger(String msg) {
        System.out.print(msg);
        while (!keyboard.hasNextInt()) {
            keyboard.nextLine();
            System.err.println("Invalid integer. Try again.");
        }
        int number = keyboard.nextInt();
        keyboard.nextLine(); //flushes the buffer
        return number;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a Double from the keyboard. Rejects null, spaces and non-numbers.
     *
     * @param msg is the text that will be displayed the user to ask them to enter a number.
     * @return Returns a double from the keyboard. 
     */
    public static double getDouble(String msg) {
        System.out.print(msg);
        while (!keyboard.hasNextDouble()) {
            keyboard.nextLine();
            System.err.println("Invalid number. Try again.");
        }
        double number = keyboard.nextDouble();
        keyboard.nextLine(); //flushes the buffer
        return number;
    }

    public static double getPercentage(String msg) {
        double percent = getDouble(msg);

        while (percent < 0 || percent > 100) {
            if (percent < 0) {
                System.err.println("Percentage cannot be negative.");
            } else if (percent > 100) {
                System.err.println("Percentage cannot be greater than 100.");
            }

            percent = getDouble(msg);
        }

        return percent;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets an integer input from the user, between the minimum and maximum numbers.
     *
     * @param msg The text that will be displayed the user.
     * @param min The minimum value for the user to input.
     * @param max The maximum value the user can input.
     * @return Returns an integer between the minimum and maximum, inclusive.
     */
    public static int getIntegerInRange(String msg, int min, int max) {
        int num = getInteger(msg);

        while (num < min || num > max) {
            System.err.printf("Error: User input must be between %d and %d.\n", min, max);
            num = getInteger(msg);
        }

        return num;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a Yes or No answer from the keyboard. Calls getString to rejects null input and spaces.
     *
     * @param msg is the text that will be displayed the user.
     * @return Returns a boolean value. True = yes; False = no. 
     */
    public static boolean getYorN(String msg) {
        String answer = getString(msg);

        while (answer.compareToIgnoreCase("y")   != 0
                && answer.compareToIgnoreCase("yes") != 0
                && answer.compareToIgnoreCase("n")   != 0
                && answer.compareToIgnoreCase("no")  != 0) {

            if (answer.replace(" ", "").equals("")) {
                System.err.println("Error: Missing y/n input.");
            } else {
                if (answer.compareToIgnoreCase("y")   != 0
                        && answer.compareToIgnoreCase("yes") != 0
                        && answer.compareToIgnoreCase("n")   != 0
                        && answer.compareToIgnoreCase("no")  != 0) {
                    System.err.println("Error: Unexpected input.");
                }
            }
            answer = getString(msg);
        }

        if  (answer.compareToIgnoreCase("y")   == 0
                || answer.compareToIgnoreCase("yes") == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Get a single character input.
     *
     * @param msg The message prompt given to the user.
     * @return The input character
     */
    public static char getChar(String msg) {
        String answer = getString(msg);

        while (answer.length() > 1) {
            System.err.println("Error: Input cannot exceed one character.");
            answer = getString(msg);
        }

        return answer.charAt(0);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a 2-dimensional coordinate, parsing for 2 integers separated by a comma.
     *
     * @param msg The message that the user will have displayed to them before their input is requested.
     * @return A Coordinate2D instance
     */
    public static Coordinate2D get2DCoordinate(String msg) {
        int input = getInteger(msg);

        int x = input / 10;
        int y = input % 10;

        return new Coordinate2D(x, y);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Closes the scanner.
     */
    public static void closeScanner() {
        try {
            if(keyboard != null) {
                keyboard.close();
            }
        }
        catch (Exception e) { // (Exception) catches all errors java might throw here
            System.err.println("Error closing reader.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Generates a random number between low and high, inclusive.
     *
     * @param low is the smallest number that will be randomly generated.
     * @param high is the largest number that will be randomly generated.
     * @return Returns the random number as an integer.
     */
    public static int getRandomNumber (int low, int high) {
        return (int)(Math.random() * ((high + 1) - low)) + low;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Generates a random double between low and high, inclusive.
     *
     * @param low is the smallest number that will be randomly generated.
     * @param high is the largest number that will be randomly generated.
     * @return Returns the random number.
     */
    public static double getRandomDouble(int low, int high) {
        return (Math.random() * (high - low)) + low;
    }
}
