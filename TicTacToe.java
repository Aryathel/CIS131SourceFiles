/**
 *  <h1>Tic Tac Toe</h1>
 *  <p>
 *      This program is a functional two player game of tic tac toe.
 *  </p>
 *  @author Houghton Mayfield
 *  @version 1.3
 *  @since 10-14-2020
 */

public class TicTacToe {
    private final static int BEST_OF = 3;     // The best of ___ number. Three is the standard.

    private final static int PLAYER_ONE = 0;  // The value for player one winning a game/match.
    private final static int PLAYER_TWO = 1; // The value for player two winning a game/match.
    private final static int TIE = 2;         // The value for the players tying a game/match.
    private final static int CONTINUE = -1;   // The value for the active game continuing.

    // Main menu options.
    private final static int PRINT_RULES = 1;
    private final static int CHANGE_CHARACTERS = 2;
    private final static int CHANGE_BOARD = 3;
    private final static int CHANGE_GAME_TYPE = 4;
    private final static int PLAY_MATCH = 5;
    private final static int QUIT = 6;

    // The types of games possible.
    private final static int CLASSIC = 0;
    private final static int BLACKOUT = 1;
    private final static String[] GAME_TYPES = { "Classic Tic Tac Toe", "Tic-Toc-Tic-Tac-Toe Blackout" };

    // The scoring for the blackout game type, given a streak of length matching the index number
    private final static int[] BLACKOUT_SCORING = { 0, 0, 1, 3, 5, 10, 12, 15, 20, 25 };

    private final static int BOARD_SIZE_MIN = 3; // The minimum value for the board for a playable game
    private final static int BOARD_SIZE_MAX = 9; // The maximum value for the board for a playable game

    private static int gameType = CLASSIC;       // The type of game being played, an index of GAME_TYPES
    private static int boardX = 3;               // The default board horizontal size.
    private static int boardY = 3;               // The default board Vertical size.
    private static int[][] scoring;

    private final static int MENU_WIDTH = 72;    // The width of the main menu, in characters.

    /** The program entry point.
     *
     * @param args Not used
     */
    public static void main(String[] args) {
        // Defines the winner of the match
        int matchResult;

        // Define default player variables.
        PlayerCharacters playerChars = new PlayerCharacters('X', 'O');

        // Refer to HeroMenu.java for documentation on how this functions.
        HeroMenu menu = new HeroMenu(MENU_WIDTH);
        menu.addOption(1, "Rules");
        menu.addOption(2, "Change Player Characters");
        menu.addOption(3, "Change Board Size");
        menu.addOption(4, "Change the Game Type to Blackout");
        menu.addOption(5, "Start the Match");
        menu.addOption(6, "Quit");


        // Keep looping the user menu options until they play a match.
        int option;
        do {
            displayMainMenu(menu, playerChars);

            option = menu.getSelection();

            switch (option) {
                case PRINT_RULES:
                    printRules();
                    break;
                case CHANGE_CHARACTERS:
                    // Get players to input their characters
                    playerChars.showPlayerCharacterMenu(MENU_WIDTH);
                    break;
                case CHANGE_BOARD:
                    // Get the player to input a new board size
                    Coordinate2D size = getValidBoardSize("Please enter a new board size: (73 for 7x3) ");
                    boardX = size.X;
                    boardY = size.Y;
                    break;
                case CHANGE_GAME_TYPE:
                    // Since there are only two game types, just convert the game type to the other option.
                    gameType = (2 / (gameType + 1)) - 1;

                    // Edit the menu to show the updated game type.
                    if (gameType == CLASSIC) {
                        menu.editOption(4, "Change the Game Type to Blackout");
                    } else if (gameType == BLACKOUT) {
                        menu.editOption(4, "Change the Game Type to Classic");
                    }
                    break;
                case PLAY_MATCH:
                    // Play a match and get results
                    matchResult = playMatch(playerChars);
                    displayFinalResults(matchResult, playerChars);
                    break;
                case QUIT:
                    System.out.println("Thanks for playing!");
                    break;
            }
        } while (option != QUIT);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Displays the main menu of the game to the user.
     *
     * @param menu A HeroMenu instance used as the main menu in the game.
     * @param playerChars A PlayerCharacters instance containing the player characters.
     */
    public static void displayMainMenu(HeroMenu menu, PlayerCharacters playerChars) {
        menu.setHeader(GAME_TYPES[gameType]);
        menu.displayHeader();
        menu.displayTwoColumn("Player One Character: " + playerChars.getPlayerOne(), "Player Two Character: " + playerChars.getPlayerTwo());
        menu.displayDivider();
        menu.displayCentered(String.format("Board Size: %dx%d", boardX, boardY));
        menu.displayDivider();
        menu.displayOptions();
        menu.displayBottom();
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the introduction to a game, including the total number of games,
     * the number at which a player automatically wins, and the rules.
     */
    public static void printRules() {
        // Printing game rules
        HeroMenu rules = new HeroMenu(MENU_WIDTH);
        // Print the header based on game type
        rules.setHeader(String.format("%s Rules", GAME_TYPES[gameType]));
        rules.displayHeader();


        if (gameType == CLASSIC) { // Print the rules for a classic tic tac toe game or any board size
            rules.displayCentered(String.format("Welcome to the Amazing game of Best-of-%d Tic-Tac-Toe!", BEST_OF));
            rules.displayCentered("Each player will take turns putting a mark on the board.");
            rules.displayCentered("Players will enter row and column like this: 12 or 23");
            int min = Math.min(boardX, boardY);
            rules.displayCentered(String.format("A player will win when they get %d of their marks in a row.", min));
            rules.displayCentered(String.format("If the board is filled without %d in a row, the game is a tie.", min));
            rules.displayCentered("");
            rules.displayCentered(String.format("The first to %.1f points is the winner! Good luck!", BEST_OF * 0.5));
        } else if (gameType == BLACKOUT) { // Print the rules for a blackout tic tac toe game or any board size
            rules.displayCentered("Welcome to Tic-Toc-Tic-Tac-Toe Blackout!");
            rules.displayCentered("Players will continue making moves until the board is full.");
            rules.displayCentered("The goal is to earn more points than the other player.");
            rules.displayCentered("Points are earned by making streaks in rows, columns, or diagonally.");
            rules.displayDivider();
            String rowMsg = "%d in a row";
            String ptsMsg = "%d points";
            for (int i = Math.max(boardX, boardY); i >= 0; i--) {
                if (BLACKOUT_SCORING[i] > 0) {
                    rules.displayTwoColumn(String.format(rowMsg, i), String.format(ptsMsg, BLACKOUT_SCORING[i]));
                }
            }
        }
        rules.displayBottom();
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Handles the actual playing and score keeping of a match.
     *
     * @param playerChars An instance of a PlayerCharacters class used to store the players' characters.
     * @return The result of the match, 0 for player 1 wins, 1 for player two wins wins, 3 for tied match
     */
    public static int playMatch(PlayerCharacters playerChars) {
        // Define a game board using the set sizes
        char[][] board = new char[boardX][boardY];

        if (gameType == CLASSIC) {
            // Create player win counters
            double playerOneScore = 0, playerTwoScore = 0;
            // The winner of any given round
            int roundWinner;

            // Play games until a player hits the required number of wins,
            //  or until the maximum number of games is reached.
            for (int game = 1; game <= BEST_OF; game++) {
                System.out.println("+-----------+ Game " + game + " +-----------+"); // Print the game number

                roundWinner = playGame(board, playerChars); // Play the game

                displayBoard(board);

                // Handle the result from playing the game.
                if (roundWinner == PLAYER_ONE) {
                    playerOneScore += 1;
                    System.out.printf("%c wins game %d!\n", playerChars.getPlayerOne(), game);
                } else if (roundWinner == PLAYER_TWO) {
                    playerTwoScore += 1;
                    System.out.printf("%c wins game %d!\n", playerChars.getPlayerTwo(), game);
                } else {
                    playerOneScore += 0.5;
                    playerTwoScore += 0.5;
                    System.out.printf("Game %d ends in a tie!\n", game);
                }

                System.out.printf("The score is: %.1f to %.1f\n", playerOneScore, playerTwoScore);

                // Check to see if a player hit the number of game wins to win the best of match.
                if (playerOneScore > BEST_OF * 0.5 || playerTwoScore > BEST_OF * 0.5) {
                    break;
                }
             }

            // Return results of the match based on who won.
            if (playerOneScore > playerTwoScore) {
                return PLAYER_ONE;
            } else if (playerOneScore < playerTwoScore) {
                return PLAYER_TWO;
            } else {
                return TIE;
            }
        } else if (gameType == BLACKOUT) {
            System.out.println("*********************************");
            System.out.println("      Tic-Toc-Tic-Tac-Toe");
            System.out.println();
            for (int i = Math.max(boardX, boardY); i >= 0; i--) {
                if (BLACKOUT_SCORING[i] > 0) {
                    System.out.printf("     %d in a row = %2d points\n", i, BLACKOUT_SCORING[i]);
                }
            }
            System.out.println("*********************************\n");

            int result = playGame(board, playerChars);

            displayBoard(board);

            return result;
        }

        // This will never be reached currently, but the compiler does not know that.
        return TIE;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Plays out an actual game, including the process of getting user move input
     *  and populating the board for each game, and returns the winner of the game.
     *
     * @param board The game board being played on, represented as a two dimensional array.
     * @param playerChars An instance of a PlayerCharacters class used to store the players' characters.
     * @return The result of the game, 0 for X wins, 1 for O wins, 3 for tied match
     */
    public static int playGame(char[][] board, PlayerCharacters playerChars) {
        Coordinate2D move;
        int result; // Loop the game while this value is -1

        initializeBoard(board);

        do {
            // Player one's turn to make a move.
            displayBoard(board);

            // If the game is in normal mode, get player moves
            move = getPlayerMove(playerChars.getPlayerOne(), board);
            if ((move.X * 10) + move.Y == 9999) {
                result = fillBoard(playerChars, board);
            } else {
                board[move.X - 1][move.Y - 1] = playerChars.getPlayerOne();

                // Check if the user has won
                result = checkForWin(playerChars, board);

                // If they have won, return
                if (result == PLAYER_ONE) {
                    return PLAYER_ONE;
                }
            }

            if (result == -1) {
                // Player two's turn to make a move.
                displayBoard(board);

                move = getPlayerMove(playerChars.getPlayerTwo(), board);

                if ((move.X * 10) + move.Y == 9999) {
                    result = fillBoard(playerChars, board);
                } else {
                    board[move.X - 1][move.Y - 1] = playerChars.getPlayerTwo();

                    // Check if the user has won
                    result = checkForWin(playerChars, board);
                    // If they have won, return. This doesn't make sense in terms of constants,
                    // but is used because the determine winner function needs to be generic
                    if (result == PLAYER_TWO) {
                        return PLAYER_TWO;
                    }
                }
            }
        } while (result == CONTINUE);

        return result; // If no winner has been decided and the board is full, tie game
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Determine if a player has won by checking every possible win case for a 3x3 board.
     *
     * @param playerChars The character of the player who is making the move.
     * @param board The current state of the game board.
     * @return An integer representing the board state: -1 = in progress, 0 = the player has won, 2 = tied.
     */
    public static int checkForWin(PlayerCharacters playerChars, char[][] board) {
        int p1Streaks, p2Streaks;
        if (gameType == CLASSIC) {
            // Checking for the lesser board size value in a row.
            p1Streaks = getStreaks(playerChars.getPlayerOne(), board,  Math.min(boardX, boardY));
            p2Streaks = getStreaks(playerChars.getPlayerTwo(), board,  Math.min(boardX, boardY));

            if (p1Streaks > 0) {
                // If a streak of desired length was found for player one
                return PLAYER_ONE;
            } else if (p2Streaks > 0) {
                // If a streak of desired length was found for player two
                return PLAYER_TWO;
            } else if (boardIsFull(board)) {
                return TIE;
            } else {
                // If no end condition detected, continue the game
                return CONTINUE;
            }
        } else if (gameType == BLACKOUT) {
            if (boardIsFull(board)) {
                scoring = new int[2][Math.max(boardX, boardY) + 1];

                int p1Total = 0, p2Total = 0;

                for (int i = 0; i <= Math.max(boardX, boardY); i++) {
                    if (BLACKOUT_SCORING[i] > 0) {
                        scoring[PLAYER_ONE][i] = getStreaks(playerChars.getPlayerOne(), board, i);
                        p1Total += scoring[PLAYER_ONE][i] * BLACKOUT_SCORING[i];
                        scoring[PLAYER_TWO][i] = getStreaks(playerChars.getPlayerTwo(), board, i);
                        p2Total += scoring[PLAYER_TWO][i] * BLACKOUT_SCORING[i];
                    }
                }

                if (p1Total > p2Total) {
                    // If a streak of desired length was found for player one
                    return PLAYER_ONE;
                } else if (p2Total > p1Total) {
                    // If a streak of desired length was found for player two
                    return PLAYER_TWO;
                } else {
                    return TIE;
                }
            }
        }

        return CONTINUE;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Calculates and displays all of the streaks that users have.
     *
     * @param scoring A 2d array containing the scores for both players.
     * @return An integer representing the board winner: 0 = player one has won, 1 = player two has won, 2 = tied.
     */
    public static void displayStreaks(PlayerCharacters playerChars, int[][] scoring) {
        int playerOneTotal = 0, playerTwoTotal = 0;

        // Print out the players and scoring header
        System.out.printf("                   Player %c     Player %c\n",
                playerChars.getPlayerOne(),
                playerChars.getPlayerTwo()
        );
        System.out.println("                   Nbr  Pts      Nbr  Pts");

        // Print out the streaks for every scoring streak length
        for (int i = 0; i <= Math.max(boardX, boardY); i++) {
            if (BLACKOUT_SCORING[i] > 0) {
                playerOneTotal += scoring[PLAYER_ONE][i] * BLACKOUT_SCORING[i];
                playerTwoTotal += scoring[PLAYER_TWO][i] * BLACKOUT_SCORING[i];

                System.out.printf(
                        "%d in a row (x%-2d):  %2d   %2d      %2d   %2d\n",
                        i, BLACKOUT_SCORING[i],
                        scoring[PLAYER_ONE][i], scoring[PLAYER_ONE][i] * BLACKOUT_SCORING[i],
                        scoring[PLAYER_TWO][i], scoring[PLAYER_TWO][i] * BLACKOUT_SCORING[i]
                );
            }
        }
        // Print the totals
        System.out.println("                       ----           ----");
        System.out.printf("     Total:            %3d            %3d\n\n", playerOneTotal, playerTwoTotal);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets the number of streaks of a certain length for a player on the game board.
     *
     * @param playerChar The character representing the player to check the streaks of.
     * @param board The current state of the game board. Is filled by this point in time.
     * @param streakLength The length of the streaks to count.
     * @return The number of streaks found of the given length, for the given character, on the given board.
     */
    public static int getStreaks(char playerChar, char[][] board, int streakLength) {
        int length, counter = 0;
        // Loop through all dimensions of the game board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // Only check for streaks if the character started on is the player character
                if (board[i][j] == playerChar) {
                    // Vertical downwards search
                    length = 1;
                    // Make sure this is the first character in the streak
                    if (j - 1 < 0 || board[i][j - 1] != playerChar) {
                        // Continue following the streak until it ends, counting the length
                        while (j + length < board[0].length && board[i][j + length] == playerChar) {
                            length++;
                        }
                        // Add to the counter if the streak length matches the one being looked for.
                        if (length == streakLength) {
                            counter++;
                        }
                    }

                    // Horizontal right search
                    length = 1;
                    // Make sure this is the first character in the streak
                    if (i - 1 < 0 || board[i - 1][j] != playerChar) {
                        // Follow the streak until it sends, counting the length
                        while (i + length < board.length && board[i + length][j] == playerChar) {
                            length++;
                        }
                        // Add to the counter if the streak length matches the one being looked for.
                        if (length == streakLength) {
                            counter++;
                        }
                    }

                    // Diagonal right and down search
                    length = 1;
                    // Make sure this is the first character in the streak
                    if ((i - 1 < 0 || j - 1 < 0) || board[i - 1][j - 1] != playerChar) {
                        // Follow the streak until it sends, counting the length
                        while (i + length < board.length &&
                                j + length < board[0].length &&
                                board[i + length][j + length] == playerChar) {
                            length++;
                        }
                        // Add to the counter if the streak length matches the one being looked for.
                        if (length == streakLength) {
                            counter++;
                        }
                    }

                    // Diagonal left and down search
                    length = 1;
                    // Make sure this is the first character in the streak
                    if ((i - 1 < 0 || j + 1 >= board[0].length) || board[i - 1][j + 1] != playerChar) {
                        // Follow the streak until it sends, counting the length
                        while (i + length < board.length &&
                                j - length >= 0 &&
                                board[i + length][j - length] == playerChar) {
                            length++;
                        }
                        // Add to the counter if the streak length matches the one being looked for.
                        if (length == streakLength) {
                            counter++;
                        }
                    }
                }
            }
        }

        return counter;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Determines if the provided game board has all spaces filled.
     *
     * @param board The current state of the game board.
     * @return A boolean representing whether the game board has all spaces filled.
     */
    public static boolean boardIsFull(char[][] board) {
        int i, j;
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a coordinate that the player is making a placement at, as a 2d coordinate.
     *
     * @param player The character of which player is making the move.
     * @param board The current state of the game board.
     * @return The coordinate of the move after is has been validated.
     */
    private static Coordinate2D getPlayerMove(char player, char[][] board) {
        Coordinate2D move = HeroIR.get2DCoordinate("What is your move, " + player + "? ");

        while (!isValidMove(move, board)) {
            move = HeroIR.get2DCoordinate("What is your move, " + player + "? ");
        }

        return move;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Determine whether a coordinate is a valid move by the user.
     *
     * @param move The attempted move by the user, as a 2D Coordinate.
     * @param board The current state of the game board.
     * @return Whether the move is a valid placement on the board.
     */
    public static boolean isValidMove(Coordinate2D move, char[][] board) {
        // Makes sure that both the X and Y of the move are within the board limits.
        if ((move.X * 10) + move.Y == 9999) {
            System.out.println("Filling board...");
            return true;
        } else if (move.X < 1 || move.X > boardX) {
            System.out.printf("That move is invalid! Column values must be in the range 1 to %d.\n", boardX);
            return false;
        } else if (move.Y < 1 || move.Y > boardY) {
            System.out.printf("That move is invalid! Row values must be in the range 1 to %d.\n", boardY);
            return false;
        } else if (board[move.X - 1][move.Y - 1] != ' ') {
            System.out.println("That space is already taken!");
            return false;
        }

        return true;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Initializes a board to have all space characters in place of null, for the sake is displaying nicely.
     *
     * @param board A 2 dimensional array holding the Tic Tac Toe game board.
     */
    public static void initializeBoard(char[][] board) {
        int i, j;

        // Assign the board to blank characters
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Print out a Tic Tac Toe board with the user moves in their places.
     *
     * @param board The 2 dimensional array containing the game board.
     */
    public static void displayBoard(char[][] board) {
        int i, j;

        // Print top legend
        System.out.print("    ");
        for (i = 1; i <= board.length; i++) {
            System.out.printf("%d   ", i);
        }
        // Insert newline then print top divider
        System.out.println();
        displayBoardDivider(board);

        // Print out the board, along with left legend
        for (i = 0; i < board[0].length; i++) {
            System.out.print((i + 1) + " ");
            for (j = 0; j < board.length; j++) {
                System.out.print("| " + board[j][i] + " ");
            }
            System.out.println("|");
            displayBoardDivider(board);
        }
        //some extra formatting
        System.out.println();
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints a horizontal divider according to the number of columns on a given game board.
     *
     * @param board The 2 dimensional array containing the game board.
     */
    public static void displayBoardDivider(char[][] board) {
        String divider = "  +";
        for (int i = 0; i < board.length; i++) {
            divider = divider.concat("---+");
        }
        System.out.println(divider);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a new coordinate that represents the size of the game board.
     *
     * @param msg The question asked to the user when getting the board size.
     * @return A 2D coordinate representing the move being made on the board.
     */
    public static Coordinate2D getValidBoardSize(String msg) {
        Coordinate2D size = HeroIR.get2DCoordinate(msg);

        while (isInvalidBoardSize(size)) {
            size = HeroIR.get2DCoordinate(msg);
        }

        return size;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Checks to see if a game board size input by the user is invalid.
     *
     * @param size A 2D Coordinate representing the size of the game board that the user is trying to create.
     * @return A boolean representing whether the move is invalid.
     */
    public static boolean isInvalidBoardSize(Coordinate2D size) {
        if (size.X < BOARD_SIZE_MIN || size.Y < BOARD_SIZE_MIN) {
            System.err.printf("Error: The board limits must be at least %d.\n", BOARD_SIZE_MIN);
            return true;
        } else if (size.X > BOARD_SIZE_MAX || size.Y > BOARD_SIZE_MAX) {
            System.err.printf("Error: The board limits cannot exceed %d.\n", BOARD_SIZE_MAX);
            return true;
        }

        return false;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the final results of a match that has been played.
     *
     * @param matchResult The result of the match, 0 for X wins, 1 for O wins, 3 for tied match
     * @param playerChars An instance of a PlayerCharacters class used to store the players' characters.
     */
    public static void displayFinalResults(int matchResult, PlayerCharacters playerChars) {
        if (gameType == BLACKOUT) {
            displayStreaks(playerChars, scoring);
        }

        if (matchResult == PLAYER_ONE) {
            System.out.printf("*******************\n%c wins the match!\n*******************\n\n", playerChars.getPlayerOne());
        } else if (matchResult == PLAYER_TWO) {
            System.out.printf("*******************\n%c wins the match!\n*******************\n\n", playerChars.getPlayerTwo());
        } else if (matchResult == TIE) {
            System.out.println("*******************\nThe match ended in a tie!\n*******************\n\n");
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** This function will fill a given board up with randomized moves.
     *
     * @param board The current game board.
     */
    private static int fillBoard(PlayerCharacters playerChars, char[][] board) {
        Coordinate2D move;
        int result;
        do {
            // Get a random move for player one
            move = getRandomValidMove(board);
            board[move.X - 1][move.Y - 1] = playerChars.getPlayerOne();

            // Check if the user has won
            result = checkForWin(playerChars, board);

            // If they have won, return
            if (result == PLAYER_ONE) {
                return PLAYER_ONE;
            }

            if (result == -1) {
                // Get a random move fo rplayer two
                move = getRandomValidMove(board);

                board[move.X - 1][move.Y - 1] = playerChars.getPlayerTwo();

                // Check if the user has won
                result = checkForWin(playerChars, board);
                // If they have won, return
                if (result == PLAYER_TWO) {
                    return PLAYER_TWO;
                }
            }
        } while (result == CONTINUE);

        return result;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** This function is used to get a random valid move, and is only used in testing.
     *
     * @param board The current game board.
     * @return A 2D coordinate representing a valid move.
     */
    private static Coordinate2D getRandomValidMove(char[][] board) {
        Coordinate2D move = new Coordinate2D(
                HeroIR.getRandomNumber(1, boardX),
                HeroIR.getRandomNumber(1, boardY)
        );

        while (!isValidTestMove(move, board)) {
            move = new Coordinate2D(
                    HeroIR.getRandomNumber(1, boardX),
                    HeroIR.getRandomNumber(1, boardY)
            );
        }

        return move;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Verifies the availability of a move during the execution of the getRandomValidMove function.
     * This function is the same as isValidMove() without the print statements.
     *
     * @param move The attempted move being made by the computer.
     * @param board The current game board.
     * @return A 2D coordinate representing a valid move.
     */
    private static boolean isValidTestMove(Coordinate2D move, char[][] board) {
        // Makes sure that both the X and Y of the move are within the limits of the board
        if (move.X < 1 || move.X > boardX) {
            return false;
        } else if (move.Y < 1 || move.Y > boardY) {
            return false;
        } else { // Returning whether the selected space is free
            return board[move.X - 1][move.Y - 1] == ' ';
        }
    }
}
