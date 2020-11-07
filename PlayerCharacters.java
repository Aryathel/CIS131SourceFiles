/**
 *  <h1>Player Characters</h1>
 *  <p>
 *      This class is used for storing players in a two player game.
 *      Each player is represented by a single character, which can be changed.
 *  </p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 10-14-2020
 */
public class PlayerCharacters {
    private final int PLAYER_ONE = 1;
    private final int PLAYER_TWO = 2;
    private final int EXIT = 3;
    private final String PLAYER_OPTION = "Change Player %d: %c";
    private final String PLAYER_PROMPT = "Player %d, please enter a character: ";

    private char playerOne;
    private char playerTwo;

    // -------------------- Constructors --------------------
    /** Constructs the PlayerCharacters instance using default Player One and Payer Two characters.
     *
     */
    public PlayerCharacters() {
        playerOne = 'X';
        playerTwo = 'Y';
    }

    /** Constructs the PlayerCharacters instance using the given Player One and Player Two characters.
     *
     * @param playerOne A Character holding the character for Player One.
     * @param playerTwo A Character holding the character for Player Two.
     */
    public PlayerCharacters(char playerOne, char playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    // -------------------- Getters --------------------
    /** Gets the character of Player One.
     *
     * @return Player One's Character.
     */
    public char getPlayerOne() {
        return playerOne;
    }

    /** Gets the character of Player Two.
     *
     * @return Player Two's Character.
     */
    public char getPlayerTwo() {
        return playerTwo;
    }

    // -------------------- Setters --------------------
    /** Sets the character of Player One.
     *
     * @param player The Character to set Player One's character to.
     */
    public void setPlayerOne(char player) {
        this.playerOne = player;
    }

    /** Sets the character of Player Two.
     *
     * @param player The Character to set Player Two's character to.
     */
    public void setPlayerTwo(char player) {
        this.playerTwo = player;
    }

    // -------------------- Methods --------------------
    /** Shows a menu to get user input for changing characters.
     *
     * @param menuWidth An Integer containing the width, in characters, of the menu.
     */
    public void showPlayerCharacterMenu(int menuWidth) {
        HeroMenu menu = new HeroMenu(menuWidth);
        menu.setHeader("Change Player Characters");
        menu.addOption(PLAYER_ONE, String.format(PLAYER_OPTION, PLAYER_ONE, playerOne));
        menu.addOption(PLAYER_TWO, String.format(PLAYER_OPTION, PLAYER_TWO, playerTwo));
        menu.addOption(EXIT, "Back");

        menu.display();
        int selection = menu.getSelection();

        while (selection != EXIT) {
            switch (selection) {
                case PLAYER_ONE:
                    playerOne = getPlayerCharacter(PLAYER_ONE);
                    menu.editOption(PLAYER_ONE, String.format(PLAYER_OPTION, PLAYER_ONE, playerOne));
                    break;
                case PLAYER_TWO:
                    playerTwo = getPlayerCharacter(PLAYER_TWO);
                    menu.editOption(PLAYER_TWO, String.format(PLAYER_OPTION, PLAYER_TWO, playerTwo));
                    break;
            }

            menu.display();
            selection = menu.getSelection();
        }
    }

    /** Gets a new Character for a player.
     *
     * @param player An Integer representing the number of the player getting a new character (1 or 2).
     * @return The player's new Character.
     */
    private char getPlayerCharacter(int player) {
        char playerChar = HeroIR.getChar(String.format(PLAYER_PROMPT, player));

        while (player == PLAYER_TWO && Character.toLowerCase(playerChar) == Character.toLowerCase(playerOne) ||
                player == PLAYER_ONE && Character.toLowerCase(playerChar) == Character.toLowerCase(playerTwo)) {

            System.err.printf("The character %c already belongs to player %d.\n", playerChar, 2 / playerChar);
            playerChar = HeroIR.getChar(String.format(PLAYER_PROMPT, player));
        }

        return playerChar;
    }
}
