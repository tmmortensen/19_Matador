package entity;

import tryYourLuck.PileOfCards;
import boundary.Graphic;

/**
 * Class to create a game board. This class takes in a lot of fields and makes
 * it a board.
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public class GameBoard {
	private DieCup dieCup;
	private Field[] fields;
	private PileOfCards pileOfCards;

	/**
	 * Constructor that makes an array for fields and a DieCup
	 */
	public GameBoard() {
		dieCup = new DieCup();
		fields = new Field[41];
		pileOfCards = new PileOfCards();
		
		createFields();
	}

	/**
	 * Updates the field-info on the GUI, to make it correspond to the info saved in field entities
	 */
	public void updateGuiFields() {
		int i, price;
		boolean subAsTitle;
		
		for(i = 1; i <= 40; i++) {
			price = 0;
			subAsTitle = false;
			
			if(fields[i] instanceof Ownable) {
				price = ((Ownable)fields[i]).price;
			}
			else if(fields[i] instanceof TryYourLuck || fields[i] instanceof Refuge) {
				subAsTitle = true;
			}
			
			Graphic.updateField(i, fields[i].name, price, subAsTitle);
		}
	}

	/**
	 * Method that calls the landOnField method on the fieldNumber that the
	 * player is on. Will get the field number from the players location.
	 * 
	 * @param player The player that landed on a field.
	 */
	public void landOnField(Player player) {
		fields[player.getLocation()].landOnField(player);
	}

	/**
	 * Get the pile of TryYourLuck-cards, to be able to draw etc. from fields.
	 * 
	 * @return The pile of cards
	 */
	public PileOfCards getPile() {
		return pileOfCards;
	}
	
	/**
	 * Get the die cup, to be able to roll etc. from fields.
	 * 
	 * @return The die cup
	 */
	public DieCup getDieCup() {
		return dieCup;
	}
	
	/**
	 * Get a field of a given number. Useful for field-operations that does not have a dedicated method
	 * 
	 * @param fieldNumber The number of the field to get
	 * @return The field of the given number
	 */
	public Field getField(int fieldNumber) {
		return fields[fieldNumber];
	}
	
	/**
	 * Gets the owner of a field.
	 * 
	 * @param fieldNumber The number of the field to get owner for.
	 * @return The owner of the field.
	 */
	public Player getOwner(int fieldNumber) {
		if (fields[fieldNumber] instanceof Ownable) {
			return ((Ownable)fields[fieldNumber]).owner;
		}

		return null;
	}
	
	/**
	 * Method that sets the owner to null in all the fields owned by a given player.
	 * Also clears the player from the GUI.
	 * 
	 * @param player The player to remove.
	 */
	public void clearFieldOwners(Player player) {
		int i;
		for (i = 0; i <= 40; i++) {
			if (getOwner(i) == player) {
				((Ownable) fields[i]).owner = null;
				((Ownable) fields[i]).isPledged = false;
				Graphic.removeOwner(i);
				Graphic.setPledgeDescription(fields[i].getName(), i, false);
				
				if(fields[i] instanceof Street) {
					((Street)fields[i]).setNumberOfHouses(0);
					Graphic.updateHouses(i, 0);
				}
			}
		}
	}
	
	/**
	 * Method to sell a field. Displays a list of sellable fields on the GUI, and calls sellField on the
	 * field selected by the player.
	 * 
	 * @param player The player who should be given the option to sell a field
	 */
	public void sellField(Player player) {
		int selectedField = getFieldSelected(player, fieldsSellableByPlayer(player));
		
		if(selectedField != 0) {
			((Ownable)fields[selectedField]).sellField();
			Graphic.removeOwner(selectedField);
		}
	}
	
	/**
	 * Method to pledge a field. Displays a list of pledgeable fields on the GUI, and calls pledgeField
	 * on the field selected by the player.
	 * 
	 * @param player The player who should be given the option to pledge a field
	 */
	public void pledgeField(Player player) {
		int selectedField = getFieldSelected(player, fieldsPledgeableByPlayer(player));
		
		if(selectedField != 0) {
			((Ownable)fields[selectedField]).pledgeField();
			Graphic.setPledgeDescription(fields[selectedField].getName(), selectedField, true);
		}
	}
	
	/**
	 * Method to un-pledge a field. Displays a list of pledged fields on the GUI, and calls unpledgeField
	 * on the field selected by the player.
	 * 
	 * @param player The player who should be given the option to unpledge a field
	 */
	public void unpledgeField(Player player) {
		int selectedField = getFieldSelected(player, fieldsPledgedByPlayer(player));
		
		if(selectedField != 0) {
			((Ownable)fields[selectedField]).unpledgeField();
			Graphic.setPledgeDescription(fields[selectedField].getName(), selectedField, false);
		}
	}
	
	/**
	 * Method to sell a house. Displays a list of fields with sellable houses on the GUI, and calls
	 * sellHouse on the field selected by the player.
	 * 
	 * @param player The player who should be given the option to sell a house
	 */
	public void sellHouse(Player player) {
		int selectedField = getFieldSelected(player, fieldsWithHousesByPlayer(player));
		
		if(selectedField != 0) {
			((Street)fields[selectedField]).sellHouse(selectedField);
		}
	}
	
	/**
	 * Method to buy a house. Displays a list of buildable fields on the GUI, and calls
	 * buyHouse on the field selected by the player.
	 * 
	 * @param player The player who should be given the option to buy a house
	 */
	public void buyHouse(Player player) {
		int selectedField = getFieldSelected(player, fieldsBuildableByPlayer(player));
		
		if(selectedField != 0) {
			((Street)fields[selectedField]).buyHouse(selectedField);
		}
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
	
	
	private void createFields() {
		// Syntax for Streets: Name, Price, ContructPrice, AssosiatedFields(1-2), Rents (0 = base, 1-4 = houses, 5 = hotel), this
		fields[1] = new Refuge("Start", 0, this);
		fields[2] = new Street("Rødovrevej", 1200, 1000, new int[] { 2, 4 }, new int[] { 40, 200, 600, 1800, 3200, 5000 }, this);
		fields[3] = new TryYourLuck("Prøv Lykken", this);
		fields[4] = new Street("Hvidovrevej", 1200, 1000, new int[] { 2, 4 }, new int[] { 80, 400, 1200, 3600, 6400, 9000 }, this);
		fields[5] = new Tax("Betal skat: 10% eller 4000", 4000, 10, this);
		fields[6] = new Shipping("H. H.", 4000, this);
		fields[7] = new Street("Roskildevej", 2000, 1000, new int[] { 7, 9, 10 }, new int[] { 120, 600, 1800, 5400, 8000, 11000 }, this);
		fields[8] = new TryYourLuck("Prøv Lykken", this);
		fields[9] = new Street("Valby Langgade", 2000, 1000, new int[] { 7, 9, 10 }, new int[] { 120, 600, 1800, 5400, 8000, 11000 }, this);
		fields[10] = new Street("Allégade", 2400, 1000, new int[] { 7, 9, 10 }, new int[] { 160, 800, 2000, 6000, 9000, 12000 }, this);
		fields[11] = new Refuge("Fængsel", 0, this);
		fields[12] = new Street("Frederiksberg Allé", 2800, 2000, new int[] { 12, 14, 15 }, new int[] { 200, 1000, 3000, 9000, 12500, 15000 }, this);
		fields[13] = new Brewery("Tuborg", 100, 3000, this);
		fields[14] = new Street("Bülowsvej", 2800, 2000, new int[] { 12, 14, 15 }, new int[] { 200, 1000, 3000, 9000, 12500, 15000 }, this);
		fields[15] = new Street("Gl. Kongevej", 3200, 2000, new int[] { 12, 14, 15 }, new int[] { 240, 1200, 3600, 10000, 14000, 18000 }, this);
		fields[16] = new Shipping("Mols-Linien", 4000, this);
		fields[17] = new Street("Bernstoffsvej", 3600, 2000, new int[] { 17, 19, 20 }, new int[] { 280, 1400, 4000, 11000, 15000, 19000 }, this);
		fields[18] = new TryYourLuck("Prøv Lykken", this);
		fields[19] = new Street("Hellerupvej", 3600, 2000, new int[] { 17, 19, 20 }, new int[] { 280, 1400, 4000, 11000, 15000, 19000 }, this);
		fields[20] = new Street("Strandvejen", 4000, 2000, new int[] { 17, 19, 20 }, new int[] { 320, 1600, 4400, 12000, 16000, 20000 }, this);
		fields[21] = new Refuge("Parkering", 0, this);
		fields[22] = new Street("Trianglen", 4400, 3000, new int[] { 22, 24, 25 }, new int[] { 360, 1800, 5000, 14000, 17500, 21000 }, this);
		fields[23] = new TryYourLuck("Prøv Lykken", this);
		fields[24] = new Street("Østerbrogade", 4400, 3000, new int[] { 22, 24, 25 }, new int[] { 360, 1800, 5000, 14000, 17500, 21000 }, this);
		fields[25] = new Street("Grønningen", 4800, 3000, new int[] { 22, 24, 25 }, new int[] { 400, 2000, 6000, 15000, 18500, 22000 }, this);
		fields[26] = new Shipping("GR", 4000, this);
		fields[27] = new Street("Bredgade", 5200, 3000, new int[] { 27, 28, 30 }, new int[] { 440, 2200, 6600, 16000, 19500, 23000 }, this);
		fields[28] = new Street("Kgs. Nytorv", 5200, 3000, new int[] { 27, 28, 30 }, new int[] { 440, 2200, 6600, 16000, 19500, 23000 }, this);
		fields[29] = new Brewery("Carlsberg", 100, 3000, this);
		fields[30] = new Street("Østergade", 5600, 3000, new int[] { 27, 28, 30 }, new int[] { 480, 2400, 7200, 17000, 20500, 24000 }, this);
		fields[31] = new GoToJail("Gå i fængsel", this);
		fields[32] = new Street("Amagertorv", 6000, 4000, new int[] { 32, 33, 35 }, new int[] { 520, 2600, 7800, 18000, 22000, 25500 }, this);
		fields[33] = new Street("Vimmelskaftet", 6000, 4000, new int[] { 32, 33, 35 }, new int[] { 520, 2600, 7800, 18000, 22000, 25500 }, this);
		fields[34] = new TryYourLuck("Prøv Lykken", this);
		fields[35] = new Street("Nygade", 6400, 4000, new int[] { 32, 33, 35 }, new int[] { 560, 3000, 9000, 20000, 24000, 28000 }, this);
		fields[36] = new Shipping("Rødby", 4000, this);
		fields[37] = new TryYourLuck("Prøv Lykken", this);
		fields[38] = new Street("Frederiksberggade", 7000, 4000, new int[] { 38, 40 }, new int[] { 700, 3500, 10000, 22000, 26000, 30000 }, this);
		fields[39] = new Tax("Betal skat: 2000", 2000, -1, this);
		fields[40] = new Street("Rådhus Pladsen", 8000, 4000, new int[] { 38, 40 }, new int[] { 1000, 4000, 12000, 28000, 34000, 40000 }, this);
	}
	
	private String[] fieldsSellableByPlayer(Player player) {
		int i, size = 0;
		
		//Find names of fields the player owns
		String[] sellableFields = new String[41];
		for(i=1; i<41; i++) {
			if(getOwner(i) == player && !associatedFieldsHasHouses(i)) {
				sellableFields[size] = fields[i].getName();
				size++;
			}
		}
		
		return trimArray(sellableFields, size);
	}
	
	private String[] fieldsPledgeableByPlayer(Player player) {
		int i, size = 0;
		
		String[] pledgeableFields = new String[41];
		for(i=1; i<41; i++) {
			if(getOwner(i) == player && !((Ownable)fields[i]).isPledged && !associatedFieldsHasHouses(i)) {
				pledgeableFields[size] = fields[i].getName();
				size++;
			}
		}
		
		return trimArray(pledgeableFields, size);
	}
	
	private String[] fieldsPledgedByPlayer(Player player) {
		int i, size = 0;
		
		String[] notPledgedFields = new String[41];
		for(i=1; i<41; i++) {
			if(getOwner(i) == player && ((Ownable)fields[i]).isPledged) {
				notPledgedFields[size] = fields[i].getName();
				size++;
			}
		}
		
		return trimArray(notPledgedFields, size);
	}
	
	private String[] fieldsWithHousesByPlayer(Player player) {
		int i, size = 0;
		
		//Find names of fields the player owns
		String[] houseFields = new String[41];
		for(i=1; i<41; i++) {
			if(getOwner(i) == player && fieldHasHouses(i)) {
				houseFields[size] = fields[i].getName();
				size++;
			}
		}
		
		return trimArray(houseFields, size);
	}
	
	private String[] fieldsBuildableByPlayer(Player player) {
		int i, size = 0;
		
		//Find names of fields the player owns
		String[] houseFields = new String[41];
		for(i=1; i<41; i++) {
			if(getOwner(i) == player && fields[i] instanceof Street) {
				Street fieldToTest = (Street)fields[i];
				
				if(fieldToTest.isBuildable()) {
					houseFields[size] = fieldToTest.getName();
					size++;
				}
			}
		}
		
		return trimArray(houseFields, size);
	}
	
	private int getFieldSelected(Player player, String[] fieldsToSelectFrom) {
		int i;
		
		//Let user select a field he owns
		String selectedField = Graphic.selectOwnedField(fieldsToSelectFrom);
		
		//Find out the number of the field selected
		for(i = 1; i<fields.length; i++) {
			if(fields[i].getName().equals(selectedField)) {
				return i;
			}
		}
		
		return 0;
	}

	private boolean associatedFieldsHasHouses(int fieldNumber) {
		if(fields[fieldNumber] instanceof Street) {
			Street streetToTest = (Street)fields[fieldNumber];
			if(streetToTest.associatedFieldsHasAnyHouses()) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean fieldHasHouses(int fieldNumber) {
		if(fields[fieldNumber] instanceof Street) {
			Street streetToTest = (Street)fields[fieldNumber];
			if(streetToTest.hasSellableHouses()) {
				return true;
			}
		}
		
		return false;
	}
	
	private String[] trimArray(String[] input, int size) {
		int i;
		
		String[] trimmed = new String[size];
		for(i=0; i<size; i++) {
			trimmed[i] = input[i];
		}
		
		return trimmed;
	}
}