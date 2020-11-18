import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * <h1>Publication</h1>
 * <p>
 *     A class representing a broad publication, created for
 *     CIS 131 Lab 8 - Inheritance and Polymorphism.
 * </p>
 * @author Houghton Mayfield
 * @since 11/15/2020
 */
public abstract class Publication {
    // --------- Fields ------------------------------------------------------------------------------------------------
    protected String title;
    protected String publisher;
    protected GregorianCalendar date;
    protected String subject;

    // --------- Getters -----------------------------------------------------------------------------------------------
    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    // --------- Setters -----------------------------------------------------------------------------------------------
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // --------- Methods -----------------------------------------------------------------------------------------------
    /** Method to convert the publication date, as a GregorianCalendar instance, to a readable String.
     *
     * @return A string containing the date of the publication.
     */
    public String getDateString() {
        SimpleDateFormat format = new SimpleDateFormat("MMMMMMMMMM dd, yyyy");
        format.setCalendar(date);

        return format.format(date.getTime());
    }

    public String toString() {
        return String.format("Title: %s\nPublisher: %s\nDate: %s\nSubject: %s",
                title, publisher, getDateString(), subject);
    }

    // --------- Constructors ------------------------------------------------------------------------------------------
    Publication(String title, String publisher, GregorianCalendar date, String subject) {
        this.title = title;
        this.publisher = publisher;
        this.date = date;
        this.subject = subject;
    }
}
