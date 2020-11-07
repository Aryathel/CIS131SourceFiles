/**
 *  <h1>Coordinate 2D<h1>
 *  <p>A simple class for creating and accessing a 2D coordinate.</p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 2020-09-16
 */

public class Coordinate2D {
    public int X;
    public int Y;

    /** Initializes the X and Y values of a 2D coordinate.
     *
     * @param X The value of the X coordinate.
     * @param Y The value of the Y coordinate.
     */
    public Coordinate2D(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    /**
     *
     * @param X The value of the X coordinate.
     * @param Y The value of the Y coordinate.
     */
    public void setCoordinate(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}
