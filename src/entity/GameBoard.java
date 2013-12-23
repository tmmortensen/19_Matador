package entity;

import java.util.Scanner;

import TryYourLuck.Pile;
import boundaryToMatador.GUI;

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
	private static Pile pileOfCards = new Pile();

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
		fields[1] = new Refuge("Start", 0);
		fields[2] = new Street("Rødovrevej", 60, 1200);
		fields[3] = new TryYourLuck("Try Your Luck");
		fields[4] = new Street("Hvidovrevej", 60, 1200);
		fields[5] = new Tax("Pay tax", 4000);
		fields[6] = new Shipping("H. H.", 4000, this);
		fields[7] = new Street("Roskildevej", 100, 2000);
		fields[8] = new TryYourLuck("Try Your Luck");
		fields[9] = new Street("Valby langgade", 100, 2000);
		fields[10] = new Street("Allégade", 120, 2400);
		fields[11] = new Refuge("Jail", 0);
		fields[12] = new Street("Frederiksberg Allé", 140, 2800);
		fields[13] = new Brewery("Tuborg", 150, 3000, this);
		fields[14] = new Street("Bülowsvej", 140, 2800);
		fields[15] = new Street("Gl. Kongevej", 140, 3200);
		fields[16] = new Shipping("Mols-Linien", 4000, this);
		fields[17] = new Shipping("Bernstoffsvej", 3600, this);
		fields[18] = new TryYourLuck("Try Your Luck");
		fields[19] = new Street("Hellerupvej", 180, 3600);
		fields[20] = new Street("Strandvejen", 180, 4000);
		fields[21] = new Refuge("Parking", 0);
		fields[22] = new Street("Trianglen", 220, 4400);
		fields[23] = new TryYourLuck("Try Your Luck");
		fields[24] = new Street("Østerbrogade", 220, 4400);
		fields[25] = new Street("Grønningen", 240, 4800);
		fields[26] = new Shipping("GR", 4000, this);
		fields[27] = new Street("Bredgade", 260, 5200);
		fields[28] = new Street("Kgs. Nytorv", 260, 5200);
		fields[29] = new Brewery("Carlsberg", 150, 3000, this);
		fields[30] = new Street("Østergade", 280, 5600);
		fields[31] = new Refuge("Go to Jail", 0);
		fields[32] = new Street("Amagertorv", 300, 6000);
		fields[33] = new Street("Vimmelskaftet", 300, 6000);
		fields[34] = new TryYourLuck("Try Your Luck");
		fields[35] = new Street("Nygade", 320, 6400);
		fields[36] = new Shipping("Rødby", 4000, this);
		fields[37] = new TryYourLuck("Try Your Luck");
		fields[38] = new Street("Frederiksberg Gade", 350, 7000);
		fields[39] = new Tax("Pay tax", 2000);
		fields[40] = new Street("Rådhus Pladsen", 400, 8000);
	}

	/**
	 * Method that calls the landOnField method on the fieldNumber that the
	 * player is on.
	 * 
	 * @param player
	 *            The player that landed on a field.
	 */
	public void landOnField(Player player) {
		fields[player.getLocation()].landOnField(player);
	}

	/**
	 * Method that sets the owner to null in all the fields owned by a given
	 * player.
	 * 
	 * @param player
	 *            The player to remove.
	 */
	public void clearFieldOwners(Player player) {
		int i;
		for (i = 0; i <= 21; i++) {
			if (getOwner(i) == player) {
				((Ownable) fields[i]).owner = null;
			}
		}
	}

	/**
	 * Gets the owner of a field.
	 * 
	 * @param fieldNumber
	 *            The number of the field to get owner for.
	 * @return The owner of the field.
	 */
	public Player getOwner(int fieldNumber) {
		if (getOwnableField(fieldNumber) != null) {
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
	 * @param fieldNumber
	 *            The number of the field to get price for.
	 * @return The price of the field.
	 */
	public int getPrice(int fieldNumber) {
		return getOwnableField(fieldNumber).price;
	}

	/**
	 * Gets the name of a field.
	 * 
	 * @param fieldNumber
	 *            The number of the field to get name for.
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
	 * @param field
	 *            Field object to insert.
	 * @param number
	 *            Place number to insert field on.
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
			return (Ownable) fields[fieldNumber];
		}

		return null;
	}

	public static void drawCard(Player player) {
		pileOfCards.nextCard();
		pileOfCards.effect(player);
		GUI.showMessage(GameBoard.pileOfCards.ShowCardText());
	}

	public int getCardNumber() {
		return pileOfCards.getCardNumber(true);
	}

	public String getCardType() {
		String variable = ""
				+ GameBoard.pileOfCards.getCardType(GameBoard.pileOfCards
						.getCardNumber(false));
		return variable;
	}
}