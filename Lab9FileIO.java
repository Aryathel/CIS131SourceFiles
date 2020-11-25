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
        int empID;
        String firstName;
        String lastName;
        double hoursWorked;
        double hourlyRate;

        ArrayList<Employee> employees = new ArrayList<>();

        File file = new File(FILENAME);

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

            printOutput(employees);
            writeOutput(employees);
        } catch (FileNotFoundException e) {
            System.out.printf("The specified payroll file, \"%s\", was not found.\n\n", FILENAME);
            System.exit(-1);
        }
    }

    public static void printOutput(ArrayList<Employee> emps) {
        System.out.println(getHeader());

        for (Employee emp : emps) {
            System.out.printf("%s\n", emp.toString());
        }
    }

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

    public static String getHeader() {
        return "------------------------------ PAYROLL REPORT ------------------------------------------\n" +
        "Employee      Employee         Pay         Hours       Regular     Overtime       Total\n" +
        " Number         Name           Rate        Worked       Pay          Pay           Pay\n" +
        "--------   ---------------    ------      --------    ---------   ----------     -------";
    }
}
