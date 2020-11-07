/**
 *  <h1>Lab 5 - Parallel Arrays</h1>
 *  <p>A lab demonstrating knowledge of parallel arrays.</p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 2020-10-19
 */

public class Lab5ParallelArrays {
    private final static int SIZE = 100;

    private final static int MIN_EMPLOYEES = 1;
    private final static int MAX_EMPLOYEES = 100;
    private final static String NUM_EMPLOYEES_QUESTION = "How many employees are there? ";

    private final static int MIN_EMPLOYEE_NUM = 1000;
    private final static int MAX_EMPLOYEE_NUM = 9999;
    private final static int MIN_HOURS_WORKED = 30;
    private final static int MAX_HOURS_WORKED = 50;
    private final static int MIN_PAY_RATE = 7;
    private final static int MAX_PAY_RATE = 25;

    private final static int REGULAR_PAY_THRESHOLD = 40;
    private final static int REGULAR_PAY = 0;
    private final static int OVERTIME_PAY = 1;
    private final static int TOTAL_PAY = 2;

    public static void main(String[] args) {
        int[] empNo = new int[SIZE];
        double[] payRate = new double[SIZE];
        double[] hoursWorked = new double[SIZE];
        double[] regularPay = new double[SIZE];
        double[] overtimePay = new double[SIZE];
        double[] totalPay = new double[SIZE];

        int numEmployees = HeroIR.getIntegerInRange(NUM_EMPLOYEES_QUESTION, MIN_EMPLOYEES, MAX_EMPLOYEES);

        if (!checkEmployeeAmount(numEmployees)) {
            numEmployees = SIZE;
        }

        populateEmployees(empNo, payRate, hoursWorked, numEmployees);

        // It was noted not to pass the arrays into the function for calculations in the instructions,
        // but I couldn't leave that much code in main. So I passed these arrays in, and then inside the function
        // I call another function to do the actual calculations.
        calculatePay(payRate, hoursWorked, regularPay, overtimePay, totalPay, numEmployees);

        sortByEmployeeNum(empNo, payRate, hoursWorked, regularPay, overtimePay, totalPay, numEmployees);
        printEmployeeData(empNo, payRate, hoursWorked, regularPay, overtimePay, totalPay, numEmployees);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Checks to make sure that the entered number of employees is not greater than the size of the employee arrays.
     *
     * @param numEmployees The number of employees entered by the user.
     * @return A boolean representing whether the value is ok or not.
     */
    public static boolean checkEmployeeAmount(int numEmployees) {
        // Check to make sure that the entered number of employees within the size of the array limits.
        if (numEmployees > SIZE) {
            System.out.printf(
                    "Error: The entered number of employees (%d) cannot be greater than the array size (%d)\n" +
                            "The number of employees will be set to %d.",
                    numEmployees, SIZE, SIZE
            );
            return false;
        }
        return true;
    }


    //------------------------------------------------------------------------------------------------------------------
    /** Populates the random values for employee numbers, pay rates, and hours worked.
     *
     * @param empNo An integer array which will contain all employee numbers.
     * @param payRate A double array which will contain all pay rates.
     * @param hoursWorked A double array which will contain hours worked.
     * @param numEmployees The number of employees to populate the array for.
     */
    public static void populateEmployees(int[] empNo, double[] payRate, double[] hoursWorked, int numEmployees) {
        for (int i = 0; i < numEmployees; i++) {
            // Assign a random unique employee number.
            empNo[i] = getAvailableEmpNo(empNo, MIN_EMPLOYEE_NUM, MAX_EMPLOYEE_NUM);
            // Assign a random pay rate value, the multiply by 2, round, then divide by 2 to get 0.5 increments.
            payRate[i] = Math.round(HeroIR.getRandomDouble(MIN_PAY_RATE, MAX_PAY_RATE) * 2.0) / 2.0;
            // Assign a random hour value, the multiply by 4, round, then divide by 4 to get 0.25 increments.
            hoursWorked[i] = Math.round(HeroIR.getRandomDouble(MIN_HOURS_WORKED, MAX_HOURS_WORKED) * 4.0) / 4.0;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a unique employee id number within the range.
     *
     * @param empNo An integer array that contains all employee numbers.
     * @param min The minimum employee number.
     * @param max The maximum employee number.
     * @return A unique employee number.
     */
    public static int getAvailableEmpNo(int[] empNo, int min, int max) {
        int num;
        boolean isUnique;

        do {
            // Get a random number
            num = HeroIR.getRandomNumber(min, max);
            // Determine if the number is unique.
            isUnique = true;
            for (int i : empNo) {
                if (num == i) {
                    isUnique = false;
                }
            }
        } while (!isUnique); // Continue getting random numbers until a unique one is found.

        return num;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Control loop that calls calculation functions to process the pay for employees.
     *
     * @param payRate An array of doubles containing employee pay rates.
     * @param hoursWorked An array of doubles containing employee hours worked.
     * @param regularPay An array of doubles to be populated with regular pay amounts.
     * @param overtimePay An array of doubles to be populated with overtime pay amounts.
     * @param totalPay An array of doubles to be populated with total pay amounts.
     * @param numEmployees The total number of employees.
     */
    public static void calculatePay(double[] payRate, double[] hoursWorked, double[] regularPay,
                                    double[] overtimePay, double[] totalPay, int numEmployees) {
        double[] calculatedPay;

        // Populate pay data
        for (int i = 0; i < numEmployees; i++) {
            calculatedPay = calculatePayValues(payRate[i], hoursWorked[i]);

            regularPay[i] = calculatedPay[REGULAR_PAY];
            overtimePay[i] = calculatedPay[OVERTIME_PAY];
            totalPay[i] = calculatedPay[TOTAL_PAY];
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Calculates the three pay values for a given employee based on their pay rate and hours worked.
     *
     * @param payRate The standard pay rate of the employee.
     * @param hoursWorked The number of hours the employee worked that week.
     * @return A double array containing the regular, overtime, and total pay values.
     */
    public static double[] calculatePayValues(double payRate, double hoursWorked) {
        double[] calculatedPay = { 0.0, 0.0, 0.0 };

        if (hoursWorked <= REGULAR_PAY_THRESHOLD) {
            calculatedPay[REGULAR_PAY] = payRate * hoursWorked;
        } else {
            calculatedPay[REGULAR_PAY] = REGULAR_PAY_THRESHOLD * payRate;
            calculatedPay[OVERTIME_PAY] = (hoursWorked - REGULAR_PAY_THRESHOLD) * (payRate * 1.5);
        }

        calculatedPay[TOTAL_PAY] = calculatedPay[REGULAR_PAY] + calculatedPay[OVERTIME_PAY];

        return calculatedPay;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Sorts the arrays of employee data by employee number.
     * This is mainly used to confirm in the output that there are no duplicate employee numbers.
     * This uses the insertion sort algorithm.
     *
     * NOTE: Technically in this program, since the pay rates and hours worked are randomly generated, it is not
     * necessary to sort anything other than the employee numbers, the output would look no different.
     * For the sake of treating it as a practical program, however, I have done so.
     *
     * @param empNo An integer array containing all employee numbers.
     * @param payRate A double array containing all employee pay rates.
     * @param hoursWorked A double array containing all employee hours worked.
     * @param regularPay A double array containing all regular pay values.
     * @param overtimePay A double array containing all overtime pay values.
     * @param totalPay A double array containing all total pay values.
     */
    public static void sortByEmployeeNum(int[] empNo, double[] payRate, double[] hoursWorked, double[] regularPay,
                                         double[] overtimePay, double[] totalPay, int numEmployees) {
        // Integer variables
        int i, j, tmpEmpNo;
        double tmpPayRate, tmpHrs, tmpRegPay, tmpOverPay, tmpTotPay;
        // Looping through all array indices starting at 1
        for (i = 1; i < numEmployees; i++) {
            // Set j equal to the index right before the current index (will be 0 first)
            j = i - 1;
            // Set tmp to store the current value of the index while values are adjusted
            tmpEmpNo = empNo[i];
            tmpPayRate = payRate[i];
            tmpHrs = hoursWorked[i];
            tmpRegPay = regularPay[i];
            tmpOverPay = overtimePay[i];
            tmpTotPay = totalPay[i];

            // Continue shifting the j index down to lower indices until the next number before it is less than tmp
            while (j >= 0 && empNo[j] > tmpEmpNo) {
                empNo[j + 1] = empNo[j];
                payRate[j + 1] = payRate[j];
                hoursWorked[j + 1] = hoursWorked[j];
                regularPay[j + 1] = regularPay[j];
                overtimePay[j + 1] = overtimePay[j];
                totalPay[j + 1] = totalPay[j];

                j = j - 1;
            }

            // Assign tmp to that place in the list
            empNo[j + 1] = tmpEmpNo;
            payRate[j + 1] = tmpPayRate;
            hoursWorked[j + 1] = tmpHrs;
            regularPay[j + 1] = tmpRegPay;
            overtimePay[j + 1] = tmpOverPay;
            totalPay[j + 1] = tmpTotPay;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints out all the populated and calculated information on the employees.
     *
     * @param empNo An integer array containing all employee numbers.
     * @param payRate A double array containing all employee pay rates.
     * @param hoursWorked A double array containing all employee hours worked.
     * @param regularPay A double array containing all regular pay values.
     * @param overtimePay A double array containing all overtime pay values.
     * @param totalPay A double array containing all total pay values.
     * @param numEmployees The total number of employees.
     */
    public static void printEmployeeData(int[] empNo, double[] payRate, double[] hoursWorked, double[] regularPay,
                                         double[] overtimePay, double[] totalPay, int numEmployees) {
        // Printing employee data header
        System.out.println("---------------------- PAYROLL REPORT ---------------------------------");
        System.out.println("Employee       Pay         Hours       Regular     Overtime      Total");
        System.out.println(" Number        Rate        Worked       Pay          Pay          Pay");
        System.out.println("--------      ------      --------    ---------   ----------    -------");

        // Printing employee data
        for (int i = 0; i < numEmployees; i++) {
            System.out.printf("  %d        %5.2f        %5.2f     %8.2f     %8.2f     %8.2f\n",
                    empNo[i], payRate[i], hoursWorked[i], regularPay[i], overtimePay[i], totalPay[i]);
        }
    }
}
