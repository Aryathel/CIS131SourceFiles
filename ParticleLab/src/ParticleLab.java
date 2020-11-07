/* A. StudentName
 * 
 * 
 */
import java.awt.*;
import java.util.*;

public class ParticleLab{
    static final int NBR_ROWS  = 200;  //180 
    static final int NBR_COLS  = 180;  //180 
    static final int CELL_SIZE = 800;  //800
    
    static final String FILE_NAME     = "./src/ParticleLabFile.txt";         //This is the name of the input file.
    static final String NEW_FILE_NAME = "./src/ParticleLabFileGreatPainting.txt";  //This is the name of the file you are saving.

    //add constants for particle types here
    public static final int EMPTY     = 0;
    public static final int METAL     = 1;
    public static final int SAND      = 2;
    public static final int GRAVITY   = 3;
    public static final int SAVEFILE  = 4;

    // constants for gravity control
    public static final boolean DOWN = true;
    public static final boolean UP   = false;
    public static final int LEFT     = 1;
    public static final int RIGHT    = 2;
    
    //do not add any more global fields
    private int row = 0;
    private int col = 0;
    // Stores current gravity state, using DIRECTION as the first index and POSITIVITY as the second.
    private boolean gravity = DOWN;
    
    private int[][] particleGrid;
    private LabDisplay display;


    //---------------------------------------------------------------------------------------------------------
    
    public static void main(String[] args){
        System.out.println("================= Starting Program =================");
        System.out.println("ROWS: " + NBR_ROWS + "\nCOLS: " + NBR_COLS + "\nCELL_SIZE: " + CELL_SIZE + "\n");
        
        ParticleLab lab = new ParticleLab(NBR_ROWS, NBR_COLS);  //creates the object
        lab.run();
    }
    

    //SandLab constructor - ran when the above lab object is created 
    public ParticleLab(int numRows, int numCols){
        String[] names = new String[5];
        
        names[EMPTY]    = "Empty";
        names[METAL]    = "Metal";
        names[SAND]     = "Sand";
        names[GRAVITY]  = "Gravity";
        names[SAVEFILE] = "SaveFile";
        
        display      = new LabDisplay("SandLab", numRows, numCols, names);  //uses the LabDisplay.class file 
        particleGrid = new int[numRows][numCols];

        if (FILE_NAME != "") {  
            System.out.println("Attempting to load: " + FILE_NAME);
            particleGrid = ParticleLabFiles.readFile(FILE_NAME);   
        } 
    }

    //called when the user clicks on a location using the given tool
    private void locationClicked(int row, int col, int tool){
        //insert code here
        if (tool != GRAVITY && tool != SAVEFILE) {
            particleGrid[row][col] = tool;
        } else if (tool == GRAVITY) {
            // Change the direction of gravity based on previous gravity direction
            gravity = !gravity;
        } else if (tool == SAVEFILE) {
            ParticleLabFiles.writeFile(particleGrid, NEW_FILE_NAME);
        }
    }

    //Examines each element of the 2D particleGrid and paints a color onto the display
    public void updateDisplay(){
        //remove the below code when ready
        Color purple = new Color (192, 0, 255);              //an example of how to create a new color
        display.setColor(0,0,purple);                        //an example of how to display the new color
        
        display.setColor(0,NBR_COLS-1,Color.yellow);         //an example of how to display a named Java color
        display.setColor(NBR_ROWS-1,0,Color.green);          //an example of how to display a named Java color
        display.setColor(NBR_ROWS-1,NBR_COLS-1,Color.red);   //an example of how to display a named Java color
        
        //insert code here
        for (int i = 0; i < particleGrid.length; i++) {
            for (int j = 0; j < particleGrid[i].length; j++) {
                if (particleGrid[i][j] == EMPTY) {
                    display.setColor(i, j, Color.black);
                } else if (particleGrid[i][j] == METAL) {
                    display.setColor(i, j, Color.gray);
                } else if (particleGrid[i][j] == SAND) {
                    display.setColor(i, j, Color.yellow);
                }
            }
        }
    }
    

    //called repeatedly.
    //causes one random particle to maybe do something.
    public void step(){
        int row = getRandomNumber(0, NBR_ROWS);
        int col = getRandomNumber(0, NBR_COLS);

        // The modifier used to increment a particle vertically or horizontally in the negative or positive direction
        int gravityMod = 1;
        if (gravity == UP) {
            gravityMod = -1;
        }

        if (particleGrid[row][col] == SAND) {
            // Move the particle if it is sand
            moveSand(row, col, gravityMod);
        }
    }

    /** The function used to move a randomly selected sand particle.
     *
     * @param row The row of the original sand particle to be moved.
     * @param col The column of the original sand particle to be moved.
     * @param gravityMod The modifier which affects the direction of the gravity.
     */
    public void moveSand(int row, int col, int gravityMod) {
        int newRow, newCol, mod = 1;

        // Select the next row based on the gravity modifier.
        newRow = row + gravityMod;

        // Wrap the row vertically if necessary
        if (newRow < 0) {
            newRow = NBR_ROWS - 1;
        } else if (newRow == NBR_ROWS) {
            newRow = 0;
        }

        // If the new particle locations is empty
        if (particleGrid[newRow][col] == EMPTY) {
            particleGrid[row][col] = EMPTY;
            particleGrid[newRow][col] = SAND;
        } else {
            // Pick a random direction for the sand particle to move if the next
            int direction = getRandomNumber(LEFT, RIGHT+1);

            // Get the modifier for direction based on the random choice
            if (direction == LEFT) {
                mod = -1;
            } else if (direction == RIGHT) {
                mod = 1;
            }

            // Calculate the new column index
            newCol = col + mod;

            // Wrap the column horizontally if necessary
            if (newCol < 0) {
                newCol = NBR_COLS - 1;
            } else if (newCol == NBR_COLS) {
                newCol = 0;
            }

            // Pile the sand diagonally if the space is available
            if (particleGrid[newRow][newCol] == EMPTY) {
                particleGrid[row][col] = EMPTY;
                particleGrid[newRow][newCol] = SAND;
            }
        }
    }

    ////////////////////////////////////////////////////
    // DO NOT modify anything below here!!! ////////////
    ////////////////////////////////////////////////////
    
    public void run(){
        while (true){
            for (int i = 0; i < display.getSpeed(); i++){
                step();
            }
            updateDisplay();
            display.repaint();
            display.pause(1);  //wait for redrawing and for mouse   
            int[] mouseLoc = display.getMouseLocation();
            if (mouseLoc != null)  //test if mouse clicked
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
        }
    }
    
    public int getRandomNumber (int low, int high){
        return (int)(Math.random() * (high - low)) + low;
    }
    
    public static int getNbrRows() {return NBR_ROWS;}
    public static int getNbrCols() {return NBR_COLS;}
    public static int getCellSize(){return CELL_SIZE;}
}