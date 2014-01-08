package entity;

import TryYourLuck.Pile;
import boundary.Graphic;

/**
 * Class to create a game board. This class takes in a lot of fields and makes
 * it a board.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class GameBoard {
	private DieCup dieCup;
	private Field[] fields;
	private Pile pileOfCards;

	/**
	 * Constructor that makes an array for fields and a DieCup
	 */
	public GameBoard(int numberOfFields) {
		dieCup = new DieCup();
		fields = new Field[numberOfFields];
		pileOfCards = new Pile();
	}

	/**
	 * Creates all the fields according to the game rules.
	 */
	public void createFields() {
		// Syntax for Streets:
		// Name, Price, ContructPrice, AssosiatedFields(1-2), Rents (0 = base, 1-4 = houses, 5 = hotel, this)
		fields[1] = new Refuge("Start", 0, this);
		fields[2] = new Street("Rødovrevej", 1200, 1000, new int[] { 2, 4 }, new int[] { 40, 200, 600, 1800, 3200, 5000 }, this);
		fields[3] = new TryYourLuck("Prøv Lykken", this);
		fields[4] = new Street("Hvidovrevej", 1200, 1000, new int[] { 2, 4 }, new int[] { 80, 400, 1200, 3600, 6400, 9000 }, this);
		fields[5] = new Tax("Pay tax", 4000, 10, this);
		fields[6] = new Shipping("H. H.", 4000, this);
		fields[7] = new Street("Roskildevej", 2000, 1000, new int[] { 7, 9, 10 }, new int[] { 120, 600, 1800, 5400, 8000, 11000 }, this);
		fields[8] = new TryYourLuck("Prøv Lykken", this);
		fields[9] = new Street("Valby Langgade", 2000, 1000, new int[] { 7, 9, 10 }, new int[] { 120, 600, 1800, 5400, 8000, 11000 }, this);
		fields[10] = new Street("Allégade", 2400, 1000, new int[] { 7, 9, 10 }, new int[] { 160, 800, 2000, 6000, 9000, 12000 }, this);
		fields[11] = new Refuge("Fængsel", 0, this);
		fields[12] = new Street("Frederiksberg Allé", 2800, 2000, new int[] { 12, 14, 15 }, new int[] { 200, 1000, 3000, 9000, 12500, 15000 }, this);
		fields[13] = new Brewery("Tuborg", 150, 3000, this);
		fields[14] = new Street("Bülowsvej", 2800, 2000, new int[] { 12, 14, 15 }, new int[] { 200, 1000, 3000, 9000, 12500, 15000 }, this);
		fields[15] = new Street("Gl. Kongevej", 3200, 2000, new int[] { 12, 14, 15 }, new int[] { 240, 1200, 3600, 10000, 14000, 18000 }, this);
		fields[16] = new Shipping("Mols-Linien", 4000, this);
		fields[17] = new Street("Bernstoffsvej", 3600, 2000, new int[] { 17, 19, 20 }, new int[] { 280, 1400, 4000, 11000, 15000, 19000 }, this);
		fields[18] = new TryYourLuck("Prøv Lykken", this);
		fields[19] = new Street("Hellerupvej", 3600, 2000, new int[] { 17, 19, 20 }, new int[] { 280, 1400, 4000, 11000, 15000, 19000 }, this);
		fields[20] = new Street("Strandvejen", 4000, 2000, new int[] { 17, 19, 20 }, new int[] { 320, 1600, 4400, 12000, 16000, 20000 }, this);
		fields[21] = new Refuge("Parking", 0, this);
		fields[22] = new Street("Trianglen", 4400, 3000, new int[] { 22, 24, 25 }, new int[] { 360, 1800, 5000, 14000, 17500, 21000 }, this);
		fields[23] = new TryYourLuck("Prøv Lykken", this);
		fields[24] = new Street("Østerbrogade", 4400, 3000, new int[] { 22, 24, 25 }, new int[] { 360, 1800, 5000, 14000, 17500, 21000 }, this);
		fields[25] = new Street("Grønningen", 4800, 3000, new int[] { 22, 24, 25 }, new int[] { 400, 2000, 6000, 15000, 18500, 22000 }, this);
		fields[26] = new Shipping("GR", 4000, this);
		fields[27] = new Street("Bredgade", 5200, 3000, new int[] { 27, 29, 30 }, new int[] { 440, 2200, 6600, 16000, 19500, 23000 }, this);
		fields[28] = new Street("Kgs. Nytorv", 5200, 3000, new int[] { 27, 29, 30 }, new int[] { 440, 2200, 6600, 16000, 19500, 23000 }, this);
		fields[29] = new Brewery("Carlsberg", 150, 3000, this);
		fields[30] = new Street("Østergade", 5600, 3000, new int[] { 27, 29, 30 }, new int[] { 480, 2400, 7200, 17000, 20500, 24000 }, this);
		fields[31] = new GoToJail("Gå i fængsel", this);
		fields[32] = new Street("Amagertorv", 6000, 4000, new int[] { 32, 33, 35 }, new int[] { 520, 2600, 7800, 18000, 22000, 25500 }, this);
		fields[33] = new Street("Vimmelskaftet", 6000, 4000, new int[] { 32, 33, 35 }, new int[] { 520, 2600, 7800, 18000, 22000, 25500 }, this);
		fields[34] = new TryYourLuck("Prøv Lykken", this);
		fields[35] = new Street("Nygade", 6400, 4000, new int[] { 32, 33, 35 }, new int[] { 560, 3000, 9000, 20000, 24000, 28000 }, this);
		fields[36] = new Shipping("Rødby", 4000, this);
		fields[37] = new TryYourLuck("Prøv Lykken", this);
		fields[38] = new Street("Frederiksberg Gade", 7000, 4000, new int[] { 38, 40 }, new int[] { 700, 3500, 10000, 22000, 26000, 30000 }, this);
		fields[39] = new Tax("Betal skat", 2000, -1, this);
		fields[40] = new Street("Rådhus Pladsen", 8000, 4000, new int[] { 38, 40 }, new int[] { 1000, 4000, 12000, 28000, 34000, 40000 }, this);
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
		for (i = 0; i <= 40; i++) {
			if (getOwner(i) == player) {
				((Ownable) fields[i]).owner = null;
				Graphic.removeOwner(i);
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

	public boolean getDieCupIdentical() {
		return dieCup.getIdentical();
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

	public void nextCard() {
		pileOfCards.nextCard();
	}
	
	public String getCardText() {
		return pileOfCards.ShowCardText();
	}
	
	public void cardEffect(Player player) {
		pileOfCards.effect(player);
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
}