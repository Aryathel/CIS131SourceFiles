/**
 *  <h>Lab 3: Mortgage Looping</h>
 *  <p>This program demonstrates an understanding of string output formatting.</p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 10-03-2020
 */

public class Lab3MortgageLoan {
    public static final String LOAN_Q = "Enter the loan amount (or enter 0 to quit): ";
    public static final String STARTING_INTEREST_Q = "Enter the starting annual interest rate as a percent (n.nnn) : ";
    public static final String ENDING_INTEREST_Q = "Enter the ending annual interest rate as a percent (n.nnn) : ";
    public static final String FIRST_TERM_Q = "Enter the first term in years for calculating payments : ";
    public static final String LAST_TERM_Q = "Enter the last term in years for calculating payments : ";

    public static final double MINIMUM_INTEREST = 1.0;
    public static final int MINIMUM_YEAR = 1;

    /** The entrypoint of the program, starts the questioning process for mortgage information.
     *
     * @param args This class can take arguments, but this is not used.
     */
    public static void main(String[] args) {
        double startingInterest, endingInterest;
        int firstTerm, lastTerm;

        int loan = getLoanAmount(LOAN_Q);

        while (loan != 0) {
            startingInterest = getInterestRate(STARTING_INTEREST_Q, MINIMUM_INTEREST);
            endingInterest = getInterestRate(ENDING_INTEREST_Q, startingInterest);
            firstTerm = getYear(FIRST_TERM_Q, MINIMUM_YEAR);
            lastTerm = getYear(LAST_TERM_Q, firstTerm);

            printInterestTable(loan, startingInterest, endingInterest, firstTerm, lastTerm);

            loan = getLoanAmount(LOAN_Q);
        }

        System.out.println("------ Program Complete ------");
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints out the table of interest values.
     *
     * @param loanAmount The original value of a loan.
     * @param startingInterest The minimum interest rate.
     * @param endingInterest The maximum interest rate.
     * @param firstTerm The shortest period of time for paying back the loan.
     * @param lastTerm The longest period of time for paying back the loan.
     */
    public static void printInterestTable(int loanAmount, double startingInterest, double endingInterest, int firstTerm, int lastTerm) {
        int rows = (int)((endingInterest - startingInterest) / 0.25);
        int cols = (lastTerm - firstTerm) / 5;
        if (cols > 4) {
            cols = 4;
        }

        int colWidth = String.format("%.2f", calculateInterest(loanAmount, firstTerm, endingInterest / 100)).length();
        if (colWidth < String.format("%d Years", lastTerm).length()) {
            colWidth = String.format("%d Years", lastTerm).length();
        }

        printInterestHeader(firstTerm, lastTerm, colWidth);

        double rate, payment;
        int spacing, lSpacing, rSpacing, len;

        for (int i = 0; i <= rows; i++) {
            rate = startingInterest + (0.25 * i);
            System.out.printf("  %.3f ", rate);
            for (int j = 0; j <= cols; j++) {
                payment = calculateInterest(loanAmount, firstTerm + (j * 5), rate / 100);

                len = String.format("%.2f", payment).length();

                spacing = colWidth - len;
                lSpacing = spacing / 2;
                if (spacing % 2 == 0) {
                    rSpacing = lSpacing;
                } else {
                    rSpacing = lSpacing + 1;
                }
                System.out.print("   ");
                if (lSpacing > 0) {
                    System.out.printf("%" + lSpacing + "c", ' ');
                }
                System.out.printf("%.2f", payment);
                if (rSpacing > 0) {
                    System.out.printf("%" + rSpacing + "c", ' ');
                }
            }
            System.out.println();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the header of the interest rate table.
     *
     * @param firstTerm An integer representing the first number of years the loan will be repaid over.
     * @param lastTerm An integer representing the last number of years the loan will be repaid over.
     * @param colWidth An integer representing the width each column is meant to take up.
     */
    public static void printInterestHeader(int firstTerm, int lastTerm, int colWidth) {
        System.out.println("\n Interest");
        System.out.print("   Rate ");
        int year;
        for (int i = 0; i <= (lastTerm - firstTerm) / 5; i++) {
            if (i >= 5) {
                break;
            }

            year = firstTerm + (5 * i);

            System.out.printf("   %" + (colWidth - 6) + "d Years", year);
        }
        System.out.println();
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Calculate the monthly payment of a loan given the interest rate, the number of years to continue payments for,
     * and the original loan price.
     *
     * @param loanAmount The original amount the loan was made for.
     * @param year The number of years the loan is being paid for.
     * @param rate The annual interest rate of the loan.
     * @return A double representing the monthly payment.
     */
    public static double calculateInterest(int loanAmount, int year, double rate) {
        double monthlyRate = rate / 12;
        double numMonths = year * 12;

        double numerator = monthlyRate * Math.pow(1 + monthlyRate, numMonths);
        double denominator = Math.pow(1 + monthlyRate, numMonths) - 1;
        double annuity = numerator / denominator;

        return annuity * loanAmount;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Get the user to enter an amount of money that the loan is for, or 0 to exit the program.
     *
     * @param msg The message to ask the use
     * @return An integer representing the amount of money a loan is for.
     */
    public static int getLoanAmount(String msg) {
        int loan = HeroIR.getInteger(msg);

        while (isInvalidLoan(loan)) {
            loan = HeroIR.getInteger(msg);
        }

        return loan;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Check an integer to see if it matches the criteria for being a valid loan amount.
     *
     * @param loan An integer representing the amount of money a loan is for.
     * @return A boolean representing whether the loan amount is invalid.
     */
    public static boolean isInvalidLoan(int loan) {
        if (loan < 0) {
            System.err.println("The loan amount cannot be less than $0.");
            return true;
        } else if (loan > 0 && loan < 1000) {
            System.err.println("The loan amount must be at least $1000.");
            return true;
        } else if (loan > 9999999) {
            System.err.println("The loan amount cannot exceed $9999999.");
            return true;
        }

        return false;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Get the user to enter an interest rate.
     *
     * @param msg The message to ask the user.
     * @param minimum The minimum interest rate that can be entered.
     * @return A double representing an interest rate.
     */
    public static double getInterestRate(String msg, double minimum) {
        // Gets a double that is not less than 0 and not greater than 100
        double interest = HeroIR.getPercentage(msg);

        // Validate the interest rate
        while (isInvalidInterestRate(interest, minimum)) {
            interest = HeroIR.getPercentage(msg);
        }

        return interest;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Check an integer to see if it matches the criteria for being a valid loan amount.
     *
     * @param interest A double representing an interest rate.
     * @param minimum The minimum interest rate that can be entered.
     * @return A boolean representing whether the loan amount is invalid.
     */
    public static boolean isInvalidInterestRate(double interest, double minimum) {
        // Checking the various conditions of a valid interest rate between the minimum and 8.00,
        // limiting the decimal increment by 0.25
        if (interest < minimum) {
            System.err.printf("The interest rate cannot be less than %.2f.\n", minimum);
            return true;
        } else if (interest > 8.00) {
            System.err.println("The interest rate cannot be greater than 8.00.");
            return true;
        } else if (interest % 0.25 != 0) {
            System.err.println("The interest rate must end in .00, .25, .50, or .75.");
            return true;
        }

        return false;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a valid year input from the user.
     *
     * @param msg The question to ask the user while retrieving the input.
     * @param min The minimum year value that can be entered by the user.
     * @return An integer representing a year number.
     */
    public static int getYear(String msg, int min) {
        int year = HeroIR.getInteger(msg);

        while (isInvalidYear(year, min)) {
            year = HeroIR.getInteger(msg);
        }

        return year;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Checks a provided integer to see if it is a valid year number.
     *
     * @param year An integer representing an unvalidated year.
     * @param min An integer representing the minimum value for the year.
     * @return A boolean representing whether the year is invalid.
     */
    public static boolean isInvalidYear(int year, int min) {
        if (year < min) {
            System.err.printf("Year value must be greater than %d.\n", min);
            return true;
        }

        // If this is not the first year being entered
        if (min != MINIMUM_YEAR) {
            // If the year is not a multiple of 5 greater than the minimum
            if ((year - min) % 5 != 0) {
                System.err.println("The year value must be a multiple of 5 greater than the first year value.");
                return true;
            }
        }

        // The year is valid
        return false;
    }
}
