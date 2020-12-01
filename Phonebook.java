/**
 * Houghton Mayfield
 * 11/30/2020
 */

/*********************************************************************************************************************
  * Phonebook.java
  * 
  * To Do:
  *   First, compile the program and run it to see what happens.
  *   Second, familiarize yourself with the code.
  * 
  * Then debug the code to fix these problems in order: 
  *   1. There is a bug that prevents the user from quitting
  *      Hints: Execute the program and try to quit. What happens? 
  *             How many menu items are there? 
  * 
  *   2. There is a bug in reading the database file 
  *      Hints: Open up the phonebook.txt file and check it out.
  *             Run the program. Select the menu item to display the database. 
  *             Open up the phonebook.txt file and compare to the original_phonebook.txt file. 
  *             What happened? 
  * 
  *   3. There is a bug in writing the database file 
  *      Hints: Execute the program. 
  *             Open up the phonebook.txt file and check it out.
  *             Run the program again. 
  *             Add two new people in the phonebook. 
  *             End the program.
  *             Open up the phonebook.txt file and check it out. 
  *             What happened? 
  *  
  *   4. There is a bug that has broken menu item 5   
  *      Hints: Execute the program. 
  *             Open up the phonebook.txt file and check it out. 
  *             Try menu item 5. 
  *             Is it different from the file? 
  * 
  *   5. There is a bug that has broken menu items 2, 3 and 4 
  *      Hints: Execute the program.
  *             Execute menu option 5 to print the phonebook. 
  *             Try menu item 2 and search for a name.
  *             What happened? 
  *********************************************************************************************************************/
import java.io.*;
import java.util.*;

public class Phonebook {
   private static int DB_SIZE = 40; 
   private static int NBR_OF_MENU_ITEMS = 6;
   private static String DATABASE_NAME = "./src/phonebook.txt";
   
   //These are two parallel arrays that must be kept in sync
   private static String dbName [] = new String [DB_SIZE];
   private static String dbPhoneNumber [] = new String [DB_SIZE];
   
   private static InputStreamReader input = new InputStreamReader(System.in);
   private static BufferedReader reader = new BufferedReader(input); 
//--------------------------------------------------------------------------------------------------------------------
   public static void main(String[] args) throws IOException {
      readDatabaseFile();   
      int menuSelection = 0;
      do {
         displayMenu();
         menuSelection = IR4.getInteger("Enter your Selection (1-6)");
         switch (menuSelection) {
            case 1: 
               addRecord();
               break;
            case 2:
               searchDatabase();
               break;
            case 3:
               modifyRecord();
               break;
            case 4:
               deleteRecord();
               break;
            case 5:
               displayDatabase();
               break;
            case 6:
               System.out.println("Thank you for using your phonebook application");
               break;
            default:
               System.err.println("Invalid menu number. Please try again.");
               break;
         }
      } while (menuSelection != NBR_OF_MENU_ITEMS);
      
      saveDatabaseFile();
      IR4.closeScanner();
   }
   //-------------------------------------------------------------------------------------------------------------------
   private static void displayMenu () {
      System.out.println("***************************************");
      System.out.println("**     Welcome to your Phonebook     **");
      System.out.println("***************************************");
      System.out.println("       1. Add a new person");
      System.out.println("       2. Search for a name");
      System.out.println("       3. Modify a person");
      System.out.println("       4. Delete a person");
      System.out.println("       5. Display phonebook");
      System.out.println("       6. Exit the program");
      System.out.println("");
   }
   
   //Menu item #1 - Add a new person
   private static void addRecord () {
      //Find the first array entry that is null
      for (int i = 0; i < DB_SIZE; i++) {
         if (dbName[i] == null) {
            dbName[i] = IR4.getString("Enter a name");  
            dbPhoneNumber[i] = IR4.getString("Enter a phone number");
            System.err.println("The record for " + dbName[i] + "  " + dbPhoneNumber[i] + " has been added.");
            sortDatabase();
            return;
         }
      }
      System.err.println("Database limit of " + DB_SIZE + " reached. Expand database and try again.");
   }
   
   //Menu item #2 - Search for a name
   private static void searchDatabase () {
      String name = IR4.getString("Enter a name to search for");
      for (int i = 0; i < DB_SIZE; i++) {
         if (dbName[i] != null) {
            if (dbName[i].equals(name)) {
               System.err.println("\n" + dbName[i] + "  " + dbPhoneNumber[i] + "\n");
               return;
            }
         }
      }
      System.err.println("Name was not found.");
   }
   
   //Menu item #3 - Modify a person
   private static void modifyRecord () {
      String name = IR4.getString("Enter a name of the record to modify");
      for (int i = 0; i < DB_SIZE; i++) {
         if (dbName[i] != null) {
            if (dbName[i].equals(name)) {
               name = IR4.getString("Enter the new name");
               dbName[i] = name;
               String phoneNumber = IR4.getString("Enter the new phone number");
               dbPhoneNumber[i] = phoneNumber;
               System.err.println("The record for " + dbName[i] + "  " + dbPhoneNumber[i] + " has been modified.");
               sortDatabase();
               return;
            }
         }
      }
      System.err.println("Name was not found.");
   }
   
   //Menu item #4 - Delete a person
   private static void deleteRecord () { 
      String name = IR4.getString("Enter the name to delete");
      for (int i = 0; i < DB_SIZE; i++) {
         if (dbName[i] != null) {
            if (dbName[i].equals(name)) {
               System.err.println("The record for " + dbName[i] + "  " + dbPhoneNumber[i]+ " has been deleted.");
               dbName[i] = null;
               dbPhoneNumber[i] = null;
               sortDatabase();
               return;
            }
         }
      }
      System.err.println("Name was not found.");
   }
   
   //Menu item #5 - Display Phonebook
   private static void displayDatabase() {
      System.err.println("\n------- Phonebook -------");
      for (int i = 0; i < DB_SIZE; i++) {
         if (dbName[i] != null) {
            System.out.println(dbName[i] + "  " + dbPhoneNumber[i]);
         }
      }
      System.out.println("");
   }
   //------------------------------------------------------------------------------------------------------------------
   private static void sortDatabase () {
      //first move all null entries to the end of the arrays
      for (int i = 0; i < DB_SIZE; i++) {
         if (dbName[i] == null) {
            int nextNotNullIndex = getNextNotNullIndex(i); 
            if (nextNotNullIndex < DB_SIZE) {
               dbName[i] = dbName[nextNotNullIndex]; 
               dbPhoneNumber[i] = dbPhoneNumber[nextNotNullIndex];
               dbName[nextNotNullIndex] = null; 
               dbPhoneNumber[nextNotNullIndex] = null; 
            }
         }
      }
      boolean needNextPass = true;
      //Perform a bubble sort, stop when null entries are found
      for (int k = 0; k < DB_SIZE && needNextPass; k++) {
         needNextPass = false;
         if (dbName[k] == null || dbName[k + 1] == null) return;
         for (int i = 0; i < DB_SIZE - k - 1; i++) {
            
            if (dbName[i+1] != null) {
               if (dbName[i].compareTo(dbName[i + 1]) > 0) {
                  String temp = dbName[i];
                  dbName[i] = dbName[i + 1];
                  dbName[i + 1] = temp;
                  
                  temp = dbPhoneNumber[i];
                  dbPhoneNumber[i] = dbPhoneNumber[i + 1];
                  dbPhoneNumber[i + 1] = temp;
                  
                  needNextPass = true; 
               }
            }
         }
      }
   }
   
   //Get the next non-null array element
   private static int getNextNotNullIndex(int i) {
      for (int j = i; j < DB_SIZE; j++) {
         if (dbName[j] != null) {
            return j;
         }
      }
      return DB_SIZE;
   }
   
   //-------------------------------------------------------------------------------------------------------------------
   private static void readDatabaseFile() throws IOException {
      //Initialize the database arrays
      for (int i = 0; i < DB_SIZE; i++) {
         dbName[i] = null;
         dbPhoneNumber[i] = null;
      }
      
      FileReader fr = new FileReader(DATABASE_NAME);
      BufferedReader br = new BufferedReader(fr);
      
      String name = "";
      int index = 0;
      //stop reading the file when the next line is null
      while ((name = br.readLine()) != null) {
         dbName[index] = name;
         dbPhoneNumber[index] = br.readLine();
         index++;
         if (index >= DB_SIZE) {
            System.err.println("Database limit of " + DB_SIZE + " reached. Expand database and try again.");
            break;
         }
      }
      br.close();
   }
   
   private static void saveDatabaseFile() throws IOException {
      
      FileWriter  fw = new FileWriter(DATABASE_NAME);
      PrintWriter pw = new PrintWriter(fw);
      
      for (int i = 0; i < DB_SIZE; i++) {
         if (dbName[i] != null) {
            pw.println(dbName[i]);
            pw.println(dbPhoneNumber[i]);
            pw.flush();
         }
      }
      pw.close();
   }
}//eof