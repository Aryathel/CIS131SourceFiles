/**
 *  <h1>Houghton Mayfield Lab 2 - Rock, Paper, Scissors, Lizard, Spock</h1>
 *  <p>
 *      This program is a game akin to rock, paper, scissors, but with a bit more complexity.
 *      The player will enter their name and play against the computer in a format of their choosing.
 *  </p>
 *  @author Houghton Mayfield
 *  @version 1.0
 *  @since 2020-09-25
 */

public class Lab2RPSLS {
    // Strings for printing
    private final static String[] INTRODUCTION = {
            "Welcome to the Wonderful Game of ROCK, PAPER, SCISSORS, LIZARD, SPOCK!",
            "Welcome to ROCK, PAPER, SCISSORS, LIZARD, SPOCK! Prepare to get demolished!"
    };
    private final static String[] GOODBYE = {
            "Come back for a rematch next time, %s!\nI will beat you next time.",
            "You can face me again anytime, %s.\nThanks for playing.",
            "%s, you were a formidable opponent.\nGood games."
    };
    private final static String LINEBREAKCHAR = "*";
    private final static String MATCHSTARTCHAR = "-";
    private final static int LINEBREAK_LENGTH = 75;

    // The maximum number of games to be played in a match
    private final static int MAX_GAME_NUMBER = 9;

    // Constants for game & match results
    private final static int PLAYER_WINS = 0;
    private final static int COMPUTER_WINS = 1;
    private final static int TIE = 2;

    // The constants for all questions asked to the user
    private final static String[] USERNAME_QUESTION = {
            "What is your name?",
            "What do you want your username to be?",
            "Enter your player name:"
    };
    private final static String[] NUM_GAMES_QUESTION = {
            "How many games do you want to play this match, %s? ",
            "%s, how many games should we play the best of? "
    };

    // All possible items in the game
    public final static String[] ITEMS = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};

    // The list of interactions for all items, in their number format:
    /// 0 = Rock
    /// 1 = Paper
    /// 2 = Scissors
    /// 3 = Lizard
    /// 4 = Spock
    private final static int[][] INTERACTIONS = {
            { 0, 3 },
            { 0, 2 },
            { 1, 0 },
            { 1, 4 },
            { 2, 1 },
            { 2, 3 },
            { 3, 4 },
            { 3, 1 },
            { 4, 2 },
            { 4, 0 }
    };

    // The strings for all game results
    private final static String[][] RESPONSES = {
            {
                "Rock crushes Lizard.", "Rock smashes Lizard."
            },
            {
                "Rock crushes Scissors.", "Rock breaks Scissors."
            },
            {
                "Paper covers Rock.", "Paper envelops Rock."
            },
            {
                "Paper disproves Spock.", "Paper cuts Spock."
            },
            {
                "Scissors cuts Paper."
            },
            {
                "Scissors decapitates Lizard.", "Scissors takes Lizard's tail.", "Scissors are immune to Lizard's poison."
            },
            {
                "Lizard poisons Spock.", "Spock is afraid of Lizards."
            },
            {
                "Lizard eats Paper.", "Paper cuts Lizard."
            },
            {
                "Spock smashes Scissors.", "Spock knows how to use Scissors safely.", "Spock does not run with Scissors."
            },
            {
                "Spock vaporizes Rock.", "It's a Rock. Spock doesn't even care about it."
            }
    };
    private final static String[] TIE_RESPONSES = {
            "A tie means we have to replay this game.",
            "We tied, so we need to play that game again.",
            "Since we tied, we need to play again. I will pick Spock this time."
    };

    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        // Initializing control variables
        String playerName;
        int bestOf;
        int playerScore = 0, computerScore = 0;
        int matchResult;

        // Printing the introduction and getting starting player input
        printIntroduction();
        playerName = HeroIR.getString(USERNAME_QUESTION[HeroIR.getRandomNumber(0, USERNAME_QUESTION.length - 1)]);
        printRules();
        bestOf = getBestOf(playerName);

        // The standard sentinel match loop, continues until 0 is entered for the number of games to play.
        while (bestOf != 0) {
            // Print a response to the number of games to be played.
            printBestOfReaction(bestOf);

            // Play the actual match
            matchResult = playMatch(playerName, bestOf);

            // Increment player scores based on the match result, and print the final match result.
            if (matchResult == PLAYER_WINS) {
                System.out.printf("\nYou won this match, %s. Well done!\n\n", playerName);
                playerScore++;
            } else if (matchResult == COMPUTER_WINS) {
                System.out.printf("\nLooks like I win this one. Good match, %s!\n\n", playerName);
                computerScore++;
            } else if (matchResult == TIE) {
                System.out.println(TIE_RESPONSES[HeroIR.getRandomNumber(0, TIE_RESPONSES.length - 1)]);
            }

            // Print the current overall session standings
            printSessionStandings(playerName, playerScore, computerScore);

            // Print the rules again and get the user input for the number of games in the next match
            printRules();
            bestOf = getBestOf(playerName);
        }

        // Print the final standings of the session once the user quits.
        printSessionResults(playerName, playerScore, computerScore);

        // Print a farewell message to the player
        printGoodBye(playerName);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the introduction to the game
     *
     */
    private static void printIntroduction() {
        // Print a random introduction from the list of possible introductions
        printLinebreak(LINEBREAKCHAR);
        System.out.println(INTRODUCTION[HeroIR.getRandomNumber(0, INTRODUCTION.length - 1)]);
        printLinebreak(LINEBREAKCHAR);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the rules of the game.
     *
     */
    private static void printRules() {
        // Print out the rules of the game
        printLinebreak(LINEBREAKCHAR);
        System.out.println("Rules of the game:");
        System.out.println("      Rock crushes Lizard and Scissors.");
        System.out.println("      Paper covers Rock and disproves Spock.");
        System.out.println("      Scissors cuts paper and decapitates Lizard.");
        System.out.println("      Lizard poisons Spock and eats Paper.");
        System.out.println("      Spock smashes Scissors and vaporizes Rock.");
        System.out.println("Players cannot pick the same weapons twice in a row.");
        printLinebreak(LINEBREAKCHAR);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints out a line of the given character that is the LINEBREAK_LENGTH number of characters long.
     *
     * @param character - The character to be used when printing the line.
     */
    private static void printLinebreak(String character) {
        // Print the given character repeated a number of times in a row
        String output = String.format("%0" + LINEBREAK_LENGTH  + "d", 0).replace("0", character);
        System.out.println(output);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the options for the user to select from for the number of games to be played in a match.
     * This uses the MAX_GAME_NUMBER constant and prints out all odd numbers between 1 and that number, inclusive.
     *
     */
    private static void printBestOfOptions() {
        System.out.print("(");
        // Loop from 1 to the maximum number of games
        for (int i = 1; i <= MAX_GAME_NUMBER; i++) {
            // For each odd number found in the loop
            if (i % 2 == 1) {
                // Print the number
                System.out.printf("%d", i);

                // If the number is not the last odd number being found, print the delimiter
                if (i <= MAX_GAME_NUMBER - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints out a response to the number of games to be played in a match.
     *
     * @param bestOf - The number of games in the given match to be played.
     */
    private static void printBestOfReaction(int bestOf) {
        // Declaring available responses
        String[] bestOf1Reactions = {
                "All or nothing. I like it. Good luck!",
                "Alright, let's go all in. May the best player win."
        };
        String[] bestOfOtherReactions = {
                "This match will be a best of %d, good luck!\n",
                "The best of %d matches will win. I don't expect to lose.\n"
        };

        // Print responses based on the number of matches to be played
        if (bestOf == 1) {
            System.out.println(bestOf1Reactions[HeroIR.getRandomNumber(0, bestOf1Reactions.length - 1)]);
        } else {
            System.out.printf(bestOfOtherReactions[HeroIR.getRandomNumber(0, bestOfOtherReactions.length - 1)], bestOf);
        }

        if (bestOf != 0) {
            printLinebreak(MATCHSTARTCHAR);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the current standings of the match based on the given player scores.
     *
     * @param playerName - The username the player entered at the beginning of the program execution.
     * @param playerScore - The final number of matches that the player won in the program execution.
     * @param computerScore - The final number of matches that the computer won in the program execution.
     */
    private static void printSessionStandings(String playerName, int playerScore, int computerScore) {
        // Choose the response based on the score
        printLinebreak(LINEBREAKCHAR);
        if (playerScore > computerScore) {
            System.out.printf("%s is leading the session %d-%d.\n", playerName, playerScore, computerScore);
        } else if (computerScore > playerScore) {
            System.out.printf("I am leading the session %d-%d.\n", computerScore, playerScore);
        } else {
            System.out.printf("The session is tied %d-%d.\n", computerScore, playerScore);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints the results of all of the matches played in the session.
     *
     * @param playerName - The username the player entered at the beginning of the program execution.
     * @param playerScore - The final number of matches that the player won in the program execution.
     * @param computerScore - The final number of matches that the computer won in the program execution.
     */
    private static void printSessionResults(String playerName, int playerScore, int computerScore) {
        System.out.println();
        printLinebreak(LINEBREAKCHAR);
        // Print different responses based on the scores given.
        if (playerScore > computerScore) {
            System.out.printf("%s won the matches %d-%d this session.\n", playerName, playerScore, computerScore);
        } else if (computerScore > playerScore) {
            System.out.printf("I win the matches %d-%d this session.\n", computerScore, playerScore);
        } else {
            System.out.printf("Looks like we tie these matches %d-%d.\n", playerScore, computerScore);
        }
        printLinebreak(LINEBREAKCHAR);
        System.out.println();
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints a random goodbye statement from the list of good byes in the constant GOODBYE.
     *
     * @param playerName - The username the player entered at the beginning of the program execution.
     */
    private static void printGoodBye(String playerName) {
        // Prints a random goodbye message from the list in the GOODBYE constant
        System.out.printf(GOODBYE[HeroIR.getRandomNumber(0, GOODBYE.length - 1)], playerName);
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Asks the user for how many games to be played in a match. Best of 3, 5, 7, etc.
     * The maximum possible is determined by the MAX_GAME_NUMBER constant.
     *
     * @param username - The username the player entered at the beginning of the program execution.
     * @return - An integer representing the number of games to be played in the match.
     */
    private static int getBestOf(String username) {
        // Print a random question about the number of games from the list in the constants
        System.out.printf(NUM_GAMES_QUESTION[HeroIR.getRandomNumber(0, NUM_GAMES_QUESTION.length - 1)], username);

        // Prints the number options for number of games to be played
        printBestOfOptions();

        // Get the user input on number of games to play and validate it
        int bestOf = HeroIR.getIntegerInRange("Enter 0 to quit.", 0, MAX_GAME_NUMBER);

        while (bestOf != 0 && bestOf % 2 != 1) {
            System.err.println("Error: The integer entered must be an odd number.");
            System.out.printf(NUM_GAMES_QUESTION[HeroIR.getRandomNumber(0, NUM_GAMES_QUESTION.length - 1)], username);
            bestOf = HeroIR.getIntegerInRange("Enter 0 to quit.", 0, MAX_GAME_NUMBER);
        }

        return bestOf;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** The function controlling the playing of a match and the game loop.
     *
     * @param playerName - The username the player entered at the beginning of the program execution.
     * @param bestOf - The number of games to be played in a match.
     * @return - An integer representing who won the match.
     */
    private static int playMatch(String playerName, int bestOf) {
        // Initialize the player and computer moves having all of them available.
        boolean[] playerMoves = { true, true, true, true, true };
        boolean[] computerMoves = { true, true, true, true, true };
        // Initialize player scores
        int playerMatchScore = 0, computerMatchScore = 0;
        int gameResult;

        // This loop limits the number of games to be played
        for (int game = 1; game <= bestOf; game++) {
            // Print the game header
            System.out.printf("\nGame #%d:\n", game);

            // Play the actual game.
            gameResult = playGame(playerName, playerMoves, computerMoves);

            // Based on the game result, increment the player's or computer's score and print the score.
            if (gameResult == PLAYER_WINS) {
                playerMatchScore++;
                System.out.printf("The score is %d-%d.\n", playerMatchScore, computerMatchScore);
            } else if (gameResult == COMPUTER_WINS) {
                computerMatchScore++;
                System.out.printf("The score is %d-%d.\n", playerMatchScore, computerMatchScore);
            } else if (gameResult == TIE) {
                // If the game is tied, the game is not counted towards the total number of games,
                // so decrement the game number.
                game--;
                System.out.printf("The score remains %d-%d.\n", playerMatchScore, computerMatchScore);
            }

            // Checks for if either the player of the computer has reached the threshold for having the best of
            // the number of games, and breaks the game loop if so.
            if (playerMatchScore >= (bestOf / 2) + 1 || computerMatchScore >= (bestOf / 2) + 1) {
                break;
            }
        }

        // After the games are completed, return the winner
        if (playerMatchScore > computerMatchScore) {
            return PLAYER_WINS;
        } else if (computerMatchScore > playerMatchScore) {
            return COMPUTER_WINS;
        } else {
            return TIE;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Plays an individual game.
     *
     * @param playerName - The username the player entered at the beginning of the program execution.
     * @param playerMoves - A boolean array dictating which moves are and are not available to the player.
     * @param computerMoves - A boolean array dictating which moves are and are not available to the computer.
     * @return - An integer representing the game result.
     */
    private static int playGame(String playerName, boolean[] playerMoves, boolean[] computerMoves) {
        // Check to make sure that the player and computer both have at least one move available,
        // and resets the move if not
        checkMoveAvailability(playerMoves);
        checkMoveAvailability(computerMoves);

        // Get the move for the player and computer
        int playerMove = getValidMove(playerName, playerMoves);
        int computerMove = getComputerMove(computerMoves);

        // Print out the choices of the player and computer
        System.out.printf("You chose %s. I chose %s.\n", ITEMS[playerMove], ITEMS[computerMove]);

        // Get the result of who won the game.
        int result = determineGameWinner(playerMove, computerMove);

        // If the round was not a tie, mark that item as used to prevent it from being used again
        if (result != TIE) {
            playerMoves[playerMove] = false;
            computerMoves[computerMove] = false;
        }

        // Return the game result
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Decides which player won a match, prints out a statement based on that, and returns the value of the winner.
     *
     * @param playerMove - An integer representing the move that the player made.
     * @param computerMove - An integer representing the move that the computer is making.
     * @return - An integer representing who won the game.
     */
    private static int determineGameWinner(int playerMove, int computerMove) {
        // Loop through all possible winning scenarios.
        for (int i = 0; i < INTERACTIONS.length; i++) {
            // If the player wins
            if (INTERACTIONS[i][0] == playerMove && INTERACTIONS[i][1] == computerMove) {
                System.out.printf("%s %s\n", RESPONSES[i][HeroIR.getRandomNumber(0, RESPONSES[i].length - 1)], "You win.");
                return PLAYER_WINS;

            // If the computer wins
            } else if (INTERACTIONS[i][0] == computerMove && INTERACTIONS[i][1] == playerMove) {
                System.out.printf("%s %s\n", RESPONSES[i][HeroIR.getRandomNumber(0, RESPONSES[i].length - 1)], "I win.");
                return COMPUTER_WINS;
            }
        }

        // If no winner was detected in all the situations, the result is a tie.
        System.out.println(TIE_RESPONSES[HeroIR.getRandomNumber(0, TIE_RESPONSES.length - 1)]);
        return TIE;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a valid weapon choice from the user.
     *
     * @param playerName - The username the player entered at the beginning of the program execution.
     * @param availableMoves - A boolean array representing the available and used moves of either player.
     * @return - An integer representing the move that the player is making.
     */
    private static int getValidMove(String playerName, boolean[] availableMoves) {
        printAvailableMoves(availableMoves);

        // Get the move from the user, and decrement it to correspond with array indexes
        int move = HeroIR.getIntegerInRange(String.format("Choose your weapon, %s.", playerName), 1, 5);
        move--;
        // Continue this process until the user selects a move that they have not already used
        while (!availableMoves[move]) {
            System.out.println("That weapon is not available.");

            move = HeroIR.getIntegerInRange(String.format("Choose your weapon, %s.", playerName), 1, 5);
            move--;
        }

        return move;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Prints out the list of moves possible, marking ones that have been used as "Not Available".
     *
     * @param availableMoves - A boolean array representing the available and used moves of either player.
     */
    private static void printAvailableMoves(boolean[] availableMoves) {
        System.out.print("(");
        // Loop through the available moves
        for (int i = 0; i < availableMoves.length; i++) {
            // Check move availability
            if (!availableMoves[i]) {
                System.out.printf("%d=%s", i + 1, "Not Available");
            } else {
                System.out.printf("%d=%s", i + 1, ITEMS[i]);
            }

            // If its not the last move option, print the delimiter
            if (i + 1 < availableMoves.length) {
                System.out.print(", ");
            }
        }
        System.out.println(")");
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Gets a random valid move from the computer.
     *
     * @param availableMoves - A boolean array representing the available moves for the computer.
     * @return - An integer representing the sleected move for the computer.
     */
    private static int getComputerMove(boolean[] availableMoves) {
        // Pick a random number move
        int move = HeroIR.getRandomNumber(0, 4);

        // Until the move is a valid move, keep picking random numbers
        while (!availableMoves[move]) {
            move = HeroIR.getRandomNumber(0, 4);
        }

        return move;
    }

    //------------------------------------------------------------------------------------------------------------------
    /** Checks to make sure that at least one move is available to the player or computer.
     *
     * @param availableMoves - A boolean array representing the available and taken moves for any player.
     */
    private static void checkMoveAvailability(boolean[] availableMoves) {
        int i;
        // Loop through all moves, if any are still available, just return
        for (i = 0; i < availableMoves.length; i++) {
            if (availableMoves[i]) {
                return;
            }
        }

        // At this point, no moves are available, so set all moves to being available, resetting the moves.
        for (i = 0; i < availableMoves.length; i++) {
            availableMoves[i] = true;
        }
    }
}
