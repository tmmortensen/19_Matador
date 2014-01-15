package entity;

import boundary.Graphic;

/**
 * Class to make a Street-field.
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public class Street extends Ownable {
	private int constructPrice, numberOfHouses;
	private int[] associatedFields, rents;

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param name The name of this field
	 * @param price The pirce of this field
	 * @param constructPrice How much it costs to build a house on this field
	 * @param associatedFields The numbers of the fields of same color/type as this field
	 * @param rents The rents for this field with 0-5 houses
	 * @param gameBoard A reference to the gameboard that this field is created in
	 */
	public Street(String name, int price, int constructPrice, int[] associatedFields, int[] rents, GameBoard gameBoard) {
		super(name, price, gameBoard);
		this.constructPrice = constructPrice;
		this.associatedFields = associatedFields;
		this.rents = rents;
		numberOfHouses = 0;
	}
	
	/**
	 * Sells a house on this field
	 * 
	 * @param thisFieldNumber This fields number. Needed to update the GUI
	 */
	public void sellHouse(int thisFieldNumber) {
		owner.addToAccount(constructPrice/2);
		numberOfHouses--;
		
		Graphic.updateHouses(thisFieldNumber, numberOfHouses);
	}
	
	/**
	 * Buys a house on this field. Takes the construct price from the owners account
	 * and adds one to the current number of houses
	 * 
	 * @param thisFieldNumber This fields number. Needed to update the GUI
	 */
	public void buyHouse(int thisFieldNumber) {
		owner.addToAccount(-constructPrice);
		numberOfHouses++;
		
		Graphic.updateHouses(thisFieldNumber, numberOfHouses);
	}
	
	/**
	 * Checks if the field has sellable houses.
	 * Takes into account if the number of houses is still "equal"
	 * 
	 * @return True if the field has houses that can be sold, otherwise false
	 */
	public boolean hasSellableHouses() {
		return numberOfHouses > 0 && !associatedFieldsHasTooManyHouses();
	}
	
	/**
	 * Checks if the associated fields has any houses
	 * 
	 * @return True if there is a house on any of the fields, otherwise false
	 */
	public boolean associatedFieldsHasAnyHouses() {
		int i;
		
		for(i = 0; i < associatedFields.length; i++) {
			Street streetToTest = (Street)gameBoard.getField(associatedFields[i]);
			if(streetToTest.numberOfHouses > 0) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Calculates the total value of the houses on this field
	 * 
	 * @return The total value of the houses on this field
	 */
	public int valueOfHouses() {
		return numberOfHouses * constructPrice;
	}

	/**
	 * Checks if the field is buildable.
	 * That is, the owner owns the associated fields, is not trying to build "un-equal"
	 * and the field does not already have 5 houses.
	 * 
	 * @return True if the field is buildable, otherwise false
	 */
	public boolean isBuildable() {
		if(numberOfHouses >= 5) {
			return false;
		}
		
		return ownsAllAssociatedFields() && associatedFieldsHasEnoughHouses();
	}

	/**
	 * Set the number of houses on this field to a specific number. Useful for
	 * debugging and testing, and also used when clearing a bankrupt player from
	 * the board.
	 * 
	 * @param numberOfHouses The number of houses to set
	 */
	public void setNumberOfHouses(int numberOfHouses) {
		this.numberOfHouses = numberOfHouses;
	}
	
	
	/**
	 * Method to calculate rent for this field.
	 * 
	 * @return The rent for this field.
	 */
	protected int getRent() {
		if(numberOfHouses > 0) {
			return rents[numberOfHouses];
		}
		else if(ownsAllAssociatedFields()) {
			//Base rent x2
			return rents[0] * 2;
		}
		else {
			//Just base rent
			return rents[0];
		}
	}
	
		
	private boolean ownsAllAssociatedFields() {
		int i;
		
		for(i = 0; i < associatedFields.length; i++) {
			if (owner != gameBoard.getOwner(associatedFields[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean associatedFieldsHasEnoughHouses() {
		int i;
		
		for(i = 0; i < associatedFields.length; i++) {
			Street streetToTest = (Street)gameBoard.getField(associatedFields[i]);
			if(streetToTest.numberOfHouses < this.numberOfHouses) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean associatedFieldsHasTooManyHouses() {
		int i;
		
		for(i = 0; i < associatedFields.length; i++) {
			Street streetToTest = (Street)gameBoard.getField(associatedFields[i]);
			if(streetToTest.numberOfHouses > this.numberOfHouses) {
				return true;
			}
		}
		
		return false;
	}
}