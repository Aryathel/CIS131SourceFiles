/**
 *  <h1>Class Section</h1>
 *  <p>A Class representing a section for a college course.</p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 11-01-2020
 */

import java.util.*;

public class ClassSection {
    // ---------- Fields -----------------------------------------------------------------------------------------------
    private int crn;
    private String depCode;
    private int courseNum;
    private String classMode;
    private String campus;
    private String meetingDays;
    private String meetingTimes;
    private int capacity;
    private int instructorID;
    private List<Student> students = new ArrayList<Student>();

    // ---------- Constructors -----------------------------------------------------------------------------------------
    /** The default constructor.
     *
     * int - Initialized to 0.
     * String - Initialized to ""
     */
    ClassSection() {
        crn = 0;
        depCode = "";
        courseNum = 0;
        classMode = "";
        campus = "";
        meetingDays = "";
        meetingTimes = "";
        capacity = 0;
        instructorID = 0;
    }

    /** The Parameterized constructor creating a specific class section using given values.
     *
     * @param crn The class CRN.
     * @param depCode The class's Department Code (e.g. CIS)
     * @param courseNum The class's course number.
     * @param classMode The classes mode (online, in person, hybrid, etc).
     * @param campus The campus the class is located at.
     * @param meetingDays The days the class meets.
     * @param meetingTimes The times the class meets on those days.
     * @param capacity The maximum number of students who can be enrolled.
     * @param instructorID The internal ID of the class instructor.
     */
    ClassSection(int crn, String depCode, int courseNum, String classMode, String campus,
                 String meetingDays, String meetingTimes, int capacity, int instructorID) {
        this.crn = crn;
        this.depCode = depCode;
        this.courseNum = courseNum;
        this.classMode = classMode;
        this.campus = campus;
        this.meetingDays = meetingDays;
        this.meetingTimes = meetingTimes;
        this.capacity = capacity;
        this.instructorID = instructorID;
    }

    // ---------- Getters ----------------------------------------------------------------------------------------------
    public int getCRN() {
        return crn;
    }

    public String getDepCode() {
        return depCode;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public String getClassMode() {
        return classMode;
    }

    public String getCampus() {
        return campus;
    }

    public String getMeetingDays() {
        return meetingDays;
    }

    public String getMeetingTimes() {
        return meetingTimes;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public int getEnrollment() { return students.size(); }

    // ---------- Setters ----------------------------------------------------------------------------------------------
    public void setCRN(int crn) {
        this.crn = crn;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public void setClassMode(String classMode) {
        this.classMode = classMode;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setMeetingDays(String meetingDays) {
        this.meetingDays = meetingDays;
    }

    public void setMeetingTimes(String meetingTimes) {
        this.meetingTimes = meetingTimes;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    // ---------- Methods ----------------------------------------------------------------------------------------------
    /** Creates a string containing all class section information.
     *
     * @return A formatted string containing class information.
     */
    public String toString() {
        String output = "***************************************\n";

        output += String.format("%-22s %d\n", "CRN:", crn);
        output += String.format("%-22s %s\n", "Department: ", depCode);
        output += String.format("%-22s %d\n", "Course Number: ", courseNum);
        output += String.format("%-22s %s\n", "Instructional Mode: ", classMode);
        output += String.format("%-22s %s\n", "Campus: ", campus);
        output += String.format("%-22s %s\n", "Meeting Days: ", meetingDays);
        output += String.format("%-22s %s\n", "Meeting Times: ", meetingTimes);
        output += String.format("%-22s %s\n", "Capacity: ", capacity);
        output += String.format("%-22s %s\n", "Enrollment: ", getEnrollment());
        output += String.format("%-22s %s\n", "Instructor's ID: ", instructorID);

        output += "***************************************";

        return output;
    }

    /** Add a student to the list of enrolled students.
     *
     * @param id The id of the student.
     * @param grade The grade of the student.
     */
    public void addStudent(int id, int grade) {
        if (!isValidGrade(grade)) {
            System.err.println("addStudent(): The grade provided is invalid.");
            return;
        }

        int i = getStudentByID(id);
        if (i > -1) {
            System.err.printf("addStudent(): A student with ID %d already exists.\n", id);
            return;
        }

        Student student = new Student(id, grade);
        students.add(student);
    }

    /** Retrieves the index of a student in the list of enrolled students.
     *
     * @param id The ID of the student to search for.
     * @return The index of the student in the list of students, or -1 if not found.
     */
    public int getStudentByID(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID() == id) {
                return i;
            }
        }

        return -1;
    }

    /** Withdraws a student form the class.
     *
     * @param id The id of the student to withdraw.
     */
    public void withdrawStudent(int id) {
        int i = getStudentByID(id);

        if (i > -1) {
            students.remove(i);
            System.out.printf("withdrawStudent(): The student with ID %d was withdrawn.\n", id);
        } else {
            System.err.printf("withdrawStudent(): A student with that ID could not be found: %d\n", id);
        }
    }

    /** Assigns a grade to a student.
     *
     * @param id The ID of the student to assign the grade to.
     * @param grade The grade to assign the student.
     */
    public void assignGrade(int id, int grade) {
        // Check the grade validity.
        if (!isValidGrade(grade)) {
            System.err.printf("assignGrade(): The grade provided is invalid: %d\n", grade);
            return;
        }

        // Get the student
        int i = getStudentByID(id);

        // Assign the grade
        if (i > -1) {
            students.get(i).setGrade(grade);
            System.out.printf("assignGrade(): Grade updated for student with ID %d. New grade: %d\n", id, grade);
        } else {
            System.err.printf("assignGrade(): A student with that ID could not be foundL %d\n", id);
        }
    }

    public void printEnrolled() {
        System.out.println("Enrolled Students:");
        for (Student student : students) {
            System.out.printf("    %s\n", student.toString());
        }
    }

    /** Ensure that any given grade integer is valid.
     *
     * @param grade The grade integer to check.
     * @return Whether the grade is valid or not.
     */
    private boolean isValidGrade(int grade) {
        return grade == Grade.A || grade == Grade.B || grade == Grade.C || grade == Grade.D || grade == Grade.F;
    }
}
