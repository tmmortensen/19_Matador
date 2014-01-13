package boundary;

import java.awt.Color;
import java.util.Random;

import boundaryToMatador.GUI;

/**
 * Class to send commands to the GUI.
 * 
 * @author DTU 02312 Gruppe 19
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

	public static Actions showMenu(String playerName, String fieldName,
			int rent, int buyPrice, boolean canBuild, boolean taxOption,
			boolean drawCard, String cardMessage) {
		String message = playerName + ", du landede på '" + fieldName + "'. ";
		String[] options = { "Slut turen", "Sælg en grund", "Pantsæt en grund",
				"Ophæv pantsætning af en grund", "Sælg et hus" };

		if (rent != 0) {
			message = message + " Du skal betale " + rent + " i leje. ";
		}

		if (buyPrice != 0) {
			message = message + " Du kan købe grunden for " + buyPrice + ".";
			options = addOption(options, "Køb grunden");
		}

		if (canBuild) {
			options = addOption(options, "Køb et hus");
		}

		if (cardMessage != null) {
			message = message + "\n" + cardMessage;
		}

		if (drawCard) {
			replaceOption(options, "Slut turen", "Træk et kort");
		}

		if (taxOption) {
			options = replaceOption(options, "Slut turen",
					"Slut turen, og betal fast beløb");
			options = addOption(options, "Slut turen, og betal procentvis skat");
		}

		String input = GUI.getUserSelection(message + "\nVælg en mulighed: ",
				options);

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

	public static String selectOwnedField(String[] fieldsOwned) {
		if (fieldsOwned.length > 0) {
			return GUI.getUserSelection("Vælg en af dine grunde.", fieldsOwned);
		}

		GUI.showMessage("Du har ingen grunde hvor denne handling kan udføres...");

		return null;
	}

	public static void showCard(String name, String message) {
		GUI.showMessage(name + ", " + message);
	}

	public static void updateHouses(int fieldNumber, int houseCount) {
		if (houseCount < 5) {
			GUI.setHotel(fieldNumber, false);
			GUI.setHouses(fieldNumber, houseCount);
		} else {
			GUI.setHouses(fieldNumber, 0);
			GUI.setHotel(fieldNumber, true);
		}
	}

	public static int getNumberOfPlayers() {
		return GUI.getUserInteger("Indtast antallet af spillere (2-6)", 2, 6);
	}

	public static String getPlayerName() {
		return GUI.getUserString("Indtast navn");
	}

	public static void getRollOk(String name) {
		GUI.getUserButtonPressed("Det er " + name + "'s tur. Tryk for at slå...", "Slå");
	}

	public static void announceWinner(String name) {
		GUI.getUserButtonPressed("Tillykke " + name + " du har vundet! Tryk OK for at afslutte...", "OK");
	}

	public static boolean taxPctChoice(int pct, int taxFromPct, int taxAmount, String name) {
		String msg = name + ", vil du betale " + pct + "% af din formue (" + taxFromPct + ") i skat, istedet for " + taxAmount + "?";
		String input = GUI.getUserButtonPressed(msg, "Ja", "Nej");

		return "Ja".equals(input);
	}

	/**
	 * Method to set the value of the dice on the GUI.
	 * 
	 * @param die1 Value of die1.
	 * @param die2 Value of die2.
	 */
	public static void setDice(int die1, int die2) {
		// Algorithm to make sure dice doesn't disturb cars or text in the game
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		Random random = new Random();
		
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
	 * Method to update all players information on the GUI according to a given
	 * array of player objects.
	 * 
	 * @param players
	 *            The array of player objects to get the information from.
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
	 * @param playerName
	 *            The name of the player who's car should be moved.
	 * @param fieldNumber
	 *            The number of the field the car should be moved to.
	 */
	public static void moveCar(String playerName, int fieldNumber) {
		GUI.removeAllCars(playerName);
		GUI.setCar(fieldNumber, playerName);
	}

	/**
	 * Method to set owner of a field. Marks the given field number with the
	 * given players color.
	 * 
	 * @param location
	 *            The number of the field.
	 * @param name
	 *            The name of the player to set as owner.
	 */
	public static void setOwner(int location, String name) {
		GUI.setOwner(location, name);
	}

	/**
	 * Method to remove the owner-marking from a field.
	 * 
	 * @param fieldNumber
	 *            The number of the field to remove owner from.
	 */
	public static void removeOwner(int fieldNumber) {
		GUI.removeOwner(fieldNumber);
	}

	/**
	 * Method to remove a players car from the board.
	 * 
	 * @param name
	 *            Name of the player to remove car for.
	 */
	public static void removePlayer(String name) {
		GUI.removeAllCars(name);
	}

	public static void printLoser(String name) {
		GUI.showMessage("Beklager, " + name + ", du er bankerot.");
	}

	public static void goToJailMessage(String name) {
		GUI.showMessage(name + ", gå i fængsel!");
	}

	public static void updateField(int fieldNumber, String title, int price,
			boolean subAsTitle) {
		if (subAsTitle) {
			GUI.setSubText(fieldNumber, title);
		} else {
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