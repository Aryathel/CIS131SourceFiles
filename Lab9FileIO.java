import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Lab 9 - File IO</h1>
 * <p>
 *     This is the 9th CIS 131 lab, meant to demonstrate knowledge of File IO systems.
 * </p>
 */

public class Lab9FileIO {
    private final static String FILENAME = "payroll.txt";
    private final static String OUTPUTFILE = "lab9output.txt";

    public static void main(String[] args) {
        // Temporary fields for reading input
        int empID;
        String firstName;
        String lastName;
        double hoursWorked;
        double hourlyRate;

        // Create arraylist to store employees
        ArrayList<Employee> employees = new ArrayList<>();

        // Create the File object
        File file = new File(FILENAME);

        // Create the file reader, and put the Employees in the list
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                empID = scanner.nextInt();
                lastName = scanner.next();
                firstName = scanner.next();
                hoursWorked = scanner.nextDouble();
                hourlyRate = scanner.nextDouble();

                employees.add(new Employee(
                        empID, firstName, lastName, hoursWorked, hourlyRate
                ));
            }

            // Print employees to command line and file.
            printOutput(employees);
            writeOutput(employees);
        } catch (FileNotFoundException e) {
            System.out.printf("The specified payroll file, \"%s\", was not found.\n\n", FILENAME);
            System.exit(-1);
        }
    }

    /** Prints the Employee output to the console.
     *
     * @param emps An ArrayList of Employees
     */
    public static void printOutput(ArrayList<Employee> emps) {
        System.out.println(getHeader());

        for (Employee emp : emps) {
            System.out.printf("%s\n", emp.toString());
        }
    }

    /** Prints the Employee output to a file.
     *
     * @param emps An ArrayList of Employees
     */
    public static void writeOutput(ArrayList<Employee> emps) {
        File outfile = new File(OUTPUTFILE);

        try {
            outfile.createNewFile();

            FileWriter writer = new FileWriter(outfile);

            writer.write(getHeader() + "\n");

            for (Employee emp : emps) {
                writer.write(emp.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.printf("The output file could not be created: %s\n", OUTPUTFILE);

            e.printStackTrace();
        }
    }

    /**
     * @return A string containing the header for the Employee output.
     */
    public static String getHeader() {
        return "------------------------------ PAYROLL REPORT ------------------------------------------\n" +
        "Employee      Employee         Pay         Hours       Regular     Overtime       Total\n" +
        " Number         Name           Rate        Worked       Pay          Pay           Pay\n" +
        "--------   ---------------    ------      --------    ---------   ----------     -------";
    }
}
