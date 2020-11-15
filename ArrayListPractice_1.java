/* Put your name here 
 * 
 * To Do: 
 *    Complete all the menu items, passing the arraylist to the module. 
 *    Check for an empty arrayList when appropriate. 
 *    Check for a valid index number when appropriate.
 * 
 *    The program should not crash under any circumstances.  
 */
import java.util.*;

public class ArrayListPractice_1 {
    private final static int EXIT = 99;
    
    private static ArrayList<String> myArrayList = new ArrayList<String>();
    
    public static void main(String[] args) {
        displayMenu();
        int menuOption = IR4.getInteger("\nPlease enter a menu item");
        while (menuOption != EXIT){
            switch(menuOption){ 
                case 1: displayList();               
                        break;
                case 2: addNameAtEndOfList();        
                        break;
                case 3: addNameByIndex();        
                        break;
                case 4: deleteName();             
                        break;  
                case 5: deleteNameByIndex();       
                        break;  
                case 6: displaySizeOfList();      
                        break;  
                case 7: deleteAllNames();         
                        break;  
                case 8: searchForName();           
                        break;  
                case 9: displayListInReverseOrder();
                        break;  
                case 10: sortAndDisplayTheList(); 
                        break;  
                case 11: displayTheMinElement();  
                        break;  
                case 12: displayTheMaxElement();  
                        break;  
                case EXIT:                         
                        break;
                default: 
                    System.err.println("Invalid menu option. Try again."); 
                    break; 
            }
            displayMenu();
            menuOption = IR4.getInteger("\nPlease enter a menu item");
        }
        System.out.println("--- ArrayList practice program ending normally ---");
    }    
    
    private static void displayList(){
        // Loop through array list and print all elements
        for (int i  = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i));
        }
    }
    private static void addNameAtEndOfList(){
        String nameToAdd = IR4.getString("Please enter a name to be added to the List. Enter 999 to stop.");
        while (!nameToAdd.equals("999")){
            System.out.println("Adding name: " + nameToAdd);

            // Add input to array list
            myArrayList.add(nameToAdd);
            
            nameToAdd = IR4.getString("Please enter a name to be added to the List. Enter 999 to stop.");
        }
        displayList();
    }
    private static void addNameByIndex(){
        // Verify that it is possible to insert an element
        if (myArrayList.size() == 0) {
            System.out.println("Cannot insert a name by index into an empty list.");
            return;
        }

        // Get user input for string and what index to put said string at
        String nameToAdd  = IR4.getString("Please enter a name to be added to the List");
        int locationToAdd = getValidNumberBetweenTwoInclusive(
                "Please enter the index location where the name should be added",
                0,
                myArrayList.size() - 1
        );

        myArrayList.add(locationToAdd, nameToAdd);
    }
    private static void deleteName(){
        // Make sure there is something to delete
        if (myArrayList.size() == 0) {
            System.out.println("Cannot delete an element from an empty list.");
            return;
        }

        // Get the name to be deleted
        String nameToDelete = IR4.getString("Please enter a name to delete");

        // Loop through the array list and delete all instances of the name in the list.
        int i;
        for (i = 0; i < myArrayList.size(); i++) {
            if (myArrayList.get(i).equals(nameToDelete)) {
                myArrayList.remove(i);
            }
        }

        // Print updated list
        displayList();
    }
    
    private static void deleteNameByIndex(){
        // Make sure there is something to delete
        if (myArrayList.size() == 0) {
            System.out.println("Cannot delete an element from an empty list.");
            return;
        }

        // Get the index of the item to be deleted
        int index = getValidNumberBetweenTwoInclusive(
                "Please enter an index to remove from the list",
                0,
                myArrayList.size() - 1
        );

        // Remove the selected index
        myArrayList.remove(index);

        // Print updated list
        displayList();
    }
    
    private static void displaySizeOfList(){
        System.out.printf("List Size: %d", myArrayList.size());
    }
    
    private static void deleteAllNames(){
        myArrayList.clear();
    }
    
    private static void searchForName(){
        String search = IR4.getString("Please enter a string to search for");

        for (String s : myArrayList) {
            if (s.equals(search)) {
                System.out.printf("\"%s\" was found in the list.\n", search);
                return;
            }
        }

        System.out.printf("\"%s\" was not found in the list.\n", search);
    }
    
    private static void displayListInReverseOrder(){
        for (int i = myArrayList.size() - 1; i >= 0; i--) {
            System.out.println(myArrayList.get(i));
        }
    }
    
    private static void sortAndDisplayTheList(){
        Collections.sort(myArrayList);
        displayList();
    }
    
    private static void displayTheMinElement(){
        // Make sure there is something to display
        if (myArrayList.size() == 0) {
            System.out.println("Cannot display an element from an empty list.");
            return;
        }

        System.out.println(myArrayList.get(0));
    }
    
    private static void displayTheMaxElement(){
        // Make sure there is something to display
        if (myArrayList.size() == 0) {
            System.out.println("Cannot display an element from an empty list.");
            return;
        }

        System.out.println(myArrayList.get(myArrayList.size() - 1));
    }
    
    private static int getValidNumberBetweenTwoInclusive (String message, int first, int second) {
        int newValue;
        newValue = IR4.getInteger (message); 
        while (newValue < first || newValue > second){
            System.out.println("Invalid entry.");
            newValue = IR4.getInteger (message); 
        }
        return newValue;  
    }
    
    private static void displayMenu(){
        System.out.println("");
        System.out.println("----- ArrayList Practice 1 Menu -----");
        System.out.println("");
        System.out.println("1.  Display the List");
        System.out.println("2.  Add a name at the end of the List");
        System.out.println("3.  Add a name by index");
        System.out.println("4.  Delete a name");
        System.out.println("5.  Delete a name by index");
        System.out.println("6.  Display the size of the List");
        System.out.println("7.  Delete all names");
        System.out.println("8.  Search for a name");
        System.out.println("9.  Display the List in reverse order");
        System.out.println("10. Sort the List");
        System.out.println("11. Display the min element");
        System.out.println("12. Display the max element");
        System.out.println("99. Exit");
    }
    
    
}