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
		fields[1] = new Refuge("Start", 200);
		fields[2] = new Street("Rødovrevej", 60, 1200);
		fields[3] = new TryYourLuck("Prøv Lykken", this);
		fields[4] = new Street("Hvidovrevej", 60, 1200);
		fields[5] = new Tax("Pay tax", 4000, 10, this);
		fields[6] = new Shipping("H. H.", 4000, this);
		fields[7] = new Street("Roskildevej", 100, 2000);
		fields[8] = new TryYourLuck("Prøv Lykken", this);
		fields[9] = new Street("Valby Langgade", 100, 2000);
		fields[10] = new Street("Allégade", 120, 2400);
		fields[11] = new Refuge("Fængsel", 0);
		fields[12] = new Street("Frederiksberg Allé", 140, 2800);
		fields[13] = new Brewery("Tuborg", 150, 3000, this);
		fields[14] = new Street("Bülowsvej", 140, 2800);
		fields[15] = new Street("Gl. Kongevej", 140, 3200);
		fields[16] = new Shipping("Mols-Linien", 4000, this);
		fields[17] = new Street("Bernstoffsvej", 160, 3600);
		fields[18] = new TryYourLuck("Prøv Lykken", this);
		fields[19] = new Street("Hellerupvej", 180, 3600);
		fields[20] = new Street("Strandvejen", 180, 4000);
		fields[21] = new Refuge("Parking", 0);
		fields[22] = new Street("Trianglen", 220, 4400);
		fields[23] = new TryYourLuck("Prøv Lykken", this);
		fields[24] = new Street("Østerbrogade", 220, 4400);
		fields[25] = new Street("Grønningen", 240, 4800);
		fields[26] = new Shipping("GR", 4000, this);
		fields[27] = new Street("Bredgade", 260, 5200);
		fields[28] = new Street("Kgs. Nytorv", 260, 5200);
		fields[29] = new Brewery("Carlsberg", 150, 3000, this);
		fields[30] = new Street("Østergade", 280, 5600);
		fields[31] = new GoToJail("Gå i fængsel");
		fields[32] = new Street("Amagertorv", 300, 6000);
		fields[33] = new Street("Vimmelskaftet", 300, 6000);
		fields[34] = new TryYourLuck("Prøv Lykken", this);
		fields[35] = new Street("Nygade", 320, 6400);
		fields[36] = new Shipping("Rødby", 4000, this);
		fields[37] = new TryYourLuck("Prøv Lykken", this);
		fields[38] = new Street("Frederiksberg Gade", 350, 7000);
		fields[39] = new Tax("Betal skat", 2000);
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

	public void drawCard(Player player) {
		pileOfCards.nextCard();
		Graphic.showCardMessage(pileOfCards.ShowCardText(), player.getName());
		pileOfCards.effect(player);
	}
}