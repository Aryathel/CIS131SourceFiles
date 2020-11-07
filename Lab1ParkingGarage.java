/**
 *  <h1>Houghton Mayfield Lab 1 - Wegougem Parking Garage</h1>
 *  <p>
 *      This is a program which allows for a parking garage to create receipts for parking, and keep
 *      track of total daily sales.
 *  </p>
 *  @author Houghton Mayfield
 *  @version 1.1
 *  @since 2020-09-16
 */

public class Lab1ParkingGarage {
    // Defining pricing constants
    private final static double WEEKDAY_RATE = 1.25;
    private final static double WEEKDAY_MIN_FEE = 3.0;
    private final static double WEEKDAY_MAX_FEE = 15.0;
    private final static double WEEKEND_RATE = 0.5;
    private final static double WEEKEND_MIN_FEE = 2.0;
    private final static double WEEKEND_MAX_FEE = 10.0;

    // Define information about valid days and inputs for the days
    private static final String [] D_ABBREVIATIONS = {"q", "m", "tu", "w", "th", "f", "sa", "su"};
    private static final String [] DAYS = {"quit", "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

    // Questions for user interaction
    private static final String DAY_QUESTION = "Please enter the day of the week (mon, tue, wed, thu, fri, sat, sun) or quit: ";
    private final static String ARRIVAL_TIME_QUESTION = "Please enter the vehicle's arrival time   (HHMM): ";
    private final static String DEPARTURE_TIME_QUESTION = "Please enter the vehicle's departure time (HHMM): ";

    /** The method which controls the input, creation, and addition of receipts for the
     * daily sales in a parking garage.
     *
     * @param args - Not used in this program
     */
    public static void main(String[] args) {
        printIntro();

        double totalCharge = 0;

        // Use created input functions to get user inputs for the day of the week
        int dayOfWeek = getValidDayOfWeek(DAY_QUESTION);

        // Declare other variables for the loop
        int arrivalTime, departureTime, duration;
        double charge;

        // Continue looping until the user inputs q or quit to quit the program
        while (dayOfWeek != 0) {
            // Use created input functions to get user inputs for the arrival time and departure time
            arrivalTime = getValidTime(ARRIVAL_TIME_QUESTION);
            departureTime = getValidTime(DEPARTURE_TIME_QUESTION);

            // Perform necessary calculations for the output
            duration = getDuration(arrivalTime, departureTime);
            charge = getCharge(duration, dayOfWeek);

            // Add the charge to the total amount charged for the day
            totalCharge += charge;

            // Printing receipt outputs
            printReceiptHeader();
            System.out.printf("Day of Week: %s\n", DAYS[dayOfWeek]);           // Print the input day of the week
            printDailyRate(dayOfWeek);                                         // Print the sales rate for the day
            System.out.printf("Arrival time: %8d\n", arrivalTime);             // Print the arrival time
            System.out.printf("Departure time: %6d\n", departureTime);         // Print the departure time.
            System.out.printf("Parking Duration: %4d minutes\n\n", duration); // Print the duration of a car's stay
            System.out.printf("Amount Charged: $%.2f\n", charge);
            System.out.println("*******************************\n");

            // Use created input functions to get user inputs for the day of the week
            dayOfWeek = getValidDayOfWeek(DAY_QUESTION);
        }

        System.out.printf("\nTotal Charged Today: $ %.2f", totalCharge);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the introductory statement to the program.
     *
     */
    public static void printIntro() {
        System.out.println("*******************************");
        System.out.println("    Wegougem Parking Garage");
        System.out.println("*******************************");
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the header for a sales receipt.
     *
     */
    public static void printReceiptHeader() {
        System.out.println("\n*******************************");
        System.out.println("    Wegougem Parking Garage");
        System.out.println("         Sales Receipt");
        System.out.println("*******************************");
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the price rate for the provided day.
     *
     * @param day - An integer representing the day of the week.
     */
    public static void printDailyRate(int day) {
        if (day == 6 || day == 7) {
            System.out.printf("Rate: $%.2f per 15 minute interval\n\n", WEEKEND_RATE);
        } else {
            System.out.printf("Rate: $%.2f per 15 minute interval\n\n", WEEKDAY_RATE);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Get a valid day of the week.
     *
     * @param msg - The message displayed to the user.
     * @return Returns a valid day of the week: 0=m, 1=tu, 2=w, etc... 8=q
     */
    public static int getValidDayOfWeek(String msg) {
        // Get original user input
        String day = HeroIR.getString(msg);

        // While the user input is not a valid day
        while (isInvalidDayOfWeek(day) == -1) {
            System.err.println("Error: Invalid day of the week.");
            day = HeroIR.getString(msg);
        }

        return isInvalidDayOfWeek(day); // The validation function returns the integer day of the week already
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Validates the day entered by the user is valid (or q for quit).
     * Any word starting with a valid day of the week text value is acceptable. (m for monday, tu for tuesday, etc)
     *
     * @param day - The user's entered value of the day of the week.
     * @return An integer weekday value: 0=m, 1=tu, 2=w, etc... 8=q, -1=not valid
     */
    public static int isInvalidDayOfWeek(String day) {
        // Get the lowercase form of the input day
        String lowerDay = day.toLowerCase();

        for (int i = 0; i < D_ABBREVIATIONS.length; i ++) {
            if (lowerDay.startsWith(D_ABBREVIATIONS[i])) {
                return i; // Return as soon as a match is found to remove extraneous loops
            }
        }

        return -1; //invalid day of week
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Get a valid time from the user in the format HHMM.
     *
     * @param msg - The message displayed to the user.
     * @return time - Returns a valid time in HHMM format.
     */
    public static int getValidTime(String msg) {
        int time;

        //Get input from the user using the below getIntegerBetweenLowAndHigh function
        time = HeroIR.getIntegerInRange(msg, 0000, 2359);

        while (isInvalidTime(time)) {
            time = HeroIR.getIntegerInRange(msg, 0000, 2359);
        }

        return time;
    }

    //------------------------------------------------------------------------------------------------------------------
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
            System.err.println("Invalid hour number: The number of hours must be between 0 and 23, inclusive\n");
            return true;
        } else if (minutes < 0 || minutes > 59) {
            System.err.println("Invalid minute number: The number of minutes must be between 0 and 59, inclusive\n");
            return true;
        }

        return false; // Means the time is OK
    }

    //------------------------------------------------------------------------------------------------------------------
    /** This function is used to determine the length of time between the provided start and end times.
     *
     * @param startTime - The start of the time period, in a 24 hour HHMM integer format.
     * @param endTime - The end of the time period, in a 24 hour HHMM integer format.
     * @return An integer number of minutes between the start and end of the time period.
     */
    public static int getDuration(int startTime, int endTime) {
        // Convert the HHMM format into just a minute total
        int startMins = ((startTime / 100) * 60) + (startTime % 100);
        int endMins = ((endTime / 100) * 60) + (endTime % 100);

        // If the minute of the end time is less than arrival time, the clock has rolled over.
        // This is accounted for by adding 24 hours to the departure time, allowing to find the difference.
        if (endMins > startMins) {
            return endMins - startMins;
        } else {
            return (endMins + (24 * 60)) - startMins;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Calculates the charge to give a car depending on the amount of time spent in the garage,
     *  and the day of the week.
     *
     * @param minutes - An integer representing the number of minutes spent in the garage.
     * @param dayOfWeek - An integer representing the day of the week.
     * @return A double representing the amount of money the car is getting charged.
     */
    public static double getCharge(int minutes, int dayOfWeek) {
        double charge = 0.0;

        int fifteenMinIncrements = minutes / 15; // Find the number of 15 minute increments

        // If the car was in the garage for less than 15 minutes, return no charge.
        if (fifteenMinIncrements == 0) {
            return charge;
        }

        // If there are extra minutes in the next increment, add one 15 minute charge
        if (minutes % 15 != 0) {
            fifteenMinIncrements += 1;
        }

        if (dayOfWeek == 6 || dayOfWeek == 7) { // If it is a weekend
            charge = fifteenMinIncrements * WEEKEND_RATE; // Calculate charge based on time spent

            // Adjust the charge to min or max fees depending on the charge up to this point
            if (charge < WEEKEND_MIN_FEE) {
                charge = WEEKEND_MIN_FEE;
            } else if (charge > WEEKEND_MAX_FEE) {
                charge = WEEKEND_MAX_FEE;
            }
        } else {
            charge = fifteenMinIncrements * WEEKDAY_RATE; // Calculate charge based on time spent

            // Adjust the charge to min or max fees depending on the charge up to this point
            if (charge < WEEKDAY_MIN_FEE) {
                charge = WEEKDAY_MIN_FEE;
            } else if (charge > WEEKDAY_MAX_FEE) {
                charge = WEEKDAY_MAX_FEE;
            }
        }

        return charge;
    }
}
