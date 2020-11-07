/**
 * Auto Generated Java Class.
 */
public class TransposeArrayExercise {
    static final int MAX = 5;
    static final int LOW  = 100;
    static final int HIGH = 300;
    
    public static void main(String[] args) { 
        int [][] originalArray  = new int [MAX][MAX];
        
        initializeOriginalArray(originalArray);
        printArray(originalArray, "Original Array");

        //Not a great way to do this because it combines output and processing
        //but I wanted to show this kind of thing is possible. 
        printArray(transposeArray1(originalArray), "Transposed Array #1");
        printArray(transposeArray2(originalArray), "Transposed Array #2");
        printArray(transposeArray3(originalArray), "Transposed Array #3");
    }

    private static int[][] transposeArray1(int [][] origArray){
        int [][] tempArray     = new int [MAX][MAX];
        
        for(int r = 0; r < origArray.length; r++){
            for(int c = 0; c < origArray[r].length; c++){
                
                //change the below line of code 
                tempArray[r][c] = origArray[c][r];
            }
        }
        return tempArray;
    }
    
    private static int[][] transposeArray2(int [][] origArray){
        int [][] tempArray     = new int [MAX][MAX];
        
        for(int r = 0; r < origArray.length; r++){
            for(int c = 0; c < origArray[r].length; c++){
                
                //change the below line of code 
                tempArray[r][c] = origArray[r][origArray[r].length - c - 1];
            }
        }
        return tempArray;
    }
    
    private static int[][] transposeArray3(int [][] origArray){
        int [][] tempArray     = new int [MAX][MAX];
        
         for(int r = 0; r < origArray.length; r++){
            for(int c = 0; c < origArray[r].length; c++){
                            
                //change the below line of code 
                tempArray[origArray.length - r - 1][origArray[r].length - c - 1] = origArray[r][c];
            }
        }
        return tempArray;
    }
    
//----------------------------------------------------------------------------------
    
    private static void initializeOriginalArray(int [][] array){
        for(int r = 0; r < array.length; r++){
            for(int c = 0; c < array[r].length; c++){
                array[r][c] = r * 10 + c; 
            }
        }
    }
    
    private static void printArray(int [][] array, String title){
        System.out.println("\n--- " + title + "---");
        
        for(int r = 0; r < array.length; r++){
            for(int c = 0; c < array[r].length; c++){
                
                System.out.printf("%02d ", array[r][c]);
            }
            System.out.println("");
        }
    }
        
    
}
