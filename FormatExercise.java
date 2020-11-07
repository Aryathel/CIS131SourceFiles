/**
 *  <p>This program demonstrates an understanding of string output formatting.</p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 2020-09-15
 */

public class FormatExercise {
    public static void main(String[] args) {
        formatList();
        displayReceipt("Monday", 135, 1.25, 745, 1000, 11.25);
    } 
    
    //----------------------------------------------------------------------------------------------------------
    
    public static void formatList() {
        String BUDGET_ITEM[] = {"Clothing", "Utilities", "Automobile", "Groceries", "Other"};
        double COST[] = {20.00, 92.99, 453.55, 84.76, 7.23};
        
        System.out.println("===========LIST OF BUDGET ITEMS=============");
        System.out.println("Item --- Category ---------------- Cost ----");

        // Loop through the budget and cost lists to print each element out one by one
        for (int i = 0; i < BUDGET_ITEM.length; i++) {
            System.out.printf("%-8d %-25s $%-8.2f\n", i, BUDGET_ITEM[i], COST[i]);
        }
    }
    
    //----------------------------------------------------------------------------------------------------------
    
    public static void displayReceipt(String dayOfWeek, 
                                      int parkingDuration, 
                                      double rate, 
                                      int aTime, 
                                      int dTime, 
                                      double amountCharged) {
        
        
        System.out.println("\n");
        System.out.println("*****************************");
        System.out.println("  Wegougem Parking Garage");
        System.out.println("       Sales Receipt");
        System.out.println("*****************************");
        System.out.println("");
        // Print receipts using placeholder formatting. Not really much to comment on here.
        System.out.printf("Day of Week: %s\n", dayOfWeek);
        System.out.printf("Rate: $.2f per 15 minute interval\n\n", rate);
        System.out.printf("%-18s%5d\n", "Arrival Time:", aTime);
        System.out.printf("%-18s%5d\n", "Departure Time:", dTime);
        System.out.printf("%-18s%5d minutes\n\n", "Parking Duration:", parkingDuration);
        System.out.printf("Amount Charged: $%.2f\n", amountCharged);
        
        System.out.println("*****************************");
        System.out.println(""); 
    }
    
}//end of class
