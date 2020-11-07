/**
 *  <h>Class Section</h>
 *  <p>A Class representing a section for a college course.</p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 11-01-2020
 */
public class ClassSection {
    private int crn;
    private String depCode;
    private int courseNum;
    private String classMode;
    private String campus;
    private String meetingDays;
    private String meetingTimes;
    private int capacity;
    private int enrollment;
    private int instructorID;

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
        enrollment = 0;
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
     * @param enrollment The number of currently enrolled students.
     * @param instructorID The internal ID of the class instructor.
     */
    ClassSection(int crn, String depCode, int courseNum, String classMode, String campus,
                 String meetingDays, String meetingTimes, int capacity, int enrollment, int instructorID) {
        this.crn = crn;
        this.depCode = depCode;
        this.courseNum = courseNum;
        this.classMode = classMode;
        this.campus = campus;
        this.meetingDays = meetingDays;
        this.meetingTimes = meetingTimes;
        this.capacity = capacity;
        this.enrollment = enrollment;
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

    public int getEnrollment() {
        return enrollment;
    }

    public int getInstructorID() {
        return instructorID;
    }

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

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    // ---------- Methods ----------------------------------------------------------------------------------------------
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
        output += String.format("%-22s %s\n", "Enrollment: ", enrollment);
        output += String.format("%-22s %s\n", "Instructor's ID: ", instructorID);

        output += "***************************************";

        return output;
    }
}
