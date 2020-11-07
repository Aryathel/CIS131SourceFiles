/**
 *  <p>This program tests various methods of the java String class.</p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 2020-10-02
 */
import java.util.Scanner;

public class StringManipulations {
    private final static int LOW = 10;
    private final static int HIGH = 30;

    public static void main(String[] args) {
        System.out.println("This program will demonstrate several ways to manipulate a string.\n");

        boolean cont;

        //Put a do-you-want-to-do-it-again loop around this code.
        do {
            String aString = getValidString(LOW, HIGH, String.format("Please enter multiple words totaling %d to %d characters.", LOW, HIGH));

            aString = displayOne(aString);  //returns the string trimmed of starting and ending spaces
            displayTwo(aString);
            displayThree(aString);
            displayFour(aString);
            displayFive(aString);
            displaySix(aString);
            displaySeven(aString);
            displayEight(aString);
            displayNine(aString);
            displayTen(aString);
            displayEleven(aString);

            System.out.println("\nThis completes our demonstration.");

            cont = IR4.getYorN("Would you like to continue? (y/n)");
        } while (cont);


        System.out.println("\n--- Program Complete ---"); 
    }
    
    //--------------------------------------------------------------------------------------------------
    //Complete each of the below functions to print the correct string or show the correct information
    //--------------------------------------------------------------------------------------------------
    
    //code this standard input validation routine using IR4.getString(...) 
    public static String getValidString(int low, int high, String msg){
        String newValue = IR4.getString(msg); // Get a string

        // Make sure string is within valid inputs
        while (newValue.length() < low || newValue.length() > high) {
            System.err.println("Error: Your string is not an acceptable length.");
            newValue = IR4.getString(msg);
        }

        return newValue;
    }
    
    public static String displayOne(String theString) {
        // Trim the leading and trailing whitespace
        theString = theString.trim();
        System.out.println("1. The trimmed length of the string is " + theString.length()
                               + " characters.  (no spaces beginning or ending the string)");
        return theString; //return the trimmed string value
    }
    
    public static void displayTwo(String theString) {
        // Check if a string is all uppercase
        if (theString.toUpperCase().equals(theString)) {
            System.out.println("2. This string is all uppercase.");
        } else {
            System.out.println("2. This string is not all uppercase.");
        }
    }
    
    public static void displayThree(String theString) {
        // Check if a string is all lowercase
        if (!theString.toLowerCase().equals(theString)) {
            System.out.println("3. This string is not all lowercase.");
        } else {
            System.out.println("3. This string is all lowercase.");
        }
    }
    
    public static void displayFour(String theString) {
        // convert the string to uppercase
        System.out.println("4. In uppercase the string is: " + theString.toUpperCase());
    }
    
    public static void displayFive(String theString) {
        // convert the string to lowercase
        System.out.println("5. In lowercase the string is: " + theString.toLowerCase());
    }
    
    public static void displaySix(String theString) {
        // Print the string three times
        String newString = theString.concat(theString);
        System.out.println("6. The string repeated is: " + newString);
    }
    
    public static void displaySeven(String theString) {
        // Replacing spaces with '#'
        System.out.println("7. The string with spaces replaced with # is: " + theString.replace(' ', '#'));
    }
    
    public static void displayEight(String theString) {
        // Determine if the string is "This is a test"
        if (theString.equals("This is a test")) {
            System.out.println("8. Comparing your string with “This is a test” shows they are the same.");
        } else {
            System.out.println("8. Comparing your string with “This is a test” shows they are not the same.");
        }
    }
    
    public static void displayNine(String theString) {
        // Determine if the string starts with the word "Hello"
        if (theString.startsWith("Hello")) {
            System.out.println("9. This string starts with the word \"Hello\"");
        } else {
            System.out.println("9. This string does not start with the word \"Hello\"");
        }
    }
    
    public static void displayTen(String theString) {
        // Determine if the string ends with the word "World!"
        if (theString.endsWith("World!")) {
            System.out.println("10. This string ends with the word \"World!\"");
        } else {
            System.out.println("10. This string does not end with the word \"World!\"");
        }
    }
    
    public static void displayEleven(String theString) {
        //If the length of theString is 5 or more:
        if (theString.length() >= 5) {
            System.out.printf("11. The 5th character of the string is: `%c`\n", theString.charAt(4));
        }
        //If the length of theString is 2 or more:
        if (theString.length() >= 2) {
            System.out.printf(
                    "12. The next to last character of the string is: `%c`\n",
                    theString.charAt(theString.length() - 2)
            );
        }
    }
}