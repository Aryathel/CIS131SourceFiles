/**
 * <h1>Particle Lab</h1>
 * <p>
 *     A demonstration of a basic provided GUI interaction, managed through particle simulation.
 * </p>
 * @author Houghton Mayfield
 * @since 11/30/2020
 * @version 1.14.5
 */

import java.awt.*;
import java.util.*;

public class ParticleLab{
    static final int NBR_ROWS  = 200;  //180 
    static final int NBR_COLS  = 180;  //180 
    static final int CELL_SIZE = 800;  //800
    
    static final String FILE_NAME     = "ParticleLabFileGreatPainting.txt";               //This is the name of the input file.
    static final String NEW_FILE_NAME = "ParticleLabFileGreatPainting.txt";  //This is the name of the file you are saving.

    //add constants for particle types here
    public static final int EMPTY     = 0;
    public static final int METAL     = 1;
    public static final int SAND      = 2;
    public static final int WATER     = 3;
    public static final int OIL       = 4;
    public static final int VAPOR     = 5;
    public static final int GENERATOR = 6;
    public static final int DESTRUCT  = 7;
    public static final int GRAVITY   = 8;
    public static final int SAVEFILE  = 9;

    // constants for gravity control
    public static final boolean DOWN = true;
    public static final boolean UP   = false;
    public static final int LEFT     = 1;
    public static final int RIGHT    = 2;
    public static final int MIDDLE   = 3;
    
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
        String[] names = new String[10];
        
        names[EMPTY]     = "Empty";
        names[METAL]     = "Metal";
        names[SAND]      = "Sand";
        names[WATER]     = "Water";
        names[OIL]       = "Oil";
        names[VAPOR]     = "Vapor";
        names[GENERATOR] = "Generator";
        names[DESTRUCT]  = "Destructor";
        names[GRAVITY]   = "Gravity";
        names[SAVEFILE]  = "SaveFile";
        
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
        for (int i = 0; i < particleGrid.length; i++) {
            for (int j = 0; j < particleGrid[i].length; j++) {
                if (particleGrid[i][j] == EMPTY) {
                    display.setColor(i, j, Color.black);
                } else if (particleGrid[i][j] == METAL) {
                    display.setColor(i, j, Color.gray);
                } else if (particleGrid[i][j] == SAND) {
                    display.setColor(i, j, Color.yellow);
                } else if (particleGrid[i][j] == WATER) {
                    display.setColor(i, j, Color.blue);
                } else if (particleGrid[i][j] == OIL) {
                    display.setColor(i, j, Color.darkGray);
                } else if (particleGrid[i][j] == GENERATOR) {
                    display.setColor(i, j, Color.green);
                } else if (particleGrid[i][j] == DESTRUCT) {
                    display.setColor(i, j, Color.magenta);
                } else if (particleGrid[i][j] == VAPOR) {
                    display.setColor(i, j, Color.lightGray);
                }
            }
        }
    }
    

    //called repeatedly.
    //causes one random particle to maybe do something.
    public void step(){
        int row = getRandomNumber(0, NBR_ROWS - 1);
        int col = getRandomNumber(0, NBR_COLS - 1);

        // The modifier used to increment a particle vertically or horizontally in the negative or positive direction
        int gravityMod = 1;
        if (gravity == UP) {
            gravityMod = -1;
        }

        if (particleGrid[row][col] == SAND) {
            // Move the particle if it is sand
            moveSand(row, col, gravityMod);
        } else if (particleGrid[row][col] == WATER) {
            // Move a water particle
            moveWater(row, col, gravityMod);
        } else if (particleGrid[row][col] == OIL) {
            moveOil(row, col, gravityMod);
        } else if (particleGrid[row][col] == VAPOR) {
            moveVapor(row, col, gravityMod);
        } else if (particleGrid[row][col] == GENERATOR) {
            handleGenerator(row, col, gravityMod);
        } else if (particleGrid[row][col] == DESTRUCT) {
            handleDestructor(row, col, gravityMod);
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
        newRow = wrapRow(newRow);


        // If the new particle locations is empty
        if (particleGrid[newRow][col] == EMPTY || particleGrid[newRow][col] == VAPOR) {
            swap(newRow, col, row, col);
        } else {
            // Pick a random direction for the sand particle to move if the next
            int direction = getRandomNumber(LEFT, RIGHT);

            // Get the modifier for direction based on the random choice
            if (direction == LEFT) {
                mod = -1;
            } else if (direction == RIGHT) {
                mod = 1;
            }

            // Calculate the new column index
            newCol = col + mod;

            // Wrap the column horizontally if necessary
            newCol = wrapCol(newCol);

            // Pile the sand diagonally if the space is available
            if (particleGrid[newRow][newCol] == EMPTY ||
                    particleGrid[newRow][newCol] == WATER ||
                    particleGrid[newRow][newCol] == OIL ||
                    particleGrid[newRow][newCol] == VAPOR) {
                swap(row, col, newRow, newCol);
            }
        }
    }

    /** The function used to move a randomly selected water particle.
     *
     * Behavior:
     * - Falls down with a little bit of random spread.
     * - Seeks to level out when pooling together.
     * - Solid particles fall through it.
     *
     * @param row The row of the original water particle to be moved.
     * @param col The column of the original water particle to be moved.
     * @param gravityMod The modifier which affects the direction of the gravity.
     */
    public void moveWater(int row, int col, int gravityMod) {
        int newRow = row + gravityMod;
        int newCol = col;
        int direction;

        // Wrap the row as necessary:
        newRow = wrapRow(newRow);

        if (particleGrid[newRow][col] == EMPTY || particleGrid[newRow][col] == OIL
                || particleGrid[newRow][col] == VAPOR) {
            direction = getLiquidDirection();
            if (direction == LEFT) {
                newCol = col - 1;
            } else if (direction == MIDDLE) {
                newCol = col;
            } else if (direction == RIGHT) {
                newCol = col + 1;
            }
        } else {
            newRow = row;
            direction = getRandomNumber(LEFT, RIGHT);
            if (direction == LEFT) {
                newCol = col - 1;
            } else if (direction == RIGHT) {
                newCol = col + 1;
            }
        }


        // Wrap the column as necessary
        newCol = wrapCol(newCol);
        if (particleGrid[newRow][newCol] == EMPTY || particleGrid[newRow][newCol] == OIL ||
                particleGrid[newRow][newCol] == VAPOR) {
            swap(row, col, newRow, newCol);
        }
    }

    /** The function used to move a randomly selected oil particle.
     *
     * Behavior:
     * - Floats on top of water, including rising through water.
     * - Acts like water otherwise, solid particles fall through it.
     *
     * @param row The row of the original oil particle to be moved.
     * @param col The column of the original oil particle to be moved.
     * @param gravityMod The modifier which affects the direction of the gravity.
     */
    public void moveOil(int row, int col, int gravityMod) {
        int newRow = row + gravityMod;
        int newCol = col;
        int direction;

        // Wrap the row as necessary:
        newRow = wrapRow(newRow);

        if (particleGrid[newRow][col] == EMPTY) {
            direction = getLiquidDirection();
            if (direction == LEFT) {
                newCol = col - 1;
            } else if (direction == MIDDLE) {
                newCol = col;
            } else if (direction == RIGHT) {
                newCol = col + 1;
            }
        } else {
            newRow = row;
            direction = getRandomNumber(LEFT, RIGHT);
            if (direction == LEFT) {
                newCol = col - 1;
            } else if (direction == RIGHT) {
                newCol = col + 1;
            }
        }


        // Wrap the column as necessary
        newCol = wrapCol(newCol);

        if (particleGrid[newRow][newCol] == EMPTY ||
                particleGrid[newRow][newCol] == VAPOR) {
            swap(row, col, newRow, newCol);
        }
    }

    /** The function used to move a randomly selected vapor particle.
     *
     * Behavior:
     * - Floats opposite to gravity.
     * - Other particles fall through it.
     * - When a vapor particle is being touched on multiple sides by other vapor particles, it turns to water and
     *   sets all other vapor particles in a 1 pixel radius to empty.
     * - Seeks to level with itself.
     *
     * @param row The row of the original vapor particle to be moved.
     * @param col The column of the original vapor particle to be moved.
     * @param gravityMod The modifier which affects the direction of the gravity.
     */
    public void moveVapor(int row, int col, int gravityMod) {
        int vaporCount = countVaporAround(row, col);

        if (vaporCount < 8) {
            int newRow = row + (gravityMod * -1); // Moves opposite to gravity
            int newCol = col;
            int direction;

            // Wrap the row as necessary:
            newRow = wrapRow(newRow);

            if (particleGrid[newRow][col] == EMPTY) {
                direction = getVaporDirection();
                if (direction == LEFT) {
                    newCol = col - 1;
                } else if (direction == MIDDLE) {
                    newCol = col;
                } else if (direction == RIGHT) {
                    newCol = col + 1;
                }
            } else {
                newRow = row;
                direction = getRandomNumber(LEFT, RIGHT);
                if (direction == LEFT) {
                    newCol = col - 1;
                } else if (direction == RIGHT) {
                    newCol = col + 1;
                }
            }

            // Wrap the column as necessary
            newCol = wrapCol(newCol);

            if (particleGrid[newRow][newCol] == EMPTY) {
                swap(row, col, newRow, newCol);
            }
        } else {
            particleGrid[row][col] = WATER;
            //clearVaporAround(row, col);
        }
    }

    /** The function used to move a randomly selected generator particle.
     *
     * Behavior:
     * - 50% chance to copy any particles above (above being defined by gravity) the generator to below, as long
     *   as there is an empty particle below.
     *
     * @param row The row of the generator to handle.
     * @param col The column of the generator to handle.
     * @param gravityMod The modifier which affects the direction of the gravity.
     */
    public void handleGenerator(int row, int col, int gravityMod) {
        // Only generate half of the time
        if (getRandomNumber(1, 2) == 2) {
            return;
        }

        int rowSrc, rowDest;

        // Get the row values for the source and destination pixels.
        rowSrc = wrapRow(row - gravityMod);
        rowDest = wrapRow(row + gravityMod);

        // Make sure source block is acceptable, and destination block is empty
        if (particleGrid[rowSrc][col] != EMPTY &&
                particleGrid[rowSrc][col] != GENERATOR &&
                particleGrid[rowSrc][col] != DESTRUCT &&
                (particleGrid[rowDest][col] == EMPTY || particleGrid[rowDest][col] == VAPOR)) {
            particleGrid[rowDest][col] = particleGrid[rowSrc][col];
        }
    }

    /** The function used to move a randomly selected destructor particle.
     *
     * Behavior:
     * - Removes any particles above (above being defined by gravity) the destructor, and replaces them with a vapor
     *   particle.
     *
     * @param row The row of the destructor to handle.
     * @param col The column of the destructor to handle.
     * @param gravityMod The modifier which affects the direction of the gravity.
     */
    public void handleDestructor(int row, int col, int gravityMod) {
        int rowCheck = wrapRow(row - gravityMod);

        if (particleGrid[rowCheck][col] != EMPTY &&
                particleGrid[rowCheck][col] != GENERATOR &&
                particleGrid[rowCheck][col] != DESTRUCT) {
            particleGrid[rowCheck][col] = VAPOR;
        }
    }

    /** Swaps the locations of two particles.
     *
     * @param row1 The row of the first particle.
     * @param col1 The column of the first particle.
     * @param row2 The row of the second particle.
     * @param col2 The column of the second particle.
     */
    public void swap(int row1, int col1, int row2, int col2) {
        int tmp = particleGrid[row1][col1];
        particleGrid[row1][col1] = particleGrid[row2][col2];
        particleGrid[row2][col2] = tmp;
    }

    /** Get a random direction for the liquid to fall in.
     *
     * @return The direction the liquid particle will be moving.
     */
    public int getLiquidDirection() {
        int selection = getRandomNumber(1, 100);
        if (selection <= 25) {
            return LEFT;
        } else if (selection <= 75) {
            return MIDDLE;
        } else {
            return RIGHT;
        }
    }

    /** Get a random direction for the vapor to fall in.
     *
     * @return The direction the vapor particle will be moving.
     */
    public int getVaporDirection() {
        int selection = getRandomNumber(1, 300);
        if (selection <= 100) {
            return LEFT;
        } else if (selection <= 200) {
            return MIDDLE;
        } else {
            return RIGHT;
        }
    }

    /** Counts the number of nearby vapor particles to a given coordinate.
     *
     * @param row The current row of a vapor particle.
     * @param col The current column of a vapor particle.
     * @return The number of vapor particles in a 1-pixel radius.
     */
    public int countVaporAround(int row, int col) {
        int count = 0;
        int rowCheck, colCheck;

        // Checking all 8 pixels in a 1 pixel radius of the center
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                rowCheck = row + i;
                colCheck = col + j;

                // Making sure the row and column being checked is viable
                rowCheck = wrapRow(rowCheck);
                colCheck = wrapCol(colCheck);

                if (!(colCheck == col && rowCheck == row)) {
                    if (particleGrid[rowCheck][colCheck] == VAPOR) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /** Clears all of the vapor particles in a 1-pixel radius of a selected pixel.
     *
     * @param row The row of the pixel to clear the vapor around.
     * @param col The column of the pixel to clear the vapor around.
     */
    public void clearVaporAround(int row, int col) {
        int rowCheck, colCheck;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                rowCheck = row + i;
                colCheck = col + j;

                // Making sure the row and column being checked is viable
                rowCheck = wrapRow(rowCheck);
                colCheck = wrapCol(colCheck);

                if (!(colCheck == col && rowCheck == row)) {
                    if (particleGrid[rowCheck][colCheck] == VAPOR) {
                        particleGrid[rowCheck][colCheck] = EMPTY;
                    }
                }
            }
        }
    }

    /** Wraps the row vertically as necessary.
     *
     * @param row The row to check wrapping for.
     * @return The corrected row value after wrapping.
     */
    public int wrapRow(int row) {
        int newRow = row;
        if (row < 0) {
            newRow = NBR_ROWS - 1;
        } else if (row == NBR_ROWS) {
            newRow = 0;
        }

        return newRow;
    }

    /** Wraps the row vertically as necessary.
     *
     * @param col The col to check wrapping for.
     * @return The corrected col value after wrapping.
     */
    public int wrapCol(int col) {
        int newCol = col;
        if (col < 0) {
            newCol = NBR_COLS - 1;
        } else if (col == NBR_COLS) {
            newCol = 0;
        }

        return newCol;
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
        high += 1;
        return (int)(Math.random() * (high - low)) + low;
    }
    
    public static int getNbrRows() {return NBR_ROWS;}
    public static int getNbrCols() {return NBR_COLS;}
    public static int getCellSize(){return CELL_SIZE;}
}