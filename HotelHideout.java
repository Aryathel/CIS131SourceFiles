/********************************************************************************************************************
    HotelHideout.java
    Author: Houghton Mayfield
   
    A fugitive is holed up in a hotel room and the FBI need our help in finding which room. 
    The hotel has 60 rooms, numbered from 1 to 60. 
   
    1. The fugitive is not in a room with the lowest 10 room numbers.
    2. The fugitive is not in the highest room number. 
    3. The fugitive is not in a room with the room number having 2 even digits.
    4. The fugitive is not in a room with the room number having 2 odd digits.
    5. The fugitive is not in a room when the second digit is not at least twice as large as the first digit. 
       For example, 12 would be eliminated because 2 is not > (1 * 2), it is equal). 
       For example, 57 would be eliminated because 7 is not > (5 * 2)). 
    6. The fugitive is not in a room that is divisible by 7.
    7. The fugitive is not in a room whose room number digits when switched is not a valid room number in the hotel. 
   
    As you eliminate rooms, print the room number and all the reasons why it has been eliminated. You should use these statements: 
       System.out.println("Room number " + i + " is the room we are seeking.");
       System.out.println("Room number " + i + " has been eliminated due to being less than 11."); 
       System.out.println("Room number " + i + " has been eliminated due to both digits being even."); 
       System.out.println("Room number " + i + " has been eliminated due to both digits being odd."); 
       System.out.println("Room number " + i + " has been eliminated due to the second digit not being at least twice as large as the first.");
       System.out.println("Room number " + i + " has been eliminated due to being divisible by 7.");
       System.out.println("Room number " + i + " has been eliminated due to switched digits not being a valid room number (" + newRoomNbr + ").");
   
    You should end up with 1 room number left where the fugitive is holed up. 
   
    As you eliminate rooms, print the room number and why it has been eliminated. For example: 
       Room number 12 has been eliminated due to the second digit not being twice as large as the first. 
       Room number 29 has been eliminated due to switched digits not being a valid room number (92 is not a valid room number). 
   
   
   ******************************************************************************************************************
    Required sample output: For each room, from 1 to 60, print all the reasons why it was eliminated from consideration.  
   
       Room number 1 has been eliminated due to being less than 11. 
       
       etc...
       
       Room number 57 has been eliminated due to both digits being odd. 
       Room number 57 has been eliminated due to the second digit not being twice as large as the first. 
       Room number 57 has been eliminated due to switched digits not being a valid room number (75). 
       
       etc..
       
       Room number xx is the room with the fugitive! 
       
   
    If your output does not follow the required format, you will not receive any credit. 
    Think about how you need to structure your program to produce the required output.     
    
  ********************************************************************************************************************/

public class HotelHideout{
   final static int NBR_OF_ROOMS = 60;
   
   public static void main(String[] args){
      boolean[] rooms = new boolean[NBR_OF_ROOMS]; // The array of rooms, true = valid room, false = invalid
      initializeRooms(rooms);                      // Set the array to all true
      
      testRooms(rooms); // Test all of the rooms

      printWinningRooms(rooms); // Print the surviving room(s) (will print the number of any 'true' room)
   }//end of main ----------------------------------------------------------------------------------------------------

   //-------------------------------------------------------------------------------------------------------------------
   /** Set all of the indices of a boolean array to true.
    *
    * @param rooms A boolean array representing the hotel rooms.
    */
   public static void initializeRooms(boolean rooms[]) {
      for (int i = 0; i < rooms.length; i++) {
         rooms[i] = true;
      }
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Test every important test case for the array of rooms, invalidating the ones which match certain criteria..
    *
    * @param rooms A boolean array representing the hotel rooms.
    */
   public static void testRooms(boolean rooms[]) {
      // For each room in the array, test each test case.
      for (int i = 0; i < rooms.length; i++) {
         testCase1(rooms, i + 1);
         testCase2(rooms, i + 1);
         testCase3(rooms, i + 1);
         testCase4(rooms, i + 1);
         testCase5(rooms, i + 1);
         testCase6(rooms, i + 1);
         testCase7(rooms, i + 1);
      }
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Eliminate all hotel rooms with a room number of 10 or less.
    *
    * @param rooms A boolean array representing the hotel rooms.
    * @param roomNumber The room number of the room being tested.
    */
   public static void testCase1(boolean rooms[], int roomNumber) {
      if (roomNumber < 11) {
         rooms[roomNumber - 1] = false;
         printRoomEliminated(roomNumber, "being less than 11");
      }
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Eliminate the hotel room with the highest number.
    *
    * @param rooms A boolean array representing the hotel rooms.
    * @param roomNumber The room number of the room being tested.
    */
   public static void testCase2(boolean rooms[], int roomNumber) {
      int highestRoom = rooms.length;
      if (roomNumber == highestRoom) {
         rooms[roomNumber - 1] = false;
         printRoomEliminated(roomNumber, "the room number the largest");
      }
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Eliminate the hotel rooms with both digits being even.
    *
    * @param rooms A boolean array representing the hotel rooms.
    * @param roomNumber The room number of the room being tested.
    */
   public static void testCase3(boolean rooms[], int roomNumber) {
      if ((roomNumber / 10) % 2 == 0 && (roomNumber % 10) % 2 == 0) {
         rooms[roomNumber - 1] = false;
         printRoomEliminated(roomNumber, "both digits being even");
      }
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Eliminate the hotel rooms with both digits being odd.
    *
    * @param rooms A boolean array representing the hotel rooms.
    * @param roomNumber The room number of the room being tested.
    */
   public static void testCase4(boolean rooms[], int roomNumber) {
      if ((roomNumber / 10) % 2 != 0 && (roomNumber % 10) % 2 != 0) {
         rooms[roomNumber - 1] = false;
         printRoomEliminated(roomNumber, "both digits being odd");
      }
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Eliminate the hotel rooms where the second digit is not at least twice as large as the first.
    *
    * @param rooms A boolean array representing the hotel rooms.
    * @param roomNumber The room number of the room being tested.
    */
   public static void testCase5(boolean rooms[], int roomNumber) {
      if (roomNumber % 10 <= (roomNumber / 10) * 2) {
         rooms[roomNumber - 1] = false;
         printRoomEliminated(roomNumber, "the second digit not being at least twice as large as the first");
      }
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Eliminate the hotel room numbers that are divisible by 7.
    *
    * @param rooms A boolean array representing the hotel rooms.
    * @param roomNumber The room number of the room being tested.
    */
   public static void testCase6(boolean rooms[], int roomNumber) {
      if (roomNumber % 7 == 0) {
         rooms[roomNumber - 1] = false;
         printRoomEliminated(roomNumber, "being divisible by 7");
      }
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Eliminate the hotel rooms that have an invalid room number when swapping the digits of the room number.
    *
    * @param rooms A boolean array representing the hotel rooms.
    * @param roomNumber The room number of the room being tested.
    */
   public static void testCase7(boolean rooms[], int roomNumber) {
      int newFirstDigit = (roomNumber % 10) * 10;
      int newSecondDigit = roomNumber / 10;
      if (newFirstDigit + newSecondDigit > 60) {
         rooms[roomNumber - 1] = false;
         printRoomEliminated(roomNumber, String.format("switched digits not being a valid room number (%02d).", newFirstDigit + newSecondDigit));
      }
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Print a room number elimination message, using the provided room number and a reason for the elimination.
    *
    * @param roomNumber The room number of the room that is being eliminated.
    * @param reason A string explaining the reason for, continuing "eliminated due to <reason>".
    */
   public static void printRoomEliminated(int roomNumber, String reason) {
      System.out.printf("Room number %02d has been eliminated due to %s.\n", roomNumber, reason);
   }

   //-------------------------------------------------------------------------------------------------------------------
   /** Print the number of all rooms in the given boolean room array which are true.
    * It is expected that the test cases run before this will leave only one array index being true.
    *
    * @param rooms A boolean array representing the hotel rooms.
    */
   public static void printWinningRooms(boolean rooms[]) {
      for (int i = 0; i < rooms.length; i++) {
         if (rooms[i]) {
            System.out.printf("\nRoom number %02d is the room with the fugitive!\n", i + 1);
         }
      }
   }
   
}//end of class