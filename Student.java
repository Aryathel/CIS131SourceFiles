/**
 * <h1>Student</h1>
 * <p>A class representing a student registered in a school system.</p>
 * @author Houghton Mayfield
 * @version 1.0.0
 * @since 11-09-2020
 */

public class Student {
    // ---------- Fields -----------------------------------------------------------------------------------------------
    private int studentID;
    private Grade grade;

    // ---------- Getters ----------------------------------------------------------------------------------------------
    public int getStudentID() { return studentID; }

    public int getGrade() { return grade.getGrade(); }

    // ---------- Setters ----------------------------------------------------------------------------------------------
    public void setStudentID(int studentID) { this.studentID = studentID; }

    public void setGrade(int grade) { this.grade.setGrade(grade); }

    // ---------- Methods ----------------------------------------------------------------------------------------------
    /** Creates a formatted string containing information on the student.
     *
     * @return The formatted student info string.
     */
    public String toString() {
        return String.format("%03d: %s", studentID, grade.toString());
    }

    // ---------- Constructors -----------------------------------------------------------------------------------------
    /** The default constructor.
     *
     * int - Initialized to 0.
     */
    public Student() {
        studentID = 0;
        grade = new Grade(4);
    }

    /** A constructor initializing all student fields.
     *
     * @param studentID The ID of the student.
     * @param grade The current grade of the student.
     */
    public Student(int studentID, int grade) {
        this.studentID = studentID;
        this.grade = new Grade(grade);
    }
}
