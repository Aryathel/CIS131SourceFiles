/**
 *  <h>Lab 6: OOP Classes</h>
 *  <p>This program serves as a basic introduction into creating an using an OOP class in Java.</p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 11-01-2020
 */

public class Lab6Driver {
    public static void main(String[] args) {
        ClassSection class1 = new ClassSection();

        class1.setCRN(20008);
        class1.setDepCode("CIS");
        class1.setCourseNum(131);
        class1.setClassMode("Online");
        class1.setCampus("East");
        class1.setMeetingDays("N/A");
        class1.setMeetingTimes("N/A");
        class1.setCapacity(30);
        class1.setEnrollment(30);
        class1.setInstructorID(654);

        System.out.println(class1.toString());

        ClassSection class2 = new ClassSection(
                20123, "CIS", 131, "Classroom",
                "East", "TTH", "3:15 to 5:30pm",
                30, 30, 654
        );

        System.out.println(class2.toString());
    }
}
