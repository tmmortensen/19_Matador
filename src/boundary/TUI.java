package boundary;

import java.util.Scanner;

/**
 * Class to handle input/output to/from the console.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class TUI {
	/**
	 * Prints game rules.
	 */
	public static void printRules() {
		System.out.println("Velkommen til matador");
		System.out.println("");
		System.out.println("Regler:");
		System.out
				.println("| You roll two dice. The sum determines which field you hit. |");
		System.out
				.println("| Each field have it's own value.                                         |");
		System.out
				.println("| You get points on some, and loses points on others.         |");
		System.out
				.println("| A player wins when the others go bankrupt.                     |");
		System.out
				.println("| Press \"Enter\" to roll, press \"q\" to exit.                              |");
		System.out
				.println("-----------------------------------------------------------------------");
		System.out.println("");
	}

	/**
	 * Gets an integer from 2-6. Keeps asking until valid input is given.
	 * 
	 * @param scanner
	 *            An open scanner to read from.
	 * @return An integer from 2-6.
	 */
	public static int getNumberOfPlayers(Scanner scanner) {
		int numberOfPlayers = 0;

		// Get the number of players from console. Keep trying until input is
		// valid
		while (numberOfPlayers == 0) {
			printNumberRequest();
			try {
				numberOfPlayers = new Integer(getUserInput(scanner));
				if (numberOfPlayers < 2 || numberOfPlayers > 6) {
					numberOfPlayers = 0;
				}
			} catch (Exception e) {
				numberOfPlayers = 0;
			}
		}

		return numberOfPlayers;
	}

	/**
	 * Method that takes input from console and translates it to boolean yes/no.
	 * Supports both upper and lower case. Keeps asking until valid input is
	 * given (Y/y or N/n).
	 * 
	 * @param scanner
	 *            A scanner to use for reading from console.
	 * @return True if Y/y is written, False if N/n.
	 */
	public static boolean getYesNo(Scanner scanner) {
		String input;

		while (true) {
			input = getUserInput(scanner);
			if ("Y".equals(input) || "y".equals(input)) {
				return true;
			}

			if ("N".equals(input) || "n".equals(input)) {
				return false;
			}

			System.out.print("Not valid. Must be \"Y\" og \"N\"");
		}
	}

	/**
	 * Prints a short text, asking the specified player to type his name.
	 * 
	 * @param playerNo
	 *            The player number to print as part of the message.
	 */
	public static void printNameRequest(int playerNumber) {
		System.out
				.println("Insert name for player " + (playerNumber + 1) + ".");
	}

	/**
	 * Prints a short text, asking the user to type the number of players.
	 */
	public static void printNumberRequest() {
		System.out.println("Select the number of players 2-6:");
	}

	/**
	 * Prints a question asking if the user would like to pay x% in Tax instead
	 * of a fixed amount.
	 * 
	 * @param pct
	 *            The Tax percent to pay.
	 * @param taxFromPct
	 *            The calculated amount of tax from percent.
	 * @param taxAmount
	 *            The fixed amount of tax.
	 */
	public static void printTaxPctChoice(int pct, int taxFromPct, int taxAmount) {
		System.out.println("Would you like to pay " + pct
				+ "% of your assets (" + taxFromPct + ") in Tax, instead of "
				+ taxAmount + "?");
	}

	/**
	 * Prints a short text, telling the player who's turn it is, and asking him
	 * to roll.
	 * 
	 * @param name
	 *            The name to print as part of the message.
	 */
	public static void printTurn(String name) {
		System.out.print("\n\nIt's " + name + "'s turn. Press enter to roll.");
	}

	/**
	 * Print info about a field.
	 * 
	 * @param fieldNumber
	 *            The field number used as part of the printed text
	 * @param fieldName
	 *            The field name used as part of the printed text
	 */
	public static void printFieldName(int fieldNumber, String fieldName) {
		System.out.println("You hit field number " + fieldNumber + ", "
				+ fieldName);
	}

	/**
	 * Prints a title for the status
	 */
	public static void printScoreIntro() {
		System.out.println("The score is now:");
	}

	/**
	 * Prints the current status of the game. Thats all players name and score.
	 * 
	 * @param players
	 *            An array of players to get the information from.
	 */
	public static void printStatus(String name, int score) {
		System.out.print(name + " = " + score + "\t");
	}

	/**
	 * Prints a message in the console, giving the player an option to buy af
	 * field.
	 * 
	 * @param name
	 *            The name of the field.
	 * @param price
	 *            The price of the field.
	 */
	public static void printBuyOption(String name, int price) {
		System.out.println("Would you like to buy " + name + " for " + price
				+ "? (Y/N)");
	}

	/**
	 * Prints the name and score of the winning player.
	 * 
	 * @param name
	 *            The name of the player who should be declared the winner.
	 * @param score
	 *            The score for the winning player.
	 */
	public static void printWinner(String name, int score) {
		System.out.println("Congratulations! " + name + " has won with "
				+ score + " points!\nPress Enter to exit.");
	}

	/**
	 * Prints the name and score of a player that is bankrupt.
	 * 
	 * @param name
	 *            The name of the player who should be declared bankrupt.
	 * @param score
	 *            The score for the losing player.
	 */
	public static void printLoser(String name, int score) {
		System.out.println("\nSorry! " + name + " you are bankrupt.");
	}

	/**
	 * Reads a line from the console.
	 * 
	 * @param scanner
	 *            The scanner to read from
	 * @return Whatever the user inputs.
	 */
	public static String getUserInput(Scanner scanner) {
		return scanner.nextLine();
	}
}