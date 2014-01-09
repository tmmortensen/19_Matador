package boundary;

import java.awt.Color;

import boundaryToMatador.GUI;

/**
 * Class to send commands to the GUI.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public class Graphic {
	public static enum Actions {
		BUY_FIELD, SELL_FIELD, MORTAGE_FIELD, BUY_HOUSE, SELL_HOUSE, END;
	}

	public static Actions showMenu(String playerName, String fieldName, int rent, int buyPrice, boolean canBuild) {
		String message = playerName + ", du landede på '" + fieldName + "'";
		String[] options = { "Sælg en grund", "Pantsæt en grund", "Sælg et hus", "Slut turen" };
		
		if(rent != 0) {
			message = message + ". Du skal betale " + rent + " i leje.";
		}
		
		if(buyPrice != 0) {
			options = addOption(options, "Køb grunden");
		}
		
		if(canBuild) {
			options = addOption(options, "Køb et hus");
		}
		
		//TODO: Overvej om man måske kan lave det så "Sælg grund"-punktet kun er der hvis man ejer en grund...?
		
		String input = GUI.getUserSelection(playerName + ", du landede på '" + fieldName + "', vælg en mulighed", options);
		
		if("Køb grunden".equals(input)) {
			return Actions.BUY_FIELD;
		}
		else if("Sælg en grund".equals(input)) {
			return Actions.SELL_FIELD;
		}
		else if("Pantsæt en grund".equals(input)) {
			return Actions.MORTAGE_FIELD;
		}
		else if("Køb et hus".equals(input)) {
			return Actions.BUY_HOUSE;
		}
		else if("Sælg et hus".equals(input)) {
			return Actions.SELL_HOUSE;
		}
		
		return Actions.END;
	}
	
	public static String selectOwnedField(String[] fieldsOwned) {
		if(fieldsOwned.length > 0) {
			return GUI.getUserSelection("Vælg en af dine grunde.", fieldsOwned);
		}
		
		GUI.showMessage("Du ejer ingen felter...");
		
		return null;
	}
	
	public static int getNumberOfPlayers() {
		return GUI.getUserInteger("Indtast antallet af spillere (2-6)", 2, 6);
	}
	
	public static String getPlayerName() {
		return GUI.getUserString("Indtast navn");
	}
	
	public static void getOk(String name) {
		GUI.getUserButtonPressed("Det er " + name + "'s tur. Tryk for at slå...", "Slå");
	}
	
	public static void announceWinner(String name) {
		GUI.getUserButtonPressed("Tillykke " + name + " du har vundet! Tryk OK for at afslutte...", "OK");
	}
	
	public static boolean taxPctChoice(int pct, int taxFromPct, int taxAmount, String name) {
		String msg = name + ", vil du betale " + pct + "% af din formue (" + taxFromPct + ") i skat, istedet for " + taxAmount + "?";
		String input = GUI.getUserButtonPressed(msg, "Ja", "Nej");
		
		if("Ja".equals(input)) {
			return true;
		}
		
		return false;
	}
	
	public static String getSelection(String name) {
		String input = GUI.getUserSelection(name + ", vælg en mulighed", "Køb feltet", "Giv turen videre");
		if("Køb feltet".equals(input)) {
			return "buy";
		}
		if("Giv turen videre".equals(input)) {
			return "donothing";
		}
		
		return null;
	}
	
	/**
	 * Method to set the value of the dice on the GUI.
	 *
	 * @param die1 Value of die1.
	 * @param die2 Value of die2.
	 */
	public static void setDice(int die1, int die2) {
		GUI.setDice(die1, die2);
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
	 * @param players The array of player objects to get the information from.
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
	 * Method to set owner of a field. Marks the given field number with the given players color.
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
	 * Method to remove a players car from the board.
	 *
	 * @param name Name of the player to remove car for.
	 */
	public static void removePlayer(String name) {
		GUI.removeAllCars(name);
	}
	
	public static void showCardMessage(String message, String name) {
		GUI.showMessage(name + ", " + message);
	}
	
	public static void printLoser(String name) {
		GUI.showMessage("Beklager, " + name + ", du er bankerot.");
	}
	
	public static void goToJailMessage(String name) {
		GUI.showMessage(name + ", gå i fængsel!");
	}
	

	private static Color getColor(int playerNumber) {
		switch(playerNumber) {
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
		String[] output = new String[options.length+1];
		
		output[0] = newOption;
		for(i = 1; i<options.length+1; i++) {
			output[i] = options[i-1];
		}
		
		return output;
	}
}