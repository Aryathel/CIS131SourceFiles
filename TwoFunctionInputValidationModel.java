/** 
 *  @author Houghton Mayfield
 *  @author This program demonstrates my understanding of the 2-function input validation model.
 *  @version 1.0  
 
 Complete the below code.
 Use IR4.java to get the input.
 
 Ask the user for a time in HHMM format.
 
 Answer these subproblems before writing ANY code:
    1. What makes time in the HHMM format valid or invalid?
        Making sure that the hour is between 00 and 23, and minutes between 0 and 59, inclusive.
    2. How do you break up HHMM into HH and MM so you can validate HH and MM?
        Read it as a 4-digit number, to get hours divide by 100, to get minutes perform % 100
 
 */

public class TwoFunctionInputValidationModel {
    private final static String ARRIVAL_TIME_QUESTION = "Please enter the vehicle's arrival time (HHMM)";
    
    /** The entry point for this program. 
      * @param args - Null: Not used in this program.  
      */ 
    public static void main(String[] args) {
        System.out.println("************************************************************************************");
        System.out.println("This program demonstrates how to code a standard two-function input validation model.");
        System.out.println("************************************************************************************\n");
        
        int arrivalTime = getValidTime(ARRIVAL_TIME_QUESTION);
        
        System.out.println("The valid arrival time is: " + arrivalTime);
        
        System.out.println("\n--- Program Complete ---"); 
    }
    
    //--------------------------------------------------------------------------------------------------
    /** Get a valid time from the user in the format HHMM.
     *
     * @param msg - The message displayed to the user.
     * @return time - Returns a valid time in HHMM format.
     */
    public static int getValidTime(String msg) {
        int time;
        
        //Get input from the user using the below getIntegerBetweenLowAndHigh function
        time = getIntegerBetweenLowAndHigh(ARRIVAL_TIME_QUESTION, 0000, 2359);

        while (isInvalidTime(time)) {
            time = getIntegerBetweenLowAndHigh(ARRIVAL_TIME_QUESTION, 0000, 2359);
        }
        
        return time;
    }
    
   /** Determines if the input is valid in HHMM format.
    *
    * @param time - The message displayed to the user.
    * @return Boolean - false if valid, true if invalid.
    */
   public static boolean isInvalidTime(int time) {
       // Find the number of hours by dividing the time by 100.
       // Because both are integers, result is automatically cast to an int.
       int hours = time / 100;
       // Find the number of minutes by using the remainder operator when dividing by 100.
       int minutes = time % 100;

       // Check resulting values to make sure they are valid,
       // or return a response declaring why it is invalid.
       if (hours < 0 || hours > 23) {
           System.out.println("Invalid hour number: The number of hours must be between 0 and 23, inclusive\n");
           return true;
       } else if (minutes < 0 || minutes > 59) {
           System.out.println("Invalid minute number: The number of minutes must be between 0 and 59, inclusive\n");
           return true;
       }
       
       return false; //means the time is OK
   }
   
   
   /** Rejects integers less than and greater than the supplied parameters.
    *
    * Using IR4, it rejects null entries, any number of spaces, and non-numbers.
    * @param msg is the text that will be displayed the user to ask them to enter a value.
    * @param low is the lowest acceptable input value, inclusive.
    * @param high is the highestt acceptable input value, inclusive.
    * @return Returns an int from the keyboard that is from a low number through a high number.
    */
   public static int getIntegerBetweenLowAndHigh(String msg, int low, int high) {
       int number;

       // Get the user input as an integer.
       number = IR4.getInteger(msg);

       // Check to make sure the user input is between accepted values
       // continue to ask for input until it is.
       while (number < low || number > high) {
           System.out.println("Invalid input value. Must be > " + low + " and < " + high);
           number = IR4.getInteger(msg);
       }
       
       return number;
   }
}