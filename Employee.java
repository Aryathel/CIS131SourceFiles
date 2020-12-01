/**
 * <h1>Employee</h1>
 * <p>
 *     Represents an employee.
 * </p>
 */

public class Employee {
    // --------- Fields ------------------------------------------------------------------------------------------------
    private int empID;
    private String firstName;
    private String lastName;

    private double hoursWorked;
    private double hourlyRate;

    // --------- Getters -----------------------------------------------------------------------------------------------
    public int getEmpID() {
        return empID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    // --------- Setters -----------------------------------------------------------------------------------------------
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    // --------- Methods -----------------------------------------------------------------------------------------------
    /** Gets regular pay for he employee.
     *
     * @return A double representing the regular pay amount for the employee.
     */
    public double getRegularPay() {
        if (hoursWorked <= 40) {
            return hoursWorked * hourlyRate;
        } else {
            return hourlyRate * 40;
        }
    }

    /** Gets overtime pay for the employee.
     *
     * @return A double representing the overtime pay for the employee.
     */
    public double getOvertimePay() {
        if (hoursWorked > 40) {
            return (hoursWorked - 40) * (hourlyRate * 1.5);
        } else {
            return 0.0;
        }
    }

    /** Gets the total pay amount for the employee.
     *
     * @return A double representing the total pay for the employee.
     */
    public double getTotalPay() {
        return getRegularPay() + getOvertimePay();
    }

    /**
     *
     * @return A formatted string representing the employee data.
     */
    public String toString() {
        return String.format("  %-8d %-18s %-12.2f %-10.2f %7.2f %12.2f %13.2f", empID, firstName + " " + lastName, hourlyRate,
                hoursWorked, getRegularPay(), getOvertimePay(), getTotalPay());
    }

    // --------- Constructors ------------------------------------------------------------------------------------------
    Employee(int empID, String firstName, String lastName, double hoursWorked, double hourlyRate) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
}
