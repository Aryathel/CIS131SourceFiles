/** 
 *  @author Houghton Mayfield
 *  @author This program demonstrates my understanding of a Sentinel loop and the 2-function input validation model.
 *  @version 1.0  
 *
 */

public class InputLoopWithSentinelExercise {
    private final static String QUESTION = "Please enter a day of the week. (M,Tu,W,Th,F,Sa,Su, or Q to quit)";
    
    private static final String [] D_ABBREVIATIONS = {"q", "m", "tu", "w", "th", "f", "sa", "su"};
    private static final String [] DAYS = {"quit", "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
     
    /** The entry point for this program.
     *
     * @param args - Not used in this program.
     */
    public static void main(String[] args) {
        System.out.println("************************************************************************************");
        System.out.println("This program demonstrates how to code a standard input loop with a sentinel value.");
        System.out.println("************************************************************************************\n");

        // Use created input functions to get a valid day of the week
        int dayOfWeek = getValidDayOfWeek(QUESTION);
        // Continue looping until the user inputs q or quit to quit the program
        while (dayOfWeek != 0) {
            System.out.println("The day of the week is: " + DAYS[dayOfWeek]);
            dayOfWeek = getValidDayOfWeek(QUESTION);
        }
        
        System.out.println("\n--- Program Complete ---");
    }
    
    //--------------------------------------------------------------------------------------------------
    /** Get a valid day of the week.
     *
     * @param msg - The message displayed to the user.
     * @return Returns a valid day of the week: 0=m, 1=tu, 2=w, etc... 8=q
     */
    public static int getValidDayOfWeek(String msg) {
        // Get original user input
        String day = IR4.getString(msg);

        // While the user input is not a valid day
        while (isInvalidDayOfWeek(day) == -1) {
            System.out.println("Invalid day of the week.");
            day = IR4.getString(msg);
        }

        return isInvalidDayOfWeek(day); // The validation function returns the integer day of the week already
    }
    
    /** Validates the day entered by the user is valid (or q for quit). 
     * Any word starting with a valid day of the week text value is acceptable. (m for monday, tu for tuesday, etc)
     *
     * @param day - The user's entered value of the day of the week.
     * @return An integer weekday value: 0=m, 1=tu, 2=w, etc... 8=q, -1=not valid
     */
    public static int isInvalidDayOfWeek(String day) {

        String lowerDay = day.toLowerCase();

        for (int i = 0; i < D_ABBREVIATIONS.length; i ++) {
            if (lowerDay.startsWith(D_ABBREVIATIONS[i])) {
                return i; // Return as soon as a match is found to remove extraneous loops
            }
        }
        
        return -1; //invalid day of week
    }   
    
}