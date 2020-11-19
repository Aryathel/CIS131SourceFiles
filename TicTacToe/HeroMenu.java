package TicTacToe;

/**
 *  <h1>Hero's Menu Program</h1>
 *  <p>
 *      This program was created for the purpose of being able to dynamically create
 *      menus for user interaction.
 *  </p>
 *  @author Houghton Mayfield
 *  @version 2.0
 *  @since 10-09-2020
 */

public class HeroMenu {
    final int DEFAULT_LINE_SPACING = 60;
    final char HORIZONTAL = '-';
    final char VERTICAL = '|';
    final char TOP_LEFT = '+';
    final char TOP_RIGHT = '+';
    final char MID_LEFT = '+';
    final char MID_RIGHT = '+';
    final char BOTTOM_LEFT = '+';
    final char BOTTOM_RIGHT = '+';

    final String SELECTION_PROMPT = "Enter your selection: ";

    int menuLineSpacing; // Declare menu width class attribute
    String header;
    int[] optionNums = {};
    String[] options = {};

    //------------------------------------------------------------------------------------------------------------------
    /** The class constructor, which sets the menu width to the default line spacing.
     *
     */
    public HeroMenu() { menuLineSpacing = DEFAULT_LINE_SPACING; }

    //------------------------------------------------------------------------------------------------------------------
    /** The class constructor, which sets the menu width.
     *
     * @param menuWidth The width, in characters, of the menu.
     */
    public HeroMenu(int menuWidth) {
        menuLineSpacing = menuWidth - 2;
    }

    public HeroMenu(int menuWidth, String header) {
        menuLineSpacing = menuWidth - 2;
        this.header = header;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the header and options of a menu.
     * This is the automatic menu printing. For more advanced menus, use manual menu printing methods.
     *
     */
    public void display() {
        displayHeader();
        if (optionNums.length > 0) {
            displayOptions();
            displayBottom();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Shows the introduction header of a menu, and prints a menu divider to continue the menu
     *
     */
    public void displayHeader() {
        displayTop();
        displayCentered(header);
        displayDivider();
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Displays the first bar of the menu.
     *
     */
    public void displayTop() {
        System.out.print(TOP_LEFT);
        for (int i = 0; i < menuLineSpacing; i++) {
            System.out.print(HORIZONTAL);
        }
        System.out.println(TOP_RIGHT);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Displays a divider between menu blocks.
     *
     */
    public void displayDivider() {
        System.out.print(MID_LEFT);
        for (int i = 0; i < menuLineSpacing; i++) {
            System.out.print(HORIZONTAL);
        }
        System.out.println(MID_RIGHT);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Displays the last bar of the menu.
     *
     */
    public void displayBottom() {
        System.out.print(BOTTOM_LEFT);
        for (int i = 0; i < menuLineSpacing; i++) {
            System.out.print(HORIZONTAL);
        }
        System.out.println(BOTTOM_RIGHT);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Displays a menu line with the provided message centered on the line
     *
     * @param msg The message which will be centered on the line
     */
    public void displayCentered(String msg) {
        int msgLen = msg.length();
        int spacing = (menuLineSpacing - msgLen);
        if (spacing > 0) {
            if (spacing % 2 == 0) { // If the spacing is an even number
                System.out.printf("%c%" + (spacing / 2) + "c%s%" + (spacing / 2) + "c%c\n", VERTICAL, ' ', msg, ' ', VERTICAL);
            } else { // If the spacing is an odd number, add an extra space to fill the line
                System.out.printf("%c%" + (spacing / 2) + "c%s%" + (spacing / 2 + 1) + "c%c\n", VERTICAL, ' ', msg, ' ', VERTICAL);
            }
        } else {
            System.out.printf("%c%s%c\n", VERTICAL, msg, VERTICAL);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Displays the registered menu options.
     *
     */
    public void displayOptions() {
        int optionNum;
        String option;

        sortOptions(); // Sort the menu options to have the numbers in order.

        // Looping through all menu options
        for (int i = 0; i < options.length; i++) {
            optionNum = optionNums[i];
            option = options[i];

            // Calculating how to space out the menu options given the width of the menu.
            int numLen = Integer.toString(optionNum).length();
            if (numLen + option.length() + 12 >= menuLineSpacing) {
                System.out.printf("     %d: %s     \n", optionNum, option);
            } else {
                int extraChars = menuLineSpacing - (numLen + option.length() + 2);

                int sidePadding = 5;
                int centerPadding = extraChars - (sidePadding * 2);
                System.out.print(VERTICAL);
                System.out.printf("%" + (sidePadding + numLen) + "d: ", optionNum);
                option = String.format("%-" + (sidePadding + option.length()) + "s", option);
                option = String.format("%" + (centerPadding + option.length()) + "s", option);
                System.out.print(option);
                System.out.println(VERTICAL);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Sorts the registered menu options by their integer option number.
     *
     */
    private void sortOptions() {
        int tempInt;
        String tempString;
        for (int i = 0; i < optionNums.length; i++) { // Loop through the whole array
            for (int j = i + 1; j < optionNums.length; j++) { // Loop through every element after the i index
                // If the first number is greater than the second, swap them
                if (optionNums[i] > optionNums[j]) {
                    tempInt = optionNums[i];
                    tempString = options[i];

                    optionNums[i] = optionNums[j];
                    options[i] = options[j];

                    optionNums[j] = tempInt;
                    options[j] = tempString;
                }
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Sets the header text for the menu.
     *
     * @param header A String containing the header that is being set.
     */
    public void setHeader(String header) {
        this.header = header;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Adds an option to the array of registered options.
     *
     * @param optionNum An integer representing the number option to add to the array of options.
     * @param optionText A String representing the text of the option.
     */
    public void addOption(int optionNum, String optionText) {
        if (checkAvailableOption(optionNum)) {
            int n = optionNums.length;

            int[] numArr = new int[n + 1];
            String[] textArr = new String[n + 1];

            for (int i = 0; i < n; i++) {
                numArr[i] = optionNums[i];
                textArr[i] = options[i];
            }

            numArr[n] = optionNum;
            textArr[n] = optionText;

            optionNums = numArr;
            options = textArr;
        } else {
            System.err.printf("Error: Option %d has already been added to the menu.\n", optionNum);
        }

    }

    //------------------------------------------------------------------------------------------------------------------
    /** Edits the text of a previously registered option.
     *
     * @param optionNum An integer representing the number option to add to the array of options.
     * @param optionText A String representing the text of the option.
     */
    public void editOption(int optionNum, String optionText) {
        // Check all options, find the one being edited, and modify the option text.
        for (int i = 0; i < optionNums.length; i++) {
            if (optionNums[i] == optionNum) {
                options[i] = optionText;
                return;
            }
        }

        // If no options were found matching the option being edited, show an error.
        System.err.printf("The option %d was not found to edit.\n", optionNum);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the provided string parameters in balanced columns in the menu.
     *
     * @param columnOne The string being printed in the first column.
     * @param columnTwo The string being printed in the second column.
     */
    public void displayTwoColumn(String columnOne, String columnTwo) {
        // Find the respective lengths of the two input strings
        int lenOne = columnOne.length();
        int lenTwo = columnTwo.length();

        int colWidth = menuLineSpacing / 2; // The general column width for each column
        int colOne = colWidth - lenOne;            // Find the padding for the first column

        System.out.print(VERTICAL);
        if (colOne > 0) {                                                            // Only applying padding if the column width exceeds the string length
            columnOne = String.format("%" + (lenOne + colOne / 2) + "s", columnOne); // Apply the left padding
            columnOne = String.format("%-" + colWidth + "s", columnOne);             // Apply the remaining right padding
        }
        System.out.print(columnOne); // Print the first column

        int colTwo = colWidth - lenTwo;
        // If total menu width is odd, the column width for the second column needs to add 1
        if (this.menuLineSpacing % 2 != 0) {
            colWidth += 1;
            colTwo += 1;
        }

        if (colTwo > 0) {                                                            // Only applying padding if the column width exceeds the string length
            columnTwo = String.format("%" + (lenTwo + colTwo / 2) + "s", columnTwo); // Apply the left padding
            columnTwo = String.format("%-" + colWidth + "s", columnTwo);             // Apply the remaining right padding
        }
        System.out.print(columnTwo); // Print the second column
        System.out.println(VERTICAL);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a selection for the menu given a minimum and maximum value.
     *
     * @return An integer representing the menu selection.
     */
    public int getSelection() {
        int selection;

        System.out.print(SELECTION_PROMPT);
        selection = HeroIR.getInteger("");

        while (!checkValidSelection(selection)) {
            System.err.println("Error: Invalid Selection");
            System.out.print(SELECTION_PROMPT);
            selection = HeroIR.getInteger("");
        }

        System.out.println();
        return selection;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Checks to see if a given integer as an option is available in the current menu.
     *
     */
    private boolean checkAvailableOption(int option) {
        // Loop through all registered options, and if any match the option number, prevent adding it.
        for (int optionNum : optionNums) {
            if (optionNum == option) {
                return false;
            }
        }

        return true;
    }

    private boolean checkValidSelection(int selection) {
        for (int optionNum : optionNums) {
            if (selection == optionNum) {
                return true;
            }
        }

        return false;
    }
}
