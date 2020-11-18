import java.util.GregorianCalendar;

/**
 * <h1>Newsletter</h1>
 * <p>
 *     A class representing a Newsletter, extending a Publication, created for
 *     CIS 131 Lab 8 - Inheritance and Polymorphism.
 * </p>
 * @author Houghton Mayfield
 * @since 11/15/2020
 * @version 0.0.1
 */
public class Newsletter extends Publication{
    // --------- Fields ------------------------------------------------------------------------------------------------
    protected String frequency;
    protected String editor;
    protected String owner;

    // --------- Getters -----------------------------------------------------------------------------------------------
    public String getFrequency() {
        return frequency;
    }

    public String getEditor() {
        return editor;
    }

    public String getOwner() {
        return owner;
    }

    // --------- Setters -----------------------------------------------------------------------------------------------
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    // --------- Methods -----------------------------------------------------------------------------------------------
    public String toString() {
        return String.format("%s\nEditor: %s\nFrequency: %s\nOwner: %s",
                super.toString(), editor, frequency, owner);
    }

    // --------- Constructors ------------------------------------------------------------------------------------------
    Newsletter(String title, String publisher, GregorianCalendar date, String subject, String frequency, String editor, String owner) {
        super(title, publisher, date, subject);

        this.frequency = frequency;
        this.editor = editor;
        this.owner = owner;
    }
}
