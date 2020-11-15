/**
 *  <h>Lab 7: OOP Classes 2</h>
 *  <p>This program serves as a basic introduction into creating an using an OOP class in Java.</p>
 *  @author Houghton Mayfield
 *  @version 1.1
 *  @since 11-09-2020
 */

public class Lab7Driver {
    public static void main(String[] args) {
        ClassSection class1 = new ClassSection();

        // Step 1
        class1.setCRN(20008);
        class1.setDepCode("CIS");
        class1.setCourseNum(131);
        class1.setClassMode("Online");
        class1.setCampus("East");
        class1.setMeetingDays("N/A");
        class1.setMeetingTimes("N/A");
        class1.setCapacity(30);
        class1.setInstructorID(654);

        System.out.println(class1.toString());

        ClassSection class2 = new ClassSection(
                20123, "CIS", 131, "Classroom",
                "East", "TTH", "3:15 to 5:30pm",
                30, 654
        );

        System.out.println(class2.toString());

        // Step 2
        class1.addStudent(1, Grade.F);
        class1.addStudent(2, Grade.F);
        class1.addStudent(3, Grade.F);
        class1.addStudent(4, Grade.F);
        class1.addStudent(5, Grade.F);

        // Step 3
        class1.printEnrolled();

        // Step 4
        class1.assignGrade(1, Grade.C);
        class1.assignGrade(2, Grade.B);
        class1.assignGrade(3, Grade.D);
        class1.assignGrade(4, Grade.A);

        // Step 5
        class1.printEnrolled();

        // Step 6
        System.out.printf("Number of Students Enrolled: %d\n", class1.getEnrollment());

        // Step 7
        class1.withdrawStudent(4);

        class1.printEnrolled();

        // Step 8
        class1.withdrawStudent(-1);

        // Step 9
        class1.addStudent(1, Grade.A);

        // Step 10
        class1.assignGrade(-1, Grade.A);

        // My Own Tests:
        // Assign a grade that does not exist.
        class1.assignGrade(1, -1);
    }
}
