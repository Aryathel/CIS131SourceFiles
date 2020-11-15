public class Grade {
    // ---------- Fields -----------------------------------------------------------------------------------------------
    public static final int A = 4;
    public static final int B = 3;
    public static final int C = 2;
    public static final int D = 1;
    public static final int F = 0;

    private int grade;

    // ---------- Getters ----------------------------------------------------------------------------------------------
    public int getGrade() {
        return grade;
    }

    // ---------- Setters ----------------------------------------------------------------------------------------------
    public void setGrade(int grade) {
        this.grade = grade;
    }

    // ---------- Methods ----------------------------------------------------------------------------------------------
    /** Converts the integer grade to a letter grade.
     *
     * @return A String containing the letter grade.
     */
    public String toString() {
        switch (grade) {
            case A:
                return "A";
            case B:
                return "B";
            case C:
                return "C";
            case D:
                return "D";
            case F:
                return "F";
            default:
                return String.format("Invalid Grade: %d", grade);
        }
    }

    // ---------- Constructors -----------------------------------------------------------------------------------------
    /** The default constructor.
     *
     */
    Grade() {
        grade = A;
    }

    /** The constructor that sets the grade right away.
     *
     * @param grade The grade to initialize to.
     */
    Grade(int grade) {
        this.grade = grade;
    }
}
