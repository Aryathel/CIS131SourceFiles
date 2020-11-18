/*********************************************************************************************************************
  * FileExercise.java
  *********************************************************************************************************************/
import java.io.*;
import java.util.*;

public class FileExercise {
    public static void main(String[] args) throws IOException {
        useBufferedReader();   
        useDataInputStream();
    }
    //-------------------------------------------------------------------------------------------------------------------
    public static void useBufferedReader() throws IOException {
        System.out.println("------- Using BufferedReader -----------------");
        
        FileWriter  fw = new FileWriter("file-1.txt");
        PrintWriter pw = new PrintWriter(fw);
        
        int nbrRecordsToWrite = getRandomNbr (1, 2);
        for (int i = 1; i <= nbrRecordsToWrite; i++) 
        {
            double randomDouble = (getRandomNbr (0, 100));  // 1. Use getRandomNumber to randomly create a double number between 0.00 and 100.00 (not an integer)
            int randomInt = getRandomNbr (0, 100);          //    The double number should contain only 2 numbers to the right of the decimal.
            
            pw.println("Student#" + i);
            pw.println(randomDouble);
            pw.println(randomInt);
            
            int randomBoolean = getRandomNbr (0, 1);
            if (randomBoolean == 0) {pw.println(false);}
                if (randomBoolean == 1) {pw.println(true);}
            
            pw.flush();
        }
        pw.close();
        
        //Read the file and print it
        FileReader fr = new FileReader("file-1.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String student = "";
        while ((student = br.readLine()) != null) {
            System.out.println(student + "   " + br.readLine() + "   " + br.readLine() + "   " + br.readLine());
        }
        br.close();
        
        System.out.println("\nAll records have been read");
        
        // 2. Each line in the output should be formatted like this:   Student#1  12.34  54  true
        // 3. The file-1.txt file should contain 5 to 20 records and they should be displayed to the console.
        //    Test this and make sure the console matches the file. 
    }
    
    //-------------------------------------------------------------------------------------------------------------------
    public static void useDataInputStream() throws IOException {
        System.out.println("\n------- Using DataInputStream -----------------");
        try ( // Create an output stream for file temp.dat
             DataOutputStream output =
             new DataOutputStream(new FileOutputStream("file-2.txt"));
        ) {
            int nbrRecordsToWrite = getRandomNbr (1, 2);
            for (int i = 1; i <= nbrRecordsToWrite; i++) 
            {
                output.writeUTF("Student#" + i);
                
                double randomDouble = (getRandomNbr (0, 100)); // 4. Use getRandomNumber to randomly create a double number between 0.00 and 100.00 (not an integer)
                output.writeDouble(randomDouble);              //    The double number should contain only 2 numbers to the right of the decimal.
                
                int randomInt = getRandomNbr (0, 100);
                output.writeInt(randomInt);
                
                int randomBoolean = getRandomNbr (0, 1);
                if (randomBoolean == 0) {output.writeBoolean(false);}
                if (randomBoolean == 1) {output.writeBoolean(true);}
            }
        }
        try ( //Read the file as input 
             DataInputStream input =
             new DataInputStream(new FileInputStream("file-2.txt"));
        ) {
            while (true) {
                System.out.println(input.readUTF() + "  " 
                                       + input.readDouble() + "  " 
                                       + input.readInt() + "  " 
                                       + input.readBoolean());
            }
        }
        catch (EOFException ex) {
            System.out.println("\nAll records have been read");
        }
        
        // 5. Each line in the output should be formatted so you can't read it, like this:    Student#1@M€        S 
        // 6. The file-2.txt file should contain 5 to 20 records and they should be displayed to the console.
        //    Test this and make sure the console matches the file. 
        
    }


    //DO NOT CHANGE THE BELOW FUNCTION!!!
    //Returns a random number from low to high, inclusive - DO NOT CHANGE!!!
    public static int getRandomNbr (int l, int h) {return (int)(Math.random() * ((h + 1) - l)) + l;}
}