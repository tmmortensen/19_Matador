package entity;

import boundary.Graphic.Actions;

/**
 * Class to make a Territory-field.
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

	/**
	 * Method to calculate rent for this field.
	 * 
	 * @return The rent for this field.
	 */
	public int getRent() {
		int rent = rents[0];
		
		if(ownsAllAssociatedFields()) {
			rent = rent * 2;
		}
		
		//TODO: Beregn pris med huse..
		
		return rent;
	}
	
	protected boolean isBuildable() {
		return ownsAllAssociatedFields();
	}

	protected void performAction(Actions action, Player player) {
		performStdActions(action, player);
		
		if (action == Actions.BUY_FIELD) {
			buyField(player);
		}
		else if(action == Actions.BUY_HOUSE) {
			buyHouse(player);
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
	
	private void buyHouse(Player player) {
		//TODO: Buy houses
	}
}