package entity;

import boundary.Graphic;
import boundary.Graphic.Actions;

/**
 * Class to make a Street-field.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class Street extends Ownable {
	private int constructPrice, numberOfHouses;
	private int[] associatedFields, rents;

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param name
	 *            The name of this field.
	 * @param rent
	 *            The rent a player should pay if landing on this field.
	 * @param price
	 *            The price of this field.
	 */
	public Street(String name, int price, int constructPrice, int[] associatedFields, int[] rents, GameBoard gameBoard) {
		super(name, price, gameBoard);
		this.constructPrice = constructPrice;
		this.associatedFields = associatedFields;
		this.rents = rents;
		numberOfHouses = 0;
	}
	
	public void sellHouse(int thisFieldNumber) {
		owner.addToAccount(constructPrice/2);
		numberOfHouses--;
		
		Graphic.updateHouses(thisFieldNumber, numberOfHouses);
	}
	
	public boolean hasSellableHouses() {
		return numberOfHouses > 0 && !associatedFieldsHasTooManyHouses();
	}
	
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
	
	protected boolean isBuildable() {
		if(numberOfHouses >= 5) {
			return false;
		}
		
		return ownsAllAssociatedFields() && associatedFieldsHasEnoughHouses();
	}

	protected void performAction(Actions action, Player player) {
		performStdActions(action, player);
		
		if (action == Actions.BUY_FIELD) {
			buyField(player);
		}
		else if(action == Actions.BUY_HOUSE) {
			buyHouse();
		}
	}
	
	
	private void buyHouse() {
		owner.addToAccount(-constructPrice);
		numberOfHouses++;
		
		Graphic.updateHouses(owner.getLocation(), numberOfHouses);
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