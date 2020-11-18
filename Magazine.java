import java.util.GregorianCalendar;

/**
 * <h1>Magazine</h1>
 * <p>
 *     A class representing a Magazine, extending a Publication, created for
 *     CIS 131 Lab 8 - Inheritance and Polymorphism.
 * </p>
 * @author Houghton Mayfield
 * @since 11/15/2020
 * @version 0.0.1
 */
public class Magazine extends Publication{
    // --------- Fields ------------------------------------------------------------------------------------------------
    protected String frequency;
    protected String editor;
    protected double subscriptionPrice;

    // --------- Getters -----------------------------------------------------------------------------------------------
    public String getFrequency() {
        return frequency;
    }

    public String getEditor() {
        return editor;
    }

    public double getSubscriptionPrice() {
        return subscriptionPrice;
    }

    // --------- Setters -----------------------------------------------------------------------------------------------
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setSubscriptionPrice(double subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    // --------- Methods -----------------------------------------------------------------------------------------------
    public String toString() {
        return String.format("%s\nEditor: %s\nFrequency: %s\nPrice: $%.2f",
                super.toString(), editor, frequency, subscriptionPrice);
    }

    // --------- Constructors ------------------------------------------------------------------------------------------
    Magazine(String title, String publisher, GregorianCalendar date, String subject, String frequency, String editor, double price) {
        super(title, publisher, date, subject);

        this.frequency = frequency;
        this.editor = editor;
        subscriptionPrice = price;
    }
}
