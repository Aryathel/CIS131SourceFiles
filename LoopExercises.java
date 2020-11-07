public class LoopExercises {
    
    final static String DISPLAY_CHAR  = "# ";
    final static String DISPLAY_BLANK = "  ";
    
    public static void main(String[] args){
        displayWelcomeMessage();
        
        printSubscripts(10,10);                   // Subscripts
        printRectangle(10,10);                    // Rectangle
        printBox(10,10);                          // Box    
        printLeftTriangle(10,10);                 // Left-leaning Triangle
        printUpsideDownLeftTriangle(10,10);       // Upside-down Left-leaning Triangle
        printUpsideDownRightSideTriangle(10, 10); // Upside-down RightSide Triangle
        printBoxWithDiagonal(10, 10);             // Box with diagonal 
                
        System.out.println("\n----- Program Complete -----"); 
    }
    
    private static void displayWelcomeMessage() {
        System.out.println("");
        System.out.println("************************************************");
        System.err.println("                 For-Loop Exercises");
        System.out.println("************************************************");
        System.out.println("");  
    }
    
    private static void printSubscripts(int nbrOfRows, int nbrOfCols) {
        System.out.println("\n--- " + nbrOfRows + "x" + nbrOfCols + " Subscripts ---");
        
        for (int r = 0; r < nbrOfRows; r++){
            for (int c = 0; c < nbrOfCols; c++){
                System.out.print(r + "," + c + DISPLAY_BLANK);
            }
            System.out.println("");
        }
    }
    
    //Display a filled in rectangle
    /*
        # # # # # # # # # #  
        # # # # # # # # # #  
        # # # # # # # # # #  
        # # # # # # # # # #  
        # # # # # # # # # #  
        # # # # # # # # # #  
        # # # # # # # # # #  
        # # # # # # # # # #  
        # # # # # # # # # #  
        # # # # # # # # # #  
    */
    private static void printRectangle(int nbrOfRows, int nbrOfCols) {
        System.out.println("\n--- " + nbrOfRows + "x" + nbrOfCols + " Shape 1 ---");

        for (int x = 0; x < nbrOfRows; x++) {
            for (int y = 0; y < nbrOfCols; y++) {
                System.out.print(DISPLAY_CHAR);
            }
            System.out.println(DISPLAY_BLANK);
        }
    }
    
    //Display a box
     /*
     # # # # # # # # # #  
     #                 #  
     #                 #  
     #                 #  
     #                 #  
     #                 #  
     #                 #  
     #                 #  
     #                 #  
     # # # # # # # # # #  
   */
    private static void printBox (int nbrOfRows, int nbrOfCols) {
        System.out.println("\n--- " + nbrOfRows + "x" + nbrOfCols + " Shape 2 ---");

        for (int x = 0; x < nbrOfRows; x++) {
            for (int y = 0; y < nbrOfCols; y++) {
                if (x == 0 || x == nbrOfRows - 1 || y == 0 || y == nbrOfCols - 1) {
                    System.out.print(DISPLAY_CHAR);
                } else {
                    System.out.print(DISPLAY_BLANK);
                }
            }
            System.out.println("");
        }
    }
     
    //Display a left-leaning triangle
    /*
     #  
     # #  
     # # #  
     # # # #  
     # # # # #  
     # # # # # #  
     # # # # # # #  
     # # # # # # # #  
     # # # # # # # # #  
     # # # # # # # # # #  
   */
    private static void printLeftTriangle (int nbrOfRows, int nbrOfCols) {
        System.out.println("\n--- " + nbrOfRows + "x" + nbrOfCols + " Shape 3 ---");

        for (int x = 0; x < nbrOfRows; x++) {
            for (int y = 0; y < nbrOfCols; y++) {
                if (y <= x) {
                    System.out.print(DISPLAY_CHAR);
                } else {
                    System.out.print(DISPLAY_BLANK);
                }
            }
            System.out.println("");
        }
    }
    
    //Display an upside-down left-leaning triangle
    /*
     # # # # # # # # # #  
     # # # # # # # # #  
     # # # # # # # #  
     # # # # # # #  
     # # # # # #  
     # # # # #  
     # # # #  
     # # #  
     # #  
     #    
   */
    private static void printUpsideDownLeftTriangle (int nbrOfRows, int nbrOfCols) {
        System.out.println("\n--- " + nbrOfRows + "x" + nbrOfCols + " Shape 4 ---");

        for (int x = 0; x < nbrOfRows; x++) {
            for (int y = 0; y < nbrOfCols; y++) {
                if (y < nbrOfRows - x) {
                    System.out.print(DISPLAY_CHAR);
                } else {
                    System.out.print(DISPLAY_BLANK);
                }
            }
            System.out.println("");
        }
    }
    
    //Display an upside-down rightside triangle
    /*
     # # # # # # # # # #  
       # # # # # # # # #  
         # # # # # # # #  
           # # # # # # #  
             # # # # # #  
               # # # # #  
                 # # # #  
                   # # #  
                     # #  
                       #    
   */
    private static void printUpsideDownRightSideTriangle (int nbrOfRows, int nbrOfCols) {
        System.out.println("\n--- " + nbrOfRows + "x" + nbrOfCols + " Shape 5 ---");

        for (int x = 0; x < nbrOfRows; x++) {
            for (int y = 0; y < nbrOfCols; y++) {
                if (y >= x) {
                    System.out.print(DISPLAY_CHAR);
                } else {
                    System.out.print(DISPLAY_BLANK);
                }
            }
            System.out.println("");
        }
    }
  
    //Display a box with a diagonal
    /*
     # # # # # # # # # #  
     #               # #  
     #             #   #   
     #           #     #  
     #         #       #  
     #       #         #  
     #     #           #  
     #   #             #  
     # #               #  
     # # # # # # # # # # 
   */
    private static void printBoxWithDiagonal(int nbrOfRows, int nbrOfCols) {
        System.out.println("\n--- " + nbrOfRows + "x" + nbrOfCols + " Shape 6 ---");
        /*
        The if else tree uses the standard hollow bow from shape 2
        and builds on it to add the diagonal line.
        */
        for (int x = 0; x < nbrOfRows; x++) {
            for (int y = 0; y < nbrOfCols; y++) {
                if (x == 0 || x == nbrOfRows - 1 || y == 0 || y == nbrOfCols - 1) {
                    System.out.print(DISPLAY_CHAR);
                } else if (y == nbrOfCols - x - 1) {
                    System.out.print(DISPLAY_CHAR);
                } else {
                    System.out.print(DISPLAY_BLANK);
                }
            }
            System.out.println("");
        }
    }
    
}//End of Class