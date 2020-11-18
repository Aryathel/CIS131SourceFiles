import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <h1>Book</h1>
 * <p>
 *     A class representing a Book, extending a Publication, created for
 *     CIS 131 Lab 8 - Inheritance and Polymorphism.
 * </p>
 * @author Houghton Mayfield
 * @since 11/15/2020
 * @version 0.0.1
 */
public class Book extends Publication {
    // --------- Fields ------------------------------------------------------------------------------------------------
    protected String ISBN;
    protected int numPages;
    protected int libOfCongressNum;
    protected int copyYear;
    protected String author;
    protected String edition;

    // --------- Getters -----------------------------------------------------------------------------------------------
    public String getISBN() {
        return ISBN;
    }

    public int getNumPages() {
        return numPages;
    }

    public int getLibOfCongressNum() {
        return libOfCongressNum;
    }

    public int getCopyYear() {
        return copyYear;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }

    // --------- Setters -----------------------------------------------------------------------------------------------
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public void setLibOfCongressNum(int libOfCongressNum) {
        this.libOfCongressNum = libOfCongressNum;
    }

    public void setCopyYear(int copyYear) {
        this.copyYear = copyYear;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    // --------- Methods -----------------------------------------------------------------------------------------------
    public String toString() {
        return String.format("%s\nAuthor: %s\nEdition: %s\nISBN: %s\nNumber of Pages: %d\n" +
                "Library of Congress Number: %d\nCopyright Year: %d",
                super.toString(), author, edition, ISBN, numPages, libOfCongressNum, copyYear);
    }

    // --------- Constructors ------------------------------------------------------------------------------------------
    Book(String title, String publisher, GregorianCalendar date, String subject, String ISBN, int numPages,
         int libOfCongressNum, int copyYear, String author, String edition) {
        super(title, publisher, date, subject);

        this.ISBN = ISBN;
        this.numPages = numPages;
        this.libOfCongressNum = libOfCongressNum;
        this.copyYear = copyYear;
        this.author = author;
        this.edition = edition;
    }
}
