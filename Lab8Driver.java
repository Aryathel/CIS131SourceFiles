import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <h1>Lab 8 - Inheritance and Polymorphism</h1>
 * <p>
 *     This is the 8th CIS131 lab regarding class inheritance and polymorphism.
 *     This sample uss the classes for publications to demonstrate knowledge of the subject.
 * </p>
 * @author Houghton Mayfield
 * @since 11/15/2020
 * @version 0.0.1
 */

public class Lab8Driver {
    public static void main(String[] args) {
        ArrayList<Publication> publications = new ArrayList<>();

        addPublications(publications);

        for (Publication item : publications) {
            System.out.println(item.toString());
            System.out.println();
        }
    }

    public static void addPublications(ArrayList<Publication> publications) {
        publications.add(new Book(
                "The Way of Kings",
                "Tor Fantasy",
                new GregorianCalendar(2010, Calendar.AUGUST, 31),
                "Fantasy Fiction",
                "978-0-7653-6527-9",
                1258,
                2010034369,
                2010,
                "Sanderson, Brandon",
                "1st"
        ));

        publications.add(new Book(
                "Words of Radiance",
                "Tor Fantasy",
                new GregorianCalendar(2014, Calendar.MARCH, 4),
                "Fantasy Fiction",
                "978-0-7653-6528-6",
                1309,
                2014008908,
                2014,
                "Sanderson, Brandon",
                "1st"
        ));

        publications.add(new Book(
                "Oathbringer",
                "Tor Fantasy",
                new GregorianCalendar(2017, Calendar.NOVEMBER, 14),
                "Fantasy Fiction",
                "978-0-7653-6529-3",
                1258,
                2017478684,
                2017,
                "Sanderson, Brandon",
                "1st"
        ));

        publications.add(new Magazine(
                "Example Magazine 1",
                "Hero Publishing, LLC.",
                new GregorianCalendar(2018, Calendar.JUNE, 12),
                "News",
                "bi-weekly",
                "Heroicos HM",
                14.99
        ));

        publications.add(new Magazine(
                "Example Magazine 2",
                "Hero Publishing, LLC.",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                "News",
                "monthly",
                "Heroicos HM",
                14.99
        ));

        publications.add(new Magazine(
                "Example Magazine 3",
                "Hero Publishing, LLC.",
                new GregorianCalendar(2020, Calendar.AUGUST, 3),
                "News",
                "annually",
                "Heroicos HM",
                14.99
        ));

        publications.add(new Newsletter(
                "School Newsletter - September",
                "Brophy College Preparatory",
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 1),
                "Information",
                "monthly",
                "Brophy Prep Publishing",
                "Brophy College Preparatory"
        ));

        publications.add(new Newsletter(
                "School Newsletter - October",
                "Brophy College Preparatory",
                new GregorianCalendar(2020, Calendar.OCTOBER, 1),
                "Information",
                "monthly",
                "Brophy Prep Publishing",
                "Brophy College Preparatory"
        ));

        publications.add(new Newsletter(
                "School Newsletter - November",
                "Brophy College Preparatory",
                new GregorianCalendar(2020, Calendar.NOVEMBER, 1),
                "Information",
                "monthly",
                "Brophy Prep Publishing",
                "Brophy College Preparatory"
        ));
    }
}
