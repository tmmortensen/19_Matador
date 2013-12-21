package entity;

import java.util.Scanner;

/**
 * Class to create a game board. This class takes in a lot of fields and makes
 * it a board.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public class GameBoard {
	private DieCup dieCup;
	Field[] fields;

	/**
	 * Constructor that makes an array for fields and a DieCup
	 */
	public GameBoard(int numberOfFields) {
		dieCup = new DieCup();
		fields = new Field[numberOfFields];
	}

	/**
	 * Creates all the fields according to the game rules.
	 */
	public void createFields(Scanner scanner) {
		fields[1] = new Territory("Tribe Encampment", 100, 1000);
		fields[2] = new Territory("Crater", 300, 1500);
		fields[3] = new Territory("Mountain", 500, 2000);
		fields[4] = new Territory("Cold Dessert", 700, 3000);
		fields[5] = new Territory("Black Cave", 1000, 4000);
		fields[6] = new Territory("The Werewall", 1300, 4300);
		fields[7] = new Territory("Mountain Village", 1600, 4750);
		fields[8] = new Territory("South Citadel", 2000, 5000);
		fields[9] = new Territory("Palace ates", 2600, 5500);
		fields[10] = new Territory("Tower", 3200, 6000);
		fields[11] = new Territory("Castle", 4000, 8000);

		fields[12] = new Refuge("Walled city", 5000);
		fields[13] = new Refuge("Monastry", 500);

		fields[14] = new LaborCamp("Huts in the mountain", 100, 2500, this);
		fields[15] = new LaborCamp("The pit", 100, 2500, this);

		fields[16] = new Tax("Goldmine", 2000);
		fields[17] = new Tax("Caravan", 4000, 10, this, scanner);

		fields[18] = new Fleet("Second Sail", 4000, this);
		fields[19] = new Fleet("Sea Grover", 4000, this);
		fields[20] = new Fleet("The Buccaneers", 4000, this);
		fields[21] = new Fleet("Privateer armade", 4000, this);
	}

	/**
	 * Method that calls the landOnField method on the fieldNumber that the player is on.
	 *
	 * @param player The player that landed on a field.
	 */
	public void landOnField(Player player) {
		fields[player.getLocation()].landOnField(player);
	}

	/**
	 * Method that sets the owner to null in all the fields owned by a given player.
	 *
	 * @param player The player to remove.
	 */
	public void clearFieldOwners(Player player) {
		int i;
		for(i = 0; i<=21; i++) {
			if(getOwner(i) == player) {
				((Ownable)fields[i]).owner = null;
			}
		}
	}

	/**
	 * Gets the owner of a field.
	 *
	 * @param fieldNumber The number of the field to get owner for.
	 * @return The owner of the field.
	 */
	public Player getOwner(int fieldNumber) {
		if(getOwnableField(fieldNumber) != null) {
			return getOwnableField(fieldNumber).owner;
		}

		return null;
	}

	/**
	 * Method to set a given player as owner of the field he is on.
	 *
	 * @param player
	 */
	public void setOwner(Player player) {
		getOwnableField(player.getLocation()).setOwner(player);
	}

	/**
	 * Gets the price of a field.
	 *
	 * @param fieldNumber The number of the field to get price for.
	 * @return The price of the field.
	 */
	public int getPrice(int fieldNumber) {
		return getOwnableField(fieldNumber).price;
	}

	/**
	 * Gets the name of a field.
	 *
	 * @param fieldNumber The number of the field to get name for.
	 * @return The name of the field.
	 */
	public String getName(int fieldNumber) {
		return fields[fieldNumber].getName();
	}

	/**
	 * Method to shake the DieCup.
	 */
	public void shakeDieCup() {
		dieCup.shakeDieCup();
	}

	/**
	 * Gets the sum of the values of the Dice in the DieCup.
	 *
	 * @return The sum of the Dice.
	 */
	public int getDieCupSum() {
		return dieCup.getSum();
	}

	/**
	 * Gets the value of Die1.
	 *
	 * @return The value of Die1.
	 */
	public int getDieValue1() {
		return dieCup.getValueDie1();
	}

	/**
	 * Gets the value of Die2.
	 *
	 * @return The value of Die2.
	 */
	public int getDieValue2() {
		return dieCup.getValueDie2();
	}
	
	/**
	 * Method to set a field.
	 * 
	 * @param field Field object to insert.
	 * @param number Place number to instert field on.
	 */
	public void setField(Field field, int number) {
		fields[number] = field;
	}

	/**
	 * A method to generate a nice string containing the value of all the
	 * fields. Also contains value of the DieCup.
	 *
	 * @return All the field values as a string.
	 */
	public String toString() {
		String output = "";
		int i;

		for (i = 0; i < fields.length; i++) {
			if (fields[i] != null) {
				output = output + fields[i] + "\n";
			}
		}

		return output + dieCup;
	}

	private Ownable getOwnableField(int fieldNumber) {
		if (fields[fieldNumber] instanceof Ownable) {
			return (Ownable)fields[fieldNumber];
		}

		return null;
	}
}