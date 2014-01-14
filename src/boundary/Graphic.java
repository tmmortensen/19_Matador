package boundary;

import java.awt.Color;
import java.util.Random;

import boundaryToMatador.GUI;

/**
 * Class to send commands to the GUI.
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public class Graphic {
	//Constants, that define where the dice can land
	private static final int[] X_LOW = { 2, 3 };
	private static final int[] X_HIGH = { 2, 3, 4, 5, 6 };
	private static final int[] Y_RANGE = { 3, 4, 5, 6, 7, 8, 9 };

	public static enum Actions {
		BUY_FIELD, SELL_FIELD, PLEDGE_FIELD, UNPLEDGE_FIELD, BUY_HOUSE, SELL_HOUSE, END_PCT, END;
	}

	/**
	 * Shows a drop-down menu on the GUI, to allow the user to select what to do.
	 * 
	 * @param playerName The name of the player to be printed as part of the message
	 * @param fieldName The name of the field to be printed as part of the message
	 * @param rent The rent of the field to be printed as part of the message
	 * @param buyPrice The price of the field to be printed as part of the message
	 * @param taxOption Decides if the option to select between pct and fixed amount of tax should be showed
	 * @param drawCard Decides if the option to end the turn should be replaced with a "draw card" option 
	 * @param cardMessage A message that can be printed with the menu, for TryYourLuck cards
	 * @return An enum telling which option was selected
	 */
	public static Actions showMenu(String playerName, String fieldName, int rent, int buyPrice, boolean taxOption, boolean drawCard, String cardMessage) {
		String message = playerName + ", du landede på '" + fieldName + "'. ";
		String[] options = { "Slut turen", "Sælg en grund", "Pantsæt en grund", "Ophæv pantsætning af en grund", "Køb et hus", "Sælg et hus" };

		// Customize options and message according to where and who
		if (rent != 0) {
			message = message + " Du skal betale " + rent + " i leje. ";
		}
		if (buyPrice != 0) {
			message = message + " Du kan købe grunden for " + buyPrice + ".";
			options = addOption(options, "Køb grunden");
		}
		if (cardMessage != null) {
			message = message + "\n" + cardMessage;
		}
		if (drawCard) {
			replaceOption(options, "Slut turen", "Træk et kort");
		}
		if (taxOption) {
			options = replaceOption(options, "Slut turen", "Slut turen, og betal fast beløb");
			options = addOption(options, "Slut turen, og betal procentvis skat");
		}

		// Get option selected from GUI
		String input = GUI.getUserSelection(message + "\nVælg en mulighed: ", options);

		// Translate String input to Enums, to be able to switch on it later.. 
		if ("Køb grunden".equals(input)) {
			return Actions.BUY_FIELD;
		}
		if ("Sælg en grund".equals(input)) {
			return Actions.SELL_FIELD;
		}
		if ("Pantsæt en grund".equals(input)) {
			return Actions.PLEDGE_FIELD;
		}
		if ("Ophæv pantsætning af en grund".equals(input)) {
			return Actions.UNPLEDGE_FIELD;
		}
		if ("Køb et hus".equals(input)) {
			return Actions.BUY_HOUSE;
		}
		if ("Sælg et hus".equals(input)) {
			return Actions.SELL_HOUSE;
		}
		if ("Slut turen, og betal procentvis skat".equals(input)) {
			return Actions.END_PCT;
		}

		return Actions.END;
	}
	
	/**
	 * Shows the fields given as String-array in a drop-down menu, and returns the one
	 * the user selects
	 * 
	 * @param fieldsOwned The array of names of fields to be selected from
	 * @return The name of the field selected
	 */

	public static String selectOwnedField(String[] fieldsOwned) {
		if (fieldsOwned.length > 0) {
			return GUI.getUserSelection("Vælg en af dine grunde.", fieldsOwned);
		}

		GUI.showMessage("Du har ingen grunde hvor denne handling kan udføres...");

		return null;
	}
	
	/**
	 * Show a message on the GUI.
	 * 
	 * @param name The player-name to be printed as part of the message
	 * @param message The message to be printed
	 */

	public static void showCard(String name, String message) {
		GUI.showMessage(name + ", " + message);
	}
	
	/**
	 * Updates the amount of houses displayed on a given field on the GUI
	 * Will display 5 houses as a hotel
	 * 
	 * @param fieldNumber The number of the field to set the houses on
	 * @param houseCount The number of houses to set
	 */

	public static void updateHouses(int fieldNumber, int houseCount) {
		if (houseCount < 5) {
			GUI.setHotel(fieldNumber, false);
			GUI.setHouses(fieldNumber, houseCount);
		} else {
			GUI.setHouses(fieldNumber, 0);
			GUI.setHotel(fieldNumber, true);
		}
	}
	
	/**
	 * Shows a text-field and asks the user to input a number of players.
	 * GUI validates the number
	 * 
	 * @return An integer from 2-6, describing how many players the user wants
	 */
	public static int getNumberOfPlayers() {
		return GUI.getUserInteger("Indtast antallet af spillere (2-6)", 2, 6);
	}
	
	/**
	 * Shows a text-field and asks the user to input a name
	 * 
	 * @return The given name as a String
	 */
	public static String getPlayerName() {
		return GUI.getUserString("Indtast navn");
	}

	/**
	 * Tells the player to press roll, and waits for "OK"
	 * 
	 * @param name The name of the player to printed as part of the message
	 */
	public static void getRollOk(String name) {
		GUI.getUserButtonPressed("Det er " + name + "'s tur. Tryk for at slå...", "Slå");
	}

	/**
	 * Shows a message telling if a player has won the game
	 * 
	 * @param name The name of the player who has won the game.
	 */
	public static void announceWinner(String name) {
		GUI.getUserButtonPressed("Tillykke " + name + " du har vundet! Tryk OK for at afslutte...", "OK");
	}

	/**
	 * Ask the player if he would like to pay to get out of jail
	 * 
	 * @return True/False for Yes/No
	 */
	public static boolean showJailOption() {
		String input;
		input = GUI.getUserSelection("Vil du betale 1000 for at komme ud af fængsel?", "Nej", "Ja");
		
		return "Ja".equals(input);
	}
	
	/**
	 * Method to set the value of the dice on the GUI.
	 * Uses fixed position in the GUI, and has its own random function,
	 * to make sure the dice does not land under/on anything on the board.
	 * 
	 * @param die1 Value of die1.
	 * @param die2 Value of die2.
	 */
	public static void setDice(int die1, int die2) {
		// Algorithm to make sure dice doesn't disturb cars or text in the game
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		Random random = new Random();
		
		while(x1 == x2 && y1 == y2) {
			y1 = Y_RANGE[random.nextInt(Y_RANGE.length)];
			y2 = Y_RANGE[random.nextInt(Y_RANGE.length)];
	
			if (y1 == 3) {
				x1 = X_HIGH[random.nextInt(X_HIGH.length)];
			}
			if (y1 >= 4 && y1 <= 6) {
				x1 = X_LOW[random.nextInt(X_LOW.length)];
			}
			if (y1 >= 7 && y1 <= 9) {
				x1 = X_HIGH[random.nextInt(X_HIGH.length)];
			}
			if (y2 == 3) {
				x2 = X_HIGH[random.nextInt(X_HIGH.length)];
			}
			if (y2 >= 4 && y2 <= 6) {
				x2 = X_LOW[random.nextInt(X_LOW.length)];
			}
			if (y2 >= 7 && y2 <= 9) {
				x2 = X_HIGH[random.nextInt(X_HIGH.length)];
			}
		}
		
		
		// Sets value and position of dice
		GUI.setDice(die1, x1, y1, die2, x2, y2);
	}

	/**
	 * Method to add a player.
	 * 
	 * @param playerName The name of the player to add.
	 * @param playerScore The score of the player to add.
	 */
	public static void addPlayer(String playerName, int playerScore, int playerNumber) {
		GUI.addPlayer(playerName, playerScore, getColor(playerNumber));
	}

	/**
	 * Updates a players score
	 * 
	 * @param name The name of the player to update
	 * @param score The new score to be displayed
	 */
	public static void updatePlayer(String name, int score) {
		GUI.setBalance(name, score);
	}

	/**
	 * Close the GUI window.
	 */
	public static void close() {
		GUI.close();
	}

	/**
	 * Method to move a car from any field to the field number given.
	 * 
	 * @param playerName The name of the player who's car should be moved.
	 * @param fieldNumber The number of the field the car should be moved to.
	 */
	public static void moveCar(String playerName, int fieldNumber) {
		GUI.removeAllCars(playerName);
		GUI.setCar(fieldNumber, playerName);
	}

	/**
	 * Method to set owner of a field. Marks the given field number with the
	 * given players color.
	 * 
	 * @param location The number of the field.
	 * @param name The name of the player to set as owner.
	 */
	public static void setOwner(int location, String name) {
		GUI.setOwner(location, name);
	}

	/**
	 * Method to remove the owner-marking from a field.
	 * 
	 * @param fieldNumber The number of the field to remove owner from.
	 */
	public static void removeOwner(int fieldNumber) {
		GUI.removeOwner(fieldNumber);
	}
	
	/**
	 * Adds a note to the description of a field, telling if it is pledged
	 * 
	 * @param fieldName The name of the field
	 * @param fieldNumber The number of the field
	 * @param isPledged Whether the field should be set as pledged or not-pledged
	 */
	public static void setPledgeDescription(String fieldName, int fieldNumber, boolean isPledged) {
		String description = fieldName;
		
		if(isPledged) {
			description = description + " (PANTSAT)";
		}
		
		GUI.setDescriptionText(fieldNumber, description);
	}

	/**
	 * Method to remove a players car from the board.
	 * 
	 * @param name Name of the player to remove car for.
	 */
	public static void removePlayer(String name) {
		GUI.removeAllCars(name);
	}

	/**
	 * Prints the name of a player who has lost the game
	 * 
	 * @param name The name of the player who has lost
	 */
	public static void printLoser(String name) {
		GUI.showMessage("Beklager, " + name + ", du er bankerot.");
	}

	/**
	 * Tells a player to go to jail
	 * 
	 * @param name The name of the player who should go to jail
	 */
	public static void goToJailMessage(String name) {
		GUI.showMessage(name + ", gå i fængsel!");
	}

	/**
	 * Sets the title and description of a field on the GUI
	 * 
	 * @param fieldNumber The number of the field to change the title/description for
	 * @param title The title to set
	 * @param price The price to set
	 * @param subAsTitle Whether the given title should be used as description (and no title should be set) 
	 */
	public static void updateField(int fieldNumber, String title, int price, boolean subAsTitle) {
		if (subAsTitle) {
			GUI.setSubText(fieldNumber, title);
		}
		else {
			GUI.setTitleText(fieldNumber, title);
			GUI.setDescriptionText(fieldNumber, title);

			if (price > 0) {
				GUI.setSubText(fieldNumber, "Pris: " + price);
			}
		}
	}

	
	private static Color getColor(int playerNumber) {
		switch (playerNumber) {
		case 0:
			return Color.RED;
		case 1:
			return Color.BLUE;
		case 2:
			return Color.YELLOW;
		case 3:
			return Color.WHITE;
		case 4:
			return Color.GREEN;
		default:
			return Color.BLACK;
		}
	}

	private static String[] addOption(String[] options, String newOption) {
		int i;
		String[] output = new String[options.length + 1];

		output[0] = newOption;
		for (i = 1; i < options.length + 1; i++) {
			output[i] = options[i - 1];
		}

		return output;
	}

	private static String[] replaceOption(String[] input, String oldString,
			String newString) {
		int i;

		for (i = 0; i < input.length; i++) {
			if (input[i].equals(oldString)) {
				input[i] = newString;
				return input;
			}
		}

		return null;
	}
}